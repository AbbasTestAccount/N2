package com.example.n2.not_used;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.n2.http_service.HttpService;

public class RestarterBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("shod?", "Service Stops! Oooooooooooooppppssssss!!!!");
        context.startService(new Intent(context, HttpService.class));;
    }
}