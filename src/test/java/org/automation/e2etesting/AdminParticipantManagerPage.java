package org.automation.e2etesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminParticipantManagerPage extends AdminParticipantAdministrationPage{
    public AdminParticipantManagerPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    //tbi_cancelParticipant-textEl
    @FindBy(id = "tbi_cancelParticipant-textEl")
    public WebElement cancelParticipantButton;
    //Click on Cancellation dialog popup
    @FindBy(id = "layerWindowext-gen1022_btnOK-btnIconEl")
    public WebElement okCancellationButton;
}
