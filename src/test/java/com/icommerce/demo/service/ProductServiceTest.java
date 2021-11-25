package com.icommerce.demo.service;

import com.icommerce.demo.domain.Category;
import com.icommerce.demo.domain.Product;
import com.icommerce.demo.repository.ProductRepository;
import com.icommerce.demo.service.dto.ProductDTO;
import com.icommerce.demo.service.mapper.ProductMapper;
import com.icommerce.demo.service.query.ProductSearchCriteria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @Test
    public void whenGetAll_shouldReturnList() {
        // given
        Category category = createCategory(1l, "Category 1");
        List<Product> mockProducts = new ArrayList<>();
        for (int i = 2; i < 5; i++) {
            mockProducts.add(createProduct((long) i, "Product " + i, "Brand 1", "Colour 1", 10.0 * i, category));
        }
        Page<Product> pagedResponse = new PageImpl(mockProducts);
        ProductSearchCriteria searchCriteria = new ProductSearchCriteria();
        when(productRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(pagedResponse);

        // when
        Page<ProductDTO> actualProducts = productService.findAll(searchCriteria, Pageable.unpaged());

        // then
        assertThat(actualProducts.getContent().size()).isEqualTo(mockProducts.size());
        verify(productRepository).findAll(any(Specification.class), any(Pageable.class));
    }

    private Product createProduct(Long id, String name, String brand, String colour, Double price, Category category) {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setBrand(brand);
        product.setColour(colour);
        product.setPrice(price);
        product.setCategory(category);
        return product;
    }

    private Category createCategory(Long id, String name) {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        return category;
    }
}
