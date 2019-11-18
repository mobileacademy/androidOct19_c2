package com.e.navdrawerapp.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class BeerEntity {
    @PrimaryKey
    @NotNull
    public Integer id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "desc")
    public String desc;
    @ColumnInfo(name = "imgUrl")
    public String imgUrl;

    public BeerEntity(Integer id, String name, String desc, String imgUrl) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.imgUrl = imgUrl;
    }
}
