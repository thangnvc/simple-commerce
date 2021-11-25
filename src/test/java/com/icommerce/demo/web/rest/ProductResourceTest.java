package com.icommerce.demo.web.rest;

import com.icommerce.demo.service.ProductService;
import com.icommerce.demo.service.dto.CategoryDTO;
import com.icommerce.demo.service.dto.ProductDTO;
import com.icommerce.demo.service.query.ProductSearchCriteria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = ProductResource.class)
public class ProductResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void whenGetAllProduct_shouldReturnList() throws Exception {
        // given
        CategoryDTO category = createCategory(1l, "Category 1");
        List<ProductDTO> mockProducts = new ArrayList<>();
        for (int i = 2; i < 5; i++) {
            mockProducts.add(createProduct((long) i, "Product " + i, "Brand 1", "Colour 1", 10.0 * i, category));
        }
        Page<ProductDTO> pagedResponse = new PageImpl(mockProducts);
        when(productService.findAll(any(ProductSearchCriteria.class), any(Pageable.class))).thenReturn(pagedResponse);

        // when
        this.mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[*].name").value(hasItem("Product 2")));
    }

    @Test
    public void whenGetAllProduct_shouldReturnEmpty() throws Exception {
        // given
        Page<ProductDTO> pagedResponse = new PageImpl(new ArrayList<>());
        when(productService.findAll(any(ProductSearchCriteria.class), any(Pageable.class))).thenReturn(pagedResponse);

        // when
        this.mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").value(hasSize(0)));
    }

    private ProductDTO createProduct(Long id, String name, String brand, String colour, Double price, CategoryDTO category) {
        ProductDTO product = new ProductDTO();
        product.setId(id);
        product.setName(name);
        product.setBrand(brand);
        product.setColour(colour);
        product.setPrice(price);
        product.setCategory(category);
        return product;
    }

    private CategoryDTO createCategory(Long id, String name) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(id);
        categoryDTO.setName(name);
        return categoryDTO;
    }
}
