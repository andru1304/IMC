package poms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CataloguePage {
    private WebDriver driver;
    @FindBy(id = "searchField")
    public WebElement searchField;

    @FindBy(id = "startSearchBtn")
    public WebElement startSearchBtn;

    @FindBy(css = ".imc-list-item__tile__container")
    public WebElement getTitleElement;

    //description
    @FindBy(xpath = "//button[@id='description' and contains(text(),'Description')]")
    public WebElement descriptionElement;

    @FindBy(xpath = "//h1[contains(@class, 'title')]")
    public WebElement courseDescription;

    @FindBy(css = ".imc-header__title-wrapper__title")
    public WebElement courseName;

    @FindBy(css = ".imc-list-details__text-container .imc-list-title")
    public WebElement courseNameSelected;

    @FindBy(css = ".imc-container")
    public WebElement courseImage;

    @FindBy(xpath = "//button[@title='Enrol']")
    public WebElement courseInfoButtonEnrolCourse;
    //click on popup button
    @FindBy(id = "enrolment_proceed_button")
    public WebElement enrolmentProceedButton;
    //curriculumButton Syllabus is displayed
    @FindBy(id = "curriculumButton")
    public WebElement syllabusElement;
    //media Bild 1
    @FindBy(css = "div.dir-tile__title[title='MOOC Bild 1']")
    public WebElement moocBild1Title;
    //media Bild 2
    @FindBy(css = "div.dir-tile__title[title='MOOC Bild 2']")
    public WebElement moocBild2Title;
    //.content-header-text moocBildTitle
    @FindBy(css = ".content-header-text")
    public WebElement moocBildTitle;
    //.image-centered
    @FindBy(css = ".image-centered")
    public WebElement moocBildImage;
    //back to course rom
    @FindBy(id = "back-to-courseroom-link")
    public WebElement backToCourseroomLink;
    //(//div[@class='dir-tile__state-indicator'])[1]
    @FindBy(css = ".dir-tile__state-indicator__icon")
    public WebElement moocBild1StateIndicator;
    //.icon-content-test-done
    @FindBy(css = ".icon-content-test-done")
    public WebElement iconMoocBildDone;
    @FindBy(xpath = "//button[@title='Add to wish list']")
    public WebElement courseInfoButtonWishListCourse;
    //Button remove from wish list //button[@title='Remove from wish list']
    @FindBy(xpath = "//button[@title='Remove from wish list']")
    public WebElement courseInfoButtonWishListCourseRemoved;
    //progress barr
    @FindBy(css = "div.dir-header__content-container-course__left__progress__bar__indicator")
    public WebElement progressBarIndicator;

    public CataloguePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    // Static By locators for waiting logic
    public static final By TITLE_ELEMENT_LOCATOR = By.cssSelector(".imc-list-item__tile__container .imc-list-title");
    public static final By GET_TITLE_ELEMENT_LOCATOR = By.cssSelector(".imc-list-item__tile__container");
    public static final By DESCRIPTION_BUTTON_LOCATOR = By.xpath("//button[@id='description' and contains(text(),'Description')]");
    public static final By COURSE_REMOVE_WISH_LIST_BUTTON_LOCATOR = By.xpath("//button[@title='Remove from wish list']");
}
