package POM.Steps;

import POM.Common.BaseClass;
import POM.Data.Constants;
import POM.Pages.HolidayPage;
import POM.Pages.LandingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class HolidayPageSteps extends BaseClass {
    WebDriverWait wait;
    HolidayPage holidayPage;
    LandingPage landingPage;
    LandingPageSteps landingPageSteps;

    public HolidayPageSteps(WebDriver driver) {
        super(driver);
        landingPageSteps = new LandingPageSteps(driver);
        landingPageSteps.getToHolidaySection()
                        .acceptCookie();
        this.holidayPage = new HolidayPage(driver);
        landingPage = new LandingPage(driver);
        wait = new WebDriverWait(driver, 7);
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
        List<WebElement> firstOfferPrices = holidayPage.offers.get(0).findElements(holidayPage.byPrice);
        return filterPrices(firstOfferPrices);
    }


    public double mostExpensiveOfferPriceOnCurrentPage(){
        return getPricesStream().max(Comparator.naturalOrder()).get();
    }

    public double leastExpensiveOfferPriceOnCurrentPage(){
        return getPricesStream().min(Comparator.naturalOrder()).get();
    }

    public Stream<Double> getPricesStream(){
        return holidayPage.offers.stream().map(o -> o.findElements(holidayPage.byPrice))
                .map(this::filterPrices);
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
            if (hasNextPage()) clickOnNextPage();
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
                    System.out.println("Offer title: " + offerTitle);
                    System.out.println("Offer Description: " + offerDescription);
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

    public void goToHolidayWithUrl(){
        driver.get(Constants.holidayURL);
    }

    public boolean priceRangeCheck(double min, double max){
        goToHolidayWithUrl();
        List<Double> prices = getPricesStream().toList();
        while (true) {
            for (Double price : prices) {
                if (price < min || price > max) return false;
            }
            if (hasNextPage()) clickOnNextPage();
            else break;
        }
        return true;
    }
}
