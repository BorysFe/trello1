package steps;

import com.trello.BoardPage;
import com.trello.LogInPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginStepDefs {
    public ChromeDriver driver;
    private LogInPage logInPage;
    private BoardPage boardPage;

    @Before
    public void fffff() {
        WebDriverManager.chromedriver()
                        .setup();
        driver = new ChromeDriver();
        logInPage = new LogInPage(driver);
        boardPage = new BoardPage(driver);
        driver.get("https://trello.com");
    }

    @Given("I launch chrome browser")
    public void i_launch_chrome_browser() {
        logInPage.openLogInPage();
    }

    @When("I open site {string} homepage")
    public void OpenSiteHomepage(String arg0) {
        logInPage.openLogInPage();
    }

    @And("Open login Page")
    public void open_Page() {
        logInPage.openLogInPage();
    }

    @And("Set login fields with login {string} and password {string}")
    public void setLoginFieldsWithLoginAndPassword(String arg0, String arg1) {
        logInPage.loginUser(arg0, arg1);
    }

    @Then("I verify that {string} message displayed")
    public void iVerifyThatMessageDisplayed(String arg0) {
        Assert.assertEquals(logInPage.getTextErrorMessage(), arg0);
    }

    @And("Close browser")
    public void closeBrowser() {
        driver.quit();
    }

    @Then("I verify Team board title {string}")
    public void iVerifyTeamBoardTitle(String arg0) {
        Assert.assertEquals(boardPage.getTeamBoardTitle(), arg0);
    }

    @Then("I verify button {string} location - {string}")
    public void iVerifyButtonLocation(String arg0, String arg1) {
        WebElement logInBtnGreen = driver.findElement(By.xpath(String.format(".//input[@id= '%s']", arg0)));
        Assert.assertEquals(logInBtnGreen.getLocation()
                                         .toString(), arg1);
    }
}
