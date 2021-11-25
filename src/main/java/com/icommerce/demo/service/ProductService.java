package com.icommerce.demo.service;

import com.icommerce.demo.repository.ProductRepository;
import com.icommerce.demo.repository.specifications.ProductSpecs;
import com.icommerce.demo.service.dto.ProductDTO;
import com.icommerce.demo.service.mapper.ProductMapper;
import com.icommerce.demo.service.query.ProductSearchCriteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(ProductSearchCriteria searchCriteria, Pageable pageable) {
        log.debug("Request to get all product with criteria {}", searchCriteria);
        return productRepository.findAll(ProductSpecs.getByCriteriaSpecs(searchCriteria), pageable)
            .map(productMapper::toDto);
    }

    @Override
    public Optional<ProductDTO> findById(Long id) {
        log.debug("Request to get product with id {}", id);
        return productRepository.findById(id)
                .map(productMapper::toDto);
    }
}
