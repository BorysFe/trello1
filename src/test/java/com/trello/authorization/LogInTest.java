package com.trello.authorization;

import com.trello.BoardPage;
import com.trello.LogInPage;
import com.trello.User;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LogInTest {

    public ChromeDriver driver;
    private LogInPage logInPage;
    private BoardPage boardPage;
    private User user;

    String userEmail = "workboris3@gmail.com";
    String userPassword = "B0r1sTr3ll0";

    @BeforeClass
    public void webDriver() {
        WebDriverManager.chromedriver()
                        .setup();
        user = new User(userEmail, userPassword);
    }

    @BeforeMethod
    public void openEnvironment() {
        driver = new ChromeDriver();
        driver.get("https://trello.com");
        logInPage = new LogInPage(driver);
        boardPage = new BoardPage(driver);
        logInPage.openLoginPageFactory(driver);
    }

    @AfterMethod
    public void signOffFromTrello() {
        boardPage.logOutIfAuthorised();
        driver.quit();
    }

    @Test
    public void authorizationValidTest() {
        logInPage.loginUser(user);

        Assert.assertEquals(boardPage.getTeamBoardTitle(), "Most popular templates");
    }

    @Test
    public void emptyFieldsTest() {
        User emptyFields = new User("", "");
        logInPage.loginUser(emptyFields);

        Assert.assertEquals(logInPage.getTextErrorMessage(), "Missing email");
    }

    @Test
    public void incorrectEmailTest() {
        User invalidUser = new User("test@qwdqwdqw", "");
        logInPage.loginUser(invalidUser);

        Assert.assertEquals(logInPage.getTextErrorMessage(), "There isn't an account for this email");
    }

    @Test
    public void logInButtonLocationCheck() {
        Assert.assertEquals(logInPage.getLoginButtonLocation(), "(296, 332)");
    }
}