package com.stelmakov.a9labastpms;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListAdapter.AlbumViewHolder> {

    private final LayoutInflater mInflater;
    private List<Album> albums; // Cached copy of words

    AlbumListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.albums_item, parent, false);
        return new AlbumViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        if (albums != null) {
            Album current = albums.get(position);
            holder.ItemViewName.setText(current.getName());
            holder.ItemViewArtist.setText(current.getArtist());
            holder.ItemViewImage.setImageURI(Uri.parse(current.getAlbumImage()));
            holder.ItemViewCategory.setText(current.getCategory());
        } else {
            // Covers the case of data not being ready yet.
            holder.ItemViewName.setText("not found");
            holder.ItemViewArtist.setText("not found");
            holder.ItemViewImage.setImageResource(R.drawable.basic);
            holder.ItemViewCategory.setText("not found");
        }
    }

    void setAlbums(List<Album> outAlb){
        albums = outAlb;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (albums != null)
            return albums.size();
        else return 0;
    }

    class AlbumViewHolder extends RecyclerView.ViewHolder {
        private final TextView ItemViewName;
        private final TextView ItemViewArtist;
        private final ImageView ItemViewImage;
        private final TextView ItemViewCategory;

        private AlbumViewHolder(View itemView) {
            super(itemView);
            ItemViewName = itemView.findViewById(R.id.itemName);
            ItemViewImage = itemView.findViewById(R.id.itemImage);
           ItemViewArtist = itemView.findViewById(R.id.itemArtist);
           ItemViewCategory = itemView.findViewById(R.id.itemCategory);
        }
    }
}