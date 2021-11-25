package com.icommerce.demo.service.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import java.io.Serializable;

@Data
public class CategoryDTO implements Serializable {
    private Long id;
    @Max(value = 255)
    private String name;
}
