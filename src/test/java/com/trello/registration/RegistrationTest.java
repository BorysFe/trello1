package com.trello.registration;

import static com.trello.Waiters.waitSeconds;

import com.trello.BoardPage;
import com.trello.RegistrationPage;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RegistrationTest {

    private ChromeDriver driver;

    @BeforeClass
    public void webDriver() {
        System.setProperty("webdriver.chrome.driver", "C://Users//Office//Downloads//chromedriver_win32 (1)" +
                "//chromedriver87.exe");
        driver = new ChromeDriver();
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

        Assert.assertEquals(registrationPage.getPrivacyPolicyText(), "By signing up, you confirm that you've read and" +
                " accepted our Terms of Service " +
                "and Privacy Policy.");
    }

    @Test
    public void signUpNewUserInvalidEmail() {
        RegistrationPage registrationPage = new RegistrationPage(driver);

        String emailUser = "workboris1@gmail";
        registrationPage.setFirstSignUpPage(emailUser);
        waitSeconds(3);

        Assert.assertEquals(registrationPage.getErrorMessage(), "Invalid email");
    }

    @Test
    public void signUpNewUserValidScenario() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        String emailUser = String.format("workboris1+%s@gmail.com", System.currentTimeMillis());
        String passwordUser = String.format("qwERty%s", System.currentTimeMillis());
        String nameUser = "Borys";
        String teamName = "My first test team";

        registrationPage.setFirstSignUpPage(emailUser);
        registrationPage.setSecondSignUpPage(nameUser, passwordUser);
// captcha
        registrationPage.setThirdSignUpPage(teamName);
        waitSeconds(4);

        String textButton = registrationPage.getButtonText();

        Assert.assertEquals(textButton, "Start 30-day free trial");
    }
}
