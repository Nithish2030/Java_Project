package Project;

import java.io.Serializable;

public class Movie implements Serializable {
    private String title;
    private String director;
    private int year;
    private String category;
    private String comment;

    public Movie(String title, String director, int year, String category, String comment) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.category = category;
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return title + " (" + year + ")";
    }
}

