import POM.Data.Constants;
import POM.Pages.MoviePage;
import POM.Steps.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class MoviePageTests {
    WebDriver driver;
    MoviePageSteps moviePageSteps;
    ConcreteMoviePageSteps concreteMoviePageSteps;
    PopupPageSteps popupPageSteps;
    AuthorisationPageSteps authorisationPageSteps;
    AccountCreationPageSteps accountCreationPageSteps;
    MoviePage moviePage;
    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File(Constants.extensionPath));
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        moviePageSteps = new MoviePageSteps(driver);
        concreteMoviePageSteps = new ConcreteMoviePageSteps(driver);
        popupPageSteps = new PopupPageSteps(driver);
        authorisationPageSteps = new AuthorisationPageSteps(driver);
        accountCreationPageSteps = new AccountCreationPageSteps(driver);
        moviePage = new MoviePage(driver);
    }

    @Test
    public void movieTest(){
        boolean checkCinemaTitles = moviePageSteps
                                        .clickOnFirstMovie()
                                        .chooseEastPoint()
                                        .checkCinemaTitles();
        Assert.assertTrue(checkCinemaTitles);
        boolean validatePopup = moviePageSteps
                                        .clickOnLastDate()
                                        .clickOnLastOptions()
                                        .checkMovieCinemaDate();
        Assert.assertTrue(validatePopup);
        moviePageSteps
                .clickOnFreeSeat()
                .clickCreateAccount()
                .fillAllFields()
                .clickOnSubmitBtn();
        Assert.assertEquals(moviePage.errorMessage.getText(), Constants.errorMessage);
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
        boolean validatePopup = popupPageSteps.checkMovieCinemaDate();
        Assert.assertTrue(validatePopup);
        popupPageSteps.clickOnFreeSeat();
        authorisationPageSteps.clickCreateAccount();
        accountCreationPageSteps
                .fillAllFields()
                .clickOnSubmitBtn();
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
