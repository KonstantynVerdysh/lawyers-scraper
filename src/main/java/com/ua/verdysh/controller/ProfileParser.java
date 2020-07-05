package com.ua.verdysh.controller;

import com.ua.verdysh.controller.helpers.ProfileParserHelper;
import com.ua.verdysh.model.LawyerProfile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class ProfileParser {
    private static final String NAME_SELECTOR = "//p[@class='staff-name']";
    private static final String JOB_POSITION_SELECTOR = "//p[@class='job-title']";
    private static final String DESCRIPTION_SELECTOR = "//p[@class='intro']";
    private static final String PHOTO_URL_SELECTOR = "//img[@id='profile-image']";
    private static final String MAIL_SELECTOR = "//li[contains(@class,'profile')] //li[@itemprop='email'] //a";

    Map<String, List<LawyerProfile>> parseProfilesByType(WebDriver driver, String typeSelector, String typeHeadingSelector) {
        Map<String, List<LawyerProfile>> result = new HashMap<>();
        String type = ProfileParserHelper.getText(driver, typeHeadingSelector);

        List<String> profilesUrl =  driver.findElements(By.xpath(typeSelector)).stream()
                .map(v -> v.getAttribute("href"))
                .collect(Collectors.toList());

        List<LawyerProfile> profiles = ProfileParserHelper.createNewProfiles(profilesUrl);
        fillProfileFields(driver, profiles);
        result.put(type, profiles);
        return result;
    }

    void fillProfileFields(WebDriver driver, List<LawyerProfile> profiles) {
        for (LawyerProfile profile : profiles) {
            driver.get(profile.getUrl());

            String fullName = ProfileParserHelper.getText(driver, NAME_SELECTOR);
            String jobPosition = ProfileParserHelper.getText(driver, JOB_POSITION_SELECTOR);
            String description = ProfileParserHelper.getText(driver, DESCRIPTION_SELECTOR);
            String photo = ProfileParserHelper.getText(driver, PHOTO_URL_SELECTOR, "src");
            String stringWithMail = ProfileParserHelper.getText(driver, MAIL_SELECTOR, "href");
            String mail = ProfileParserHelper.cutMail(stringWithMail);

            profile.setFullName(fullName);
            profile.setJobPosition(jobPosition);
            profile.setDescription(description);
            profile.setPhoto(photo);
            profile.setMail(mail);
        }
    }
}
