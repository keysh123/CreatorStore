package com.example.CreatorStore.services;

import com.example.CreatorStore.dto.OrderItemRequest;
import com.example.CreatorStore.dto.OrderRequest;
import com.example.CreatorStore.entities.Order;
import com.example.CreatorStore.entities.OrderItem;
import com.example.CreatorStore.entities.Product;
import com.example.CreatorStore.repositories.OrderItemRepository;
import com.example.CreatorStore.repositories.OrderRepository;
import com.example.CreatorStore.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService  {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    @Transactional
    public Order createOrder(OrderRequest orderRequest){
        BigDecimal totalPrice = BigDecimal.ZERO;
        Order order = new Order();
        order.setCustomerName(orderRequest.getCustomerName());
        order.setCustomerEmail(orderRequest.getCustomerEmail());
        order.setStatus("CONFIRMED");
        List<OrderItem> orderItems = new ArrayList<>();
        for(OrderItemRequest itemRequest : orderRequest.getItems()){
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(()->new RuntimeException("product not found"));
            if(product.getStockQuantity()<itemRequest.getQuantity()){
                throw new RuntimeException("Not enough stock");
            }
            BigDecimal priceOfItem=product.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity()));
            totalPrice = totalPrice.add(priceOfItem);

            product.setStockQuantity(product.getStockQuantity()- itemRequest.getQuantity());
            productRepository.save(product);

            OrderItem orderItem=OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(itemRequest.getQuantity())
                    .priceAtPurchase(product.getPrice())
                    .build();
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        order.setTotalPrice(totalPrice);
//        orderRepository.save(order);
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

}
