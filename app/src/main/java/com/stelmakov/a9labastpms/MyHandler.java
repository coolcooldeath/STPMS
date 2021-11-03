package com.stelmakov.a9labastpms;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

public class MyHandler {
    public ActivityResultLauncher<Intent> resultAlbumImage;
    public  MyHandler(ActivityResultLauncher<Intent> resultAlbumImage){
        this.resultAlbumImage = resultAlbumImage;
    }


    public void addAlbumImage(View v) {
        Intent imagePickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        imagePickerIntent.setType("image/*");
        resultAlbumImage.launch(imagePickerIntent);
    }


}