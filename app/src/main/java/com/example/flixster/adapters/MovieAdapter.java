package com.example.flixster.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.DetailActivity;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    //Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }
    // Involves populating data into teh item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get the movie passed into position
        Movie movie = movies.get(position);
        //bind movie data into VH
        holder.bind(movie);
    }
    // return total count of items in list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        RelativeLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(final Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageUrl;
            //if phone is in landscape, set imageUrl = backdrop image.
            //else imageUrl = poster image
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                imageUrl = movie.getBackdropPath();
            else
                imageUrl = movie.getPosterPath();
            Glide.with(context).load(imageUrl).into(ivPoster);
            //register click listener on whole container
            //when clicked, navigate to new activity w/ movie details
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, DetailActivity.class);
                    context.startActivity(i);
                }
            });
        }
    }
}
