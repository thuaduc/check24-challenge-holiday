package com.company.holidaybackend.Search;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class SearchCriteria {
    private String key;
    private String operation;
    private Object value;
}
