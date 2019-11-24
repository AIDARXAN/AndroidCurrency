package com.e.currency.db;

import com.e.currency.entity.Currency;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = Currency.class, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract WordDao getWordDao();

}
