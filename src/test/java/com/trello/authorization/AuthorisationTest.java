package com.trello.authorization;

import com.trello.BoardPage;
import com.trello.LogInPage;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AuthorisationTest {

    private ChromeDriver driver;


    @BeforeClass
    public void webDriver() {
        System.setProperty("webdriver.chrome.driver", "C://Users//Office//Downloads//chromedriver_win32 (1)//chromedriver87.exe");
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void signOffFromTrello() {
        new BoardPage(driver).logOutUser();
    }

    @Test
    public void authorizationValidTest() {
        LogInPage logInPage  = new LogInPage(driver);

        logInPage.logInExistedUser1();

        String title = driver.getTitle();
        Assert.assertEquals(title, "Boards | Trello");
    }

    @Test
    public void emptyFieldsTest() {
        LogInPage logInPage  = new LogInPage(driver);

        logInPage.logInNewUser("", "");

        String message = logInPage.getTextErrorMessage();
        Assert.assertEquals(message, "Missing email");
    }

    @Test
    public void incorrectEmailTest() {
        LogInPage logInPage  = new LogInPage(driver);

        logInPage.logInNewUser("Test", " ");

        String message = logInPage.getTextErrorMessage();
        Assert.assertEquals(message,"There isn't an account for this username");
    }
}