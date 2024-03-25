package POM.Pages;

import POM.Common.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class KartingPage extends BaseClass {
    @FindBy(css = "span.main-category-span.open.searched-category")
    public WebElement kartingElement;
    public KartingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
