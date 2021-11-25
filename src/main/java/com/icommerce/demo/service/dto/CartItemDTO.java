package com.icommerce.demo.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CartItemDTO implements Serializable {
    private Long id;
    private String name;
    private Double price;
    private String brand;
    private String colour;
    private Integer quantity;

    public CartItemDTO(ProductDTO product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.brand = product.getBrand();
        this.colour = product.getColour();
        this.quantity = 1;
    }
}
