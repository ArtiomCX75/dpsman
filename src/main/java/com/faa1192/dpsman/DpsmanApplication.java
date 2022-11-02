package com.faa1192.dpsman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DpsmanApplication {

    public static void main(String[] args) {
        SpringApplication.run(DpsmanApplication.class, args);
    }

    private static final String errMsg = "No more license numbers available";

    @GetMapping("/random")
    public String random() {
        try {
            return RegNumberGenerator.getRandom();
        } catch (Exception e) {
            return errMsg;
        }
    }


    @GetMapping("/next")
    public String next() {
        try {
            return RegNumberGenerator.getNext();
        } catch (Exception e) {
            return errMsg;
        }
    }

    @GetMapping("/")
    public String test() {
        return "Test connection";
    }

}
