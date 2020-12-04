package com.example.rnpt.messages;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.rnpt.R;

public class MessageUserWarning {

    Context context;
    String textMessage;

    public MessageUserWarning(Context context, String textMessage) {
        this.context = context;
        this.textMessage = textMessage;
    }

    public MessageUserWarning() {
    }

    public void ShowMessage(){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Предупреждение!!")
                .setMessage(textMessage)
                .setIcon(R.mipmap.icon_magnifier)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

}
