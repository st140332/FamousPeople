package com.vlad.famouspeople;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Vlad on 29.03.2018.
 */
@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "person_id", onDelete = CASCADE))
public class Point {

    public Point(Integer count,long personId) {
        this.count = count;
        this.personId = personId;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "count")
    public Integer count;
    @ColumnInfo(name = "person_id")
    public long personId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }
}
