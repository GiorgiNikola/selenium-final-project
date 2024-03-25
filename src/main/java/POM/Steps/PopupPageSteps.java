package POM.Steps;

import POM.Common.BaseClass;
import POM.Pages.PopupPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PopupPageSteps extends BaseClass {
    ConcreteMoviePageSteps concreteMoviePageSteps;
    PopupPage popupPage;
    public PopupPageSteps(WebDriver driver) {
        super(driver);
        this.driver = driver;
        concreteMoviePageSteps = new ConcreteMoviePageSteps(driver);
        popupPage = new PopupPage(driver);
    }

    public boolean checkMovieCinemaDate(){
        WebElement lastOption = popupPage.movieOptions.get(popupPage.movieOptions.size() - 1);
        String cinema = lastOption.findElement(By.cssSelector("p.cinema-title")).getText();
        String date = lastOption.findElement(By.xpath("//p[@style='width:35px;']")).getText();
        if (!popupPage.popupFilmName.getText().equals(popupPage.filmName.getText())) return false;
        if (!popupPage.popupCinemaAndDate.get(0).getText().equals(cinema)) return false;
        if (!popupPage.popupCinemaAndDate.get(1).getText().contains(date)) return false;
        return true;
    }

    public PopupPageSteps clickOnFreeSeat(){
        popupPage.freeSeats.get(0).click();
        return this;
    }
}
