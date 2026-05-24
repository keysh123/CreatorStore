package com.example.CreatorStore.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
    @NotBlank(message = "name is required")
    private String customerName;
    @NotBlank(message = "email is required")
    @Email(message = "Enter valid email")
    private String customerEmail;
    @Valid
    @NotEmpty(message = "order must contain atleast 1 item")
    private List<OrderItemRequest> items;
}
