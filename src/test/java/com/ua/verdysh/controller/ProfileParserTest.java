package com.ua.verdysh.controller;

import com.ua.verdysh.controller.helpers.ScraperHelper;
import com.ua.verdysh.model.LawyerProfile;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProfileParserTest {
    private static final String URL = "https://www.baker-law.co.uk/site/people/profile/mark.ridley";
    private static ProfileParser parser;
    private static WebDriver driver;

    @BeforeAll
    static void before() {
        System.setProperty("webdriver.gecko.driver", "geckodriver");
        driver = new FirefoxDriver();
        parser = new ProfileParser();
    }

    @Test
    void fillProfileFields_fillProfileFieldsFromWebsite() {
        driver.get(URL);
        List<LawyerProfile> profiles = ScraperHelper.createNewProfiles(Collections.singletonList(URL));
        parser.fillProfileFields(driver, profiles);
        LawyerProfile profile = profiles.get(0);

        String expectedName = "Mark Ridley";
        String actualName = profile.getFullName();

        String expectedPosition = "HEAD OF DISPUTE RESOLUTION";
        String actualPosition = profile.getJobPosition();

        String expectedDescription = "I am a Partner and head of the Dispute Resolution department.";
        String actualDescription = profile.getDescription();

        String expectedPhotoUrl = "https://www.baker-law.co.uk/cms/photo/people_profile/profile_mark_ridley.jpg";
        String actualPhotoUrl = profile.getPhoto();

        String expectedMail = "mark.ridley@baker-law.co.uk";
        String actualMail = profile.getMail();

        assertEquals(expectedName, actualName);
        assertEquals(expectedPosition, actualPosition);
        assertEquals(expectedDescription, actualDescription);
        assertEquals(expectedPhotoUrl, actualPhotoUrl);
        assertEquals(expectedMail, actualMail);
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}