package com.example.rnpt.fragments;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.example.rnpt.R;
import com.example.rnpt.maps.MainMaps;

public class Fragment_settings extends Fragment {
    EditText editText_company_name, editText_url_service;
    String company, url_service;
    Button bLocateMySpase, buttonPushLocate;
    Activity activity;
    TextView textViewLocate;

    public Fragment_settings(Activity activity) {
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Инициализиуем переменные
        initializationVariable(view);

        //set Listener
        setListeners();

        // Заполняем реквизиты тестовыми данными
        setTestingData();
        // Заполняем форму
        fillInDataOnForm(view);

    }

    private void setListeners() {

    }

    private void initializationVariable(View view) {

        editText_company_name = view.findViewById(R.id.company_name);
        editText_url_service = view.findViewById(R.id.url_service_fns);

    }

    private void setTestingData() {

       company = "ООО Вертигора";
       url_service = "http://localhost:8087/mock/consumer/authService";

    }

    private void fillInDataOnForm(View view) {

        editText_company_name.setText(company);
        editText_url_service.setText(url_service);

    }
}
