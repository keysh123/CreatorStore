package com.example.CreatorStore.controllers;

import com.example.CreatorStore.dto.OrderRequest;
import com.example.CreatorStore.entities.Order;
import com.example.CreatorStore.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody @Valid OrderRequest orderRequest){
        return orderService.createOrder(orderRequest);
    }
    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }
}
