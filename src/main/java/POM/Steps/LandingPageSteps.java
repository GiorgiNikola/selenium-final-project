package POM.Steps;

import POM.Common.BaseClass;
import POM.Data.Constants;
import POM.Pages.LandingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPageSteps extends BaseClass {
    WebDriver driver;
    LandingPage landingPage;
    Actions actions;
    WebDriverWait wait;
    public LandingPageSteps(WebDriver driver) {
        super(driver);
        this.driver = driver;
        driver.get(Constants.siteURl);
        this.landingPage = new LandingPage(driver);
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, 5);
    }

    public LandingPageSteps getToHolidaySection(){
        landingPage.holidayBtn.click();
        return this;
    }

    public void getToMovieSection(){
        landingPage.movieBtn.click();
    }

    public void clickLogo(){
        landingPage.homeLogo.click();
    }

    public LandingPageSteps clickCategories(){
        landingPage.categories.click();
        return this;
    }

    public LandingPageSteps acceptCookie(){
        landingPage.acceptCookie.click();
        return this;
    }
    public String returnColorOfKarting(){
        String color = landingPage.kartingElement.getCssValue("Color");
        return Color.fromString(color).asHex().toUpperCase();
    }

    public void hoverAndClick(){
        actions.moveToElement(landingPage.categorySport).perform();
        wait.until(ExpectedConditions.visibilityOf(landingPage.karting));
        landingPage.karting.click();
    }
}
