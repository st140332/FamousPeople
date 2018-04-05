package com.vlad.famouspeople;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import org.w3c.dom.Text;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Vlad on 29.03.2018.
 */
@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "person_id", onDelete = CASCADE))
public class Point {

    public Point(Integer count,long personId,String comment,String createdAt) {
        this.count = count;
        this.personId = personId;
        this.comment = comment;
        this.createdAt=createdAt;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "count")
    public Integer count;
    @ColumnInfo(name = "comment")
    public String comment;
    @ColumnInfo(name = "person_id")
    public long personId;
    @ColumnInfo(name = "author_id")
    public long authorId;
    @ColumnInfo(name = "created_at")
    public String createdAt;


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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
