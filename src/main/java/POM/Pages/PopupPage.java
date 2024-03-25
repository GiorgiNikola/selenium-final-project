package POM.Pages;

import POM.Common.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PopupPage extends BaseClass {
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
    public PopupPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
