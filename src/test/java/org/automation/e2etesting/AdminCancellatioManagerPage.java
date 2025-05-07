package org.automation.e2etesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminCancellatioManagerPage extends AdminParticipantAdministrationPage{
    public AdminCancellatioManagerPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //tbi_removeParticipant-itemEl
    @FindBy(id = "tbi_removeParticipant-itemEl")
    public WebElement removeParticipantButton;
}
