package com.trello;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Test1 {
    @Test
    public void test1() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://trello.com/");

    }
}
