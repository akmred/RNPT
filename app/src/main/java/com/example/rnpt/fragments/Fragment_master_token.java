package com.example.rnpt.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rnpt.R;

public class Fragment_master_token extends Fragment {
    EditText editText_key_master_token, editText_url_service;
    String URL_SERVICE, KEY_MASTER_TOKEN;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_master_token, container, false);

        // Инициализиуем переменные
        initializationVariable(view);
        // Заполняем реквизиты тестовыми данными
        setTestingData();
        // Заполняем форму
        fillInDataOnForm(view);

        return view;

    }

    private void fillInDataOnForm(View view) {

        editText_url_service.setText(URL_SERVICE);
        editText_key_master_token.setText(KEY_MASTER_TOKEN);

    }

    private void setTestingData() {

        URL_SERVICE = "http://localhost:8095/open-api/ais3/AsyncService/0.1";
        KEY_MASTER_TOKEN = "4da6a18ef1864a40ba5a99b114f0ea55";

    }

    private void initializationVariable(View view) {
        editText_key_master_token = view.findViewById(R.id.key_master_token);
        editText_url_service = view.findViewById(R.id.url_service);
    }
}
