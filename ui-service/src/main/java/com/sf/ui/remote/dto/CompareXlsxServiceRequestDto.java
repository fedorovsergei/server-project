package com.sf.ui.remote.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompareXlsxServiceRequestDto {

    private String oneCFile;
    private Integer oneCNamePosition;
    private Integer oneCPlusPosition;
    private Integer oneCMinusPosition;
    private String pepsiFileBase64;
    private Integer pepsiNamePosition;
    private Integer pepsiCountPosition;
}
