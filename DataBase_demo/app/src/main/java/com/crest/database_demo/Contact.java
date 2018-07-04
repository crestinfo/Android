package com.crest.database_demo;

public class Contact {

    //private variables
    int id;
    String name, tech, gender;


    // Empty constructor
    public Contact() {

    }

    public Contact(int id, String name, String tech, String gender) {
        this.id = id;
        this.name = name;
        this.tech = tech;
        this.gender = gender;
    }

    public Contact(String name, String tech, String gender) {
        this.name = name;
        this.tech = tech;
        this.gender = gender;
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

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
