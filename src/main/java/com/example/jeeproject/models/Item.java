package com.example.jeeproject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Item {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String type;
    private String category;
    private LocalDate purchaseDate;
    private LocalDate deliveryDate;

    public Item() {
    }

    public Item(String name, String type, String category, LocalDate purchaseDate, LocalDate deliveryDate) {
        this.name = name;
        this.type = type;
        this.category = category;
        this.purchaseDate = purchaseDate;
        this.deliveryDate = deliveryDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
