package POM.Steps;

import POM.Common.BaseClass;
import POM.Data.Constants;
import POM.Pages.MoviePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MoviePageSteps extends BaseClass {
    WebDriverWait wait;
    MoviePage moviePage;
    LandingPageSteps landingPageSteps;
    JavascriptExecutor js;
    public MoviePageSteps(WebDriver driver) {
        super(driver);
        this.driver = driver;
        landingPageSteps = new LandingPageSteps(driver);
        landingPageSteps.getToMovieSection();
        landingPageSteps.acceptCookie();
        moviePage = new MoviePage(driver);
        wait = new WebDriverWait(driver, 7);
        js = (JavascriptExecutor) driver;
    }

    public MoviePageSteps clickOnFirstMovie(){
        // xpath is written in a way that it makes sure first movie has option cavea east point
        // so that later steps are not failed
        moviePage.moviesWhichAreInCaveaEastPoint.get(0).click();
        return this;
    }

    public MoviePageSteps chooseEastPoint(){
        moviePage.eastPoint.click();
        return this;
    }

    public boolean checkCinemaTitles(){
        for (WebElement element : moviePage.cinemaTitles){
            if (element.getText().isBlank())continue;
            else if (!element.getText().equals(Constants.caveaEastPoint)) return false;
        }
        return true;
    }

    public MoviePageSteps clickOnLastDate(){
        moviePage.movieDates.get(moviePage.movieDates.size() - 1).click();
        return this;
    }

    public MoviePageSteps clickOnLastOptions(){
        moviePage.movieOptions.get(moviePage.movieOptions.size() - 1).click();
        wait.until(ExpectedConditions.visibilityOf(moviePage.popupFilmName));
        return this;
    }

    public boolean checkMovieCinemaDate(){
        WebElement lastOption = moviePage.movieOptions.get(moviePage.movieOptions.size() - 1);
        String cinema = lastOption.findElement(By.cssSelector("p.cinema-title")).getText();
        String date = lastOption.findElement(By.xpath("//p[@style='width:35px;']")).getText();
        if (!moviePage.popupFilmName.getText().equals(moviePage.filmName.getText())) return false;
        if (!moviePage.popupCinemaAndDate.get(0).getText().equals(cinema)) return false;
        if (!moviePage.popupCinemaAndDate.get(1).getText().contains(date)) return false;
        return true;
    }

    public MoviePageSteps clickOnFreeSeat(){
        moviePage.freeSeats.get(0).click();
        return this;
    }

    public MoviePageSteps clickCreateAccount(){
        moviePage.createAccount.click();
        return this;
    }

    public MoviePageSteps fillAllFields(){
        moviePage.email.sendKeys("giorgi@gia");
        moviePage.password.sendKeys("Tbcfinal123");
        moviePage.passwordRetype.sendKeys("Tbcfinal123");
        moviePage.male.click();
        moviePage.firstName.sendKeys("gia");
        js.executeScript("window.scrollBy(0,400)", "");
        moviePage.lastName.sendKeys("nikoladze");
        Select selectYear = new Select(moviePage.selectBirthYear);
        selectYear.selectByVisibleText("2004");
        moviePage.phoneNumber.sendKeys("511180450");
        moviePage.smsCode.sendKeys("1111");
        for (WebElement checkbox : moviePage.checkMarks){
            checkbox.click();
        }
        return this;
    }

    public void clickOnSubmitBtn(){
        moviePage.registrationBtn.click();
    }
}
