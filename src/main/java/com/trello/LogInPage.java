package com.trello;

import static com.trello.Waiters.waitSeconds;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {

    @FindBy(xpath = ".//a[@href='/login']")
//    @FindBys({ @FindBy(xpath = ".//a[@href='/login']"), @FindBy(xpath = ".//a[@class= 'btn btn-sm btn-link
//    text-white']") })
    private WebElement openLogInPageButton;

    @FindBy(xpath = ".//input[@id= 'password']/../preceding-sibling::input")
    private WebElement userField;

    @FindBy(xpath = ".//input[@id= 'password']")
    private WebElement passwordField;

    @FindBy(xpath = ".//input[@id= 'password']/../../following-sibling::input")
    private WebElement logInButton;

    @FindBy(xpath = ".//input[@id= 'password']/ancestor::div[count(*) > 1]//p")
    private WebElement errorMessage;

    public LogInPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void logInNewUser(String userEmail, String userPassword) {
        openLogInPageButton.click();
        userField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        logInButton.click();
        waitSeconds(4);
    }

    public void logInWithEmail(String userEmail) {
        openLogInPageButton.click();
        userField.sendKeys(userEmail);
        waitSeconds(3);
        logInButton.click();
        waitSeconds(3);
    }

    public void openLogInPage() {
        openLogInPageButton.click();
    }

    public String getLoginButtonLocation() {
        return logInButton.getLocation()
                          .toString();
    }

    public String getTextErrorMessage() {
        return errorMessage.getText();
    }
}