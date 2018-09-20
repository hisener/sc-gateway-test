package com.github.hisener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
public class TomcatApplication {

  public static void main(String[] args) {
    SpringApplication.run(TomcatApplication.class, args);
  }

  @Controller
  static class DummyController {

    @GetMapping("/redirect")
    public ModelAndView redirectWithUsingRedirectPrefix(ModelMap model) {
      return new ModelAndView("redirect:http://google.com", model);
    }

    @GetMapping("/empty-response")
    public ResponseEntity<Void> responseEntity() {
      return ResponseEntity.ok().build();
    }

    @GetMapping("/foo")
    public ResponseEntity<String> string() {
      return ResponseEntity.ok("foo");
    }
  }
}
