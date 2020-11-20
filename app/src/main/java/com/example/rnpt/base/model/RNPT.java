package com.example.rnpt.base.model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

// @Entity - признак табличного объекта, то есть объект
// будет сохраняться в базе данных в виде строки
// indices - указывает на индексы в таблице
@Entity(indices = {@Index(value = {RNPT.NAMERNPT, RNPT.DATARECEIVED})})
public class RNPT {

    public final static String ID = "id";
    public final static String NAMERNPT = "NameRnpt";
    public final static String DATARECEIVED = "DataReceived";

    // @PrimaryKey - указывает на ключевую запись
    // autoGenerate = true - автоматическая генерация ключа
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    public long id;

    // Имя РНПТ
    // @ColumnInfo - позволяет создавать параметры колонки в БД
    // name = "name" - такое будет имя колонки
    @ColumnInfo(name = NAMERNPT)
    public String NameRnpt;

    // Признак, что данные получены
    @ColumnInfo(name = DATARECEIVED)
    public boolean DataReceived;

    // @Embeddeb - хранить поля воженного класса, как поля таблицы
    @Embedded
    public DataRNPT dataRNPT;

}
