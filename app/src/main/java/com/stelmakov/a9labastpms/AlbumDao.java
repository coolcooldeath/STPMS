package com.stelmakov.a9labastpms;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.stelmakov.a9labastpms.Album;

import java.util.List;

@Dao
public interface AlbumDao {

    @Query("SELECT * FROM Album")
    LiveData<List<Album>> getAll();

    @Query("SELECT * FROM Album WHERE name = :name")
    LiveData<Album> getByName(long name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Album album);

    @Update
    void update(Album album);

    @Delete
    void delete(Album album);

}