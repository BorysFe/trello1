package com.trello.authorization;

import com.trello.BoardPage;
import com.trello.LogInPage;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AuthorisationTest {

    private ChromeDriver driver;

    @BeforeClass
    public void webDriver() {
        System.setProperty("webdriver.chrome.driver", "C://Users//Office//Downloads//chromedriver_win32 (1)" +
                "//chromedriver87.exe");
        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void openEnvironment() {
        driver.get("https://trello.com");
    }

    @AfterMethod
    public void signOffFromTrello() {
        BoardPage boardPage = new BoardPage(driver);
        boardPage.logOutIfAuthorised();
    }

    @AfterClass
    public void browserQuit() {
        driver.quit();
    }

    @Test
    public void authorizationValidTest() {
        LogInPage logInPage = new LogInPage(driver);

        String emailUser = "fesenko.b@icloud.com";
        String passwordUser = "B0r1sTr3ll0";

        logInPage.logInNewUser(emailUser, passwordUser);

        String title = driver.getTitle();
        Assert.assertEquals(title, "Boards | Trello");
    }

    @Test
    public void emptyFieldsTest() {
        LogInPage logInPage = new LogInPage(driver);

        logInPage.logInNewUser("", "");

        String message = logInPage.getTextErrorMessage();
        Assert.assertEquals(message, "Missing email");
    }

    @Test
    public void incorrectEmailTest() {
        LogInPage logInPage = new LogInPage(driver);

        logInPage.logInWithEmail("Test@eeefef");

        Assert.assertEquals(logInPage.getTextErrorMessage(), "There isn't an account for this email");
    }
}