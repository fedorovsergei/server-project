package com.fedorov.demo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
public class CompareField {

    private String code;
    private Double count;
}
