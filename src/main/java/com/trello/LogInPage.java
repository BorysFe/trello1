package com.trello;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {

    WaitUtils waitUtils;

    @FindBy(xpath = ".//a[@href='/login']")
    private WebElement openLogInPageButton;

    @FindBy(xpath = ".//input[@id= 'user']")
    private WebElement userField;

    @FindBy(xpath = ".//input[@id= 'password']")
    private WebElement passwordField;

    @FindBy(xpath = ".//input[@id= 'login']")
    private WebElement logInButton;

    @FindBy(xpath = ".//div[@id= 'error']")
    private WebElement errorMessage;

    public LogInPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        waitUtils = new WaitUtils(driver);
    }

    public void logInNewUser(String userEmail, String userPassword) {
        waitUtils.waitElementToBeClickableShort(openLogInPageButton);
        openLogInPageButton.click();
        waitUtils.waitVisibilityOfElementLong(userField);
        userField.sendKeys(userEmail);
        waitUtils.waitVisibilityOfElementShort(passwordField);
        passwordField.sendKeys(userPassword);
        waitUtils.waitElementToBeClickableShort(logInButton);
        logInButton.click();
    }

    public void logInWithEmail(String userEmail) {
        waitUtils.waitElementToBeClickableShort(openLogInPageButton);
        openLogInPageButton.click();
        waitUtils.waitVisibilityOfElementLong(userField);
        userField.sendKeys(userEmail);
        waitUtils.waitElementToBeClickableShort(logInButton);
        logInButton.click();
    }

    public void openLogInPage() {
        waitUtils.waitElementToBeClickableShort(openLogInPageButton);
        openLogInPageButton.click();
    }

    public String getLoginButtonLocation() {
        waitUtils.waitVisibilityOfElementShort(logInButton);
        return logInButton.getLocation()
                          .toString();
    }

    public String getTextErrorMessage() {
        waitUtils.waitVisibilityOfElementLong(errorMessage);
        return errorMessage.getText();
    }
}