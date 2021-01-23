package com.trello.authorization;

import com.trello.BoardPage;
import com.trello.LogInPage;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AuthorisationTest {

    public ChromeDriver driver;



//    @BeforeClass
//    public void setUp() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//    }

    @BeforeMethod
    public void openEnvironment() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://trello.com");
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
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

        String emailUser = "workboris3@gmail.com";
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

    @Test
    public void logInButtonLocationCheck() {
        LogInPage logInPage = new LogInPage(driver);

        logInPage.openLogInPage();

        Assert.assertEquals(logInPage.getLoginButtonLocation(), "(296, 332)");
    }
}