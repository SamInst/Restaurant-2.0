package com.example.Restaurant.infrastructure;

import com.example.Restaurant.entitys.PaymentForm;
import com.example.Restaurant.repositorys.PaymentFormRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Component
public class PaymentFormImplementation implements PaymentFormRepository {
@PersistenceContext
private EntityManager manager2;

    @Override
    public List<PaymentForm> all() {
        return manager2.createQuery("from PaymentForm", PaymentForm.class).getResultList();}
    @Override
    public PaymentForm perId(long id) {return manager2.find(PaymentForm.class, id);}
    @Transactional
    @Override
    public PaymentForm add(PaymentForm paymentForm) {return manager2.merge(paymentForm);}
   @Transactional
    @Override
    public void remove(PaymentForm paymentForm) {
        paymentForm = perId(paymentForm.getId());
        manager2.remove(paymentForm);
    }
}
