package POM.Steps;

import POM.Common.BaseClass;
import POM.Pages.AuthorisationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthorisationPageSteps extends BaseClass {
    AuthorisationPage authorisationPage;
    WebDriverWait wait;
    public AuthorisationPageSteps(WebDriver driver) {
        super(driver);
        authorisationPage = new AuthorisationPage(driver);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickCreateAccount(){
        wait.until(ExpectedConditions.visibilityOf(authorisationPage.createAccount));
        authorisationPage.createAccount.click();
    }
}
