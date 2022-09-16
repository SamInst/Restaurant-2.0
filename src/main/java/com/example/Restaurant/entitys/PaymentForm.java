package com.example.Restaurant.entitys;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_payment_form")
public class PaymentForm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "description")
    private String description;
    public String getToDescribe() {
        return description;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentForm that = (PaymentForm) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
