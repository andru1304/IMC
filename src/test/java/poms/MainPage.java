package poms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    //System Navigation
    @FindBy(id = "navigationRoot")
    public WebElement systemNavigation;
    //Home menu
    @FindBy(xpath = "//a[@title='Home']")
    public WebElement homeMenuItem;
    //Catalog Menu
    @FindBy(xpath = "//a[@title='Catalogue']")
    public WebElement catalogueMenu;

    @FindBy(xpath = "//a[@title='TD Shopify Catalogue']")
    public WebElement shopifyCatalogueMenu;

    @FindBy(css = "button[title ='My learning']")
    public WebElement mylearningMenue;

    @FindBy(css = ".ui-notification .message")
    public WebElement notificationMessageText;
    //Message login
    @FindBy(id = "idm-error")
    public WebElement loginErrorMessage;
    //Message button
    @FindBy(css = "a[title='Messages']")
    public WebElement messagesSection;
    //select Language
    @FindBy(css = "button[title='Select language']")
    public WebElement selectLanguageSection;
    //Shopping Card
    @FindBy(css = "button[title='Shopify cart']")
    public WebElement shopifyCartSection;
    //user menu avatar
    @FindBy(css = ".avatarWrapper")
    public WebElement avatarUserMenu;
    //sign out for the user
    @FindBy(css = ".logout-section")
    public WebElement logOutSection;
    public static final By HOME_MENU_ITEM_LOCATOR = By.xpath("//a[@title='Home']");
    //navigation root navigationRoot
    public static final By NAVIGATION_MENU_ITEM_LOCATOR = By.id("navigationRoot");
    public static final By CATALOGUE_MENU_ITEM_LOCATOR = By.xpath("//a[@title='Catalogue']");

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
