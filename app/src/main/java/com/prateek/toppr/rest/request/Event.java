package com.prateek.toppr.rest.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prateek.kesarwani on 25/09/16.
 */

public class Event {

    private String id;
    private String name;

    @SerializedName("image")
    private String imagePath;

    private String category;

    private String description;

    private String experience;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
