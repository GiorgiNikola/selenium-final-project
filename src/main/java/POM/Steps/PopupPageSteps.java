package POM.Steps;

import POM.Common.BaseClass;
import POM.Pages.PopupPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PopupPageSteps extends BaseClass {
    ConcreteMoviePageSteps concreteMoviePageSteps;
    JavascriptExecutor js;
    PopupPage popupPage;
    public PopupPageSteps(WebDriver driver) {
        super(driver);
        concreteMoviePageSteps = new ConcreteMoviePageSteps(driver);
        popupPage = new PopupPage(driver);
        js = (JavascriptExecutor) driver;
    }

    public boolean checkMovie(){
        return popupPage.popupFilmName.getText().equals(popupPage.filmName.getText());
    }

    public boolean checkCinema(){
        WebElement lastOption = popupPage.movieOptions.get(popupPage.movieOptions.size() - 1);
        String cinema = lastOption.findElement(By.cssSelector("p.cinema-title")).getText();
        return popupPage.popupCinemaAndDate.get(0).getText().equals(cinema);
    }

    public boolean checkDate(){
        WebElement lastOption = popupPage.movieOptions.get(popupPage.movieOptions.size() - 1);
        String date = lastOption.findElement(By.xpath("//p[@style='width:35px;']")).getText();
        return popupPage.popupCinemaAndDate.get(1).getText().contains(date);
    }

    public void clickOnFreeSeat(){
        js.executeScript("arguments[0].click();", popupPage.freeSeats.get(0));
    }
}
