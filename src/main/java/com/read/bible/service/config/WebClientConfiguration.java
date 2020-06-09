package com.read.bible.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientConfiguration {

  public WebClientConfiguration() {}

  @Value("${firebase.database-url}")
  private String firebaseUrl;

  @Bean(name = "webClientFirebase")
  public WebClient webClientConfig(WebClient.Builder webClient) {
    return webClient.baseUrl(firebaseUrl)
        .build();
  }

}
