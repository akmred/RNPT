package com.example.rnpt.base.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.rnpt.base.model.RNPT;

import java.util.List;

// Описание объекта, обрабатывающие данные,
// @Dao - доступ к данным
// В этом классе описывается, как будет происходить обработка данных
@Dao
public interface RnptDao {

    // Метод для добавления рнпт в базу данных
    // @Insert - признак добавления
    // onConflict - что делать, если такая запись уже есть
    // В данном случае просто заменим ее
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertRNTP(RNPT rnpt);

    // Получим список всех РНПТ
    @Query("SELECT * FROM rnpt")
    List<RNPT> getAllRNPT();

    // Получим данные только по одному РНПТ по id
    @Query("SELECT * FROM rnpt WHERE id = :id")
    RNPT getRNPTByID(long id);

    // Получить количество записей в таблице
    @Query("SELECT COUNT() FROM rnpt")
    int getCountRNPTs();
}
