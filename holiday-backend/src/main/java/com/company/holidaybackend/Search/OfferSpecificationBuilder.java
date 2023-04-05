package com.company.holidaybackend.Search;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class OfferSpecificationBuilder {

    private List<SpecSearchCriteria> params;

    public OfferSpecificationBuilder() {
        this.params = new ArrayList<>();
    }

    public final OfferSpecificationBuilder with(String key, String operation,
                                                Object value) {
        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            params.add(new SpecSearchCriteria(key, op, value));
        }
        return this;
    }

    public Specification build() {
        if (params.size() == 0)
            return null;

        Specification result = new OfferSpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = Specification.where(result).and(new OfferSpecification(params.get(i)));
        }

        return result;
    }
}
