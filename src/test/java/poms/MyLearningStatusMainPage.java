package poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyLearningStatusMainPage extends MainPage{
    public MyLearningStatusMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    //learning status
    @FindBy(xpath = "//span[text()='Learning status']")
    public WebElement learningStatus;
    //Current tab mylearnings-current
    @FindBy(id = "mylearnings-current")
    public WebElement myLearningsCurrent;
    //Pending enrolments tab mylearnings-pending
    @FindBy(id = "mylearnings-pending")
    public WebElement myLearningsPendingEnrolments;
    //Recommended tab mylearnings-recommended
    @FindBy(id = "mylearnings-recommended")
    public WebElement myLearningsRecommended;
    //Completed tab
    @FindBy(id = "mylearnings-completed")
    public WebElement myLearningsCompleted;
    //canceled tab
    @FindBy(id = "mylearnings-cancelled")
    public WebElement myLearningsCancelled;
    //mylearnings-wishlist
    @FindBy(id = "mylearnings-wishlist")
    public WebElement myLearningsWishlist;
    @FindBy(css = ".imc-list-details__text-container")
    public WebElement courseName;
}
