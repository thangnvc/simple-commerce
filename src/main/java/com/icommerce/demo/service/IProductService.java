package com.icommerce.demo.service;

import com.icommerce.demo.service.dto.ProductDTO;
import com.icommerce.demo.service.query.ProductSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface IProductService {
    Page<ProductDTO> findAll(ProductSearchCriteria searchCriteria, Pageable pageable);
    Optional<ProductDTO> findById(Long id);
}
