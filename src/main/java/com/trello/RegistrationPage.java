package com.trello;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

    @FindBy(linkText = "Sign Up")
    private WebElement signUpLink;

    @FindBy(linkText = ".//input[@type= 'email']")
    private WebElement emailField;

    @FindBy(xpath = ".//input[@id=\"signup-submit\"]")
    private WebElement continueBtn;

    @FindBy(xpath = ".//input[@id=\"displayName\"]")
    public WebElement nameField;

    @FindBy(xpath = ".//input[@id= 'password']")
    private WebElement passwordField;

    @FindBy(xpath = ".//input[@id= 'moonshotCreateTeam'")
    private WebElement newTeamField;

    @FindBy(className = "nch-select")
    private WebElement teamTypesDropdown;

    @FindBy(xpath = ".//div[@id='react-select-2-option-0']/li")
    private WebElement firstTeamType;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement continueBtnOnTeamPage;

    @FindBy(xpath = "//p[@class= 'error-message']")
    private WebElement errorMessage;

    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        driver.get("https://trello.com");
        signUpLink.click();
    }

    public void firstSignUpPage(String userEmail) {
        Waiters.waitSeconds(4);
        emailField.sendKeys(userEmail);
        continueBtn.submit();
        Waiters.waitSeconds(4);
    }

    public void secondSignUpPage(String userPassword, String userName) {
        nameField.sendKeys(userName);
        passwordField.sendKeys(userPassword);
        continueBtn.click();
    }

    public void thirdSignUpPage(String teamName) {
        newTeamField.sendKeys(teamName);
        teamTypesDropdown.click();
        firstTeamType.click();
        continueBtnOnTeamPage.click();
        Waiters.waitSeconds(4);
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}