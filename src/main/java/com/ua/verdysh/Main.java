package com.ua.verdysh;

import com.ua.verdysh.controller.LawyersScraper;
import com.ua.verdysh.model.LawyerProfile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        System.setProperty("web.gecko.driver", "geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        LawyersScraper scraper = new LawyersScraper();
        Map<String, List<LawyerProfile>> profiles = scraper.getProfiles(driver);
        driver.quit();
    }
}