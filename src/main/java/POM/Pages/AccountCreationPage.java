package POM.Pages;

import POM.Common.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AccountCreationPage extends BaseClass {
    @FindBy(css = "input[name='email']")
    public WebElement email;
    @FindBy(css = "input[name='password']")
    public WebElement password;
    @FindBy(css = "input[name='PasswordRetype']")
    public WebElement passwordRetype;
    @FindBy(css = "input#Gender1")
    public WebElement male;
    @FindBy(css = "input[name='firstname']")
    public WebElement firstName;
    @FindBy(css = "input[name='lastname']")
    public WebElement lastName;
    @FindBy(css = "select[name='birth_year']")
    public WebElement selectBirthYear;
    @FindBy(css = "input[name='phone']")
    public WebElement phoneNumber;
    @FindBy(css = "div.pb-10px input#test")
    public WebElement firstCheckMark;
    @FindBy(css = "div.pb-10px input#tbcAgreement")
    public WebElement secondCheckMark;
    @FindBy(css = "input[name='sms_code']")
    public WebElement smsCode;
    @FindBy(css = "p#input-error-email")
    public WebElement errorMessage;
    @FindBy(css = "button#registrationBtn")
    public WebElement registrationBtn;
    public AccountCreationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
