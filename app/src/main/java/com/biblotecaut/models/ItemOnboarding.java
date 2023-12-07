package com.biblotecaut.models;

public class ItemOnboarding {
    private Integer image;
    private String description;

    public ItemOnboarding(Integer image, String description) {
        this.image = image;
        this.description = description;
    }

    public Integer getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }
}
