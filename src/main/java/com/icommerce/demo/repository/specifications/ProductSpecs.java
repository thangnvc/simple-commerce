package com.icommerce.demo.repository.specifications;

import com.icommerce.demo.domain.Category;
import com.icommerce.demo.domain.Category_;
import com.icommerce.demo.domain.Product;
import com.icommerce.demo.domain.Product_;
import com.icommerce.demo.service.query.ProductSearchCriteria;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class ProductSpecs {

    public static Specification<Product> getByCriteriaSpecs(ProductSearchCriteria searchCriteria) {
        return (Specification<Product>) (root, query, criteriaBuilder) -> {
            Join<Product, Category> productCategoryJoin = root.join("category");

            List<Predicate> predicateList = new ArrayList<>();
            addCollectionPredicateIfExist(predicateList, searchCriteria.getCategoryIds(), e -> root.join(Product_.category).get(Category_.id).in(e));
            addPredicateIfExist(predicateList, searchCriteria.getBrand(), e -> createLikePredicateIgnoreCase(root.get(Product_.brand), criteriaBuilder, e));
            addPredicateIfExist(predicateList, searchCriteria.getPriceFrom(), e -> criteriaBuilder.greaterThanOrEqualTo(root.get(Product_.price), e));
            addPredicateIfExist(predicateList, searchCriteria.getPriceFrom(), e -> criteriaBuilder.lessThanOrEqualTo(root.get(Product_.price), e));
            addPredicateIfExist(predicateList, searchCriteria.getColour(), e -> createLikePredicateIgnoreCase(root.get(Product_.colour), criteriaBuilder, e));

            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
    }

    static <T> void addPredicateIfExist(List<Predicate> predicateList, T value, Function<T, Predicate> func) {
        if (Objects.nonNull(value)) {
            predicateList.add(func.apply(value));
        }
    }

    static <T> void addCollectionPredicateIfExist(List<Predicate> predicateList, Collection<T> values, Function<Collection<T>, Predicate> func) {
        if (!CollectionUtils.isEmpty(values)) {
            predicateList.add(func.apply(values));
        }
    }

    static Predicate createLikePredicateIgnoreCase(Path<String> referencedPath, CriteriaBuilder criteriaBuilder, String value) {
        return criteriaBuilder.like(criteriaBuilder.upper(referencedPath), "%" + StringUtils.upperCase(value) + "%");
    }

    static <Y> Predicate createEqualPredicate(Path<Y> referencedPath, CriteriaBuilder criteriaBuilder, Object objToCompared) {
        return criteriaBuilder.equal(referencedPath, objToCompared);
    }
}
