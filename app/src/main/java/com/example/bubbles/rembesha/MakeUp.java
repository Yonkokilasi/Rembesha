package com.example.bubbles.rembesha;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bubbles on 6/20/17.
 */
@Parcel
public class MakeUp {
     String name;
     double price;
    String imageUrl;
   String websiteLink;
    String brand;
    double rating;
     List<String> colors = new ArrayList<>();
     String description;
   String productType;
    String category;

    public MakeUp(String name, double price, String imageUrl, String websiteLink, String brand, double rating, ArrayList<String> colors, String description, String productType,String category) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.websiteLink = websiteLink;
        this.brand = brand;
        this.rating = rating;
        this.colors = colors;
        this.description = description;
        this.productType = productType;
        this.category = category;
    }

    public  MakeUp() {

    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getWebsiteLink() {
        return websiteLink;
    }

    public String getBrand() {
        return brand;
    }

    public double getRating() {
        return rating;
    }

    public List<String> getColors() {
        return colors;
    }

    public String getDescription() {
        return description;
    }

    public String getProductType() {
        return productType;
    }
    public String getCategory() {
        return category;
    }
}
