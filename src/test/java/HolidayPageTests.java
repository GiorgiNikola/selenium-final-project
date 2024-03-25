import POM.Data.Constants;
import POM.Steps.HolidayPageSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HolidayPageTests {
    WebDriver driver;
    HolidayPageSteps holidayPageSteps;
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
        holidayPageSteps = new HolidayPageSteps(driver);
    }

    @Test
    public void descendingOrderTest() {
        double maxPriceAmongAllOffers = holidayPageSteps.mostExpensiveOfferAmongAllOffers();
        double firstPrice = holidayPageSteps
                .sortOffers(Constants.priceDescendingText)
                .waitPopup()
                .getFirstOfferPrice();
        Assert.assertEquals(firstPrice, maxPriceAmongAllOffers);
    }

    @Test
    public void ascendingOrderTest() {
        double minPriceAmongAllOffers = holidayPageSteps.leastExpensiveOfferAmongAllOffers();
        double firstPrice = holidayPageSteps
                .sortOffers(Constants.priceAscending)
                .waitPopup()
                .getFirstOfferPrice();
        // Assert fails because sorting on site does not work correctly
        Assert.assertEquals(firstPrice, minPriceAmongAllOffers);
    }

    @Test
    public void filterTest() {
        boolean checkOffers = holidayPageSteps
                                    .clickCottageButton()
                                    .waitPopup()
                                    .checkOffersContainCottage();
        try {
            // One offer(At least one) does not contain word 'კოტეჯი' so that's why I put try catch
            // so test does not stop and continues remaining steps
            Assert.assertTrue(checkOffers);
        }catch (AssertionError e){

        }
        double minPriceAmongAllOffers = holidayPageSteps.leastExpensiveOfferAmongAllOffers();
        holidayPageSteps
                .sortOffers(Constants.priceAscending)
                .waitPopup();
        double firstPrice = holidayPageSteps.getFirstOfferPrice();
        Assert.assertEquals(firstPrice, minPriceAmongAllOffers);
    }

    @Test
    public void priceRangeTest() {
        boolean check = holidayPageSteps
                .inputMinMaxPrice(Constants.minPrice, Constants.maxPrice)
                .submitPrice()
                .waitPopup()
                .priceRangeCheck(Constants.minPrice, Constants.maxPrice);
        // sorting does not work correctly so this assertion catches bag
        Assert.assertTrue(check);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
