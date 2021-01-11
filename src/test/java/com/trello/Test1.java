package com.trello;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

public class Test1 {

   public ChromeDriver driver;
   public Actions action;

    @BeforeClass
    public void openMainPageAndLogIn() {

        System.setProperty("webdriver.chrome.driver", "C://Users//Office//Downloads//chromedriver_win32 (1)//chromedriver87.exe");
        driver = new ChromeDriver();
        action = new Actions(driver);
        driver.get("https://trello.com/");
        driver.findElementByLinkText("Log In").click();
        driver.findElementById("user").sendKeys("fesenko.b@icloud.com");
        driver.findElementById("password").sendKeys("B0r1sTr3ll0");
        driver.findElementById("login").click();
    }


    @BeforeMethod
    public void logIn() {

    }

    @Test
    public void test1() {
        action.release(driver.findElementByClassName("_1ZG6N6wwI3fkaY"));

//        driver.fi
//        driver.findElementByClassName("_1ZG6N6wwI3fkaY");
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertTrue(title.equals("Boards | Trello"));

//        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//        jsExecutor.executeScript("arguments[0].play();", video);

//        Actions action = new Actions(driver);
//        action.doubleClick();


//        driver.quit();
    }
}
