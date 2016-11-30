package com.example.kirillantonov.imagedownloader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenOnReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Intent i = new Intent(context, DownloaderService.class);
        context.startService(i);
    }
}
