package command_providers;

import Utilities.ScreenCapture;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;


public class ElementAssertions {
    private WebDriver driver;
    private By locator;

    public ElementAssertions (WebDriver driver, By locator){
        this.driver = driver;
        this.locator = locator;
    }
    public void elementIsDisplayed() throws Exception{
        try {
            boolean displayed = driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e){
            ScreenCapture.getScreenShot(driver);
            throw new Exception("Element is not displayed for the locator:" + locator);
        }

        }
    }



