package com.trello.boards;

import com.trello.BoardPage;
import com.trello.LogInPage;
import com.trello.WaitUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NewBoardTest {

    public ChromeDriver driver;
    WaitUtils waitUtils;

    String emailUser = "fesenko.b@icloud.com";
    String passwordUser = "B0r1sTr3ll0";

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
        boardPage.logOutUser();
    }

    @AfterClass
    public void browserQuit() {
        driver.quit();
    }

    @Test
    public void searchBoardFieldCheck() {
        LogInPage logInPage = new LogInPage(driver);
        BoardPage boardPage = new BoardPage(driver);

        logInPage.logInNewUser(emailUser, passwordUser);

        Assert.assertEquals(boardPage.getSearchFieldAttribute("placeholder"), "Find boards by name…", "Placeholder is" +
                " wrong");
    }

    @Test
    public void userExistedBoardCheck() {
        LogInPage logInPage = new LogInPage(driver);
        BoardPage boardPage = new BoardPage(driver);
        Actions action = new Actions(driver);

        String boardTitle = "Borys Trello 1";

        logInPage.logInNewUser(emailUser, passwordUser);
        boardPage.openBoardsMenu();

        WebElement userBoard = driver.findElement(By.xpath(boardPage.getCustomUserBoardTitle(boardTitle)));

        Assert.assertTrue(userBoard.isEnabled(), "The board isn't found");

        action.click(userBoard)
              .build()
              .perform();
        waitUtils.waitInvisibilityOfElementShort(userBoard);
        Assert.assertEquals(boardPage.getBoardTitleAttribute("value"), boardTitle, "The board title is wrong");
    }

    @Test
    public void addNewBoardCheck() {
        LogInPage logInPage = new LogInPage(driver);
        BoardPage boardPage = new BoardPage(driver);

        String newBoardTitle = "Auto test - " + System.currentTimeMillis();

        logInPage.logInNewUser(emailUser, passwordUser);
        boardPage.openMemberMenu();
        boardPage.addNewBoard(newBoardTitle);

        Assert.assertEquals(boardPage.getBoardTitleAttribute("value"), newBoardTitle, "The board title is wrong");

        boardPage.closeBoard();
        Assert.assertEquals(boardPage.getClosedBoardMessage(), String.format("%s is closed.", newBoardTitle), "The " +
                "deleted board is wrong");
    }

    @Test
    public void permanentlyDeleteBoardCheck() {
        LogInPage logInPage = new LogInPage(driver);
        BoardPage boardPage = new BoardPage(driver);

        String newBoardTitle = String.format("Auto test - %s", System.currentTimeMillis());

        logInPage.logInNewUser(emailUser, passwordUser);
        boardPage.openMemberMenu();
        boardPage.addNewBoard(newBoardTitle);
        boardPage.deleteBoardPermanently();

        Assert.assertEquals(boardPage.getDeletedMessage(), "Board not found.", "With deleting of the board something " +
                "went wrong");
    }
}