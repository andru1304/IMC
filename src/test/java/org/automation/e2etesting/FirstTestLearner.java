package org.automation.e2etesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstTestLearner extends BaseTest{
    private LoginPage loginPage;

    @BeforeEach
    public void initLoginPage() {
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        logger.info("Navigated to login page");
    }
    @Test
    public void firstTestLearner() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String userLoginNameValue = DefaultValues.DEFAULT_USERNAME_2012; // Use default username
        String userPasswordValue = DefaultValues.DEFAULT_PASSWORD_2012; // Use default password
        String adminLoginNameValue = DefaultValues.DEFAULT_ADMINLOGIN; // Use default admin username
        String adminPasswordValue = DefaultValues.DEFAULT_ADMINPASSWORD; // Use default admin password
        String expectedLoginMessageText = "welcome back! Select a current course in order to continue learning.";
        String expectedCourseName = "DevOps Fundamentals: Tools and Techniques for Continuous Integration and Deployment";
        String searchCourseName = "DevOps Fundamentals Tools and Techniques";

        //System is available and login dashboard page is displayed with available login options user and password field are available
        assertTrue(loginPage.userPassword.isDisplayed());
        assertTrue(loginPage.userPassword.isDisplayed());
        assertTrue(loginPage.loginButton.isEnabled());

        //Login with the learner account
        loginPage.login(userLoginNameValue, userPasswordValue);
        MainPage mainPage = new MainPage(driver);

        //User can access the system and the system navigation is available and the personal dashboard is
        assertTrue(mainPage.systemNavigation.isDisplayed());
        assertTrue(mainPage.avatarUserMenu.isDisplayed());
        String loginMessageText = mainPage.notificationMessageText.getText();
        assertTrue(loginMessageText.contains(expectedLoginMessageText));

        //From the navigation access the Catalog
        mainPage.catalogueMenu.click();
        CataloguePage cataloguePage = new CataloguePage(driver);

        //Catalog page is opened and content is displayed and the search bar is available
        assertTrue(cataloguePage.searchField.isDisplayed());

        //Search for the course  "DevOps Fundamentals Tools and Techniques"
        cataloguePage.searchField.sendKeys(searchCourseName);
        cataloguePage.startSearchBtn.click();
        Utils.sleepInSeconds(2);

        //Search is executed and in the search result you have a course tile with the name: "DevOps Fundamentals: Tools and Techniques for Continuous Integration and Deployment"
        String titleElement = cataloguePage.getTitleElement.getText();
        assertTrue(titleElement.contains(expectedCourseName));
        //Click on the course tile
        cataloguePage.getTitleElement.click();

        //Course description page opens and course information is displayed
        Utils.sleepInSeconds(2);
        String courseNameElement = cataloguePage.courseName.getText();
        assertTrue(courseNameElement.contains(expectedCourseName));

        //Make sure the following are available : course name, course image, catalog breadcrumb, course type, course action  buttons Enroll and Add to wishlist
        assertTrue(cataloguePage.courseName.getText().contains(expectedCourseName));

        //All elements are available and can be interacted with
        assertTrue(cataloguePage.courseName.isDisplayed());
        assertTrue(cataloguePage.courseImage.isDisplayed());
        assertTrue(cataloguePage.courseInfoButtonEnrolCourse.isEnabled());
        assertTrue(cataloguePage.courseInfoButtonWishListCourse.isEnabled());

        //Enroll to course by clicking on the enroll button
        cataloguePage.courseInfoButtonEnrolCourse.click();
        cataloguePage.enrolmentProceedButton.click();
        wait.until(ExpectedConditions.visibilityOf(cataloguePage.syllabusElement));

        //Course enrollment is possible and course room is opened and the syllabus is displayed
        assertTrue(cataloguePage.syllabusElement.getText().contains("Syllabus"));
        Utils.sleepInSeconds(2);
        assertTrue(cataloguePage.courseDescription.getText().contains(expectedCourseName));

        //Check if medias are available in the Syllabus section
        assertTrue(cataloguePage.moocBild1Title.isDisplayed());
        assertTrue(cataloguePage.moocBild2Title.isDisplayed());

        //Open Mooc Bild 1 by clicking on the media tile or on the --> button and check the media description page
        cataloguePage.moocBild1Title.click();

        //Media page opens
        assertTrue(cataloguePage.moocBildTitle.getText().contains("MOOC Bild 1"));

        //Check if media page  contains media name media image and media description next media button and back to course button
        assertTrue(cataloguePage.moocBildImage.isDisplayed());
        assertTrue(cataloguePage.backToCourseroomLink.isDisplayed());

        //All elements are available and can be interacted with and media status is passed green checkmark on right corner of media
        assertTrue(cataloguePage.iconMoocBildDone.isDisplayed());

        //Go back to the course syllabus
        cataloguePage.backToCourseroomLink.click();

        //Course room is loaded and syllabus is displayed
        assertTrue(cataloguePage.syllabusElement.getText().contains("Syllabus"));

        //Check status for first media and course progress
        Utils.sleepInSeconds(1);
        assertTrue(cataloguePage.moocBild1StateIndicator.isDisplayed());

        //First media tile has passed status and course progress is 50%
        String progressStyle = cataloguePage.progressBarIndicator.getAttribute("style");
        Utils.sleepInSeconds(1);
        assertTrue(progressStyle.contains("50%"));

        //Open the second media and conclude it and check course progress and status
        String moocBild2TitleExpectedText = cataloguePage.moocBild2Title.getText();
        Utils.scrollToElementAndClickWhenVisible(driver, cataloguePage.moocBild2Title, 5);
        System.out.println(moocBild2TitleExpectedText);

        //cataloguePage.moocBild2Title.click();
        cataloguePage.backToCourseroomLink.click();

        //Media page opens media name media image and media description is displayd media status is passed and you can navigate back to course syllabus
        assertTrue(moocBild2TitleExpectedText.contains("MOOC Bild 2"));

        //Logout user from the user profile picture button navigation top right
        mainPage.avatarUserMenu.click();
        mainPage.logOutSection.click();

        //User can logout
        assertTrue(loginPage.userPassword.isDisplayed());
        assertTrue(loginPage.userPassword.isDisplayed());
        assertTrue(loginPage.loginButton.isEnabled());

        //Login with the admin account
        loginPage.login(adminLoginNameValue, adminPasswordValue);

        //User can access the system and the system navigation is available and the personal dashboard is loaded
        String loginAdminMessageText = mainPage.notificationMessageText.getText();
        assertTrue(loginAdminMessageText.contains(expectedLoginMessageText));
        AdminMainPage adminMainPage = new AdminMainPage(driver);
        adminMainPage.btnAdminSearch.isDisplayed();
        adminMainPage.btnAdminSettings.isDisplayed();

        //From the navigation access the category switch next to user profile and select admin context
        adminMainPage.btnAdminCategories.click();
        adminMainPage.btnAdminAdmin.click();

        //Admin context is selected and admin navigation options are available
        assertTrue(adminMainPage.adminContent.isEnabled());

        //Select Content from the navigation menu and from the dropdown menu select courses
        adminMainPage.adminContent.click();
        adminMainPage.btnContentCourses.click();

        //The course manager opens and available course are displayed in a list seach option is available and left sidebar is displayed
        driver.switchTo().frame("contentframe");
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe.x-component.x-fit-item.x-component-default")));
        String expectedUrl = "https://14-24-masterdb.imc-ms-deployment.imc-cs.com/ils/navigation/administrator/content_folder/content_course_folder/content_course_manager";

        // Use WebDriverWait to wait for the URL to change
        wait.until(driver -> Objects.equals(driver.getCurrentUrl(), expectedUrl));
        assertEquals(expectedUrl, driver.getCurrentUrl());
        AdminContentPage adminContentPage = new AdminContentPage(driver);
        String nameCourses = adminContentPage.headlineContent.getText();
        assertEquals("Courses", nameCourses);
        assertTrue(adminContentPage.sidebarPanel.isDisplayed());

        //In the search box enter the name of the course and start the search with the search button
        // Wait until the loading indicator is not visible
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'x-mask-msg-inner')]")));
        adminContentPage.searchInput.click();
        adminContentPage.searchInput.sendKeys(expectedCourseName);
        adminContentPage.searchButton.click();

        //Search is executed and in the search result you have a course tile with the name: "DevOps Fundamentals: Tools and Techniques for Continuous Integration and Deployment"
        String courseName = adminContentPage.searchNameResults.getText();
        System.out.printf("courseName is: %s", courseName);

        //Select the course from the result table by clicking on the row
        adminContentPage.searchNameResults.click();

        //Course is selected
        String infoPanelDeteilTitle = adminContentPage.infoPanelMainHeadlineTitle.getText();
        assertTrue(infoPanelDeteilTitle.contains(expectedCourseName));

        //From the left side action bar go to option participant administration
        adminContentPage.participantsButton.click();
        adminContentPage.participantsText.click();
        String originalWindow = driver.getWindowHandle();

        // Wait for the new tab to open
        Utils.sleepInSeconds(1);
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break; // Switch to the new tab
            }
        }

        //Course participant manager is opened and a list with all participants is displayed
        driver.switchTo().frame("contentframe");
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe.x-component.x-fit-item.x-component-default")));
        AdminParticipantManagerPage adminParticipantManagerPage = new AdminParticipantManagerPage(driver);
        String adminTesPage = adminParticipantManagerPage.headlineParticipantManagerText.getText();
        assertTrue(adminTesPage.contains(expectedCourseName));
        driver.switchTo().frame("iframe_c_participants_booked_person_3");

        //using the search option search for user mustaffuser2012
        adminParticipantManagerPage.searchParticipantName.click();
        adminParticipantManagerPage.searchParticipantName.sendKeys(userLoginNameValue);
        adminParticipantManagerPage.startSearchButton.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'x-mask-msg-inner')]")));

        //User is found
        assertTrue(adminParticipantManagerPage.participantNameResults.getText().contains(userLoginNameValue));

        //Select the user and from the action bar cancel the user
        adminParticipantManagerPage.participantNameResults.click();

        //Cancellation dialog popup is displayed and user can be cancelled and is moved to the cancelled tab
        adminParticipantManagerPage.participantButton.click();
        assertTrue(adminParticipantManagerPage.cancelParticipantButton.isDisplayed());
        adminParticipantManagerPage.cancelParticipantButton.click();
        Utils.sleepInSeconds(1);
        adminParticipantManagerPage.okCancellationButton.click();

        //Select the cancel tab and search the user mustaffuser2012 and from the options bar remove the participant
        driver.switchTo().defaultContent();
        driver.switchTo().frame("contentframe");
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe.x-component.x-fit-item.x-component-default")));
        adminParticipantManagerPage.cancellationListTab.click();
        AdminCancellatioManagerPage adminCancellatioManagerPage = new AdminCancellatioManagerPage(driver);
        driver.switchTo().frame("iframe_c_participants_cancelled_person_3");
        adminCancellatioManagerPage.searchParticipantName.click();
        adminCancellatioManagerPage.searchParticipantName.sendKeys(userLoginNameValue);
        adminCancellatioManagerPage.startSearchButton.click();

        //user is found can be selected and removed from the course
        assertTrue(adminCancellatioManagerPage.participantNameResults.getText().contains(userLoginNameValue));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'x-mask-msg-inner')]")));
        adminCancellatioManagerPage.participantNameResults.click();
        adminCancellatioManagerPage.participantButton.click();
        adminCancellatioManagerPage.removeParticipantButton.click();

        //Click save and close from the upper left corner of the course manager
        driver.switchTo().defaultContent();
        driver.switchTo().frame("contentframe");
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe.x-component.x-fit-item.x-component-default")));
        adminCancellatioManagerPage.defaultSaveCloseButton.click();

        //Course participant administration manager is closed
        driver.switchTo().window(originalWindow);
        String currentTabUrl1 = driver.getCurrentUrl();
        System.out.println("Current Tab URL: " + currentTabUrl1);
        mainPage.avatarUserMenu.click();
        mainPage.logOutSection.click();
    }
}
