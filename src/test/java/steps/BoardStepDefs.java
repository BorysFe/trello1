package steps;

import com.trello.BoardPage;
import com.trello.LogInPage;
import com.trello.WaitUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BoardStepDefs {

    WaitUtils waitUtils;
    public ChromeDriver driver;
    private LogInPage logInPage;
    private BoardPage boardPage;
    private WebElement userBoard;

    private final String boardTitle = ".//a[@title= '%s']";

    @BeforeStep
    public void openMainPage() {
        logInPage = new LogInPage(driver);
        boardPage = new BoardPage(driver);
    }

    @And("Open Boards Menu")
    public void openBoardsMenu() {
        boardPage.openBoardsMenu();
    }

    @Then("Expected {string} must be {string}. If failed - write text {string}")
    public void expectedMustBeIfFailedWriteText(String arg0, String arg1, String arg2) {
        Assert.assertEquals(boardPage.getSearchFieldAttribute(arg0), arg1, arg2);
    }

    @Then("Expected the board title")
    public void expectedTheBoardTitle() {
        Assert.assertTrue(userBoard.isEnabled(), "The board isn't found");
    }

    @And("The board title is {string}")
    public void theBoardTitleIs(String arg0) {
        Assert.assertEquals(boardPage.getBoardTitleText(), arg0, "The board title is wrong");
    }

    @And("Add new board with title {string}")
    public void addNewBoardWithTitle(String arg0) {
        boardPage.openMemberMenu();
        boardPage.addNewBoard(arg0);
    }

    @And("Close board {string}")
    public void closeBoard(String arg0) {
        boardPage.closeBoard();
        Assert.assertEquals(boardPage.getClosedBoardMessage(), String.format("%s is closed.", arg0), "The " +
                "deleted board is wrong");
    }
}
