package com.example.mental_health_programs_collaborative_projects.health_resource_fragment;

public class Titleinfo {
    private String title;
    private String py_title;

    public Titleinfo(String title, String py_title) {
        this.title = title;
        this.py_title = py_title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPy_title() {
        return py_title;
    }

    public void setPy_title(String py_title) {
        this.py_title = py_title;
    }
}
