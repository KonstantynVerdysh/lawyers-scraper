package com.ua.verdysh.controller;

import com.ua.verdysh.controller.helpers.ParserHelper;
import com.ua.verdysh.model.LawyerProfile;
import org.openqa.selenium.WebDriver;

import java.util.List;

class ProfileParser {
    private static final String NAME_SELECTOR = "//p[@class='staff-name']";
    private static final String JOB_POSITION_SELECTOR = "//p[@class='job-title']";
    private static final String DESCRIPTION_SELECTOR = "//p[@class='intro']";
    private static final String PHOTO_URL_SELECTOR = "//img[@id='profile-image']";
    private static final String MAIL_SELECTOR = "//li[contains(@class,'profile')] //li[@itemprop='email'] //a";

    public void fillProfileFields(WebDriver driver, List<LawyerProfile> profiles) {
        for (LawyerProfile profile : profiles) {
            driver.get(profile.getUrl());

            String fullName = ParserHelper.getText(driver, NAME_SELECTOR);
            String jobPosition = ParserHelper.getText(driver, JOB_POSITION_SELECTOR);
            String description = ParserHelper.getText(driver, DESCRIPTION_SELECTOR);
            String photo = ParserHelper.getText(driver, PHOTO_URL_SELECTOR, "src");
            String stringWithMail = ParserHelper.getText(driver, MAIL_SELECTOR, "href");
            String mail = ParserHelper.cutMail(stringWithMail);

            profile.setFullName(fullName);
            profile.setJobPosition(jobPosition);
            profile.setDescription(description);
            profile.setPhoto(photo);
            profile.setMail(mail);
        }
    }
}
