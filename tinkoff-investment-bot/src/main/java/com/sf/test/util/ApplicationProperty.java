package com.sf.test.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.key")
public class ApplicationProperty {

    private String sandbox;
    private String readOnly;
}
