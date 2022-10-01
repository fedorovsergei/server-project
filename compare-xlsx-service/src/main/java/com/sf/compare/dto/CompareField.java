package com.sf.compare.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompareField {

    private String code;
    private Double count;
}
