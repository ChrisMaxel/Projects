package com.example.clipbutler2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class Preferences extends AppCompatActivity {
    final String TAG = Preferences.class.getSimpleName();

    Button applyChangesButton;
    EditText maxClipsNum;
    Switch darkModeSwitch;
    boolean tempDarkMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        maxClipsNum = findViewById(R.id.maxClipsNum);
        applyChangesButton = findViewById(R.id.applyChangesButton);

        maxClipsNum.setText(Integer.toString(ClipButlerApp.maxClips));

        darkModeSwitch = findViewById(R.id.darkModeSwitch);
        tempDarkMode = ClipButlerApp.darkMode;
        darkModeSwitch.setChecked(tempDarkMode);
        Log.d(TAG, "Dark mode currently: "+tempDarkMode);

        darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tempDarkMode = !tempDarkMode;
                Log.d(TAG, "temp dark mode: "+tempDarkMode);
            }
        });
    }

    public void prefsToHomeButtonClick(View view){
        Intent intent = new Intent(Preferences.this, HomeScreen.class);
        startActivity(intent);
    }

    public void applyChangesButtonClick(View view){
        Log.d(TAG, "applyChangesButtonClick");

        applyMaxClipsSetting();
        applyDarkModeSetting();

        if (HelperFunctions.saveSettingsFileSuccessfully(this)) {
            Log.d(TAG, "\tsuccess");
            Toast.makeText(this, "Settings Updated Successfully", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Error occurred while saving settings", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "\tFAILURE");
        }
    }

    private void applyMaxClipsSetting() {
        String maxClipsStr = maxClipsNum.getText().toString();
        int tempMax = HelperFunctions.parseIntNoError(maxClipsStr); //-1 on error

        if (tempMax >= 1) {
            ClipButlerApp.buffer = new Buffer<>(tempMax, ClipButlerApp.buffer.toArray());
            ClipButlerApp.maxClips = tempMax;
            Toast.makeText(this,
                    String.format("Max Number of Clips: %d", tempMax),
                    Toast.LENGTH_SHORT).show();
        }
        else {
            Log.d(TAG, "\tmaxClips input is bad");
            Toast.makeText(this,
                    "Amount must be at least 1",
                    Toast.LENGTH_SHORT).show();
        }
        Log.d(TAG, "\tmaxClips: "+ClipButlerApp.maxClips);
    }

    private void applyDarkModeSetting() {
        ClipButlerApp.darkMode = tempDarkMode;
        Log.d(TAG, "\tDark mode should be: "+tempDarkMode);
    }
}
