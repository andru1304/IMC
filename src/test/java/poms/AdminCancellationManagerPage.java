package poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminCancellationManagerPage extends AdminParticipantAdministrationPage {
    public AdminCancellationManagerPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //tbi_removeParticipant-itemEl
    @FindBy(id = "tbi_removeParticipant-itemEl")
    public WebElement removeParticipantButton;
}
