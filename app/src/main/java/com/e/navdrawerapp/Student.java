package com.e.navdrawerapp;

public class Student {

    private String name;
    private String univ;
    private String picture; // URL

    public Student(String name, String univ, String picture) {
        this.name = name;
        this.univ = univ;
        this.picture = picture;
    }

    public Student(String name, String univ) {
        this.name = name;
        this.univ = univ;
    }

    public String getName() {
        return name;
    }

    public String getUniv() {
        return univ;
    }

}