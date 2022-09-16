package com.example.Restaurant.domain.Cruds.PaymentCrud;

import com.example.Restaurant.entitys.PaymentForm;
import com.example.Restaurant.RestaurantApplication;
import com.example.Restaurant.repositorys.PaymentFormRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class PaymentQueryMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(RestaurantApplication.class)
                .web(WebApplicationType.NONE).run(args);

        PaymentFormRepository paymentFormRepository= applicationContext.getBean(PaymentFormRepository.class);

        List<PaymentForm> allPayments = paymentFormRepository.all();
        for (PaymentForm paymentForm : allPayments){
            System.out.println(paymentForm.getToDescribe());
        }
    }
}
