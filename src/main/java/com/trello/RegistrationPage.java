package com.trello;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
    WaitUtils waitUtils;

    @FindBy(xpath = ".//a[@href='/signup']")
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

    @FindBy(xpath = ".//div[@data-test-id= 'moonshot-team-type-select']")
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
        waitUtils = new WaitUtils(driver);
    }

    public void openSignUpPage() {
        waitUtils.waitVisibilityOfElementLong(signUpLink);
        signUpLink.click();
        waitUtils.waitInvisibilityOfElementLong(signUpLink);
    }

    public void setFirstSignUpPage(String userEmail) {
        waitUtils.waitElementToBeClickableLong(signUpLink);
        signUpLink.click();
        waitUtils.waitVisibilityOfElementLong(emailField);
        emailField.sendKeys(userEmail);
        waitUtils.waitElementToBeClickableShort(continueFirstPageBtn);
        continueFirstPageBtn.submit();
        waitUtils.waitInvisibilityOfElementShort(continueFirstPageBtn);
    }

    public void setSecondSignUpPage(String userName, String userPassword) {
        waitUtils.waitVisibilityOfElementLong(signUpLink);
        nameField.sendKeys(userName);
        waitUtils.waitVisibilityOfElementLong(signUpLink);
        passwordField.sendKeys(userPassword);
        waitUtils.waitElementToBeClickableLong(signUpLink);
        continueSecondPageBtn.click();
        waitUtils.waitInvisibilityOfElementShort(continueFirstPageBtn);

    }

    public void setThirdSignUpPage(String teamName) {
        waitUtils.waitVisibilityOfElementLong(newTeamField);
        newTeamField.sendKeys(teamName);
        waitUtils.waitVisibilityOfElementShort(newTeamField);
        teamTypesDropdown.click();
        waitUtils.waitVisibilityOfElementShort(newTeamField);
        firstTeamType.click();
        waitUtils.waitVisibilityOfElementShort(newTeamField);
        continueBtnOnTeamPage.click();
        waitUtils.waitInvisibilityOfElementLong(continueFirstPageBtn);
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