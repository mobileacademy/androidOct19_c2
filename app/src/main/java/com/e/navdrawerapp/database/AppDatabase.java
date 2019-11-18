package com.e.navdrawerapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {BeerEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "myDatabase.db").build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract BeerDao beerDao();
}
