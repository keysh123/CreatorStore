package com.example.CreatorStore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name is required")
    @Column(nullable = false)
    private String name;

    private String description;

    private String category;

    @NotBlank(message = "price is required")
    @DecimalMin(value="0.0",inclusive = false,message = "Price must be greater than 0")
    @Column(nullable = false)
    private BigDecimal price;

    @NotBlank(message = "stock quantity is required")
    @Min(value = 0,message = "Stock cannot be <=0 ")
    @Column(name="stock_quantity",nullable = false)
    private Integer stockQuantity;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;
}
