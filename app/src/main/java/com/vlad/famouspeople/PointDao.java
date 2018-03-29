package com.vlad.famouspeople;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Vlad on 29.03.2018.
 */
@Dao
public interface PointDao {
    @Query("SELECT * FROM point")
    List<Point> getAllPoints();

    @Query("SELECT * FROM point WHERE id = :id")
    Point getById(int id);

    @Insert
    void insert(Point point);

    @Delete
    void delete(Point point);

    @Update
    void update(Point point);
}
