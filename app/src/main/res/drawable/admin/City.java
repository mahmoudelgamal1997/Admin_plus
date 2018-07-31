package com.example2017.android.admin;

/**
 * Created by M7moud on 27-Nov-17.
 */
public class City {
    private String title;
    private String img;



    public City() {
    }

    public City(String title,  String img) {
        this.title = title;
        this.img = img;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

