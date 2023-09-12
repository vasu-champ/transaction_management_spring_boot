package com.vasu.springboottransactiondemo.dto;

import com.vasu.springboottransactiondemo.entity.Order;
import com.vasu.springboottransactiondemo.entity.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private Order order;
    private Payment payment;
}