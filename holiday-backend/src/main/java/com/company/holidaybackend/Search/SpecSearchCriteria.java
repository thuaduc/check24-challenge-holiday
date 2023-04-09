package com.company.holidaybackend.Search;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class SpecSearchCriteria {

    private String key;
    private SearchOperation operation;
    private Object value;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private boolean orPredicate;

    public SpecSearchCriteria(String key, SearchOperation operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
        //this.orPredicate = orPredicate != null && orPredicate.equals(SearchOperation.OR_PREDICATE_FLAG);
    }


}
