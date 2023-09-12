package com.vasu.springboottransactiondemo.service;

import com.vasu.springboottransactiondemo.dto.OrderRequest;
import com.vasu.springboottransactiondemo.dto.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);
}
