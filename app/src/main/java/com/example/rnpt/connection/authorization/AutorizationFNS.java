package com.example.rnpt.connection.authorization;

import android.util.Log;
import android.widget.Toast;

import com.example.rnpt.MainActivity;

public class AutorizationFNS {
    private DataAuthorization dataAuthorization;
    private Boolean ready;

    public AutorizationFNS() {
        ready = false;
        runToExecute();
    }

    private void runToExecute() {

        // Отправляем сообщение в сервис авторизации ФНС получаем зашифрованный ответ
        CenterConnectionAutorization centerConnectionAutorization = new CenterConnectionAutorization(new CenterConnectionAutorization.OnResponseCompleted() {
            @Override
            public void onCompleted(String content) {

                // Если получили ответ, то передаем на расшифровку
                ConvertDataAnswerCenterConnection convertDataAnswerCenterConnection =
                        new ConvertDataAnswerCenterConnection(content, new ConvertDataAnswerCenterConnection.OnRequerConvertAnswer() {
                            @Override
                            public void onComplete(DataAuthorization dataAuthorization) {

                                dataAuthorization = dataAuthorization;
                                ready = true;

                            }

                            @Override
                            public void onError(String textError) {

                            }
                        });
            }

            @Override
            public void onError(String textError) {

            }

        });
        centerConnectionAutorization.postmessage();



    }

    public Boolean getReady() {
        return ready;
    }

    public DataAuthorization getDataAuthorization() {
        return dataAuthorization;
    }
}
