package com.trello;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

public class UserManipulations extends WebDriverSettings{

    @BeforeMethod
    public void openTrello() {
        driver = new ChromeDriver();
        driver.get("https://trello.com/");
    }

    public void logInWithExistedUser() {
        driver.findElementByLinkText("Log In").click();
        driver.findElementById("user").sendKeys("fesenko.b@icloud.com");
        driver.findElementById("password").sendKeys("B0r1sTr3ll0");
        driver.findElementById("login").click();
    }

    public void signUpNewUser() {
        driver.findElementByLinkText("Sign Up").click();
        driver.findElementById("email").sendKeys("workboris1@gmail+"+System.currentTimeMillis()+".com");

    }

//    @AfterClass
//    public void closeBrowser() {
//        driver.quit();
//    }
}
