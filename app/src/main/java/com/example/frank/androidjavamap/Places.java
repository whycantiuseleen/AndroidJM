package com.example.frank.androidjavamap;

/**
 * Created by Frank on 19/11/2017.
 */

public class Places {
    private String title, genre;

    public Places() {
    }

    public Places(String title, String genre) {
        this.title = title;
        this.genre = genre;
//        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

//    public String getYear() {
//        return year;
//    }
//
//    public void setYear(String year) {
//        this.year = year;
//    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
