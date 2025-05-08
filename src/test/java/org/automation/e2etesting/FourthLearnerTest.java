package org.automation.e2etesting;

import AllureScreenshot.ScreenshotUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import poms.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FourthLearnerTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeEach
    public void initLoginPage() {
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        logger.info("Navigated to login page");
    }
    @Epic("E2E Testing")
    @Feature("Login")
    @Story("Learner can login and access the system")
    @Description("This test verifies the login functionality for a learner user.")
    @Test
    void passLoginLearnerFunctionalityTest() {
        logger.info("Starting login functionality for a learner user.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String userLoginNameValue = DefaultValues.DEFAULT_USERNAME_2015; // Use default username
        String userPasswordValue = DefaultValues.DEFAULT_PASSWORD_2015; // Use default password
        String expectedLoginMessageText = "welcome back! Select a current course in order to continue learning.";
        //System is available and login dashboard page is displayed with available login options user and password field are available
        try {
            assertTrue(loginPage.userPassword.isDisplayed());
            assertTrue(loginPage.userPassword.isDisplayed());
            assertTrue(loginPage.loginButton.isEnabled());
            //Login with the learner account
            loginPage.login(userLoginNameValue, userPasswordValue);
            //User can access the system and the system navigation is available and the personal dashboard is loaded
            MainPage mainPage = new MainPage(driver);
            Utils.waitForElementToBeDisplayed(driver, MainPage.CATALOGUE_MENU_ITEM_LOCATOR, 10);
            String loginMessageText = mainPage.notificationMessageText.getText();
            assertTrue(loginMessageText.contains(expectedLoginMessageText));
            assertTrue(mainPage.homeMenuItem.isDisplayed());
            assertTrue(mainPage.catalogueMenu.isDisplayed());
            assertTrue(mainPage.shopifyCatalogueMenu.isDisplayed());
            assertTrue(mainPage.mylearningMenue.isDisplayed());
            assertTrue(mainPage.avatarUserMenu.isDisplayed());
        } catch (Exception e) {
            // Capture a screenshot on test failure
            ScreenshotUtils.attachPageScreenshot(driver, "Test Failure Screenshot");
            throw e;
        } finally {
            logger.info("Ending login functionality for a learner user.");
        }
    }
    @Epic("E2E Testing")
    @Feature("Login")
    @Story("Learner can't login and access the system")
    @Description("This test verifies fail the login functionality for a learner user.")
    @Test
    void failLoginLearnerFunctionalityTest() {
        logger.info("Starting fail login functionality for a learner user.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String userLoginNameValue = DefaultValues.DEFAULT_USERNAME_2015; // Use default username
        String expectedLoginMessageText = "welcome back! Select a current course in order to continue learning.";
        //System is available and login dashboard page is displayed with available login options user and password field are available
        try {
            assertTrue(loginPage.userPassword.isDisplayed());
            assertTrue(loginPage.userPassword.isDisplayed());
            assertTrue(loginPage.loginButton.isEnabled());
            //Login with the learner account
            MainPage mainPage = new MainPage(driver);
            loginPage.login(userLoginNameValue, DefaultValues.DEFAULT_PASSWORD_2015);
            String loginMessageText = mainPage.notificationMessageText.getText();
            assertTrue(loginMessageText.contains(expectedLoginMessageText));
        } catch (Exception e) {
            // Capture a screenshot on test failure
            ScreenshotUtils.attachPageScreenshot(driver, "Test Failure Screenshot");
            throw e;
        } finally {
            logger.info("Ending login failed functionality for a learner user.");
        }
    }
}
