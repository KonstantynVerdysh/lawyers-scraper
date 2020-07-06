package com.ua.verdysh.controller;

import com.ua.verdysh.controller.helpers.ScraperHelper;
import com.ua.verdysh.controller.helpers.ParserHelper;
import com.ua.verdysh.model.LawyerProfile;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.*;
import java.util.stream.Collectors;

public class LawyersScraper {
    private static final String TYPE_SELECTOR_SEPARATOR = " ";
    private static final String TYPE_HEADING_SELECTOR = " /preceding-sibling::h2[1]";
    private static final List<String> TYPE_SELECTORS = Arrays.asList(
            "//ul[contains(@id,'partners')] /li //div /a",
            "//ul[contains(@id,'Our')] /li //div /a",
            "//ul[contains(@id,'support')] /li //div /a");

    public Map<String, List<LawyerProfile>> getProfiles(WebDriver driver) {
        Map<String, List<LawyerProfile>> result = new HashMap<>();

        ScraperHelper.loadMainPage(driver);
        List<String> typeHeadingSelectors = buildTypeHeadingSelector();

        for (int count = 0; count < TYPE_SELECTORS.size(); count++) {
            ScraperHelper.loadStaffPage(driver);
            Map<String, List<LawyerProfile>> typeProfiles = parseProfilesByType(driver,
                    TYPE_SELECTORS.get(count), typeHeadingSelectors.get(count));
            result.putAll(typeProfiles);
        }
        return result;
    }

    private Map<String, List<LawyerProfile>> parseProfilesByType(WebDriver driver, String typeSelector, String typeHeadingSelector) {
        Map<String, List<LawyerProfile>> result = new HashMap<>();
        String type = ParserHelper.getText(driver, typeHeadingSelector);

        List<String> profilesUrl =  driver.findElements(By.xpath(typeSelector)).stream()
                .map(v -> v.getAttribute("href"))
                .collect(Collectors.toList());

        List<LawyerProfile> profiles = ScraperHelper.createNewProfiles(profilesUrl);
        ProfileParser parser = new ProfileParser();
        parser.fillProfileFields(driver, profiles);
        result.put(type, profiles);
        return result;
    }

    private List<String> buildTypeHeadingSelector() {
        return TYPE_SELECTORS.stream()
                .map(v -> StringUtils.substringBefore(v, TYPE_SELECTOR_SEPARATOR) + TYPE_HEADING_SELECTOR)
                .collect(Collectors.toList());
    }
}
