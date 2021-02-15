package steps;

import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {

    public ChromeDriver driver;

    @Before
    public void webDriver() {
        WebDriverManager.chromedriver()
                        .setup();
        driver = new ChromeDriver();
        driver.get("https://trello.com");
    }

//    @After
//    public void closeBrowser() {
//    }
}
