package com.example.kirillantonov.imagedownloader;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class DownloaderService extends Service implements Runnable {
    @Nullable

    private static final String url =
            "http://i.dailymail.co.uk/i/pix/2015/09/28/08/2CD1E26200000578-0-image-a-312_1443424459664.jpg";
    private File f;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId)  {
        f = new File(getFilesDir(), MainActivity.fileName);
        if (!f.exists()) {
            new Thread(this).start();
        }
        return START_STICKY;
    }

    @Override
    public void run() {
        InputStream is = null;
        FileOutputStream os = null;

        try {
            is = new BufferedInputStream(new URL(url).openStream());
            os = new FileOutputStream(f);
            byte[] buffer = new byte[1024];
            int x;
            while ((x = is.read(buffer)) != -1) {
                os.write(buffer, 0, x);
            }

            sendBroadcast(new Intent(MainActivity.IS));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
