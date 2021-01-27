package com.trello;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BoardPage {

    WaitUtils waitUtils;

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

    @FindBy(xpath = ".//h1[@class= 'js-board-editing-target board-header-btn-text']")
    private WebElement boardTitle;

    @FindBy(xpath = ".//a[@class='board-menu-navigation-item-link js-open-more']")
    private WebElement openMoreBtn;

    @FindBy(xpath = ".//a[@class='board-menu-navigation-item-link js-close-board']")
    private WebElement closeBoardBtn;

    @FindBy(xpath = ".//input[@value='Close']")
    private WebElement confirmationCloseBoardBtn;

    @FindBy(xpath = ".//div[@class='big-message quiet closed-board']//h1")
    private WebElement closeBoardMessage;

    @FindBy(xpath = ".//div[@class= 'js-react-root']//p")
    private WebElement textAfterDeleteBoard;

    @FindBy(xpath = ".//a[@class='quiet js-delete']")
    private WebElement deleteBoard;

    @FindBy(xpath = ".//input[@value='Delete']")
    private WebElement confirmationDeleteBoardBtn;

    @FindBy(xpath = ".//div[@class= 'js-react-root']//h1")
    private WebElement deleteBoardMessage;

    @FindBy(xpath = ".//span[@aria-label= 'TemplateBoardIcon']/../following-sibling::h2")
    private WebElement mostPopularTemplatesTitle;

    private final String userBoard = ".//a[@title= '%s']";

    public BoardPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        waitUtils = new WaitUtils(driver);
    }

    public void logOutIfAuthorised() {
        if (openMemberMenuList.size() > 0) {
            if (openBoardsMenuButton.isDisplayed()) {
                logOutUser();
            }
        }
    }

    public void logOutUser() {
        waitUtils.waitElementToBeClickableLong(openMemberMenuButton);
        openMemberMenuButton.click();
        waitUtils.waitElementToBeClickableLong(menuLogOutLink);
        menuLogOutLink.click();
    }

    public void openMemberMenu() {
        waitUtils.waitVisibilityOfElementLong(openBoardsMenuButton);
        openMemberMenuButton.click();
    }

    public void openBoardsMenu() {
        waitUtils.waitElementToBeClickableLong(openBoardsMenuButton);
        openBoardsMenuButton.click();
    }

    public void addNewBoard(String newBoardTitle) {
        openBoardsMenuButton.click();
        waitUtils.waitElementToBeClickableShort(newBoardLink);
        newBoardLink.click();
        waitUtils.waitVisibilityOfElementShort(newBoardTitleField);
        newBoardTitleField.clear();
        waitUtils.waitVisibilityOfElementShort(newBoardTitleField);
        newBoardTitleField.sendKeys(newBoardTitle);
        waitUtils.waitElementToBeClickableLong(newBoardSubmit);
        newBoardSubmit.click();
    }

    public String getClosedBoardMessage() {
        waitUtils.waitVisibilityOfElementShort(closeBoardMessage);
        return closeBoardMessage.getText();
    }

    public void closeBoard() {
        waitUtils.waitElementToBeClickableLong(openMoreBtn);
        openMoreBtn.click();
        waitUtils.waitElementToBeClickableShort(closeBoardBtn);
        closeBoardBtn.click();
        waitUtils.waitElementToBeClickableShort(confirmationCloseBoardBtn);
        confirmationCloseBoardBtn.click();
    }

    public void deleteBoardPermanently() {
        closeBoard();
        waitUtils.waitVisibilityOfElementLong(deleteBoard);
        deleteBoard.click();
        waitUtils.waitVisibilityOfElementShort(confirmationDeleteBoardBtn);
        confirmationDeleteBoardBtn.click();

        waitUtils.waitVisibilityOfElementLong(textAfterDeleteBoard);
    }

    public String getSearchFieldAttribute(String attributeName) {
        waitUtils.waitVisibilityOfElementShort(searchField);
        return searchField.getAttribute(attributeName);
    }

    public String getBoardTitleAttribute() {
        waitUtils.waitVisibilityOfElementShort(boardTitle);
        return boardTitle.getText();
    }

    public String getDeletedMessage() {
        waitUtils.waitVisibilityOfElementLong(deleteBoardMessage);
        return deleteBoardMessage.getText();
    }

    public String getCustomUserBoardTitle(String boardTitle) {
        waitUtils.waitVisibilityOfElementByLong(By.xpath(String.format(userBoard, boardTitle)));
        return String.format(userBoard, boardTitle);
    }

    public String getTeamBoardTitle() {
        waitUtils.waitVisibilityOfElementLong(mostPopularTemplatesTitle);
        return mostPopularTemplatesTitle.getText();
    }
}
