package org.automation.e2etesting;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Utils {
    private static final Logger logger = LoggerFactory.getLogger(Utils.class);

    /**
     * Waits for the element identified by the locator to be visible on the page.
     */
    public static void waitForElementToBeDisplayed(WebDriver driver, By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.info("Element is visible: {}", locator);
        } catch (TimeoutException e) {
            logger.error("Timeout waiting for visibility of element: {}", locator, e);
            throw new RuntimeException("Element not visible after " + timeoutInSeconds + " seconds: " + locator, e);
        }
    }

    /**
     * Sleeps for the given number of seconds.
     */
    public static void sleepInSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
            logger.debug("Slept for {} seconds", seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Sleep was interrupted", e);
            throw new RuntimeException("Sleep was interrupted", e);
        }
    }

    /**
     * Scrolls to a WebElement, waits for it to become clickable, and clicks it.
     */
    public static void scrollToElementAndClickWhenVisible(WebDriver driver, WebElement element, int timeoutSeconds) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll the element into view
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        logger.debug("Scrolled element into view.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

        // Optional: Wait for notification message to disappear
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.cssSelector("div.ui-notification-fix-bottom__block__text")));
            logger.debug("Notification bar disappeared.");
        } catch (TimeoutException e) {
            logger.warn("Notification did not disappear in time.");
        }

        // Wait for element to be clickable
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            logger.info("Clicked on element successfully.");
        } catch (TimeoutException e) {
            logger.error("Element was not clickable after {} seconds", timeoutSeconds, e);
            throw new RuntimeException("Element not clickable after " + timeoutSeconds + " seconds", e);
        }
    }
}
