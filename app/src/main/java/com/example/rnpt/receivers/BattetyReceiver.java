package com.example.rnpt.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.rnpt.messages.MessageUserWarning;

public class BattetyReceiver  extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        // Create message
        MessageUserWarning messageUserWarning = new MessageUserWarning(context, "Низкий заряд баттареи!");
        messageUserWarning.ShowMessage();
    }
}
