package com.example.VWorks.Service;

public class EmailRegexChecker extends RegexChecker {
    @Override
    protected String getRegex() {
        return "^\\S+@\\S+\\.\\S+$";
    }
}
