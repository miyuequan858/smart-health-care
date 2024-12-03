// Hospital.java
package com.example.travelassistant.model;

import java.io.Serializable;

public class Hospital implements Serializable {
    private String name;
    private String distance;
    private String attributes;
    private String location;
    private String contact;
    private String imageUrl; // 医院图片 URL

    public Hospital(String name, String distance, String attributes, String location, String contact, String imageUrl) {
        this.name = name;
        this.distance = distance;
        this.attributes = attributes;
        this.location = location;
        this.contact = contact;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}