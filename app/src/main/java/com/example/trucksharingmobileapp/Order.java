package com.example.trucksharingmobileapp;

public class Order {
    private String type;
    private String senderName;
    private String receiverName;
    private String pickupTime;
    private String dropoffTime;
    private String weight;
    private String height;
    private String width;
    private String length;
    private String quantity;
    private int orderid;


    private int image;



    public Order(String senderName, String receiverName, String pickupTime, String dropoffTime, String weight, String height, String width, String length, String quantity, String type) {
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.pickupTime = pickupTime;
        this.dropoffTime = dropoffTime;
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.length = length;
        this.quantity = quantity;
        this.type = type;

    }
    public Order(String receiverName, String dropoffTime) {
        this.receiverName = receiverName;
        this.dropoffTime = dropoffTime;
    }

    public Order(String receiverName, String dropoffTime, int image) {
        this.receiverName = receiverName;
        this.dropoffTime = dropoffTime;
        this.image = image;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getDropoffTime() {
        return dropoffTime;
    }

    public void setDropoffTime(String dropoffTime) {
        this.dropoffTime = dropoffTime;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getOrderid() {
        return orderid;
    }
}
