package com.example.navdrawertest.ui.gallery;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.navdrawertest.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private Activity hostActivity;
    private List<String> imageUrlList;

    DataAdapter(Activity activity, List<String> UrlList) {
        hostActivity = activity;
        imageUrlList = UrlList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        /*
        InputStream fis = null;
        try {
            fis = hostActivity.getContentResolver()
                    .openInputStream(Uri.parse(imageUrlList.get(position)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap mBitmap = BitmapFactory.decodeStream(fis, null, null);
        holder.mImageView.setImageBitmap(mBitmap);
         */
        Glide.with(hostActivity)
                .asBitmap()
                .load(imageUrlList.get(position))
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return imageUrlList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private ImageView mImageView;
        final DataAdapter mDataAdapter;

        ViewHolder(@NonNull View itemView, DataAdapter adapter) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.itemImageView);
            mDataAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }
    }

}
