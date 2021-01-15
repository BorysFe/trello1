package com.trello.boards;

import com.trello.BoardPage;
import com.trello.LogInPage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NewBoardTest {

    private ChromeDriver driver;

    @BeforeClass
    public void webDriver() {
        System.setProperty("webdriver.chrome.driver", "C://Users//Office//Downloads//chromedriver_win32 (1)//chromedriver87.exe");
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void signOffFromTrello() {
//        System.setProperty("webdriver.chrome.driver", "C://Users//Office//Downloads//chromedriver_win32 (1)//chromedriver87.exe");
//        ChromeDriver driver = new ChromeDriver();

//        List<WebElement> openMemberMenu = driver.findElementsByXPath(".//button[contains(@aria-label, 'Open Member Menu')]");
//
//        if (openMemberMenu.size() > 0) {
//            openMemberMenu.get(0).click();
//            driver.findElementByLinkText("Sign Off").click();
//        }

        BoardPage boardPage = new BoardPage(driver);
        boardPage.logOutUser();

    }

    @Test
    public void searchBoardFieldCheck() {
        LogInPage logInPage = new LogInPage(driver);
        BoardPage boardPage = new BoardPage(driver);

        logInPage.logInExistedUser1();

        Assert.assertEquals(boardPage.searchField.getAttribute("placeholder"), "Find boards by name…", "Placeholder is wrong");
    }

    @Test
    public void userExistedBoardCheck() {
        LogInPage logInPage = new LogInPage(driver);
        BoardPage boardPage = new BoardPage(driver);
        Actions action = new Actions(driver);

        WebElement userBoard = driver.findElement(By.xpath(".//a[@title= 'Borys_Tech-Stack']"));
        String boardTitle = driver.findElement(By.xpath(".//input[@class= 'board-name-input js-board-name-input']"))
                                  .getAttribute("value");

        logInPage.logInExistedUser1();
        boardPage.openMemberMenu();

        Assert.assertTrue(userBoard.isEnabled(), "The board isn't found");

        action.click(userBoard).build().perform();

        Assert.assertEquals(boardTitle, "Borys_Tech-Stack", "The board title is wrong");
    }

    @Test
    public void addNewBoardCheck() {
        LogInPage logInPage = new LogInPage(driver);
        BoardPage boardPage = new BoardPage(driver);

        String newBoardTitle = "Auto test - "+System.currentTimeMillis();

        logInPage.logInExistedUser1();
        boardPage.openMemberMenu();
        boardPage.addNewBoard(newBoardTitle);

        Assert.assertEquals(boardPage.boardTitle.getAttribute("value"), newBoardTitle, "The board title is wrong");
        Assert.assertEquals(boardPage.getClosedBoardMessage(), String.format("%s is closed.", newBoardTitle), "The deleted board is wrong");
    }

    @Test
    public void permanentlyDeleteBoardCheck() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        LogInPage logInPage = new LogInPage(driver);
        BoardPage boardPage = new BoardPage(driver);

        String newBoardTitle = "Auto test - "+System.currentTimeMillis();

        logInPage.logInExistedUser1();
        boardPage.openMemberMenu();
        boardPage.addNewBoard(newBoardTitle);
        boardPage.closeBoard();

        js.executeScript("arguments[0].click();", boardPage.deleteBoard);
        js.executeScript("arguments[0].click();", boardPage.confirmationDeleteBoardBtn);

        Assert.assertEquals(boardPage.deleteBoardMessage.getText(), "Board not found.", "With deleting of the board something went wrong");
    }
}
