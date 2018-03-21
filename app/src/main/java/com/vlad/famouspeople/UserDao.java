package com.vlad.famouspeople;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;



/**
 * Created by Vlad on 20.03.2018.
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Query("SELECT * FROM user WHERE id = :id")
    User getById(int id);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}
