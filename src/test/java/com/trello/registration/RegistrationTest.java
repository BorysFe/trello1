package com.trello.registration;


import com.trello.BoardPage;
import com.trello.RegistrationPage;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RegistrationTest {

    public ChromeDriver driver;

    @BeforeClass
    public void webDriver() {
        WebDriverManager.chromedriver()
                        .setup();
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
    public void privacyPolicyTextCheck() {
        RegistrationPage registrationPage = new RegistrationPage(driver);

        registrationPage.openSignUpPage();

        Assert.assertEquals(registrationPage.getPrivacyPolicyText(), "By signing up, you confirm that you've read and" +
                " accepted our Terms of Service " +
                "and Privacy Policy.");
    }

    @Test
    public void signUpNewUserInvalidEmail() {
        RegistrationPage registrationPage = new RegistrationPage(driver);

        String emailUser = "workboris1@gmail";
        registrationPage.setFirstSignUpPage(emailUser);

        Assert.assertEquals(registrationPage.getErrorMessage(), "Invalid email");
    }

    @Test
    public void signUpNewUserValidScenario() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        String emailUser = String.format("workboris1+%s@gmail.com", System.currentTimeMillis());
        System.out.println(emailUser);
        String passwordUser = String.format("qwERty%s", System.currentTimeMillis());
        String nameUser = "Borys";
        String teamName = "My first test team";

        registrationPage.setFirstSignUpPage(emailUser);
        registrationPage.setSecondSignUpPage(nameUser, passwordUser);
// captcha
        registrationPage.setThirdSignUpPage(teamName);

        String textButton = registrationPage.getButtonText();

        Assert.assertEquals(textButton, "Start 30-day free trial");
    }
}
