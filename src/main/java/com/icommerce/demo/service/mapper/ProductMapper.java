package com.icommerce.demo.service.mapper;

import com.icommerce.demo.domain.Product;
import com.icommerce.demo.domain.Product;
import com.icommerce.demo.service.dto.ProductDTO;
import com.icommerce.demo.service.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { CategoryMapper.class })
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {
    @Mapping(source = "category", target = "category")
    ProductDTO toDto(Product product);

    @Mapping(target = "category", ignore = true)
    Product toEntity(ProductDTO productDTO);

    default Product fromId(Long id) {
        if (id == null) {
            return null;
        }
        Product product = new Product();
        product.setId(id);
        return product;
    }
}

