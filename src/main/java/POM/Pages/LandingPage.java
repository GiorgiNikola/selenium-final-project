package POM.Pages;

import POM.Common.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends BaseClass {
    @FindBy(css = "header div.Menus a[href*='dasveneba']")
    public WebElement holidayBtn;
    @FindBy(css = "a.Newlogo")
    public WebElement homeLogo;
    @FindBy(css = "p.categoriesTitle")
    public WebElement categories;
    @FindBy(xpath = "//a[@class='mainCategories'][contains(text(),'სპორტი')]")
    public WebElement categorySport;
    @FindBy(xpath = "//a[text()='კარტინგი']")
    public WebElement karting;
    @FindBy(css = "div.acceptCookie")
    public WebElement acceptCookie;
    @FindBy(css = "header div.Menus a[href*='events']")
    public WebElement movieBtn;
    public LandingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
