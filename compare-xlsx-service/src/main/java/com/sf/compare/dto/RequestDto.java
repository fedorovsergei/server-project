package com.sf.compare.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RequestDto {

    private String oneCFile;
    private Integer oneCNamePosition;
    private Integer oneCPlusPosition;
    private Integer oneCMinusPosition;
    private String pepsiFileBase64;
    private Integer pepsiNamePosition;
    private Integer pepsiCountPosition;
}
