package com.trello.registration;

import static com.trello.Waiters.waitSeconds;

import com.trello.BoardPage;
import com.trello.LogInPage;
import com.trello.RegistrationPage;
import com.trello.Waiters;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.List;

public class RegistrationTest {

    private ChromeDriver driver;
    RegistrationPage registrationPage = new RegistrationPage(driver);


    @BeforeClass
    public void webDriver() {
        System.setProperty("webdriver.chrome.driver", "C://Users//Office//Downloads//chromedriver_win32 (1)//chromedriver87.exe");
//        driver = new ChromeDriver();
    }

    @AfterMethod
    public void signOffFromTrello() {
        BoardPage boardPage = new BoardPage(driver);
        boardPage.logOutUser();
    }

    @Test
    public void privacyPolicyTextCheck() {
        System.setProperty("webdriver.chrome.driver", "C://Users//Office//Downloads//chromedriver_win32 (1)//chromedriver87.exe");
        ChromeDriver driver = new ChromeDriver();

        String message = driver.findElementById("signup").getText();
        Assert.assertEquals(message, "By signing up, you confirm that you've read and accepted our Terms of Service and Privacy Policy.");
    }

    @Test
    public void signUpNewUserInvalidEmail() {
        String emailUser = "workboris1@gmail";
        registrationPage.firstSignUpPage(emailUser);
        waitSeconds(3);

        Assert.assertEquals(registrationPage.getErrorMessage(),"Invalid email");
    }

    @Test
    public void signUpNewUserValidScenario() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        String emailUser = "workboris1+" + System.currentTimeMillis() + "@gmail.com";
        String passwordUser = "qwERty"+System.currentTimeMillis();
        String nameUser = "Borys";
        String teamName = "My first test team";


        registrationPage.firstSignUpPage(emailUser);
        Assert.assertEquals(registrationPage.nameField.getAttribute("placeholder"), "Enter full name");

        registrationPage.secondSignUpPage(passwordUser, nameUser);
// captcha
        registrationPage.thirdSignUpPage(teamName);
        waitSeconds(4);

        String textButton = driver.findElementByXPath(".//button[@data-test-id='moonshot-try-bc-free-trial']").getText();

        Assert.assertEquals(textButton, "Start 30-day free trial");
    }
}
