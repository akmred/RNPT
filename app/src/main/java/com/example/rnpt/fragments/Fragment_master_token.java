package com.example.rnpt.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

    private  static  final String TAG = "cat";

    public Fragment_master_token(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_master_token, container, false);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Инициализиуем переменные
        initializationVariable(view);
        // Заполняем реквизиты тестовыми данными
        setTestingData();
        // Заполняем форму
        fillInDataOnForm(view);

        Log.e(TAG, "onViewCreate");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("URL_SERVICE", editText_url_service.getText().toString());
        editor.putString("KEY_MASTER_TOKEN", editText_key_master_token.getText().toString());
        editor.commit();

        Log.e(TAG, "onDestroy");

    }

    private void fillInDataOnForm(View view) {

        // для получения настроек нет необходимости в Editor, получаем их прямо из SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        String value_url = sharedPreferences.getString("URL_SERVICE", "null");
        String value_token = sharedPreferences.getString("KEY_MASTER_TOKEN", "null");

        if (value_url != "null"){

            editText_url_service.setText(value_url);
            editText_key_master_token.setText(value_token);

        }else {

            editText_url_service.setText(URL_SERVICE);
            editText_key_master_token.setText(KEY_MASTER_TOKEN);
        }
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
