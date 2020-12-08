package com.example.rnpt.fragments.dialogfragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.rnpt.MainActivity;
import com.example.rnpt.R;
import com.example.rnpt.fragments.Fragment_list_rnpt;
import com.example.rnpt.messages.MessageUsersError;

public class DialogEditRnpt extends DialogFragment {
    private int position;

    public DialogEditRnpt() {

    }
    public DialogEditRnpt(int position) {
        this.position = position;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_rnpt, null);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edit_rnpt = ((EditText)view.findViewById(R.id.element_edit_rnpt)).getText().toString();

                if (edit_rnpt.isEmpty()){

                    MessageUserError("Поле РНПТ должно быть заполнено");

                }else{

                    Fragment_list_rnpt fragment_list_rnpt = (Fragment_list_rnpt) getFragmentManager().findFragmentById(R.id.context_main);
                    //  ((Fragment_list_rnpt)getActivity()).onDialogResult(edit_rnpt, "OK");
                    dismiss();
                    fragment_list_rnpt.onDialogResultNewElement(edit_rnpt, "OK");
                }
            }
        });

       view.findViewById(R.id.button_esc).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               dismiss();

           }
       });

    }

    private void MessageUserError(String textMessage) {

        MessageUsersError messageUserError = new MessageUsersError(getContext(), textMessage);
        messageUserError.ShowMessage();
    }


}
