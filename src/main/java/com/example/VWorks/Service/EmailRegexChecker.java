package com.example.VWorks.Service;

public class EmailRegexChecker extends RegexChecker {
    @Override
     public String getRegex() {
        return "^\\S+@\\S+\\.\\S+$";
    }
}
