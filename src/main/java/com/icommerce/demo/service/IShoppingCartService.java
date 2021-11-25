package com.icommerce.demo.service;

import com.icommerce.demo.service.dto.CartItemDTO;
import com.icommerce.demo.service.dto.ProductDTO;

import java.util.Set;

public interface IShoppingCartService {
    void addProduct(String clientId, ProductDTO product);
    Set<CartItemDTO> getCartItems(String clientId);
    void clearAll();
}
