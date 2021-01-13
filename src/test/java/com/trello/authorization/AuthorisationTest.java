package com.trello.authorization;

import com.trello.Waiters;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

public class AuthorisationTest {

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
    public void authorizationValidTest() {
        System.setProperty("webdriver.chrome.driver", "C://Users//Office//Downloads//chromedriver_win32 (1)//chromedriver87.exe");
        ChromeDriver driver = new ChromeDriver();

        driver.get("https://trello.com/");
        driver.findElementByLinkText("Log In").click();
        driver.findElementById("user").sendKeys("fesenko.b@icloud.com");
        driver.findElementById("password").sendKeys("B0r1sTr3ll0");
        driver.findElementById("login").click();
        Waiters.waitSeconds(4);
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertEquals(title, "Boards | Trello");
    }

    @Test
    public void emptyFieldsTest() {
        System.setProperty("webdriver.chrome.driver", "C://Users//Office//Downloads//chromedriver_win32 (1)//chromedriver87.exe");
        ChromeDriver driver = new ChromeDriver();

        driver.get("https://trello.com/");
        driver.findElementByLinkText("Log In").click();
        String message = driver.findElementById("error").getText();

        Assert.assertEquals(message, "Missing email");
    }

        @Test
        public void incorrectEmailTest() {
            System.setProperty("webdriver.chrome.driver", "C://Users//Office//Downloads//chromedriver_win32 (1)//chromedriver87.exe");
            ChromeDriver driver = new ChromeDriver();

            driver.get("https://trello.com/");
            driver.findElementByLinkText("Log In").click();
            driver.findElementById("user").sendKeys("Test");
            String message = driver.findElementById("error").getText();
            Assert.assertEquals(message,"There isn't an account for this username");
    }
}
