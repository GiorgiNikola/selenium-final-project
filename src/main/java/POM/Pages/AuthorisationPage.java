package POM.Pages;

import POM.Common.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthorisationPage extends BaseClass {
    @FindBy(css = "a.black-hover")
    public WebElement createAccount;
    public AuthorisationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
