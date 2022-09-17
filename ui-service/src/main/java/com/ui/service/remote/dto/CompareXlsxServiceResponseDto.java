package com.ui.service.remote.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CompareXlsxServiceResponseDto {

    private Set<CompareField> oneCFile;
    private Set<CompareField> pepsiFile;
    private Set<CompareField> compare;
}
