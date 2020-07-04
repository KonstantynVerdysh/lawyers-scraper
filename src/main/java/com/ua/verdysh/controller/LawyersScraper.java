package com.ua.verdysh.controller;

import com.ua.verdysh.controller.helpers.LawyersScraperHelper;
import com.ua.verdysh.model.LawyerProfile;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

import java.util.*;
import java.util.stream.Collectors;

public class LawyersScraper {
    private static final String TYPE_HEADING_SELECTOR = " /preceding-sibling::h2[1]";
    private static final List<String> TYPE_SELECTORS = Arrays.asList(
            "//ul[contains(@id,'partners')] /li //div /a",
            "//ul[contains(@id,'Our')] /li //div /a",
            "//ul[contains(@id,'support')] /li //div /a");

    public Map<String, List<LawyerProfile>> getProfiles(WebDriver driver) {
        LawyersScraperHelper.loadMainPage(driver);
        Map<String, List<LawyerProfile>> profiles = new HashMap<>();
        ProfileParser parser = new ProfileParser();

        List<String> typeHeadingSelectors = buildTypeHeadingSelector();

        for (int count = 0; count < TYPE_SELECTORS.size(); count++) {
            LawyersScraperHelper.loadStaffPage(driver);
            Map<String, List<LawyerProfile>> typeProfiles = parser.parseProfilesByType(driver,
                    TYPE_SELECTORS.get(count), typeHeadingSelectors.get(count));
            profiles.putAll(typeProfiles);
        }
        return profiles;
    }

    private List<String> buildTypeHeadingSelector() {
        return TYPE_SELECTORS.stream()
                .map(v -> StringUtils.substringBefore(v, " ") + TYPE_HEADING_SELECTOR)
                .collect(Collectors.toList());
    }
}
