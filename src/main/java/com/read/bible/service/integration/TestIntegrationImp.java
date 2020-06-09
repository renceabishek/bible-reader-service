package com.read.bible.service.integration;

import com.read.bible.service.model.Member;
import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TestIntegrationImp implements TestIntegration {

  @Value("${firebase.path.member}")
  private String path;

  private final WebClient webClientFirebase;

  public TestIntegrationImp(WebClient webClientFirebase) {
    this.webClientFirebase = webClientFirebase;
  }

  @Override
  public Mono<Member> getMember() {
    return webClientFirebase.get()
        .uri(uriBuilder -> uriBuilder.path(path+".json")
            .build())
        .retrieve()
        .onStatus(HttpStatus::isError, resp -> resp.createException()
            .map(Exception::new)
            .flatMap(Mono::error))
        .bodyToMono(Member.class);
  }
}
