package com.trello.boards;

import com.trello.Waiters;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

public class NewBoardTest {

    String newBoardTitle = "Auto test - "+System.currentTimeMillis();

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
    public void searchBoardFieldCheck() {
        System.setProperty("webdriver.chrome.driver", "C://Users//Office//Downloads//chromedriver_win32 (1)//chromedriver87.exe");
        ChromeDriver driver = new ChromeDriver();
        WebElement searchField = driver.findElementByXPath(".//input[@name= 'search-boards']");

        driver.get("https://trello.com/");
        driver.findElementByLinkText("Log In").click();
        driver.findElementById("user").sendKeys("fesenko.b@icloud.com");
        driver.findElementById("password").sendKeys("B0r1sTr3ll0");
        driver.findElementById("login").click();
        Waiters.waitSeconds(4);
        driver.findElementByXPath(".//button[@aria-label= 'Open Boards Menu']//span").click();

        Assert.assertEquals(searchField.getAttribute("placeholder"), "Find boards by name…", "Placeholder is wrong");
    }

    @Test
    public void userExistedBoardCheck() {
        System.setProperty("webdriver.chrome.driver", "C://Users//Office//Downloads//chromedriver_win32 (1)//chromedriver87.exe");
        ChromeDriver driver = new ChromeDriver();
        Actions action = new Actions(driver);

        WebElement userBoard = driver.findElementByXPath(".//a[@title= 'Borys_Tech-Stack']");
        String boardTitle = driver.findElementByXPath(".//input[@class= 'board-name-input js-board-name-input']").getAttribute("value");

        driver.get("https://trello.com/");
        driver.findElementByLinkText("Log In").click();
        driver.findElementById("user").sendKeys("fesenko.b@icloud.com");
        driver.findElementById("password").sendKeys("B0r1sTr3ll0");
        driver.findElementById("login").click();
        Waiters.waitSeconds(4);
        driver.findElementByXPath(".//button[@aria-label= 'Open Boards Menu']//span").click();

        Assert.assertTrue(userBoard.isEnabled(), "The board isn't found");

        action.click(userBoard).build().perform();

        Assert.assertEquals(boardTitle, "Borys_Tech-Stack", "The board title is wrong");
    }

    @Test
    public void addNewBoardCheck() {
        System.setProperty("webdriver.chrome.driver", "C://Users//Office//Downloads//chromedriver_win32 (1)//chromedriver87.exe");
        ChromeDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String onBoardTitle = driver.findElementByXPath(".//input[@class= 'board-name-input js-board-name-input']").getAttribute("value");

        driver.get("https://trello.com/");
        driver.findElementByLinkText("Log In").click();
        driver.findElementById("user").sendKeys("fesenko.b@icloud.com");
        driver.findElementById("password").sendKeys("B0r1sTr3ll0");
        driver.findElementById("login").click();
        Waiters.waitSeconds(4);
        driver.findElementByXPath(".//button[@aria-label= 'Open Boards Menu']//span").click();

        driver.findElementByXPath(".//button[@data-test-id='header-boards-menu-create-board']").click();
        driver.findElementByXPath(".//input[@aria-label='Add board title']").sendKeys(newBoardTitle);
        driver.findElementByXPath(".//button[@data-test-id='create-board-submit-button']").click();

        Assert.assertEquals(onBoardTitle, newBoardTitle, "The board title is wrong");
    }

    @Test
    public void deleteNewBoardCheck() {
        System.setProperty("webdriver.chrome.driver", "C://Users//Office//Downloads//chromedriver_win32 (1)//chromedriver87.exe");
        ChromeDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String confirmationMessage = driver.findElementByXPath(".//div[@class='big-message quiet closed-board']//h1").getText();

        driver.get("https://trello.com/");
        driver.findElementByLinkText("Log In").click();
        driver.findElementById("user").sendKeys("fesenko.b@icloud.com");
        driver.findElementById("password").sendKeys("B0r1sTr3ll0");
        driver.findElementById("login").click();
        Waiters.waitSeconds(4);
        driver.findElementByXPath(".//button[@aria-label= 'Open Boards Menu']//span").click();
        driver.findElementByXPath(".//a[@title= '" + newBoardTitle + "']").click();


        WebElement moreLink = driver.findElementByXPath(".//a[@class='board-menu-navigation-item-link js-open-more']");
        js.executeScript("arguments[0].click();", moreLink);

        WebElement closeBoardLink = driver.findElementByXPath(".//a[@class='board-menu-navigation-item-link js-close-board']");
        js.executeScript("arguments[0].click();", closeBoardLink);
        WebElement closeBoardLinkConfirm = driver.findElementByXPath(".//input[@value='Close']");
        js.executeScript("arguments[0].click();", closeBoardLinkConfirm);

        Assert.assertEquals(confirmationMessage, newBoardTitle+" is closed.", "The deleted board is wrong");
    }
}
