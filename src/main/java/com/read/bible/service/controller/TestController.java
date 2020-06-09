package com.read.bible.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bible")
public class TestController {

  @GetMapping(value = "/test")
  public String getValue() {
    return "Success!";
  }
}
