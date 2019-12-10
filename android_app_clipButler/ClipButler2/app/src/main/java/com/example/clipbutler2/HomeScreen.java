package com.example.clipbutler2;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.provider.Settings;
import android.widget.Toast;

public class HomeScreen extends Activity {
    final String TAG = HomeScreen.class.getSimpleName();
    private Switch ActivateCBSwitch;
    private boolean activateCBSwitchState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        activateCBSwitchState = isCBServiceRunning();
        ActivateCBSwitch = findViewById(R.id.ActivateCBSwitch);
        ActivateCBSwitch.setChecked(activateCBSwitchState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    private final static int REQUEST_CODE = 10101;
    private void checkDrawOverlayPermission() {
        // Checks if app already has permission to draw overlays
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                // If not, form up an Intent to launch the permission request
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                // Launch Intent, with the supplied request code
                startActivityForResult(intent, REQUEST_CODE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (Settings.canDrawOverlays(this)) {
                    // Launch the service
                    startCBService();
                }
                else {
                    Toast.makeText(this, "Sorry. Can't use ClipButler without permission", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void startCBService() {
        Log.d(TAG, "startCBService");
        Intent startCBService = new Intent(this, CBService.class);
        if (isCBServiceRunning()) {
            //Do nothing
        }
        else {
            //Start service
            Log.d(TAG, "\tCBService running");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (Settings.canDrawOverlays(this)) {
                    startService(startCBService);
                }
                else {
                    checkDrawOverlayPermission();
                }
            }
        }
    }

    private boolean isCBServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service :
                manager.getRunningServices(Integer.MAX_VALUE)) {
            if (service.service.getClassName().equals(CBService.class.getName())) {
                return true;
            }
        }
        return false;
    }

    public void prefsButtonClick(View view) {
        Intent intent = new Intent(HomeScreen.this, Preferences.class);
        startActivity(intent);
    }

    public void manageClipsBtnClick(View view) {
        Intent intent = new Intent(HomeScreen.this, ManageClips.class);
        startActivity(intent);
    }

    public void activateCBSwitch(View view) {
        activateCBSwitchState = !activateCBSwitchState;
        Log.d(TAG, "activeCBState: " + activateCBSwitchState);

        if (!activateCBSwitchState) {
            CBService.running = false;
            Intent stopCBS = new Intent(this, CBService.class);
            stopService(stopCBS);
            Toast.makeText(this, "ClipButler Service Deactivated", Toast.LENGTH_SHORT).show();
        }
        else {
            CBService.running = true;
            startCBService();
            Toast.makeText(this, "ClipButler Service Activated", Toast.LENGTH_SHORT).show();
            HelperFunctions.readSettingsFileSuccessfully(this);
        }
    }
}
