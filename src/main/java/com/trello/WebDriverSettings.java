package com.trello;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

public class WebDriverSettings {

    public ChromeDriver driver;

    @BeforeClass

    public void webDriver() {
        System.setProperty("webdriver.chrome.driver", "C://Users//Office//Downloads//chromedriver_win32 (1)//chromedriver87.exe");
    }
}
