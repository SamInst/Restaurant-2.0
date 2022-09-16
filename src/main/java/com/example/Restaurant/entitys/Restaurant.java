package com.example.Restaurant.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String name;

    @Column(name="Delivery_Fee",nullable = false)
    private BigDecimal deliveryFee;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime dateRegistration;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime dateUpdate;



    @ManyToOne
//     @JoinColumn(name = "kitchen_code", nullable = false)
    private Kitchen kitchen;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "restaurant_payment_form",
            joinColumns = @JoinColumn(name = "restaurant"),
            inverseJoinColumns = @JoinColumn(name = "payment_form"))
    private List<PaymentForm> paymentForms = new ArrayList<>();

    @Embedded
    private Address address;


    public Kitchen getKitchen() {
        return kitchen;
    }

    public void setKitchen(Kitchen kitchen) {
        this.kitchen = kitchen;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Restaurant that)) return false;
        return id == that.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
