package com.distance.model;

public class ZipCode {
    private String zipcode;
    private double latitude;
    private double longitude;

    public ZipCode(String zipcode, double latitude, double longitude) {
        this.zipcode = zipcode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getZipcode() {
        return zipcode;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}