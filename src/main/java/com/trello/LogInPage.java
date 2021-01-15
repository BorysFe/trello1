package com.trello;

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

    @FindBy(className = "error")
    private WebElement edrrorMessage;

    @FindBy(css = "error")
    private WebElement erddrorMessage;

    @FindBy(name = "error")
    private WebElement errorMeпssage;

    @FindBy(partialLinkText = "error")
    private WebElement errorMаessage;

    @FindBy(tagName = "error")
    private WebElement erdddrorMessage;


    String emailUser1 = "fesenko.b@icloud.com";
    String passwordUser1 = "B0r1sTr3ll0";

    public LogInPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        driver.get("https://trello.com");
        openLogInPageButton.click();
    }

    public void openLogInPage() {
        openLogInPageButton.click();
    }

    public void logInExistedUser1() {
        userField.sendKeys(emailUser1);
        passwordField.sendKeys(passwordUser1);
        logInButton.click();
        Waiters.waitSeconds(4);
    }

    public void logInNewUser(String userEmail, String userPassword) {
        userField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        logInButton.click();
        Waiters.waitSeconds(4);
    }

    public String getTextErrorMessage() {
        return  errorMessage.getText();
    }


}