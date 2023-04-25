package com.example.VWorks.Service;

public class WebsiteRegexChecker extends RegexChecker {

    @Override
    protected String getRegex() {
        return "^(http://|https://)?(www\\.)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/\\S*)?$";
    }
}
