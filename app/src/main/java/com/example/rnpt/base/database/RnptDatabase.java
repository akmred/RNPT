package com.example.rnpt.base.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.rnpt.base.dao.RnptDao;
import com.example.rnpt.base.model.DataConverter;
import com.example.rnpt.base.model.RNPT;

@Database(entities = {RNPT.class}, version = 1)
@TypeConverters(DataConverter.class)
public abstract class RnptDatabase extends RoomDatabase {
    public abstract RnptDao getRnptDao();
}
