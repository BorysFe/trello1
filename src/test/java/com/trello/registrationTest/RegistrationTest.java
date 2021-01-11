package com.trello.registrationTest;

import com.trello.UserManipulations;
import com.trello.Waiters;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class RegistrationTest extends UserManipulations {
    public Actions action;

    @BeforeMethod
    public void openSignUpPage() {
        driver.findElementByLinkText("Sign Up").click();
    }

    @AfterMethod
    public void signOffFromTrello() {
        driver.findElementByXPath(".//button[contains(@aria-label, 'Open Member Menu')]").click();
        driver.findElementByLinkText("Sign Off").click();
    }

    @Test
    public void signUpPageUnderFieldTextTest() {
        String message = driver.findElementById("signup").getText();
        Assert.assertEquals(message, "By signing up, you confirm that you've read and accepted our Terms of Service and Privacy Policy.");
    }

    @Test
    public void signUpNewUserWithError() {
        driver.findElementById("email").sendKeys("workboris1@gmail");
        driver.findElementById("signup-submit").click();
        Waiters.waitForNSeconds(3);
        String message = driver.findElementById("error").getText();
        Assert.assertEquals(message,"Invalid email");
    }

    @Test
    public void signUpNewUser() {
        driver.findElementById("email").sendKeys("workboris1+"+System.currentTimeMillis()+"@gmail.com");
        driver.findElementById("signup-submit").click();
        Waiters.waitForNSeconds(4);
        String fieldPlaceHolder = driver.findElementById("displayName").getAttribute("placeholder");
        Assert.assertEquals(fieldPlaceHolder, "Enter full name");
    }
}
