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
    LogInPage logInPage = new LogInPage(driver);


    @BeforeClass
    public void webDriver() {
        System.setProperty("webdriver.chrome.driver", "C://Users//Office//Downloads//chromedriver_win32 (1)//chromedriver87.exe");
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void signOffFromTrello() {
        BoardPage boardPage = new BoardPage(driver);
        boardPage.logOutUser();
    }

    @Test
    public void authorizationValidTest() {
        logInPage.logInExistedUser1();

        String title = driver.getTitle();
        Assert.assertEquals(title, "Boards | Trello");
    }

    @Test
    public void emptyFieldsTest() {
        logInPage.logInNewUser("", "");

        String message = logInPage.getTextErrorMessage();
        Assert.assertEquals(message, "Missing email");
    }

    @Test
    public void incorrectEmailTest() {
        logInPage.logInNewUser("Test", " ");

        String message = logInPage.getTextErrorMessage();
        Assert.assertEquals(message,"There isn't an account for this username");
    }
}
