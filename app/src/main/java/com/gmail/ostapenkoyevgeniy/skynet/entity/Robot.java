package com.gmail.ostapenkoyevgeniy.skynet.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Robot {
    @JsonProperty("id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    @JsonProperty("year")
    private int year;

    public Robot() {
    }

    public Robot(int id, String name, String type, int year) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Robot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
