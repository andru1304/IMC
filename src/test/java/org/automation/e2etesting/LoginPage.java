package org.automation.e2etesting;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;
    private static final String URL = "https://14-24-masterdb.imc-ms-deployment.imc-cs.com/ilp/pages/external-dashboard.jsf?menuId=1104&locale=en-GB&client=anonymous#/?dashboardId=6";

    // Define the WebElements using @FindBy annotations
    @FindBy(id = "externalForm:login")
    public WebElement userLoginName;

    @FindBy(id = "externalForm:password")
    public WebElement userPassword;

    @FindBy(id = "externalForm:loginButtonAbsolute")
    public WebElement loginButton;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    // Method to navigate to the login page
    public void navigateToLoginPage() {
        driver.get(URL); // Use the URL constant here
    }
    // Method to log in
    public void login(String username, String password) {
        userLoginName.sendKeys(username);
        userPassword.sendKeys(password);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", loginButton);
        loginButton.click();
    }
}
