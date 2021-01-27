package com.trello;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
    private WebElement logInBtnGreen;

    @FindBy(xpath = ".//input[@value= 'Log in with Atlassian']")
    private WebElement loginBtnAtlassian;

    @FindBy(xpath = ".//button[@id='login-submit']")
    private WebElement loginBtnBlue;

    @FindBy(xpath = ".//div[@id= 'error']")
    private WebElement errorMessage;

    public LogInPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        waitUtils = new WaitUtils(driver);
    }

    @Before
    public void openLoginPageFactory(ChromeDriver driver) {
        PageFactory.initElements(driver, LogInPage.class)
                   .openLogInPage();
    }

    public void logInNewUser(String userEmail, String userPassword) {

        waitUtils.waitElementToBeClickableShort(openLogInPageButton);
        openLogInPageButton.click();
        waitUtils.waitVisibilityOfElementLong(userField);
        userField.sendKeys(userEmail);
        if (waitUtils.waitVisibilityOfElementBooleanShort(loginBtnAtlassian)) {
            loginBtnAtlassian.click();
            waitUtils.waitVisibilityOfElementShort(passwordField);
            passwordField.sendKeys(userPassword);
            waitUtils.waitElementToBeClickableShort(loginBtnBlue);
            loginBtnBlue.click();
        } else {
            waitUtils.waitVisibilityOfElementShort(passwordField);
            passwordField.sendKeys(userPassword);
            waitUtils.waitElementToBeClickableShort(logInBtnGreen);
            logInBtnGreen.click();
        }
    }

    public void logInNewUserFactory(User user) {
        waitUtils.waitVisibilityOfElementLong(userField);
        userField.sendKeys(user.getUserLogin());
        if (waitUtils.waitVisibilityOfElementBooleanShort(loginBtnAtlassian)) {
            loginBtnAtlassian.click();
            waitUtils.waitVisibilityOfElementShort(passwordField);
            passwordField.sendKeys(user.getUserPassword());
            waitUtils.waitElementToBeClickableShort(loginBtnBlue);
            loginBtnBlue.click();
        } else {
            waitUtils.waitVisibilityOfElementShort(passwordField);
            passwordField.sendKeys(user.getUserPassword());
            waitUtils.waitElementToBeClickableShort(logInBtnGreen);
            logInBtnGreen.click();
        }
    }

    public WebElement openLogInPage() {
        waitUtils.waitElementToBeClickableShort(openLogInPageButton);
        openLogInPageButton.click();
        return userField;
    }

    public String getLoginButtonLocation() {
        waitUtils.waitVisibilityOfElementShort(logInBtnGreen);
        return logInBtnGreen.getLocation()
                            .toString();
    }

    public String getTextErrorMessage() {
        waitUtils.waitVisibilityOfElementLong(errorMessage);
        return errorMessage.getText();
    }
}