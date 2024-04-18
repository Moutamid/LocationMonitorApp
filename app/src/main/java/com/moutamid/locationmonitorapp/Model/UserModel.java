package com.moutamid.locationmonitorapp.Model;

public class UserModel {
    public String name, email, phone_number, id;
    public String key;
    public Object lat;
    public Object lng;

    public UserModel() {
    }

    public UserModel(String key, Object lat, Object lng,  String name) {
        this.key = key;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public UserModel(Object lat, Object lng, String image_url, String name) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getLat() {
        return lat;
    }

    public void setLat(Object lat) {
        this.lat = lat;
    }

    public Object getLng() {
        return lng;
    }

    public void setLng(Object lng) {
        this.lng = lng;
    }
}
