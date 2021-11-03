package com.stelmakov.a9labastpms;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AlbumRepository {

    private AlbumDao mAlbumDao;
    private LiveData<List<Album>> mAllAlbums;

    AlbumRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mAlbumDao = db.albumDao();
        mAllAlbums = mAlbumDao.getAll();
    }

    LiveData<List<Album>> getAll() {
        return mAllAlbums;
    }

    public void insert (Album album) {
        new insertAsyncTask(mAlbumDao).execute(album);
    }
    public void delete (Album album) {
        new deleteAsyncTask(mAlbumDao).execute(album);
    }

    private static class insertAsyncTask extends AsyncTask<Album, Void, Void> {

        private AlbumDao mAsyncTaskDao;

        insertAsyncTask(AlbumDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Album... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }


    private static class deleteAsyncTask extends AsyncTask<Album, Void, Void> {

        private AlbumDao mAsyncTaskDao;

        deleteAsyncTask(AlbumDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Album... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}