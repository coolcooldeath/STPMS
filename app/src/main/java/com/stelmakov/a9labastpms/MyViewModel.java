package com.stelmakov.a9labastpms;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import java.util.List;

import javax.security.auth.callback.Callback;

public class MyViewModel extends ViewModel {

    private AlbumRepository mRepository;

    private LiveData<List<Album>> mAllAlbums;

    public MyViewModel (Application app) {
        mRepository = new AlbumRepository(app);
        mAllAlbums = mRepository.getAll();
    }

    LiveData<List<Album>> getAll() { return mAllAlbums; }

    public void insert(Album album) { mRepository.insert(album); }
    public void delete(Album album) { mRepository.delete(album); }

}
