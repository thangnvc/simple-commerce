package com.icommerce.demo.service;

import com.icommerce.demo.domain.Category;
import com.icommerce.demo.domain.Product;
import com.icommerce.demo.repository.CategoryRepository;
import com.icommerce.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DummyDataService {

    private final CategoryRepository categoryRepository;
	private final ProductRepository productRepository;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        initialDummyData();
    }

    private void initialDummyData() {
		Category category = createCategory("Category 1");
		category = categoryRepository.save(category);
		productRepository.save(createProduct("Product 1", "Brand 1", "Colour 1", 10.0, category));
		productRepository.save(createProduct("Product 2", "Brand 1", "Colour 2", 12.0, category));
		productRepository.save(createProduct("Product 3", "Brand 1", "Colour 1", 13.0, category));
	}

    private Product createProduct(String name, String brand, String colour, Double price, Category category) {
        Product product = new Product();
        product.setName(name);
        product.setBrand(brand);
        product.setColour(colour);
        product.setPrice(price);
        product.setCategory(category);
        return product;
    }

    private Category createCategory(String name) {
        Category category = new Category();
        category.setName(name);
        return category;
    }
}
