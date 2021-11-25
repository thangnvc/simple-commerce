package com.icommerce.demo.web.rest;

import com.icommerce.demo.service.IProductService;
import com.icommerce.demo.service.dto.ProductDTO;
import com.icommerce.demo.service.query.ProductSearchCriteria;
import com.icommerce.demo.web.rest.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class ProductResource {

    private final IProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts(ProductSearchCriteria searchCriteria, Pageable pageable) {
        log.debug("REST request to get a page of product");
        Page<ProductDTO> page = productService.findAll(searchCriteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/products");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
