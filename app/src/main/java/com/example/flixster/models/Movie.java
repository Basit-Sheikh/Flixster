package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {
    int movieID;
    String posterPath;
    String title;
    String overview;
    String backdropPath;
    double rating;

    public Movie(){} //empty contrsuctor needed by parceler library

    public Movie(JSONObject jsonObject) throws JSONException {

        movieID = jsonObject.getInt(("id"));
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        backdropPath = jsonObject.getString("backdrop_path");
        rating = jsonObject.getDouble("vote_average");
    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        ArrayList<Movie> movies = new ArrayList<>();
        for (int i = 0; i < movieJsonArray.length(); i++) {
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    public int getMovieID() {
        return movieID;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }
    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    public double getRating() {
        return rating;
    }
}


