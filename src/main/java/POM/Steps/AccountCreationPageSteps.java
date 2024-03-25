package POM.Steps;

import POM.Common.BaseClass;
import POM.Pages.AccountCreationPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AccountCreationPageSteps extends BaseClass {
    AccountCreationPage accountCreationPage;
    JavascriptExecutor js;
    public AccountCreationPageSteps(WebDriver driver) {
        super(driver);
        accountCreationPage = new AccountCreationPage(driver);
        js = (JavascriptExecutor) driver;
    }

    public AccountCreationPageSteps fillAllFields(){
        accountCreationPage.email.sendKeys("giorgi@gia");
        accountCreationPage.password.sendKeys("Tbcfinal123");
        accountCreationPage.passwordRetype.sendKeys("Tbcfinal123");
        accountCreationPage.male.click();
        accountCreationPage.firstName.sendKeys("gia");
        js.executeScript("window.scrollBy(0,400)", "");
        accountCreationPage.lastName.sendKeys("nikoladze");
        Select selectYear = new Select(accountCreationPage.selectBirthYear);
        selectYear.selectByVisibleText("2004");
        accountCreationPage.phoneNumber.sendKeys("511180450");
        accountCreationPage.smsCode.sendKeys("1111");
        js.executeScript("arguments[0].click();", accountCreationPage.firstCheckMark);
        js.executeScript("arguments[0].click();", accountCreationPage.secondCheckMark);
//        accountCreationPage.firstCheckMark.click();
//        accountCreationPage.secondCheckMark.click();
//        for (WebElement checkbox : accountCreationPage.checkMarks){
//            checkbox.click();
//        }
        return this;
    }

    public void clickOnSubmitBtn(){
        js.executeScript("arguments[0].click();", accountCreationPage.registrationBtn);
        //accountCreationPage.registrationBtn.click();
    }
}
