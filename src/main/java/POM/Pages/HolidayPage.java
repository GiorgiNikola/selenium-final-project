package POM.Pages;

import POM.Common.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HolidayPage extends BaseClass {
    @FindBy(className = "special-offer")
    public List<WebElement> offers;
    @FindBy(xpath = "//a[@class='pagination__link pagination__link--active']/parent::div//following-sibling::div[1]")
    public WebElement nextPage;
    @FindBy(id = "sort")
    public WebElement selectElement;
    @FindBy(css = "div.freeze")
    public WebElement loadElement;
    @FindBy(xpath = "//div[@class='category-filter-desk']//label[@class='location-label'][contains(text(),'კოტეჯი')]//input[@name='arrangement']")
    public WebElement cottageBtn;
    @FindBy(xpath = "//div[@class='category-filter-desk']//input[@id='minprice']")
    public WebElement minPriceField;
    @FindBy(xpath = "//div[@class='category-filter-desk']//input[@id='maxprice']")
    public WebElement maxPriceField;
    @FindBy(css = "div[class='category-filter-desk'] div[class='submit-button']")
    public WebElement submitBtn;
    @FindBy(xpath = "//div[@class='special-offer']//div[contains(@class,'special-offer-title')]")
    public List<WebElement> offerTitles;
    @FindBy(xpath = "//div[@class='special-offer']//div[contains(@class,'special-offer-text')]")
    public List<WebElement> offerDescriptions;
    @FindBy(xpath = "//a[normalize-space()='1']")
    public WebElement firstPage;
    public By byPrice = By.className("deal-voucher-price"),
    byFreeze = By.className("freeze");


    public HolidayPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
