package com.example.VWorks.ServiceTests;

import com.example.VWorks.Service.EmailRegexChecker;
import org.junit.Assert;
import org.junit.Test;

public class EmailRegexCheckerTest {

    @Test
    public void testValidWebsite() {
        // Arrange
        String website = "https://www.test.com";

        // Act
        String result = new EmailRegexChecker().check(website).getBody().toString();

        // Assert
        Assert.assertEquals("Input matches regex pattern.", result);
    }

    @Test
    public void testInvalidWebsite() {
        // Arrange
        String website = "test";

        // Act
        String result = new EmailRegexChecker().check(website).getBody().toString();

        // Assert
        Assert.assertEquals("Input does not match regex pattern.", result);
    }


    @Test
    public void testWebsiteWithPort() {
        // Arrange
        String website = "http://example.com:8080";

        // Act
        boolean result = new EmailRegexChecker().check(website).hasBody();

        // Assert
        Assert.assertTrue(result);
    }

    // Add more tests as needed
}

