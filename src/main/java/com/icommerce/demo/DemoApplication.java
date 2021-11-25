package com.icommerce.demo;

import com.icommerce.demo.domain.Category;
import com.icommerce.demo.domain.Product;
import com.icommerce.demo.repository.CategoryRepository;
import com.icommerce.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication implements CommandLineRunner {

//	private final CategoryRepository categoryRepository;
//	private final ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//initialDummyData();
	}

//	private void initialDummyData() {
//		Category category = createCategory("Category 1");
//		category = categoryRepository.save(category);
//		productRepository.save(createProduct("Product 1", "Brand 1", "Colour 1", 10.0, category));
//		productRepository.save(createProduct("Product 2", "Brand 1", "Colour 2", 12.0, category));
//		productRepository.save(createProduct("Product 3", "Brand 1", "Colour 1", 13.0, category));
//	}

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
