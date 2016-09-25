package com.prateek.toppr.rest;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.Html;
import android.text.Spanned;

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
    private boolean isFavourite;

    public Event() {
    }

    public Event(String id) {
        this.id = id;
    }

    protected Event(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.imagePath = in.readString();
        this.category = in.readString();
        this.description = in.readString();
        this.experience = in.readString();
        this.isFavourite = in.readByte() != 0;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getExperience() {
        return experience;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public Spanned getSharableText() {

        return Html.fromHtml("<p>" + "<i>Event Name: </i>" + name +
                "                <br><br><i>Category: </i>" + category +
                "                <br><br><i>Description: </i>" + description +
                "                <br><br><i>Experience: </i>" + experience +
                "                <br><br><b> REGISTER SOON </b>" + "</p>");
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
        dest.writeByte(this.isFavourite ? (byte) 1 : (byte) 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        return id != null ? id.equals(event.id) : event.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
