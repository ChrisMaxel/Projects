package com.example.clipbutler2;

import android.app.Application;

public class ClipButlerApp extends Application {
    public static Buffer<CharSequence> buffer;

    public static int maxClips = 10;
    public static boolean darkMode = false;
}
