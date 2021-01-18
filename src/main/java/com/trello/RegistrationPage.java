package com.trello;

import static com.trello.Waiters.waitSeconds;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

    @FindBy(linkText = "Sign Up")
    private WebElement signUpLink;

    @FindBy(xpath = "//p[@class='quiet tos']")
    private WebElement privacyPolicyText;

    @FindBy(xpath = ".//input[@id= 'email']")
    private WebElement emailField;

    @FindBy(xpath = ".//input[@id= 'signup-submit']")
    private WebElement continueFirstPageBtn;

    @FindBy(xpath = ".//input[@id= 'displayName']")
    private WebElement nameField;

    @FindBy(xpath = ".//button[@id= 'signup-submit']")
    private WebElement continueSecondPageBtn;

    @FindBy(xpath = ".//input[@id= 'password']")
    private WebElement passwordField;

    @FindBy(xpath = ".//input[@id= 'moonshotCreateTeam']")
    private WebElement newTeamField;

    @FindBy(className = "nch-select")
    private WebElement teamTypesDropdown;

    @FindBy(xpath = ".//div[@id= 'react-select-2-option-0']/li")
    private WebElement firstTeamType;

    @FindBy(xpath = ".//button[@type= 'submit']")
    private WebElement continueBtnOnTeamPage;

    @FindBy(xpath = "//p[@class= 'error-message']")
    private WebElement errorMessage;

    @FindBy(xpath = ".//button[@data-test-id= 'moonshot-try-bc-free-trial']")
    private WebElement trialBtn;

    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void openSignUpPage() {
        signUpLink.click();
        waitSeconds(2);
    }

    public void setFirstSignUpPage(String userEmail) {
        signUpLink.click();
        emailField.sendKeys(userEmail);
        continueFirstPageBtn.submit();
        waitSeconds(4);
    }

    public void setSecondSignUpPage(String userName, String userPassword) {
        nameField.sendKeys(userName);
        passwordField.sendKeys(userPassword);
        continueSecondPageBtn.click();
    }

    public void setThirdSignUpPage(String teamName) {
        newTeamField.sendKeys(teamName);
        teamTypesDropdown.click();
        firstTeamType.click();
        continueBtnOnTeamPage.click();
        waitSeconds(4);
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public String getPrivacyPolicyText() {
        return privacyPolicyText.getText();
    }

    public String getButtonText() {
        return trialBtn.getText();
    }
}