package com.trello.registration;

import static com.trello.Waiters.waitSeconds;

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


    public Actions action;

//    @BeforeClass
//    public void webDriver() {
//        System.setProperty("webdriver.chrome.driver", "C://Users//Office//Downloads//chromedriver_win32 (1)//chromedriver87.exe");
//        ChromeDriver driver = new ChromeDriver();
//
//    }

//    @BeforeMethod
//    public void openSignUpPage() {
//        System.setProperty("webdriver.chrome.driver", "C://Users//Office//Downloads//chromedriver_win32 (1)//chromedriver87.exe");
//        ChromeDriver driver = new ChromeDriver();
//
//
//    }

    @AfterMethod
    public void signOffFromTrello() {
        System.setProperty("webdriver.chrome.driver", "C://Users//Office//Downloads//chromedriver_win32 (1)//chromedriver87.exe");
        ChromeDriver driver = new ChromeDriver();

        List<WebElement> openMemberMenu = driver.findElementsByXPath(".//button[contains(@aria-label, 'Open Member Menu')]");

        if (openMemberMenu.size() > 0) {
            openMemberMenu.get(0).click();
            driver.findElementByLinkText("Sign Off").click();
        }
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
        System.setProperty("webdriver.chrome.driver", "C://Users//Office//Downloads//chromedriver_win32 (1)//chromedriver87.exe");
        ChromeDriver driver = new ChromeDriver();

        driver.findElementById("email").sendKeys("workboris1@gmail");
        driver.findElementById("signup-submit").click();
        waitSeconds(3);
        String message = driver.findElementById("error").getText();
        Assert.assertEquals(message,"Invalid email");
    }

    @Test
    public void signUpNewUserValidScenario() {
        System.setProperty("webdriver.chrome.driver", "C://Users//Office//Downloads//chromedriver_win32 (1)//chromedriver87.exe");
        ChromeDriver driver = new ChromeDriver();

        driver.get("https://trello.com/");
        driver.findElementByLinkText("Sign Up").click();
        waitSeconds(4);
        driver.findElementById("email").sendKeys("workboris1+" + System.currentTimeMillis() + "@gmail.com");
        driver.findElementById("signup-submit").click();
        waitSeconds(4);

        String fieldPlaceHolder = driver.findElementById("displayName").getAttribute("placeholder");
        Assert.assertEquals(fieldPlaceHolder, "Enter full name");

        driver.findElementById("displayName").sendKeys("Borys");
        String password = "qwERty"+System.currentTimeMillis();
        driver.findElementById("password").sendKeys(password);
        driver.findElementById("signup-submit").click();
// captcha
        driver.findElementById("moonshotCreateTeam").sendKeys("My first test team");
        driver.findElementByClassName("nch-select").click();
        driver.findElementByXPath(".//div[@id='react-select-2-option-0']/li").click();
        driver.findElementByXPath(".//button[@type='submit']").click();
        waitSeconds(4);

        String textButton = driver.findElementByXPath(".//button[@data-test-id='moonshot-try-bc-free-trial']").getText();

        Assert.assertEquals(textButton, "Start 30-day free trial");
    }
}
