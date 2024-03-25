import POM.Data.Constants;
import POM.Pages.LandingPage;
import POM.Steps.LandingPageSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class LandingPageTests {
    WebDriver driver;
    LandingPageSteps landingPageSteps;
    LandingPage landingPage;
    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File(Constants.extensionPath));
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        landingPageSteps = new LandingPageSteps(driver);
        landingPage = new LandingPage(driver);
    }

    @Test
    public void activeCategoryTest(){
        landingPageSteps
                .acceptCookie()
                .clickCategories()
                .hoverAndClick();
        Assert.assertEquals(driver.getCurrentUrl(), Constants.kartingUrl);
        Assert.assertEquals(landingPageSteps.returnColorOfKarting(), Constants.kartingColor);
    }

    @Test
    public void logoTest(){
        landingPageSteps
                .getToHolidaySection()
                .clickLogo();
        Assert.assertEquals(driver.getCurrentUrl(), Constants.siteURl);
    }


    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
