package poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AdminParticipantAdministrationPage {
    public AdminParticipantAdministrationPage(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "headline_mainText")
    public WebElement headlineParticipantManagerText;
    //Cancellation list tab
    @FindBy(id = "tab-1014-btnInnerEl")
    public WebElement cancellationListTab;

    @FindBy(xpath = "//tr[contains(@class, 'x-grid-row') and contains(@class, 'x-grid-data-row')]")
    public List<WebElement> searchParticipantNameResults;
    //participant Name result
    @FindBy(xpath = "//tr[contains(@class, 'x-grid-row') and contains(@class, 'x-grid-data-row')]/td[2]")
    public WebElement participantNameResults;
    //se poate muta in adminmanager page
    @FindBy(css = "input[id='searchTerm-inputEl']")
    public WebElement searchParticipantName;
    @FindBy(id = "startSearchButton-btnIconEl")
    public WebElement startSearchButton;
    @FindBy(id = "tbb_participant-btnIconEl")
    public WebElement participantButton;
    //Save and close button
    @FindBy(id = "default_saveClose-btnIconEl")
    public WebElement defaultSaveCloseButton;
}
