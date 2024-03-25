package POM.Pages;

import POM.Common.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MoviePage extends BaseClass {
    @FindBy(className = "movies-deal")
    public List<WebElement> moviesWhichAreInCaveaEastPoint;
    @FindBy(xpath = "//a[@class='ui-tabs-anchor'][text()='კავეა ისთ ფოინთი']")
    public WebElement eastPoint;
    @FindBy(xpath = "//div[@aria-labelledby='ui-id-5']//p[@class='cinema-title']")
    public List<WebElement> cinemaTitles;
    @FindBy(xpath = "//div[@aria-labelledby='ui-id-5']//ul[@class='tabs ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all']/li[@role='tab']")
    public List<WebElement> movieDates;
    @FindBy(xpath = "//div[@aria-labelledby='ui-id-5']//div[@class='seanse-details ui-tabs-panel ui-widget-content ui-corner-bottom']")
    public List<WebElement> movieOptions;
    @FindBy(css = "p.name")
    public WebElement filmName;
    @FindBy(css = "div.content-header p.movie-title")
    public WebElement popupFilmName;
    @FindBy(css = "div.content-header p.movie-cinema")
    public List<WebElement> popupCinemaAndDate;
    @FindBy(css = "div.seat.free")
    public List<WebElement> freeSeats;
    @FindBy(css = "a.black-hover")
    public WebElement createAccount;
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
    @FindBy(css = "span.checkmark")
    public List<WebElement> checkMarks;
    @FindBy(css = "input[name='sms_code']")
    public WebElement smsCode;
    @FindBy(css = "p#input-error-email")
    public WebElement errorMessage;
    @FindBy(css = "button#registrationBtn")
    public WebElement registrationBtn;
    public MoviePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
