package com.vlad.famouspeople;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Transaction;

/**
 * Created by Vlad on 29.03.2018.
 */
@Dao
public abstract class UserPointDao {

    @Insert
    public abstract void insertUser(User user);

    @Insert
    public abstract void insertPoint(Point point);

    @Transaction
    public void insertUserAndPoint(User user, Point point)
    {
        insertUser(user);
        insertPoint(point);
    }
}
