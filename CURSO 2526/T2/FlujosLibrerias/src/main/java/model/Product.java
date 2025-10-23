package model;// Welcome.java


import java.time.OffsetDateTime;

@lombok.Data
public class Product {
    private long id;
    private String title;
    private String description;
    private String category;
    private double price;
    private double discountPercentage;
    private double rating;
    private long stock;
    private String[] tags;
    private String brand;
    private String sku;
    private long weight;
    private Dimensions dimensions;
    private String warrantyInformation;
    private String shippingInformation;
    private String availabilityStatus;
    private Review[] reviews;
    private String returnPolicy;
    private long minimumOrderQuantity;
    private Meta meta;
    private String[] images;
    private String thumbnail;
}

// Dimensions.java


@lombok.Data
class Dimensions {
    private double width;
    private double height;
    private double depth;
}

// Meta.java


@lombok.Data
class Meta {
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private String barcode;
    private String qrCode;
}

// Review.java


@lombok.Data
class Review {
    private long rating;
    private String comment;
    private OffsetDateTime date;
    private String reviewerName;
    private String reviewerEmail;
}
