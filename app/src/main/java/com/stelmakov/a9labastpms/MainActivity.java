package com.stelmakov.a9labastpms;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MyViewModel model;
    List<Album> MainAlbums;
    private ImageView albumImage;
    MyHandler myHandler;
    private Uri albumImageUri = Uri.parse("");
    EditText name;
    EditText artist;
    Spinner category;
    private Toast notification;
/*    ActivityMainBinding binding;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        model = new MyViewModel(getApplication());
        name = findViewById(R.id.name);
        artist = findViewById(R.id.artist);
        category = findViewById(R.id.category);
        albumImage = findViewById(R.id.albumImage);
       /* binding = DataBindingUtil.setContentView(this, R.layout.activity_main);*/
         myHandler = new MyHandler(resultAlbumImage);

     /*   binding.setHandler(myHandler);*/
        String[] albumCategories = {"Категория...", "Эмбиент", "Хардкор", "IDM", "Хтонический даб", "Деконструктивный шансон", "Дрон", "Авангард", "Экспериментальная", "Макабрическое техно"};
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, albumCategories);
        categoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(categoriesAdapter);

        /* model = ViewModelProviders.of(this).get(MyViewModel.class);*/
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final AlbumListAdapter adapter = new AlbumListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        model.getAll().observe(this, new Observer<List<Album>>() {
            @Override
            public void onChanged(@Nullable final List<Album> albums) {
                // Update the cached copy of the words in the adapter.
                adapter.setAlbums(albums);
                MainAlbums = albums;
            }
        });


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(MainActivity.this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Toast.makeText(MainActivity.this,"Для удаления зажмите альбом",Toast.LENGTH_LONG).show();
                    }

                    @Override public void onLongItemClick(View view, int position) {

                        model.delete(MainAlbums.get(position));
                        Toast.makeText(MainActivity.this,"Альбом удален!",Toast.LENGTH_LONG).show();
                    }
                })
        );
    }

    public  void LoadImage(View V){
        myHandler.addAlbumImage(V);

    }



    public void Insert(View v){
        String name = this.name.getText().toString();
        String category = this.category.getSelectedItem().toString();
        String albumImage = this.albumImageUri.toString();
        String artist = this.artist.getText().toString();
        Album album = new Album(name,category,albumImage,artist);

        try {
            model.insert(album);
            notification = Toast.makeText(this, "Добавлено успешно", Toast.LENGTH_LONG);

        } catch(Exception e ){
            notification = Toast.makeText(this, "Ошибка добавления", Toast.LENGTH_LONG);
        } finally{
            notification.show();
        }

    }


    private ActivityResultLauncher<Intent> resultAlbumImage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                albumImageUri = result.getData().getData();
                albumImage.setImageURI(albumImageUri);

            }
        }
    });



}