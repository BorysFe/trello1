package com.trello;

import static com.trello.Waiters.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BoardPage {

    @FindBy(xpath = ".//button[@aria-label= 'Open Member Menu']")
    private WebElement openMemberMenuButton;

    @FindBy(xpath = ".//button[contains(@aria-label, 'Open Member Menu')]")
    List<WebElement> openMemberMenuList;

    @FindBy(xpath = ".//button[contains(@aria-label, 'Open Boards Menu')]")
    private WebElement openBoardsMenuButton;

    @FindBy(xpath = ".//button[@data-test-id = 'header-member-menu-logout']")
    private WebElement menuLogOutLink;

    @FindBy(xpath = ".//input[@name= 'search-boards']")
    private WebElement searchField;

    @FindBy(xpath = ".//button[@data-test-id= 'header-boards-menu-create-board']")
    private WebElement newBoardLink;

    @FindBy(xpath = ".//input[@placeholder= 'Add board title']")
    private WebElement newBoardTitleField;

    @FindBy(xpath = ".//button[@data-test-id='create-board-submit-button']")
    private WebElement newBoardSubmit;

    @FindBy(xpath = ".//input[@class= 'board-name-input js-board-name-input']")
    private WebElement boardTitle;

    @FindBy(xpath = ".//a[@class='board-menu-navigation-item-link js-open-more']")
    private WebElement openMoreBtn;

    @FindBy(xpath = ".//a[@class='board-menu-navigation-item-link js-close-board']")
    private WebElement closeBoardBtn;

    @FindBy(xpath = ".//input[@value='Close']")
    private WebElement confirmationCloseBoardBtn;

    @FindBy(xpath = ".//div[@class='big-message quiet closed-board']//h1")
    private WebElement closeBoardMessage;

    @FindBy(xpath = ".//a[@class='quiet js-delete']")
    private WebElement deleteBoard;

    @FindBy(xpath = ".//input[@value='Delete']")
    private WebElement confirmationDeleteBoardBtn;

    @FindBy(xpath = ".//div[@class= 'js-react-root']//h1")
    private WebElement deleteBoardMessage;

    private final String userBoard = ".//a[@title= '%s']";

    public BoardPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void logOutIfAuthorised() {
         if (openMemberMenuList.size() > 0) {
             if (openBoardsMenuButton.isDisplayed()) {
                 logOutUser();
             }
        }
    }

    public void logOutUser() {
        openMemberMenuButton.click();
        menuLogOutLink.click();
    }

    public void openMemberMenu() {
        openMemberMenuButton.click();
    }

    public void openBoardsMenu() {
        openBoardsMenuButton.click();
    }

    public void addNewBoard(String newBoardTitle) {
        openBoardsMenuButton.click();
        newBoardLink.click();
        waitSeconds(2);
        newBoardTitleField.clear();
        newBoardTitleField.sendKeys(newBoardTitle);
        newBoardSubmit.click();
        waitSeconds(2);
    }

    public String getClosedBoardMessage() {
        return closeBoardMessage.getText();
    }

    public void closeBoard() {
        waitSeconds(2);
        openMoreBtn.click();
        waitSeconds(2);
        closeBoardBtn.click();
        waitSeconds(2);
        confirmationCloseBoardBtn.click();
        waitSeconds(2);
    }

    public void deleteBoardPermanently() {
        waitSeconds(2);
        closeBoard();
        deleteBoard.click();
        waitSeconds(2);
        confirmationDeleteBoardBtn.click();
        waitSeconds(2);
    }

    public String getSearchFieldAttribute(String attributeName) {
        return searchField.getAttribute(attributeName);
    }

    public String getBoardTitleAttribute(String attributeName) {
        return boardTitle.getAttribute(attributeName);
    }

    public String getDeletedMessage() {
        return deleteBoardMessage.getText();
    }

    public String getCustomUserBoardTitle(String boardTitle) {
        return String.format(userBoard, boardTitle);
    }
}