package com.ua.verdysh.controller.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LawyersScraperHelper {
    private static final String WEBSITE = "https://www.baker-law.co.uk/";
    private static final String STAFF_BUTTON = "li.item.item-people.has-sub-menu > a";

    private LawyersScraperHelper() {}

    public static void loadMainPage(WebDriver driver) {
        driver.get(WEBSITE);
    }

    public static void loadStaffPage(WebDriver driver) {
        driver.findElement(By.cssSelector(STAFF_BUTTON)).click();
    }
}
