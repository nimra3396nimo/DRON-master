package com.example.nimra.dron;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Maham on 7/8/2018.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Upload> mUploads;

    public ImageAdapter(Context context, List<Upload> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Upload uploadCurrent = mUploads.get(position);
        holder.textViewdate.setText(uploadCurrent.getDate());
        holder.textViewtime.setText(uploadCurrent.getTime());
        holder.textViewlongitude.setText(uploadCurrent.getLongitude());
        holder.textViewlatitude.setText(uploadCurrent.getLatitude());
        Picasso.with(mContext)
                .load(uploadCurrent.getmImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewdate;
        public TextView textViewtime;
        public TextView textViewlongitude;
        public TextView textViewlatitude;
        public ImageView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);

            textViewdate = itemView.findViewById(R.id.text_view_name);
            textViewtime = itemView.findViewById(R.id.timetext);
            textViewlongitude = itemView.findViewById(R.id.longitudetext);
            textViewlatitude = itemView.findViewById(R.id.latitudetext);
            imageView = itemView.findViewById(R.id.image_view_upload);
        }
    }
}

