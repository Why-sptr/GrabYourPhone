package com.example.grabyourphone;

public class ModelData {
    private String phoneName;
    private String brand;
    private String phoneImage;
    private String specification;

    public ModelData(String phoneName, String brand, String phoneImage, String specification) {
        this.phoneName = phoneName;
        this.brand = brand;
        this.phoneImage = phoneImage;
        this.specification = specification;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPhoneImage() {
        return phoneImage;
    }

    public void setPhoneImage(String phoneImage) {
        this.phoneImage = phoneImage;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }
}