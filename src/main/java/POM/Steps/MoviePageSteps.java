package POM.Steps;

import POM.Common.BaseClass;
import POM.Pages.MoviePage;
import org.openqa.selenium.WebDriver;

public class MoviePageSteps extends BaseClass {
    MoviePage moviePage;
    LandingPageSteps landingPageSteps;
    public MoviePageSteps(WebDriver driver) {
        super(driver);
        landingPageSteps = new LandingPageSteps(driver);
        landingPageSteps.getToMovieSection();
        landingPageSteps.acceptCookie();
        moviePage = new MoviePage(driver);
    }

    public void clickOnFirstMovie(){
        // xpath is written in a way that it makes sure first movie has option cavea east point
        // so that later steps are not failed
        moviePage.moviesWhichAreInCaveaEastPoint.get(0).click();
    }

}
