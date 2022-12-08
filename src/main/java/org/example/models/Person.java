package org.example.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Mikhail Suslov
 */
public class Person {

    private int id;
    @NotEmpty(message = "Name must not be empty")
    @Size(min = 3, max = 100, message = "Name's length must be between 3 and 100 characters")
    private String name;
    @NotEmpty(message = "Year of birth must not be empty")
    @Min(value = 1900, message = "Year of birth must be more then 1900")
    private int year;

    public Person(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public Person() {

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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
