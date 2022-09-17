package com.sf.weather.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.remote")
public class ApplicationProperty {

    private String url;
    private String weatherNow;
    private String weatherEvening;
    private String agent;
}
