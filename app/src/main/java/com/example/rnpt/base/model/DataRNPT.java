package com.example.rnpt.base.model;


import androidx.room.ColumnInfo;

// Выделенный класс, для обработки из класса RNPT
// В таблице RNPT этот класс будет являться полями таблицы
// в эти поля будет записываться ответ от сервиса по РНПТ
public class DataRNPT {
    public String nameProduct;
    public String kodTNVED;
    public String countryOfOrigin;
    public String dataRegistration;
    public String unit;
    public Integer amountInput;
    public Integer amountOutput;
    public Integer amountTurnover;

}
