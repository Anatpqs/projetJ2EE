package com.mycompany.Entity;

import com.mycompany.Entity.Categories;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produit_id")
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categorie_id", nullable = false)
    private Categories categories;
    private double price;
    private double weight;
    private String description;


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Categories getCategories() {
        return categories;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    public String getDescription() {
        return description;
    }

    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems;

}
