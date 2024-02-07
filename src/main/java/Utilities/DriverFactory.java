package Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class DriverFactory {
    private static final Logger LOGGER = LogManager.getLogger(DriverFactory.class);
    private DriverFactory() {

    }

    private static final DriverFactory instance = new DriverFactory();

    public static DriverFactory getInstance() {

        return instance;
    }

    ThreadLocal<WebDriver> driver = ThreadLocal.withInitial(() -> {
        String environment = System.getProperty("environment") == null ? "local" : System.getProperty("environment");
        String browser = System.getProperty("browser") == null ? "chrome" : System.getProperty("browser");
        URL gridUrl = null;

        try {
            gridUrl = new URL(ReadConfigFiles.getPropertyValues("GridUrl"));
        } catch (Exception e) {
            LOGGER.error("Grid URL is incorrect: " + e.getMessage());
        }

        if (environment.equals("remote") && browser.equals("chrome")) {
            LOGGER.info("Running the automation test in Remote environment: " + gridUrl + " and using Chrome Browser");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*");
            return new RemoteWebDriver(gridUrl, chromeOptions);
        } else if (environment.equals("remote") && browser.equals("firefox")) {
            LOGGER.info("Running the automation test in Remote environment: " + gridUrl + " and using Firefox Browser");
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            return new RemoteWebDriver(gridUrl, firefoxOptions);
        } else if (environment.equals("remote") && browser.equals("edge")) {
            LOGGER.info("Running the automation test in Remote environment: " + gridUrl + " and using Edge Browser");
            EdgeOptions edgeOptions = new EdgeOptions();
            return new RemoteWebDriver(gridUrl, edgeOptions);
        } else {
            LOGGER.info("Running the automation test in Local environment using Chrome Browser");
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            return new ChromeDriver(options);
        }
    });
    public WebDriver getDriver(){
        return driver.get();
    }
    public void removeDriver(){
        getDriver().quit();
        driver.remove();
    }
}