package com.example.app_mymanager;

public class Model {

    private String category,task,id;

    public Model() {
    }

    public Model(String category, String task, String id) {
        this.category = category;
        this.task = task;
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
