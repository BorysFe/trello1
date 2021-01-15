package com.trello;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BoardPage {

    @FindBy (xpath = ".//button[contains(@aria-label, 'Open Member Menu')]")
    private WebElement openMemberMenuButton;

    @FindBy (xpath = ".//button[contains(@aria-label, 'Open Boards Menu')]")
    private WebElement openBoardsMenuButton;

    @FindBy (xpath =  ".//button[@data-test-id = 'header-member-menu-logout']")
    private WebElement menuLogOutLink;

    @FindBy (xpath = ".//input[@name= 'search-boards']")
    public WebElement searchField;

    @FindBy (xpath = ".//button[@data-test-id= 'header-boards-menu-create-board']")
    private WebElement newBoardLink;

    @FindBy (xpath = ".//input[@placeholder= 'Add board title']")
    private WebElement newBoardTitleField;

    @FindBy (xpath = ".//button[@data-test-id='create-board-submit-button']")
    private WebElement newBoardSubmit;

    @FindBy (xpath = ".//input[@class= 'board-name-input js-board-name-input']")
    public WebElement boardTitle;

    @FindBy (xpath = ".//a[@class='board-menu-navigation-item-link js-open-more']")
    private WebElement openMoreBtn;

    @FindBy (xpath = ".//a[@class='board-menu-navigation-item-link js-close-board']")
    private WebElement closeBoardBtn;

    @FindBy (xpath = ".//input[@value='Close']")
    private WebElement confirmationCloseBoardBtn;

    @FindBy (xpath = ".//div[@class='big-message quiet closed-board']//h1")
    private WebElement closeBoardMessage;

    @FindBy (className = "quiet js-delete")
    public  WebElement deleteBoard;

    @FindBy (xpath = ".//input[@value='Delete']")
    public WebElement confirmationDeleteBoardBtn;

    @FindBy (xpath = ".//div[@class= 'js-react-root']//h1")
    public WebElement deleteBoardMessage;

    public BoardPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void logOutUser() {
        if (openMemberMenuButton.isDisplayed()) {
            openMemberMenuButton.click();
            menuLogOutLink.click();
        }
    }

    public void openSearchField() {
        searchField.click();
    }

    public void openMemberMenu() {
        openMemberMenuButton.click();
    }

    public void newBoardTitle() {

    }

    public void addNewBoard(String newBoardTitle) {
        openBoardsMenuButton.click();
        newBoardLink.click();
        Waiters.waitSeconds(4);
        newBoardTitleField.clear();
        newBoardTitleField.sendKeys(newBoardTitle);
        newBoardSubmit.click();
        Waiters.waitSeconds(3);
    }

    public String getClosedBoardMessage() {
    return closeBoardMessage.getText();
    }

    public void closeBoard() {
        Waiters.waitSeconds(4);
        openMoreBtn.click();
        closeBoardBtn.click();
        confirmationCloseBoardBtn.submit();
    }
}