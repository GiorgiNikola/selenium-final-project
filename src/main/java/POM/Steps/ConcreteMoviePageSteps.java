package POM.Steps;

import POM.Common.BaseClass;
import POM.Data.Constants;
import POM.Pages.ConcreteMoviePage;
import POM.Pages.PopupPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConcreteMoviePageSteps extends BaseClass {
    ConcreteMoviePage concreteMoviePage;
    PopupPage popupPage;
    WebDriverWait wait;
    public ConcreteMoviePageSteps(WebDriver driver) {
        super(driver);
        concreteMoviePage = new ConcreteMoviePage(driver);
        popupPage = new PopupPage(driver);
        wait = new WebDriverWait(driver, 7);
    }

    public ConcreteMoviePageSteps chooseEastPoint(){
        concreteMoviePage.eastPoint.click();
        return this;
    }

    public boolean checkCinemaTitles(){
        for (WebElement element : concreteMoviePage.cinemaTitles){
            if (element.getText().isBlank())continue;
            else if (!element.getText().equals(Constants.caveaEastPoint)) return false;
        }
        return true;
    }

    public ConcreteMoviePageSteps clickOnLastDate(){
        concreteMoviePage.movieDates.get(concreteMoviePage.movieDates.size() - 1).click();
        return this;
    }

    public void clickOnLastOptions(){
        concreteMoviePage.movieOptions.get(concreteMoviePage.movieOptions.size() - 1).click();
        wait.until(ExpectedConditions.visibilityOf(popupPage.popupFilmName));
    }
}
