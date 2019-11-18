package com.e.navdrawerapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BeerDao {

    @Query("SELECT * FROM BeerEntity")
    LiveData<List<BeerEntity>> getAll();

    @Query("SELECT * From BeerEntity WHERE id IN (:beerIds)")
    List<BeerEntity> getAllById(Integer[] beerIds);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(BeerEntity... beers);

    @Delete
    void delete(BeerEntity beer);
}
