package com.stelmakov.a9labastpms;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class Album implements Serializable {
    @PrimaryKey
    @NonNull
    private String name;
    private String category;
    private String albumImage;
    private String artist;



    public Album(String name, String category,String albumImage, String artist) {
        this.name = name;
        this.category = category;
        this.albumImage = albumImage;
        this.artist = artist;

    }

    public String getName() {return name;}
    public String getCategory() {return category;}
    public String getAlbumImage() {return albumImage;}
    public String getArtist() {return artist;}

    public void setName(String name) {this.name = name;}
    public void setCategory(String category) {this.category = category;}
    public void setAlbumImage(String albumImage) {this.albumImage = albumImage;}
    public void setArtist(String artist) {this.artist = artist;}


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Album album = (Album) o;
        return this.name.equals(album.name) &&
                this.albumImage.equals(album.albumImage) &&
                this.category.equals(album.category) &&
                this.artist.equals(album.artist);

    }
}
