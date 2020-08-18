package com.read.bible.service.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.read.bible.service.config.properties.UrlProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientConfiguration {

  private final UrlProperties urlProperties;

  public WebClientConfiguration(UrlProperties urlProperties) {
    this.urlProperties = urlProperties;
  }

  @Bean(name = "webClientFirebase")
  public WebClient webClientConfig(WebClient.Builder webClient) {
    return webClient.baseUrl(urlProperties.getDatabase())
        .build();
  }

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    return objectMapper;
  }

}
