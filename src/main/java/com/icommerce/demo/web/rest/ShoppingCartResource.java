package com.icommerce.demo.web.rest;

import com.icommerce.demo.service.IProductService;
import com.icommerce.demo.service.IShoppingCartService;
import com.icommerce.demo.service.dto.CartItemDTO;
import com.icommerce.demo.service.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Set;

@RequestMapping("/api")
@Slf4j
@RestController
@RequiredArgsConstructor
public class ShoppingCartResource {

    private final IShoppingCartService shoppingCartService;
    private final IProductService productService;

    @PostMapping("/carts")
    public ResponseEntity<Set<CartItemDTO>> addProductToCart(@RequestHeader("x-client-id") String clientId,
                                                             @RequestBody Long productId) {
        ProductDTO product = productService.findById(productId)
                .orElseThrow(NoSuchElementException::new);
        shoppingCartService.addProduct(clientId, product);
        return ResponseEntity.ok(shoppingCartService.getCartItems(clientId));
    }
}
