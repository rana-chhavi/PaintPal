package com.chhrana.paintPal.painting;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.domain.Specification;

public class PaintingSpecification {

    public static Specification<Painting> withOwnerId(Integer ownerId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get("owner").get("id"), ownerId
        );
    }
}
