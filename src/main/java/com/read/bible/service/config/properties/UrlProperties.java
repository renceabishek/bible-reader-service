package com.read.bible.service.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "firebase.url")
@Data
public class UrlProperties {

  private String project;
  private String database;
  private String storage;
}
