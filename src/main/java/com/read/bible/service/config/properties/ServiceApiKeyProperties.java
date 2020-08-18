package com.read.bible.service.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "firebase.config")
public class ServiceApiKeyProperties {

  public String credentials;
}
