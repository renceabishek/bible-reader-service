package com.read.bible.service.controller;

import com.read.bible.service.integration.TestIntegration;
import com.read.bible.service.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bible")
public class TestController {

  @Autowired
  private TestIntegration testIntegration;

  @GetMapping(value = "/test")
  public Mono<Member> getValue() {
    return testIntegration.getMember();
  }
}
