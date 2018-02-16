package edu.usc.projecttalent.cognitive.util;

import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class Fileutils {

    public static boolean fileWritePermission = false;

    public Fileutils() {

    }

    public void writeFile(String fileName, String fileContent) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        fileName = Build.SERIAL + "_" + fileName + timestamp + ".txt";
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);

        try {

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(fileContent);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
