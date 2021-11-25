package com.icommerce.demo.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductDTO implements Serializable {
    private Long id;
    private String name;
    private Double price;
    private String brand;
    private String colour;
    private CategoryDTO category;

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        ProductDTO product = (ProductDTO) other;
        return id.equals(product.id);
    }
}
