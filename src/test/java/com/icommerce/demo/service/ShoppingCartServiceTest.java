package com.icommerce.demo.service;

import com.icommerce.demo.service.dto.ProductDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class ShoppingCartServiceTest {
    private static final String CLIENT_ID = "AAAAAAA";
    @Autowired
    private ShoppingCartService shoppingCart;

    private ProductDTO product1;
    private ProductDTO product2;

    @BeforeEach
    private void init() {
        product1 = createProduct(1l, "Name 1", "Brand 1", "Colour 1", 1.0);
        product2 = createProduct(2l, "Name 2", "Brand 1", "Colour 1", 2.0);
        shoppingCart.clearAll();
    }

    @Test
    public void whenAddNewProduct_shouldSuccess() {
        // when
        shoppingCart.addProduct(CLIENT_ID, product1);
        // then
        assertThat(shoppingCart.getCartItems(CLIENT_ID).size()).isEqualTo(1);
        assertThat(shoppingCart.getCartItems(CLIENT_ID).stream().findFirst().get().getQuantity()).isEqualTo(1);
    }

    @Test
    public void whenAddAlreadyAddedProduct_shouldIncreaseQuantity() {
        // given
        shoppingCart.addProduct(CLIENT_ID, product1);
        // when
        shoppingCart.addProduct(CLIENT_ID, product1);
        // then
        assertThat(shoppingCart.getCartItems(CLIENT_ID).size()).isEqualTo(1);
        assertThat(shoppingCart.getCartItems(CLIENT_ID).stream()
                .findFirst()
                .get()
                .getQuantity()).isEqualTo(2);
    }

    @Test
    public void whenAddOtherProduct_shouldSuccess() {
        // given
        shoppingCart.addProduct(CLIENT_ID, product1);
        // when
        shoppingCart.addProduct(CLIENT_ID, product2);
        // then
        assertThat(shoppingCart.getCartItems(CLIENT_ID).size()).isEqualTo(2);
        assertThat(shoppingCart.getCartItems(CLIENT_ID).stream()
                .filter(e -> product2.getId().equals(e.getId()))
                .findFirst()
                .get()
                .getQuantity()).isEqualTo(1);
    }

    private ProductDTO createProduct(Long id, String name, String brand, String colour, Double price) {
        ProductDTO product = new ProductDTO();
        product.setId(id);
        product.setName(name);
        product.setBrand(brand);
        product.setColour(colour);
        product.setPrice(price);
        return product;
    }
}
