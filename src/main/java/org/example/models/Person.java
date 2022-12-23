package org.example.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Mikhail Suslov
 */
public class Person {

    private int id;
    @NotEmpty(message = "ФИО не должно быть пустым")
    @Size(min = 3, max = 100, message = "Длина ФИО должна быть в пределах 3-100 символов")
    private String name;
    @Min(value = 1900, message = "Год рождения должен быть более 1900")
    private int year;

    public Person(String name, int year) {
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
