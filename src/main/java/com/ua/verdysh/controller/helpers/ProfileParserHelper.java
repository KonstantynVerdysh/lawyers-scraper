package com.ua.verdysh.controller.helpers;

import com.ua.verdysh.model.LawyerProfile;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

public class ProfileParserHelper {
    private ProfileParserHelper() {}

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

    public static List<LawyerProfile> createNewProfiles(List<String> profilesUrl) {
        return profilesUrl.stream()
                .map(ProfileParserHelper::setUrlToProfile)
                .collect(Collectors.toList());
    }

    private static LawyerProfile setUrlToProfile(String url) {
        LawyerProfile profile = new LawyerProfile();
        profile.setUrl(url);
        return profile;
    }
}
