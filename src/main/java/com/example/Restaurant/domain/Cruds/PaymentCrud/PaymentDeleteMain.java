package com.example.Restaurant.domain.Cruds.PaymentCrud;

import com.example.Restaurant.Entitys.PaymentForm;
import com.example.Restaurant.RestaurantApplication;
import com.example.Restaurant.repositorys.PaymentFormRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class PaymentDeleteMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(RestaurantApplication.class)
                .web(WebApplicationType.NONE).run(args);
        PaymentFormRepository paymentFormRepository = applicationContext.getBean(PaymentFormRepository.class);

        PaymentForm paymentForm = new PaymentForm();
        paymentForm.setId(4L);
        paymentFormRepository.remove(paymentForm);
    }
}
