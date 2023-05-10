package com.example.VWorks.Service;

import org.springframework.http.ResponseEntity;

import java.util.regex.Pattern;

 public abstract class RegexChecker {

    protected abstract String getRegex();

    public ResponseEntity check(String input) {
        String regex = getRegex();
        boolean matches = Pattern.matches(regex, input);

        if (matches) {
            return ResponseEntity.ok("Input matches regex pattern.");
        } else {
            return ResponseEntity.badRequest().body("Input does not match regex pattern.");
        }
    }
}

