package poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminContentPage extends AdminMainPage{
    public AdminContentPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    //Slide barr
    @FindBy(id = "toolbar_backgroundPanel-body")
    public WebElement sidebarPanel;
    //course are displayed
    @FindBy(css = ".headline_mainText")
    public WebElement headlineContent;
    //search item
    @FindBy(css = "input[id='searchTerm-inputEl']")
    public WebElement searchInput;
    //click on search
    @FindBy(id = "startSearchButton-btnIconEl")
    public WebElement searchButton;
    //search the name of the course
    @FindBy(xpath = "//tr[contains(@class, 'x-grid-row') and contains(@class, 'x-grid-data-row')]/td[2]")
    public WebElement searchNameResults;
    //Info panel details show the courses selected
    @FindBy(css = "td[class='infoPanelMainHeadlineRow']")
    public WebElement infoPanelMainHeadlineTitle;
    //Participant administration button
    @FindBy(id = "tbb_participants-btnIconEl")
    public WebElement participantsButton;
    //participant administration text to click
    @FindBy(id = "tbi_participants-textEl")
    public WebElement participantsText;
}
