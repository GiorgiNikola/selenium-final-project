package POM.Steps;

import POM.Common.BaseClass;
import POM.Pages.KartingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.Color;

public class KartingPageSteps extends BaseClass {
    KartingPage kartingPage;
    public KartingPageSteps(WebDriver driver) {
        super(driver);
        kartingPage = new KartingPage(driver);
    }
    public String returnColorOfKarting(){
        String color = kartingPage.kartingElement.getCssValue("Color");
        return Color.fromString(color).asHex().toUpperCase();
    }
}
