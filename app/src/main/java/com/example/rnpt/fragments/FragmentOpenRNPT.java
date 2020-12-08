package com.example.rnpt.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rnpt.R;
import com.example.rnpt.connection.authorization.ConvertDataAnswerCenterConnection;
import com.example.rnpt.connection.getpost.CenterConnectionRnpt;
import com.example.rnpt.connection.getpost.model.getid.ResponseInfo;

public class FragmentOpenRNPT extends Fragment {
    String stringRNPT;

    public FragmentOpenRNPT() {

    }

    public FragmentOpenRNPT(String stringRNPT) {
        this.stringRNPT = stringRNPT;
    }

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
        initializationRequest();
        // Заполняем реквизиты тестовыми данными
        setTestingData();
        // Заполняем форму
        fillInDataOnForm(view);

    }

    // Выполняем асихронный запрос к сервису ФНС
    private void initializationRequest() {

        CenterConnectionRnpt convertDataAnswerCenterConnection= new CenterConnectionRnpt(stringRNPT, getActivity());
        convertDataAnswerCenterConnection.infoCheck();

    }

    // При получении информации по РНППТ вызывается эта процедура
    public void downLoadInfoRNPT(ResponseInfo responseInfo){

        System.out.println("Здесь загружаем в фрагмент информацию по РНПТ");

    }

    private void initializationVariable(View view) {



    }

    private void setTestingData() {


    }

    private void fillInDataOnForm(View view) {
    }
}
