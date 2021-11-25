package com.icommerce.demo.service;

import com.icommerce.demo.service.dto.CartItemDTO;
import com.icommerce.demo.service.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShoppingCartService implements IShoppingCartService {
    private Map<String, Set<CartItemDTO>> carts = new HashMap<>();

    @Override
    public void addProduct(String clientId, ProductDTO product) {
        if (carts.containsKey(clientId)) {
            if (this.hasProductExisted(clientId, product.getId())) {
                carts.get(clientId).forEach(e -> {
                    if (product.getId().equals(e.getId())) {
                        e.setQuantity(e.getQuantity() + 1);
                    }
                });
            } else {
                carts.get(clientId).add(new CartItemDTO(product));
            }
        } else {
            Set<CartItemDTO> cartItems = new HashSet<>();
            cartItems.add(new CartItemDTO(product));
            carts.put(clientId, cartItems);
        }
    }

    @Override
    public Set<CartItemDTO> getCartItems(String clientId) {
        if (carts.containsKey(clientId)) {
            return Collections.unmodifiableSet(carts.get(clientId));
        }
        return Collections.emptySet();
    }

    @Override
    public void clearAll() {
        this.carts.clear();
    }

    private boolean hasProductExisted(String clientId, Long productId) {
        return carts.get(clientId).stream()
                .filter(e -> e.getId().equals(productId))
                .findFirst()
                .isPresent();
    }
}
