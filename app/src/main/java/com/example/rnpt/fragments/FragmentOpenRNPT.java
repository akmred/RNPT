package com.example.rnpt.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rnpt.R;

public class FragmentOpenRNPT extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_open_rnpt, container, false);

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

    }

    private void initializationVariable(View view) {



    }

    private void setTestingData() {


    }

    private void fillInDataOnForm(View view) {
    }
}
