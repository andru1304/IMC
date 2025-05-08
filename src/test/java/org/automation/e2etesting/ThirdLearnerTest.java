package org.automation.e2etesting;

import AllureScreenshot.ScreenshotUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import poms.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ThirdLearnerTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeEach
    public void initLoginPage() {
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        logger.info("Navigated to login page");
    }
    @Epic("E2E Testing")
    @Feature("Login and Admin")
    @Story("Learner and Admin can login and access the system")
    @Description("This test verifies the login functionality for a learner user and an admin user.")
    @Test
    public void thirdLearnerTest() {
        logger.info("Starting ThirdLearnerTest test");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String userLoginNameValue = DefaultValues.DEFAULT_USERNAME_2015; // Use default username
        String userPasswordValue = DefaultValues.DEFAULT_PASSWORD_2015; // Use default password
        String adminLoginNameValue = DefaultValues.DEFAULT_ADMINLOGIN; // Use default admin username
        String adminPasswordValue = DefaultValues.DEFAULT_ADMINPASSWORD; // Use default admin password
        String userPasswordValueWrong = DefaultValues.DEFAULT_PASSWORD_2016; // Use default password
        String expectedLoginMessageText = "welcome back! Select a current course in order to continue learning.";
        String errorMassageExpected = "The login/password combination is not correct!";
        String errorMessageMultipleTimesExpected = "Either this user name does not exist in our system or it has been blocked. Please contact your system administrator.";
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
        mainPage.avatarUserMenu.click();
        mainPage.logOutSection.click();
        //Login failed due to wrong username / password combination
        loginPage.login(userLoginNameValue, userPasswordValueWrong);
        //The user is redirected to a login page with an error message telling "The login/password combination is not correct!"
        String loginMessageErrorDisplayedText = mainPage.loginErrorMessage.getText();
        System.out.println(loginMessageErrorDisplayedText);
        assertTrue(mainPage.loginErrorMessage.isDisplayed());
        assertEquals(errorMassageExpected, loginMessageErrorDisplayedText);
        //Login 2 more times with the wrong username / password combination
        mainPage.homeMenuItem.click();
        loginPage.login(userLoginNameValue, userPasswordValueWrong);
        Utils.waitForElementToBeDisplayed(driver, MainPage.HOME_MENU_ITEM_LOCATOR, 10);
        mainPage.homeMenuItem.click();
        loginPage.login(userLoginNameValue, userPasswordValueWrong);
        Utils.waitForElementToBeDisplayed(driver, MainPage.HOME_MENU_ITEM_LOCATOR, 10);
        mainPage.homeMenuItem.click();
        loginPage.login(userLoginNameValue, userPasswordValueWrong);
        //The login page will display the following error : "Either this user name does not exist in our system or it has been blocked. Please contact your system administrator."
        String loginMessageErrorDisplayedText1 = mainPage.loginErrorMessage.getText();
        System.out.println(loginMessageErrorDisplayedText1);
        assertTrue(loginMessageErrorDisplayedText1.contains(errorMessageMultipleTimesExpected));
        //Login with admin account
        Utils.waitForElementToBeDisplayed(driver, MainPage.HOME_MENU_ITEM_LOCATOR, 10);
        mainPage.homeMenuItem.click();
        loginPage.login(adminLoginNameValue, adminPasswordValue);
        //Login works and the dashboard page for the admin user is loaded
        //From the navigation type in the search option right hand navigation second icon with small magnify glass
        AdminMainPage adminMainPage = new AdminMainPage(driver);
        adminMainPage.btnAdminSearch.click();
        //Serch text box opens
        assertTrue(adminMainPage.btnAdminSearch.isDisplayed());
        //Enter in the serach box
        adminMainPage.btnAdminSearch.sendKeys("Denial of access");
        adminMainPage.btnDenialOfAccess.click();
        //Manager with users that are blocked is displayed
        //todo
        //assertTrue(adminMainPage.contentDenialOfAccess.isDisplayed());
        //Search for the user mystaff2015 and reset the access
        driver.switchTo().frame("contentframe");
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe.x-component.x-fit-item.x-component-default")));
        adminMainPage.startSearchButton.sendKeys(userLoginNameValue);
        adminMainPage.startSearchButton.click();
        //adminMainPage.btnStartSearchButton.click();
        //mystaffuser2015 is found in the list can be selected number of failed atempts is displayed and user can be reset
        String testUser = adminMainPage.userSearchResult.getText();
        adminMainPage.userSearchResult.click();
        System.out.println(testUser);
        assertTrue(testUser.contains(userLoginNameValue));
        assertEquals(userLoginNameValue, testUser);
        adminMainPage.getDenialOfAccessButton.click();
        adminMainPage.resetSelectedLoginButton.click();
        adminMainPage.scrollAndClickResetLoginPopup();
        //Logout admin user
        driver.switchTo().defaultContent();
        mainPage.avatarUserMenu.click();
        mainPage.logOutSection.click();
        //Admin can logout
        assertTrue(loginPage.userPassword.isDisplayed());
        assertTrue(loginPage.userPassword.isDisplayed());
        assertTrue(loginPage.loginButton.isEnabled());
        //Login with correct credentials for learner user
        loginPage.login(userLoginNameValue, userPasswordValue);
        //User can access the system and the system navigation is available and the personal dashboard is loaded
        //String loginMessageText = mainPage.notificationMessageText.getText();
        assertTrue(loginMessageText.contains(expectedLoginMessageText));
        assertTrue(mainPage.homeMenuItem.isDisplayed());
        assertTrue(mainPage.catalogueMenu.isDisplayed());
        assertTrue(mainPage.shopifyCatalogueMenu.isDisplayed());
        assertTrue(mainPage.mylearningMenue.isDisplayed());
        assertTrue(mainPage.avatarUserMenu.isDisplayed());
        //Logout learner user
        mainPage.avatarUserMenu.click();
        mainPage.logOutSection.click();
        //The user can logout
        assertTrue(loginPage.userPassword.isDisplayed());
        assertTrue(loginPage.userPassword.isDisplayed());
        assertTrue(loginPage.loginButton.isEnabled());
        } catch (Exception e) {
            // Capture a screenshot on test failure
            ScreenshotUtils.attachPageScreenshot(driver, "Test Failure Screenshot");
            throw e;
        } finally {
            logger.info("Ending ThirdTestLearner test");
        }
    }
}
