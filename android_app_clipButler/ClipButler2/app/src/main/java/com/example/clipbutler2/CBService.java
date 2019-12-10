package com.example.clipbutler2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.solver.widgets.Helper;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GestureDetectorCompat;

import java.io.File;

public class CBService extends Service implements ClipboardManager.OnPrimaryClipChangedListener, View.OnTouchListener{
    final String TAG = CBService.class.getSimpleName();
    private ClipboardManager clipboard;
    public static boolean running = false;
    public  boolean darkModeActive = ClipButlerApp.darkMode;
    private long numClicks = 0;
    private long lastClickTime = 0;
    private long clickTiming = 300;
    private long tripleThreshold = 350;
    public boolean isShowing;
    public boolean butlerIsEnabled = true; // changeable
    private WindowManager bufferWindowManager;
    private WindowManager listenerWindowManager; // Tiny manager that reads and passes click info

    private View listenerView; // View specified for click info
    private RelativeLayout bufferLayout; // Layout of buffer UI

    // Colors
    int listBg = Color.DKGRAY;
    int listTxtC = Color.WHITE;
    int headerBg = Color.GRAY;
    int bufferBG = Color.BLACK;

    GestureDetectorCompat gestureDetector;

    private boolean getDarkModeOption() {
        darkModeActive = ClipButlerApp.darkMode; // Gets wherever the boolean
        return darkModeActive;
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public int screenHeight = getScreenHeight(); // Used to ensure buffer will not appear off screen
    public int screenWidth = getScreenWidth(); // These can be moved to a separate funciton

    @Override
    public void onCreate() {
        super.onCreate();
        if (butlerIsEnabled) {
            Log.d(TAG, "onCreate");
            //Spawn notification
            //Connect to clipboard manager
            //Load initial strings
            setUpComponents();
            readClips();
            readSettings();
        } else{
            stopSelf();
        }
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        //setUpComponents();

        if (running) {
            return START_STICKY;
        }
        else {
            stopSelf();
            return START_NOT_STICKY;
        }
        //return START_NOT_STICKY;
        //return super.onStartCommand(intent, flags, startId); !!## This is what "should" be returned
    }

    private void setUpComponents() {
        Log.d(TAG, "setUpComponents");
        createTripleClickListener();
        clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.addPrimaryClipChangedListener(this);
        ClipButlerApp.buffer = new Buffer<>(5);
        CBService.running = true;
        initNotification();
        displayNotification();
    }

    private void createTripleClickListener() {
        bufferWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        listenerWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        listenerView =  new LinearLayout(this);
        LayoutParams params = new LayoutParams(1, LayoutParams.MATCH_PARENT);
        listenerView.setLayoutParams(params);
        listenerView.setOnTouchListener(this);

        int layoutParamApiType;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            layoutParamApiType = LayoutParams.TYPE_PHONE;
        }
        else {
            layoutParamApiType = LayoutParams.TYPE_APPLICATION_OVERLAY;
        }

        LayoutParams mParams = new LayoutParams(
                1,
                1,
                layoutParamApiType,
                LayoutParams.FLAG_NOT_FOCUSABLE |
                        LayoutParams.FLAG_NOT_TOUCH_MODAL |
                        LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSPARENT
        );
        listenerView.setClickable(false);
        listenerView.setFocusable(false);
        mParams.gravity = Gravity.START | Gravity.TOP;
        listenerWindowManager.addView(listenerView, mParams);
    }

    private void openBuffer() { // Opens window manager and components needed for buffer viewing
        isShowing = true;
        final WindowManager.LayoutParams params;
        int layoutParamsType;

        // Get darkmode
        if (ClipButlerApp.darkMode){
            System.out.println("Darkmode");
            listBg = Color.DKGRAY;
            listTxtC = Color.WHITE;
            headerBg = Color.GRAY;
            bufferBG = Color.BLACK;

        } else {
            listBg = Color.WHITE;
            listTxtC = Color.BLACK;
            headerBg = Color.BLACK;
            bufferBG = Color.WHITE;
            System.out.println("Lightmode");
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            layoutParamsType = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            layoutParamsType = LayoutParams.TYPE_PHONE;
        }

        params = new WindowManager.LayoutParams( // Location of buffer, (Adjustable)
                screenWidth / 2,
                screenHeight / 3,
                layoutParamsType,
                0,
                PixelFormat.TRANSLUCENT);

        params.x = (screenWidth / 2) - screenWidth / 4;
        params.y = 0;

        bufferLayout = new RelativeLayout(this) {
            @Override
            public boolean dispatchKeyEvent(KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    System.out.println("Got key event");
                    int keyCode = event.getKeyCode();
                    //System.out.println("action detected");
                    //System.out.println(event.getKeyCode());
                    // Check if the HOME button is pressed
                    if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME || keyCode == KeyEvent.KEYCODE_ALL_APPS) {
                        Log.v(TAG, "Clicked somewhere else");
                        closeBuffer(1);
                        return true; // Can change to false to let other apps take over but not recomended
                    }
                }
                // Otherwise don't intercept the event
                return super.dispatchKeyEvent(event);
            }
        };

        RelativeLayout.LayoutParams bufferViewParams = new RelativeLayout.LayoutParams(20,20);
        bufferViewParams.setMargins(10,10,10,10);
        bufferViewParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        bufferLayout.setLayoutParams(bufferViewParams);
        // Check setttings if dark mode then color.BLACK else color.WHITE
        bufferLayout.setBackgroundColor(bufferBG); // Can be any color
        bufferWindowManager.addView(bufferLayout,params);

        //Connect buffer array to but(t)ler UI
        ListView DynamicListView = new ListView(this);
        String[] bufferStringArray = ClipButlerApp.buffer.toStringArray();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (CBService.this, android.R.layout.simple_list_item_1, bufferStringArray){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                // Initialize a TextView for ListView each Item
                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text color of TextView (ListView Item)
                // IF DarkMode, set text white
                tv.setTextColor(listTxtC);

                // Generate ListView Item using TextView
                return view;
            }
        };

        DynamicListView.setAdapter(adapter);

        DynamicListView.setBackgroundColor(listBg);
        adapter.notifyDataSetChanged();

        // Add header of "clibbutler" to listView
        TextView cb_header = new TextView(this); // customizable
        cb_header.setText("ClipButler"); //@string/app_name
            cb_header.setTextColor(Color.WHITE); // Should always be white

        // If darkmode set Gray
        cb_header.setBackgroundColor(headerBg);
        cb_header.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        cb_header.setHeight(100);
        cb_header.setWidth(screenWidth/2);
        //cb_header.setPadding(0,0,0,0);
        DynamicListView.addHeaderView(cb_header);

        bufferLayout.addView(DynamicListView);
        bufferLayout.setOnTouchListener(this);

        DynamicListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                /* Depending on ID, create paste intent or something like that */
                if (position <=0) {
                    return; // prevents negative index
                }
                position = position - 1; // position will need to be offset due to header
                String bufferItem = ClipButlerApp.buffer.getItemString(position);
                ///System.out.println("pasting buffer Item: " + bufferItem);
                ClipData clipString = ClipData.newPlainText("text",bufferItem);
                clipboard.setPrimaryClip(clipString);
                showPasteToast();
                view.performClick();
            }
        });
    }

    private void showPasteToast(){
        Toast.makeText(this, "Selection has been prepared to paste", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onPrimaryClipChanged() {
        Log.d(TAG, "onPrimaryClipChanged");

        CharSequence text = getPrimaryClipText();
        Log.d(TAG, "\ttext: "+text.toString());
        if (!isTextEmpty(text)) {
            Log.d(TAG, "\tcontained: "+ClipButlerApp.buffer.contains(text));
            ClipButlerApp.buffer.insert(text);
            Log.d(TAG, ClipButlerApp.buffer.toString());
            Toast.makeText(this, "Copy Captured", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean clipboardHasPrimaryClip() {
        return clipboard.hasPrimaryClip();
    }

    private boolean cbHasTargetTextType() {
        if (clipboardHasPrimaryClip()) {
            ClipDescription clipDesc = clipboard.getPrimaryClipDescription();
            return clipDesc.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
                    || clipDesc.hasMimeType(ClipDescription.MIMETYPE_TEXT_HTML);
        }
        return false;
    }

    private CharSequence getPrimaryClipText() {
        CharSequence text = "";
        if (cbHasTargetTextType()) {
            text = clipboard.getPrimaryClip().getItemAt(0).getText();
        }
        return text;
    }

    private boolean isTextEmpty(String text) {
        return text.equals("");
    }

    private boolean isTextEmpty(CharSequence text) {
        return text.toString().equals("");
    }


    private void closeBuffer(int method){
        System.out.println("Closing buffer" +method);
        numClicks = 0;
        if (bufferLayout != null) {
            bufferWindowManager.removeView(bufferLayout);
            bufferLayout = null;
            isShowing = false;
        }

        if (method == 0) { // If called from onDestroy (0) then remove triple click listener
            if (listenerView != null) {
                listenerWindowManager.removeView(listenerView);
                listenerView = null;
            }
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        long clickTiming;
        int action = motionEvent.getAction();
        if (isShowing & action == MotionEvent.ACTION_UP){ // If clicked outside of buffer else and is already showing
            closeBuffer(1);
            numClicks = 0;
        }
        long currentClickTime = motionEvent.getEventTime();
        clickTiming =  getClickTiming(lastClickTime,currentClickTime);
        lastClickTime = currentClickTime;
        if ((clickTiming < tripleThreshold) & (lastClickTime !=0)) {
            numClicks ++;
            if ( numClicks >= 2){
                Log.v(TAG,"Got Tripple click");
                if (!isShowing) {
                    openBuffer();
                }
                numClicks = 0;
            }
        } else if (clickTiming > 350 || isShowing) {
            // Reset triple click counter
            numClicks = 0;
        }
        view.performClick(); // Necessary to pass along information
        return false;
    }


    private long getClickTiming(long click1, long click2){
        return click2 - click1;
    }


    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
        //Destroy notification
        //Disconnect from clipboard manager
        //Save strings
        clipboard.removePrimaryClipChangedListener(this);
        CBService.running = false;
        killNotification();
    }

    @Override
    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
        closeBuffer(1);
        restartSelf();
    }

    private void restartSelf() {
        Log.d(TAG, "restarting CBS");
        Intent intent = new Intent(this, CBService.class);
        saveClips();
        saveSettings();
        stopSelf();
        startService(intent);
    }

    private void saveClips() {
        Log.d(TAG, "saveClips");
        if (HelperFunctions.saveClipsFileSuccessfully(this)) {
            Log.d(TAG, "\tsuccess");
        }
        else {
            Log.d(TAG, "\tFAILURE");
        }
    }

    private void readClips() {
        Log.d(TAG, "readClips");
        try {
            File clipFile = new File(getFilesDir(), "clips.txt");
            String[] clipFileContent = HelperFunctions.readFileAsStringArray(clipFile);

            for (String line : clipFileContent) {
                String reverseFilter = line.replace("\\n", "\n");
                ClipButlerApp.buffer.insert(reverseFilter);
            }

            if (clipFileContent.length > 0) {
                Log.d(TAG, "\tsuccess");
                Log.d(TAG, "\t" + ClipButlerApp.buffer.toString());
            }
            else {
                Log.d(TAG, "\tFAILURE");
            }
        } catch(Exception e) {
            Log.d(TAG, "\tERROR");
        }
    }

    private void saveSettings() {
        Log.d(TAG, "saveSettings");
        boolean saveCorrectly = HelperFunctions.saveSettingsFileSuccessfully(this);
        if (saveCorrectly) {
            Log.d(TAG, "\tsuccess");
        }
        else {
            Log.d(TAG, "\tFAILURE");
        }
    }

    private void readSettings() {
        Log.d(TAG, "readSettings");
        if (HelperFunctions.readSettingsFileSuccessfully(this)) {
            Log.d(TAG, "\tsuccess");
        }
        else {
            Log.d(TAG, "\tFAILURE");
        }
    }

    private static final String CHANNEL_NAME = "CB Notification Channel";
    private static final String NOTIFICATION_CHANNEL_ID = "CBS Active";
    private static final int NOTIFICATION_ID = 123;

    private void initNotification() {
        Log.d(TAG, "initNotification");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_LOW;

            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, CHANNEL_NAME, importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.WHITE);
            //Disables showing blank app badge
            notificationChannel.setShowBadge(false);
            //Sets whether notifications from these Channel should be visible on lock screen or not
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        else {
            Notification notify = new NotificationCompat.Builder(this)
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .setDefaults(NotificationCompat.DEFAULT_LIGHTS)
                    .setSmallIcon(R.drawable.ic_stat_cbactive)
                    .setAutoCancel(false)
                    .setContentTitle("ClipButler Active")
                    .setContentText("ClipButler Service Currently Active")
                    .setLights(Color.WHITE, 500, 500)
                    .build();

            notify.flags |= Notification.FLAG_ONGOING_EVENT;
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(11001001, notify);
        }
    }

    private void displayNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                    .setContentTitle("ClipButler Active")
                    .setContentText("ClipButler Service currently active")
                    .setSmallIcon(R.drawable.ic_stat_cbactive);

            Notification notification = builder.build();
            notification.flags = Notification.FLAG_ONGOING_EVENT;

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(NOTIFICATION_ID, notification);
        }
    }

    private void killNotification() {
        Log.d(TAG, "kill Notification");
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.cancelAll();
    }
}
