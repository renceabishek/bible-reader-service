package com.read.bible.service.integration;

import com.read.bible.service.model.Member;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TestIntegration {

  public Mono<Member> getMember();
}
