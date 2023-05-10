package com.example.VWorks.ServiceTests;

import com.example.VWorks.Service.EmailRegexChecker;
import com.example.VWorks.Service.WebsiteRegexChecker;
import org.junit.Assert;
import org.junit.Test;

public class WebsiteRegexCheckerTest {
    @Test
    public void testValidEmail() {
        // Arrange
        String website = "test@email.com";

        // Act
        String result = new EmailRegexChecker().check(website).getBody().toString();

        // Assert
        Assert.assertEquals("Input matches regex pattern.", result);
    }
    @Test
    public void testInvalidEmail() {
        // Arrange
        String website = "testemailcom";

        // Act
        String result = new EmailRegexChecker().check(website).getBody().toString();

        // Assert
        Assert.assertEquals("Input does not match regex pattern.", result);
    }
}

