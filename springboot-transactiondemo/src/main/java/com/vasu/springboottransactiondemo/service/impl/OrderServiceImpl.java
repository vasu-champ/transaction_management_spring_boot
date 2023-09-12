package com.vasu.springboottransactiondemo.service.impl;

import com.vasu.springboottransactiondemo.dto.OrderRequest;
import com.vasu.springboottransactiondemo.dto.OrderResponse;
import com.vasu.springboottransactiondemo.entity.Order;
import com.vasu.springboottransactiondemo.entity.Payment;
import com.vasu.springboottransactiondemo.exception.PaymentException;
import com.vasu.springboottransactiondemo.repository.OrderRepository;
import com.vasu.springboottransactiondemo.repository.PaymentRepository;
import com.vasu.springboottransactiondemo.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;

    public OrderServiceImpl(OrderRepository orderRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest) {

        Order order = orderRequest.getOrder();
        order.setStatus("INPROGRESS");
        order.setOrderTackingNumber(UUID.randomUUID().toString());
        orderRepository.save(order);

        Payment payment = orderRequest.getPayment();

        if(!payment.getType().equals("DEBIT")){
            throw new PaymentException("Payment card type do not support");
        }

        payment.setOrderId(order.getId());
        paymentRepository.save(payment);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderTackingNumber(order.getOrderTackingNumber());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setMessage("SUCCESS");
        return orderResponse;
    }


}