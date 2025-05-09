package org.automation.e2etesting;

import allureScreenshot.AutoScreenshotExtension;
import allureScreenshot.ScreenshotUtils;
import allureScreenshot.WebdriverExtension;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import poms.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SecondLearnerTest extends BaseTest {
    private LoginPage loginPage;
    @RegisterExtension
    WebdriverExtension webDriverManager = new WebdriverExtension();

    @RegisterExtension
    AutoScreenshotExtension screenshotManager = new AutoScreenshotExtension(
            c -> this.webDriverManager.getWebDriver(c));
    @BeforeEach
    public void initLoginPage() {
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        logger.info("Navigated to login page");
    }
    @Epic("E2E Testing")
    @Feature("Login and Catalogue")
    @Story("Learner can login and access the catalogue")
    @Description("This test verifies the login and catalogue functionality for a learner user.")
    @Test
    public void secondLearnerTest() {
        logger.info("Starting secondLearnerTest test");

        String userLoginNameValue = DefaultValues.DEFAULT_USERNAME_2016; // Use default username
        String userPasswordValue = DefaultValues.DEFAULT_PASSWORD_2016; // Use default password
        String expectedLoginMessageText = "welcome back! Select a current course in order to continue learning.";
        String courseName = "Course SD New Course Room";
        try {
        // Verify login page elements
        assertTrue(loginPage.userPassword.isDisplayed(), "User password field should be displayed");
        assertTrue(loginPage.loginButton.isEnabled(), "Login button should be enabled");

        // Login with the learner account
        loginPage.login(userLoginNameValue, userPasswordValue);

        // Access the main page
        MainPage mainPage = new MainPage(driver);
        Utils.waitForElementToBeDisplayed(driver, MainPage.NAVIGATION_MENU_ITEM_LOCATOR, 10);

        // Verify main page elements
        assertTrue(mainPage.homeMenuItem.isDisplayed(), "Home menu item should be displayed");
        assertTrue(mainPage.catalogueMenu.isDisplayed(), "Catalogue menu should be displayed");
        assertTrue(mainPage.shopifyCatalogueMenu.isDisplayed(), "Shopify catalogue menu should be displayed");
        assertTrue(mainPage.mylearningMenue.isDisplayed(), "My Learning menu should be displayed");
        assertTrue(mainPage.avatarUserMenu.isDisplayed(), "User avatar menu should be displayed");

        String loginMessageText = mainPage.notificationMessageText.getText();
        assertTrue(loginMessageText.contains(expectedLoginMessageText), "Login message should contain expected text");

        // Access the Catalog
        mainPage.catalogueMenu.click();
        CataloguePage cataloguePage = new CataloguePage(driver);
        Utils.sleepInSeconds(1);

        // Verify catalogue page elements
        assertTrue(cataloguePage.searchField.isDisplayed(), "Search field should be displayed");

        // Search for the course
        cataloguePage.searchField.click();
        cataloguePage.searchField.sendKeys(courseName);
        cataloguePage.startSearchBtn.click();
        Utils.sleepInSeconds(2);

        // Verify search results
        Utils.waitForElementToBeDisplayed(driver, CataloguePage.TITLE_ELEMENT_LOCATOR, 10);
        String titleElement = cataloguePage.getTitleElement.getText();
        assertTrue(titleElement.contains(courseName), "Title element should contain the course name");

        // Click on the course tile
        cataloguePage.getTitleElement.click();
        Utils.sleepInSeconds(1);

        // Verify course description page elements
        assertTrue(cataloguePage.descriptionElement.isDisplayed(), "Course description should be displayed");
        assertTrue(cataloguePage.courseName.isDisplayed(), "Course name should be displayed");
        assertTrue(cataloguePage.courseImage.isDisplayed(), "Course image should be displayed");
        assertTrue(cataloguePage.courseInfoButtonEnrolCourse.isDisplayed(), "Enroll button should be displayed");

        // Add to wishlist
        cataloguePage.courseInfoButtonWishListCourse.click();
        Utils.waitForElementToBeDisplayed(driver, CataloguePage.COURSE_REMOVE_WISH_LIST_BUTTON_LOCATOR, 10);
        assertTrue(cataloguePage.courseInfoButtonWishListCourseRemoved.isDisplayed(), "Remove from wishlist button should be displayed");

        // Access the Learning status
        MyLearningStatusMainPage myLearningStatusMainPage = new MyLearningStatusMainPage(driver);
        myLearningStatusMainPage.mylearningMenue.click();
        myLearningStatusMainPage.learningStatus.click();
        Utils.sleepInSeconds(1);

        // Verify learning status page elements
        assertTrue(myLearningStatusMainPage.myLearningsCurrent.isDisplayed(), "Current learnings should be displayed");
        assertTrue(myLearningStatusMainPage.myLearningsPendingEnrolments.isDisplayed(), "Pending enrolments should be displayed");
        assertTrue(myLearningStatusMainPage.myLearningsRecommended.isDisplayed(), "Recommended learnings should be displayed");
        assertTrue(myLearningStatusMainPage.myLearningsCompleted.isDisplayed(), "Completed learnings should be displayed");
        assertTrue(myLearningStatusMainPage.myLearningsCancelled.isDisplayed(), "Cancelled learnings should be displayed");
        assertTrue(myLearningStatusMainPage.myLearningsWishlist.isDisplayed(), "Wishlist should be displayed");

        // Access the Wishlist section
        myLearningStatusMainPage.myLearningsWishlist.click();

        // Verify that the course added to the wishlist is available
        Utils.waitForElementToBeDisplayed(driver, CataloguePage.TITLE_ELEMENT_LOCATOR, 10);
        assertTrue(myLearningStatusMainPage.courseName.isDisplayed(), "Course name in wishlist should be displayed");
        assertTrue(myLearningStatusMainPage.courseName.isEnabled(), "Course name in wishlist should be enabled");

        // Open the course description by clicking on the course tile
        myLearningStatusMainPage.courseName.click();
        Utils.waitForElementToBeDisplayed(driver, CataloguePage.DESCRIPTION_BUTTON_LOCATOR, 10);

        // Verify course description page elements
        assertTrue(cataloguePage.descriptionElement.isDisplayed(), "Course description should be displayed");
        assertTrue(cataloguePage.courseInfoButtonWishListCourseRemoved.isEnabled(), "Remove from wishlist button should be enabled");

        // Remove from wishlist
        cataloguePage.courseInfoButtonWishListCourseRemoved.click();
        assertTrue(cataloguePage.courseInfoButtonWishListCourse.isDisplayed(), "Add to wishlist button should be displayed");
        assertTrue(cataloguePage.courseInfoButtonWishListCourse.isEnabled(), "Add to wishlist button should be enabled");

        // Logout the user
        mainPage.avatarUserMenu.click();
        mainPage.logOutSection.click();

        // Verify that the user is logged out
        assertTrue(loginPage.userPassword.isDisplayed(), "User password field should be displayed after logout");
        assertTrue(loginPage.loginButton.isEnabled(), "Login button should be enabled after logout");
        } catch (Exception e) {
            // Capture a screenshot on test failure
            ScreenshotUtils.attachPageScreenshot(driver, "Test Failure Screenshot");
            throw e;
        } finally {
            logger.info("End secondLearnerTest test");
        }
    }
}
