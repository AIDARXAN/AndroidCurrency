package com.e.currency;

import android.app.Application;

import com.e.currency.db.AppDatabase;

import androidx.room.Room;

public class App extends Application {
    private static AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        appDatabase = Room
                .databaseBuilder(this, AppDatabase.class, "app-database1")
                .build();
    }

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
