package com.example.rnpt.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.rnpt.MainActivity;
import com.example.rnpt.R;
import com.example.rnpt.connection.getpost.CenterConnectionRnpt;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;

public class Fragment_auth_google extends Fragment {
    Activity activity;

    // Используется, чтобы определить результат активити регистрации через Гугл
    private static final int RC_SIGN_IN = 40404;
    private static final String TAG = "GoogleAuth";

    // Клиент для регистрации пользователя через Гугл
    private GoogleSignInClient googleSignInClient;

    // Кнопка регистрации через гугл
    private com.google.android.gms.common.SignInButton buttonSignIn;
    // Кнопка выхода из Гугл
    private MaterialButton buttonSingOut;

    public Fragment_auth_google(Activity activity) {

        this.activity = activity;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_auth_google, container, false);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Инициализиуем переменные
        initializationVariable(view);

        // Пробуем автоматически зайти
        if (checkLoggingIn()) {
            checkCurrentFragment();
        }

    }

    // Переключение на другой фрагмент
    private void checkCurrentFragment() {

        ((MainActivity)getActivity()).inLoggingGoogle();

    }

    //https://developers.google.com/identity/sign-in/android/backend-auth?authuser=1
    // Получение данных пользователя
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Регистрация прошла успешно
            disableSign();
            updateUI(account.getEmail());

            checkCurrentFragment();

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }

    public boolean checkLoggingIn(){

        enableSign();
        // Проверим, заходил ли пользователь в этом приложении через Гугл
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(activity);
        if (account != null) {
            // Пользователь уже заходил, сделаем кнопку недоступной
            disableSign();
            // Обновим почтовый адрес этого пользователя и выведем его на экран
            updateUI(account.getEmail());
            return true;
        }

       return  false;

    }

    private void initializationVariable(View view) {

        // Конфигурация запроса на регистрацию пользователя, чтобы получить
        // идентификатор пользователя, его почту и основной профайл (регулируется параметром)
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Получить клиента для регистрации, а также данных по клиенту
        googleSignInClient = GoogleSignIn.getClient(activity, gso);

        // кнопка регистрации пользователя
        buttonSignIn = activity.findViewById(R.id.sign_in_button);
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                signIn();
                                            }
                                        }
        );

        // кнопка выхода
        buttonSingOut = activity.findViewById(R.id.sing_out_button);
        buttonSingOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

    }

        // Инициация регистрации пользователя
        private void signIn() {
            Intent signInIntent = googleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        }

        // Выход из учетной записи в приложении
        private void signOut() {
            googleSignInClient.signOut()
                    .addOnCompleteListener(activity, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            updateUI("email");
                            enableSign();
                        }
                    });
        }

    // Обновить данные о пользователе на экране
    private void updateUI(String e_mail) {
        TextView email = activity.findViewById(R.id.email);
        email.setText(e_mail);
    }

    private void enableSign(){
        buttonSignIn.setEnabled(true);
        buttonSingOut.setEnabled(false);
    }

    private void disableSign(){
        buttonSignIn.setEnabled(false);
        buttonSingOut.setEnabled(true);
    }


}



