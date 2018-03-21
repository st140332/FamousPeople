package com.vlad.famouspeople;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Vlad on 20.03.2018.
 */
@Database(entities = {User.class},version=1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
