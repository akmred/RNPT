package com.example.rnpt.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import com.example.rnpt.messages.MessageUserWarning;

public class NoConnection extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null) {
            // Create message
            MessageUserWarning messageUserWarning = new MessageUserWarning(context, "Нет сети!");
            messageUserWarning.ShowMessage();
        }
    }
}
