package POM.Steps;

import POM.Common.BaseClass;
import POM.Data.Constants;
import POM.Pages.AccountCreationPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class AccountCreationPageSteps extends BaseClass {
    AccountCreationPage accountCreationPage;
    JavascriptExecutor js;
    public AccountCreationPageSteps(WebDriver driver) {
        super(driver);
        accountCreationPage = new AccountCreationPage(driver);
        js = (JavascriptExecutor) driver;
    }

    public AccountCreationPageSteps fillAllFields(){
        accountCreationPage.email.sendKeys(Constants.invalidEmail);
        accountCreationPage.password.sendKeys(Constants.password);
        accountCreationPage.passwordRetype.sendKeys(Constants.password);
        accountCreationPage.male.click();
        accountCreationPage.firstName.sendKeys(Constants.firstName);
        js.executeScript("window.scrollBy(0,400)", "");
        accountCreationPage.lastName.sendKeys(Constants.lastName);
        accountCreationPage.phoneNumber.sendKeys(Constants.phoneNumber);
        accountCreationPage.smsCode.sendKeys(Constants.smsCode);
        return this;
    }

    public AccountCreationPageSteps selectYear(){
        js.executeScript("arguments[0].value = '2004';", accountCreationPage.selectBirthYear);
        return this;
    }

    public AccountCreationPageSteps acceptCheckMarks(){
        js.executeScript("arguments[0].click();", accountCreationPage.firstCheckMark);
        js.executeScript("arguments[0].click();", accountCreationPage.secondCheckMark);
        return this;
    }

    public void clickOnSubmitBtn(){
        js.executeScript("arguments[0].click();", accountCreationPage.registrationBtn);
    }
}
