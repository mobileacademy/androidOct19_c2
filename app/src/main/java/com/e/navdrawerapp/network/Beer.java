package com.e.navdrawerapp.network;

import com.google.gson.annotations.SerializedName;

/**
 *  "id": 1,
 *         "name": "Buzz",
 *         "tagline": "A Real Bitter Experience.",
 *         "first_brewed": "09/2007",
 *         "description": "A light, crisp and bitter IPA brewed with English and American hops. A small batch brewed only once.",
 *         "image_url": "https://images.punkapi.com/v2/keg.png",
 */

// Using Gson lib to deserialize the JSON response need to use @SerializedName
public class Beer {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("tagline")
    private String tagline;
    @SerializedName("description")
    private String description;
    @SerializedName("image_url")
    private String imageUrl;

    public Beer(String name, String desc, String url) {
        this.name = name;
        description = desc;
        imageUrl = url;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTagline() {
        return tagline;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
