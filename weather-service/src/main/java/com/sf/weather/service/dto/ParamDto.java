package com.sf.weather.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParamDto {

    private String now;
    private String feeling;
}
