package poms;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotExtension implements TestWatcher {
    private static final Logger logger = LoggerFactory.getLogger(ScreenshotExtension.class);
    private WebDriver driver;

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    private void takeScreenshot(ExtensionContext context) {
        if (driver instanceof TakesScreenshot) {
            try {
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String testMethod = context.getTestMethod().map(method -> method.getName()).orElse("unknown");
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
                Path destination = Path.of("screenshots", testMethod + "_" + timestamp + ".png");

                Files.createDirectories(destination.getParent());
                Files.copy(screenshot.toPath(), destination);
                logger.info("Screenshot saved to: {}", destination.toAbsolutePath());
            } catch (Exception e) {
                logger.error("Failed to capture screenshot", e);
            }
        } else {
            logger.warn("⚠ WebDriver does not support screenshots.");
        }
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        logger.error("Test failed: {}", context.getDisplayName(), cause);
        takeScreenshot(context);
        quitDriverSafely();
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        logger.info("Test passed: {}", context.getDisplayName());
        quitDriverSafely();
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        logger.warn("Test aborted: {}", context.getDisplayName(), cause);
        takeScreenshot(context);
        quitDriverSafely();
    }

    private void quitDriverSafely() {
        if (driver != null) {
            try {
                driver.quit();
                logger.info("WebDriver quit successfully.");
            } catch (Exception e) {
                logger.warn("Error while quitting WebDriver", e);
            } finally {
                driver = null; // Prevent reuse
            }
        }
    }
}
