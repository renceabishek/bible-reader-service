package com.read.bible.service.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "firebase.storage")
public class StorageProperties {

  private String meeting;
  private String activity;
  private String profilePic;
}
