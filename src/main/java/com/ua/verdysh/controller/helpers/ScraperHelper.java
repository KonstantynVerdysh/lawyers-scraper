package com.ua.verdysh.controller.helpers;

import com.ua.verdysh.model.LawyerProfile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

public class ScraperHelper {
    private static final String WEBSITE = "https://www.baker-law.co.uk/";
    private static final String STAFF_BUTTON = "li.item.item-people.has-sub-menu > a";

    private ScraperHelper() {}

    public static void loadMainPage(WebDriver driver) {
        driver.get(WEBSITE);
    }

    public static void loadStaffPage(WebDriver driver) {
        driver.findElement(By.cssSelector(STAFF_BUTTON)).click();
    }

    public static List<LawyerProfile> createNewProfiles(List<String> profilesUrl) {
        return profilesUrl.stream()
                .map(ScraperHelper::setUrlToProfile)
                .collect(Collectors.toList());
    }

    private static LawyerProfile setUrlToProfile(String url) {
        LawyerProfile profile = new LawyerProfile();
        profile.setUrl(url);
        return profile;
    }
}
