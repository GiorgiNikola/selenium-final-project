package POM.Pages;

import POM.Common.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ConcreteMoviePage extends BaseClass {
    @FindBy(xpath = "//a[@class='ui-tabs-anchor'][text()='კავეა ისთ ფოინთი']")
    public WebElement eastPoint;
    @FindBy(xpath = "//div[@aria-labelledby='ui-id-5']//p[@class='cinema-title']")
    public List<WebElement> cinemaTitles;
    @FindBy(xpath = "//div[@aria-labelledby='ui-id-5']//ul[@class='tabs ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all']/li[@role='tab']")
    public List<WebElement> movieDates;
    @FindBy(xpath = "//div[@aria-labelledby='ui-id-5']//div[@class='seanse-details ui-tabs-panel ui-widget-content ui-corner-bottom']")
    public List<WebElement> movieOptions;
    public ConcreteMoviePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
