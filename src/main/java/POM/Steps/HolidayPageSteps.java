package POM.Steps;

import POM.Data.Constants;
import POM.Pages.LandingPage;
import POM.Pages.HolidayPage;
import POM.Common.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Comparator;
import java.util.List;

public class HolidayPageSteps extends BaseClass {
    WebDriverWait wait;
    HolidayPage holidayPage;
    LandingPageSteps landingPageSteps;
    LandingPage landingPage;
    public HolidayPageSteps(WebDriver driver) {
        super(driver);
        this.driver = driver;
        landingPageSteps = new LandingPageSteps(driver);
        landingPageSteps.getToHolidaySection()
                        .acceptCookie();
        this.holidayPage = new HolidayPage(driver);
        wait = new WebDriverWait(driver, 7);
        landingPage = new LandingPage(driver);
    }

    public HolidayPageSteps sortOffers(String order){
        Select select = new Select(holidayPage.selectElement);
        select.selectByVisibleText(order);
        return this;
    }

    public HolidayPageSteps waitPopup(){
        WebElement freeze = wait.until(ExpectedConditions.presenceOfElementLocated(holidayPage.byFreeze));
        wait.until(ExpectedConditions.invisibilityOf(freeze));
        return this;
    }


    public double getFirstOfferPrice() {
        return holidayPage.offers.get(0).findElements(holidayPage.byPrice).stream()
                .filter(p -> !p.getText().isBlank()).map(p -> Double.parseDouble(p.getText().trim().replaceAll("[^\\d.]", "")))
                .sorted().findFirst().get();
    }


    public double mostExpensiveOfferPriceOnCurrentPage(){
        return holidayPage.offers.stream().map(o -> o.findElements(holidayPage.byPrice))
                .map(this::filterPrices).max(Comparator.naturalOrder()).get();
    }

    public double leastExpensiveOfferPriceOnCurrentPage(){
        return holidayPage.offers.stream().map(o -> o.findElements(holidayPage.byPrice))
                .map(this::filterPrices).min(Comparator.naturalOrder()).get();
    }

    public double filterPrices(List<WebElement> prices){
        return prices.stream().filter(p -> !p.getText().isBlank())
                .map(p -> Double.parseDouble(p.getText().trim().replaceAll("[^\\d.]", "")))
                .sorted().findFirst().get();
    }

    public boolean hasNextPage(){
        return holidayPage.nextPage.getText().trim().matches(".*\\d.*");
    }

    public void clickOnNextPage(){
        holidayPage.nextPage.click();
    }

    public double mostExpensiveOfferAmongAllOffers(){
        double max = Integer.MIN_VALUE;
        while (true){
            double currentMax = mostExpensiveOfferPriceOnCurrentPage();
            if (currentMax > max)max = currentMax;
            if (hasNextPage()) clickOnNextPage();
            else break;
        }
        return max;
    }

    public double leastExpensiveOfferAmongAllOffers(){
        double min = Integer.MAX_VALUE;
        while (true){
            double currentMin = leastExpensiveOfferPriceOnCurrentPage();
            if (currentMin < min)min = currentMin;
            if (hasNextPage()) holidayPage.nextPage.click();
            else break;
        }
        return min;
    }

    public HolidayPageSteps clickCottageButton(){
        holidayPage.cottageBtn.click();
        return this;
    }

    public boolean checkOffersContainCottage(){
        while (true) {
            for (int i = 0; i < holidayPage.offerTitles.size(); i++) {
                String offerTitle = holidayPage.offerTitles.get(i).getText();
                String offerDescription = holidayPage.offerDescriptions.get(i).getText();
                if (!(offerTitle.contains(Constants.cottageText) || offerDescription.contains(Constants.cottageText))) {
                    System.out.println("Offer does not contain word 'კოტეჯი' not in title nor in description!");
                    return false;
                }
            }
            if (hasNextPage()) clickOnNextPage();
            else break;
        }
        return true;
    }

    public HolidayPageSteps inputMinMaxPrice(int min, int max){
        holidayPage.minPriceField.sendKeys(String.valueOf(min));
        holidayPage.maxPriceField.sendKeys(String.valueOf(max));
        return this;
    }

    public HolidayPageSteps submitPrice(){
        holidayPage.submitBtn.click();
        return this;
    }

    public void goToFirstPage(){
        holidayPage.firstPage.click();
    }

    public boolean priceRangeCheck(double min, double max){
        double minimumPrice = leastExpensiveOfferAmongAllOffers();
        goToFirstPage();
        double maximumPrice = mostExpensiveOfferPriceOnCurrentPage();
        return !(minimumPrice < min) && !(maximumPrice > max);
    }
}
