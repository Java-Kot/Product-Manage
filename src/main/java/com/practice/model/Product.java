package com.practice.model;

public class Product {
    private int id;
    private String prodname;
    private double price;
    private String description;
    private String vendorx;

    public Product(int id, String prodname, double price, String description, String vendorx) {
        this.id = id;
        this.prodname = prodname;
        this.price = price;
        this.description = description;
        this.vendorx = vendorx;
    }

    public int getId() {
        return id;
    }

    public String getProdname() {
        return prodname;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getVendorx() {
        return vendorx;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVendorx(String vendorx) {
        this.vendorx = vendorx;
    }
}
