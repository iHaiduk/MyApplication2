package com.tested.model;

/**
 * Created by Ihor on 29.11.2014.
 */
public class TestModel {
    private long id;
    private String name;

    public TestModel(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
