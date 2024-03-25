package POM.Common;

import org.openqa.selenium.WebDriver;

public abstract class BaseClass {
    protected WebDriver driver;

    public BaseClass(WebDriver driver) {
        this.driver = driver;
    }
}
