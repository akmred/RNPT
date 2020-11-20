package com.example.rnpt;

import android.app.Application;

import androidx.room.Room;

import com.example.rnpt.base.dao.RnptDao;
import com.example.rnpt.base.database.RnptDatabase;

public class App extends Application {

    private static App instance;

    // база данных
    private RnptDatabase db;

    // Получаем объект приложения
    public static App getInstance() { return instance; };

    @Override
    public void onCreate() {
        super.onCreate();
        // Для синглтона, сохраняем объект приложения
        instance = this;

        // Строим базу
        db = Room.databaseBuilder(
                getApplicationContext(),
                RnptDatabase.class,
                "rnpt_database")
                .allowMainThreadQueries()
                .build();

    }

    // Получаем RnptDao для составления запросов
    public RnptDao getRnptDao() {
        return db.getRnptDao();
    }

}
