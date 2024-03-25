import POM.Data.Constants;
import POM.Pages.LandingPage;
import POM.Steps.KartingPageSteps;
import POM.Steps.LandingPageSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LandingPageTests {
    WebDriver driver;
    LandingPageSteps landingPageSteps;
    KartingPageSteps kartingPageSteps;
    LandingPage landingPage;
    @BeforeTest
    @Parameters("browser")
    public void setup(String browser){
        if (browser.equalsIgnoreCase(Constants.chromeName)){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }else if (browser.equalsIgnoreCase(Constants.firefoxName)){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }else if (browser.equalsIgnoreCase(Constants.edgeName)){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        landingPageSteps = new LandingPageSteps(driver);
        kartingPageSteps = new KartingPageSteps(driver);
        landingPage = new LandingPage(driver);
    }

    @Test
    public void activeCategoryTest(){
        landingPageSteps
                .acceptCookie()
                .clickCategories()
                .hoverAndClick();
        Assert.assertEquals(driver.getCurrentUrl(), Constants.kartingUrl);
        Assert.assertEquals(kartingPageSteps.returnColorOfKarting(), Constants.kartingColor);
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
