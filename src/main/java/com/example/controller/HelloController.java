package com.example.controller;

import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.core.env.Environment;
import java.util.UUID;

@RestController
public class HelloController {
    private static final String VISITOR_ID_COOKIE = "visitorId";
    private final Environment environment;

    public HelloController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/")
    public ResponseEntity<String> hello(
            @CookieValue(value = VISITOR_ID_COOKIE, required = false) String visitorId) {
        
        if (visitorId == null) {
            visitorId = UUID.randomUUID().toString();
            ResponseCookie cookie = ResponseCookie.from(VISITOR_ID_COOKIE, visitorId)
                    .maxAge(60 * 60 * 24) // 1 day
                    .path("/")
                    .httpOnly(true)
                    .secure(isProductionEnvironment())
                    .sameSite("Strict")
                    .build();

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .body("Hello stranger!");
        }

        return ResponseEntity.ok("Hello " + visitorId.substring(0, Math.min(8, visitorId.length())) + "!");
    }

    private boolean isProductionEnvironment() {
        String[] activeProfiles = environment.getActiveProfiles();
        return activeProfiles.length > 0 && "prod".equals(activeProfiles[0]);
    }
} 