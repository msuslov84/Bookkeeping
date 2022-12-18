package org.example.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * @author Mikhail Suslov
 */
public class Book {

    private int id;
    @NotEmpty(message = "Title must not be empty")
    private String title;
    @NotEmpty(message = "Author must not be empty")
    private String author;
    @NotEmpty(message = "Year of publishing must not be empty")
    @Min(value = 1500, message = "Year of publishing must be more then 1500")
    private int year;

    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
