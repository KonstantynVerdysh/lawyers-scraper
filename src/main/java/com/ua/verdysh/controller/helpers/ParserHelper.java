package com.ua.verdysh.controller.helpers;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ParserHelper {
    private ParserHelper() {}

    public static String getText(WebDriver driver, String selector) {
        if (!driver.findElements(By.xpath(selector)).isEmpty()) {
            return driver.findElement(By.xpath(selector)).getText();
        }
        return "";
    }

    public static String getText(WebDriver driver, String selector, String attribute) {
        if (!driver.findElements(By.xpath(selector)).isEmpty()) {
            return driver.findElement(By.xpath(selector)).getAttribute(attribute);
        }
        return "";
    }

    public static String cutMail(String stringWithMail) {
        return StringUtils.substringBetween(stringWithMail, ":", "?");
    }
}
