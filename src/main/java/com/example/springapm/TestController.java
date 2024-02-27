package com.example.springapm;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
  @GetMapping("/encode")
  public String encodeTest(@RequestParam String text) {
    return ScpDbAgentUtil.encode(text);
  }
  
  @GetMapping("/decode")
  public String decodeTest(@RequestParam String text) {
    return ScpDbAgentUtil.decode(text);
  }
}
