package io.pivotal.pal.tracker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    final String HELLO_STRING = "hello";

    @GetMapping("/")
    public String sayHello() {
        return HELLO_STRING;
    }
}
