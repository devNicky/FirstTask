package com.example.firstyalantistask;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
        Context context;
        IToaster toaster;
        private List<String> imagesLinks;

    public RecyclerViewAdapter(List<String> imagesLinks, IToaster toaster) {
        this.imagesLinks = imagesLinks;
        this.toaster=toaster;
    }

        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            public ImageView image;

            public MyViewHolder(View view) {
                super(view);
                image = (ImageView) view.findViewById(R.id.imageView);
                image.setOnClickListener(this);
            }
            //calls interface method for make toast
            @Override
            public void onClick(View v) {
                toaster.toast(v);
            }
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.image_layout, parent, false);
            context = parent.getContext();
            return new MyViewHolder(itemView);
        }
        //get display size, load with Picasso loader link and resize image to 1/2 of screen size
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            DisplayMetrics display = context.getResources().getDisplayMetrics();
            int width = display.widthPixels/2;
            String link = imagesLinks.get(position);
            ImageView image = holder.image;
            Picasso.with(context).load(link).resize(width,width).into(image);

        }

        @Override
        public int getItemCount() {
            return imagesLinks.size();
        }
    }