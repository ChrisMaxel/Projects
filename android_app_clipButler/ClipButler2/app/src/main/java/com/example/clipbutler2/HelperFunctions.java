package com.example.clipbutler2;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class HelperFunctions {
    public static boolean saveFileSuccessfully(File file, String content) {
        try {
            FileWriter fw = new FileWriter(file, false);
            fw.write(content);
            fw.close();
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public static String[] readFileAsStringArray(File file) {
        try {
            ArrayList<String> contents = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                contents.add(line);
            }
            String[] trueContents = new String[contents.size()];
            for (int index = 0; index < contents.size(); index++) {
                trueContents[index] = contents.get(index);
            }
            return trueContents;
        } catch(Exception e) {
            return new String[0];
        }
    }

    public static boolean saveSettingsFileSuccessfully(Context context) {
        File file = new File(context.getFilesDir(), "settings.txt");
        StringBuilder builder = new StringBuilder(2);
        builder.append("max clips: " + ClipButlerApp.maxClips+'\n');
        builder.append("dark mode: " + ClipButlerApp.darkMode);

        return saveFileSuccessfully(file, builder.toString());
    }

    public static boolean saveClipsFileSuccessfully(Context context) {
        File clipFile = new File(context.getFilesDir(), "clips.txt");

        StringBuilder builder = new StringBuilder(ClipButlerApp.buffer.currentSize());
        Object[] array = ClipButlerApp.buffer.toArray();
        for (int index = 0; index < array.length; index++) {
            String raw = array[index].toString();
            String filtered = raw.replace("\n","\\n");
            filtered += '\n';
            builder.append(filtered);
        }

        return saveFileSuccessfully(clipFile, builder.toString());
    }

    public static boolean readSettingsFileSuccessfully(Context context) {
        File settingsFile = new File(context.getFilesDir(), "settings.txt");
        String[] settingsContent = HelperFunctions.readFileAsStringArray(settingsFile);
        boolean totalSuccess = true;

        try {
            for (String line : settingsContent) {
                if (line.contains("max clips: ")) {
                    String content = line.replace("max clips: ", "");
                    int settingMaxClips = Integer.parseInt(content);
                    if (settingMaxClips > 0) {
                        ClipButlerApp.maxClips = settingMaxClips;
                        ClipButlerApp.buffer =
                                new Buffer(settingMaxClips, ClipButlerApp.buffer.toArray());
                    }
                } else if (line.contains("dark mode: ")) {
                    String content = line.replace("dark mode: ", "");
                    ClipButlerApp.darkMode = Boolean.parseBoolean(content);
                }
            }

            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public static int parseIntNoError(String input) {
        try {
            return Integer.parseInt(input);
        } catch(Exception e) {
            return -1;
        }
    }
}
