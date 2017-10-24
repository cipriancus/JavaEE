package com.laborator.model;

import java.util.UUID;

public class Record {
    private String id;
    private String name;
    private String age;
    private Category category;//breed

    public Record(String name, String age, Category category){
        this.id =  UUID.randomUUID().toString();
        this.name=name;
        this.age=age;
        this.category=category;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }
}
