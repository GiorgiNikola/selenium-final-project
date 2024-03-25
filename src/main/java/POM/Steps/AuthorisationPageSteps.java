package POM.Steps;

import POM.Common.BaseClass;
import POM.Pages.AuthorisationPage;
import org.openqa.selenium.WebDriver;

public class AuthorisationPageSteps extends BaseClass {
    AuthorisationPage authorisationPage;
    public AuthorisationPageSteps(WebDriver driver) {
        super(driver);
        authorisationPage = new AuthorisationPage(driver);
    }

    public AuthorisationPageSteps clickCreateAccount(){
        authorisationPage.createAccount.click();
        return this;
    }
}
