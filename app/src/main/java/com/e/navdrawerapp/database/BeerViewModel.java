package com.e.navdrawerapp.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class BeerViewModel extends AndroidViewModel {

    public BeerViewModel(@NonNull Application application) {
        super(application);
    }

    private LiveData<List<BeerEntity>> beerList;

    public LiveData<List<BeerEntity>> getBeerList() {
        AppDatabase database = AppDatabase.getDatabase(getApplication());
        beerList = database.beerDao().getAll();
        return beerList;
    }

}
