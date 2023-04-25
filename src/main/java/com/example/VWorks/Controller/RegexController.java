package com.example.VWorks.Controller;

import com.example.VWorks.Service.EmailRegexChecker;
import com.example.VWorks.Service.RegexChecker;
import com.example.VWorks.Service.WebsiteRegexChecker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/Regex")
public class RegexController {

    @PostMapping("/Email")
    public ResponseEntity checkEmailRegex(@RequestBody String input) {
        RegexChecker regexChecker = new EmailRegexChecker();
        return regexChecker.check(input);
    }

    @PostMapping("/Website")
    public ResponseEntity checkWebsiteRegex(@RequestBody String input) {
        RegexChecker regexChecker = new WebsiteRegexChecker();
        return regexChecker.check(input);
    }

}
