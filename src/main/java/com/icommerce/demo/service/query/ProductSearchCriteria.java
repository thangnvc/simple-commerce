package com.icommerce.demo.service.query;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class ProductSearchCriteria implements Serializable {
    private Set<Long> categoryIds;
    private Double priceFrom;
    private Double priceTo;
    private String brand;
    private String colour;
}
