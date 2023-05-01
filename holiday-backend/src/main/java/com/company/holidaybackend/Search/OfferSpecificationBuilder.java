package com.company.holidaybackend.Search;

import com.company.holidaybackend.Model.Offer;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OfferSpecificationBuilder {

    private final List<SpecSearchCriteria> params;

    public OfferSpecificationBuilder() {
        this.params = new ArrayList<>();
    }

    public Object get_by_key(String key) {
        for (SpecSearchCriteria i : params) {
            if (Objects.equals(i.getKey(), key)) {
                return i.getValue();
            }
        }
        return null;
    }

    public final void with(String key, String operation,
                           Object value) {
        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        System.out.println(key);
        System.out.println("type " + value.getClass());
        params.add(new SpecSearchCriteria(key, op, value));

    }

    public Specification<Offer> build() {
        if (params.size() == 0)
            return null;

        for (SpecSearchCriteria param : params) {
            System.out.println(param.toString());
        }

        Specification<Offer> result = new OfferSpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = Specification.where(result).and(new OfferSpecification(params.get(i)));
        }

        return result;
    }

}
