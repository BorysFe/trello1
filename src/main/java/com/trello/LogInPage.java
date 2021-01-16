package com.trello;

import static com.trello.Waiters.waitSeconds;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {

    @FindBy(linkText = "Log In")
    private WebElement openLogInPageButton;

    @FindBy(id = "user")
    private WebElement userField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login")
    private WebElement logInButton;

    @FindBy(id = "error")
    private WebElement errorMessage;

    public LogInPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        driver.get("https://trello.com");
        openLogInPageButton.click();
    }

    public void logInNewUser(String userEmail, String userPassword) {
        userField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        logInButton.click();
        waitSeconds(4);
    }

    public void logInWithEmail(String userEmail) {
        userField.sendKeys(userEmail);
        waitSeconds(3);
        logInButton.click();
        waitSeconds(3);
    }

    public String getTextErrorMessage() {
        return errorMessage.getText();
    }
}