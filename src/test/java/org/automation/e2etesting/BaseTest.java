package org.automation.e2etesting;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    // Register extension ONCE per test class
    @RegisterExtension
    static ScreenshotExtension screenshotExtension = new ScreenshotExtension();

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        screenshotExtension.setDriver(driver);  // Pass driver into the extension
        logger.info("WebDriver initialized.");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            //driver.quit();
            logger.info("WebDriver closed.");
        }
    }
}
