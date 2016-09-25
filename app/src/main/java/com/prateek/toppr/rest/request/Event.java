package com.prateek.toppr.rest.request;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prateek.kesarwani on 25/09/16.
 */

public class Event implements Parcelable {

    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel source) {
            return new Event(source);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
    private String id;
    private String name;
    @SerializedName("image")
    private String imagePath;
    private String category;
    private String description;
    private String experience;

    public Event() {
    }

    protected Event(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.imagePath = in.readString();
        this.category = in.readString();
        this.description = in.readString();
        this.experience = in.readString();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.imagePath);
        dest.writeString(this.category);
        dest.writeString(this.description);
        dest.writeString(this.experience);
    }
}
