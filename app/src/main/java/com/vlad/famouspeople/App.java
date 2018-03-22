package com.vlad.famouspeople;

import android.app.Application;
import android.arch.persistence.room.Room;

/**
 * Created by Vlad on 21.03.2018.
 */

public class App extends Application {
    public static App instance;

    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "pro")
                .allowMainThreadQueries()
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
