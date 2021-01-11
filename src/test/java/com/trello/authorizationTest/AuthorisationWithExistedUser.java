package com.trello.authorizationTest;

import com.trello.UserManipulations;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AuthorisationWithExistedUser extends UserManipulations {

//   public ChromeDriver driver;
   public Actions action;

//    public void openMainPageAndLogIn() {
//
//        System.setProperty("webdriver.chrome.driver", "C://Users//Office//Downloads//chromedriver_win32 (1)//chromedriver87.exe");
//        driver = new ChromeDriver();
//        action = new Actions(driver);
//        driver.get("https://trello.com/");
//        driver.findElementByLinkText("Log In").click();
//        driver.findElementById("user").sendKeys("fesenko.b@icloud.com");
//        driver.findElementById("password").sendKeys("B0r1sTr3ll0");
//        driver.findElementById("login").click();
//        driver.findElementByXPath();
//    }


    @BeforeMethod
    public void logIn() {
        UserManipulations toDo = new UserManipulations();
        toDo.logInWithExistedUser();
    }

    @Test
    public void test1() {
        action.release(driver.findElementByClassName("_1ZG6N6wwI3fkaY"));

//        driver.fi
//        driver.findElementByClassName("_1ZG6N6wwI3fkaY");
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertEquals(title, "Boards | Trello");

//        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//        jsExecutor.executeScript("arguments[0].play();", video);

//        Actions action = new Actions(driver);
//        action.doubleClick();


    }
}
