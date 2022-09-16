package com.example.Restaurant.entitys;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private BigDecimal price;
    @Column
    private Boolean active;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;


    public void setId(Long id) {this.id = id;}
    public Long getId() {return id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public BigDecimal getPrice() {return price;}
    public void setPrice(BigDecimal price) {this.price = price;}
    public Boolean getActive() {return active;}
    public void setActive(Boolean active) {this.active = active;}
    public Restaurant getRestaurant() {return restaurant;}
    public void setRestaurant(Restaurant restaurant) {this.restaurant = restaurant;}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }
    @Override
    public int hashCode() {return Objects.hash(id);}
}
