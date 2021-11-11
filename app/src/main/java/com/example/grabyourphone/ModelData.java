package com.example.grabyourphone;

public class ModelData {
    private String movieName;
    private String movieDate;
    private String movieImage;

    public ModelData(String movieName, String movieDate, String movieImage) {
        this.movieName = movieName;
        this.movieDate = movieDate;
        this.movieImage = movieImage;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieDate() {
        return movieDate;
    }

    public void setMovieDate(String movieDate) {
        this.movieDate = movieDate;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }
}
