package com.example.Restaurant.repositorys;

import com.example.Restaurant.entitys.PaymentForm;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentFormRepository {
    List<PaymentForm> all();
    PaymentForm perId(long id);
    PaymentForm add(PaymentForm paymentForm);
    void remove (PaymentForm paymentForm);
}
