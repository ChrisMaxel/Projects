package com.example.clipbutler2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

//import com.example.clipbutler2.R;

public class ManageClips extends AppCompatActivity {
    final String TAG = HomeScreen.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_clips);
    }


    public void manageToHomeButtonClick(View view) {
        Log.d(TAG, "settingsButtonClick");
        Intent intent = new Intent(ManageClips.this, HomeScreen.class);
        startActivity(intent);
    }
}
