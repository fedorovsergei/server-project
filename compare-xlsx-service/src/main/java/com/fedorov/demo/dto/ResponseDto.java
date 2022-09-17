package com.fedorov.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class ResponseDto {

    private Set<CompareField> oneCFile;
    private Set<CompareField> pepsiFile;
    private Set<CompareField> compare;
}
