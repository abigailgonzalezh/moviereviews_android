package com.example.moviereviews;

public class Movie {
    private int id;
    private String name;
    private int review;

    public Movie() {
        this.setId(0);
        this.setName("");
        this.setReview(0);
    }

    public Movie(int id, String name, int review) {
        this.setId(id);
        this.setName(name);
        this.setReview(review);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }
}
