import POM.Data.Constants;
import POM.Pages.AccountCreationPage;
import POM.Steps.*;
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

public class MoviePageTests {
    WebDriver driver;
    MoviePageSteps moviePageSteps;
    ConcreteMoviePageSteps concreteMoviePageSteps;
    PopupPageSteps popupPageSteps;
    AuthorisationPageSteps authorisationPageSteps;
    AccountCreationPageSteps accountCreationPageSteps;
    AccountCreationPage accountCreationPage;
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
        moviePageSteps = new MoviePageSteps(driver);
        concreteMoviePageSteps = new ConcreteMoviePageSteps(driver);
        popupPageSteps = new PopupPageSteps(driver);
        authorisationPageSteps = new AuthorisationPageSteps(driver);
        accountCreationPageSteps = new AccountCreationPageSteps(driver);
        accountCreationPage = new AccountCreationPage(driver);
    }

    @Test
    public void realMovieTest(){
        moviePageSteps
                .clickOnFirstMovie();
        boolean checkCinemaTitles = concreteMoviePageSteps
                                        .chooseEastPoint()
                                        .checkCinemaTitles();
        Assert.assertTrue(checkCinemaTitles);
        concreteMoviePageSteps
                .clickOnLastDate()
                .clickOnLastOptions();

        boolean checkMovie = popupPageSteps.checkMovie();
        Assert.assertTrue(checkMovie);
        boolean checkCinema = popupPageSteps.checkCinema();
        Assert.assertTrue(checkCinema);
        boolean checkDate = popupPageSteps.checkDate();
        Assert.assertTrue(checkDate);
        popupPageSteps
                .clickOnFreeSeat();
        authorisationPageSteps
                .clickCreateAccount();
        accountCreationPageSteps
                .fillAllFields()
                .selectYear()
                .acceptCheckMarks()
                .clickOnSubmitBtn();
        Assert.assertEquals(accountCreationPage.errorMessage.getText(), Constants.errorMessage);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
