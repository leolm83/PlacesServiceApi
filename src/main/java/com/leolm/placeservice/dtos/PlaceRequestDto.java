package com.leolm.placeservice.dtos;

import jakarta.validation.constraints.NotBlank;

public class PlaceRequestDto{

    @NotBlank
    private String name;
    @NotBlank
    private String state;

    private String slug;

    public PlaceRequestDto() {
    }

    public PlaceRequestDto(String name, String state) {
        this.name = name;
        this.state = state;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
