package poms;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminMainPage extends MainPage{
    private final WebDriver driver;
    public AdminMainPage(WebDriver driver) {
        super(driver); // Call the constructor of the parent class (MainPage)
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize the elements
    }
    //settings button
    @FindBy(css = "button[title='Settings']")
    public WebElement btnAdminSettings;
    //search button
    @FindBy(css = "button[title='Search']")
    public WebElement btnAdminSearch;
    //denial of access //span[@title='Denial of access']
    @FindBy(xpath = "//span[@title='Denial of access']")
    public WebElement btnDenialOfAccess;
    //Categories Switch
    @FindBy(css = "button[title='Categories switch']")
    public WebElement btnAdminCategories;
    //From Categories Switch button Admin
    @FindBy(css = "button[title='Admin']")
    public WebElement btnAdminAdmin;
    @FindBy(xpath = "//div[span[text()='Content']]")
    public WebElement adminContent;
    //Click dropdown Content
    @FindBy(xpath = "//a[span[text()='Courses']]")
    public WebElement btnContentCourses;
    @FindBy(css = "input[id='searchTerm-inputEl']")
    public WebElement startSearchButton;
    //user search results
    @FindBy(xpath = "//tr[contains(@class, 'x-grid-row') and contains(@class, 'x-grid-data-row')]/td[2]")
    public WebElement userSearchResult;
    //reset button
    @FindBy(id = "tbb_reset-btnIconEl")
    public WebElement getDenialOfAccessButton;
    //reset selected login attends
    @FindBy(id = "tbi_reset-itemEl")
    public WebElement resetSelectedLoginButton;
    //Deny access users
    //Popup to reset login attempts //span[text()='Reset']
    @FindBy(xpath = "//span[text()='Reset']")
    public WebElement popupResetLoginButton;

    public void scrollAndClickResetLoginPopup() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", popupResetLoginButton);
        js.executeScript("arguments[0].click();", popupResetLoginButton);
    }
}
