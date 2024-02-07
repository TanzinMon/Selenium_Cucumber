package Step_Definitions;

import Utilities.ReadConfigFiles;
import command_providers.ActOn;
import command_providers.AssertThat;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class TestSteps {

    private static final Logger LOGGER = LogManager.getLogger(TestSteps.class);

    private static final By SearchIcon = By.xpath("//button[@aria-label='Search']");
    private static final By SearchBarInputField = By.xpath("//input[@aria-label='Search Xfinity.com']");
    private static final By SearchBar = By.xpath("//*[@id=\"root\"]/section/header/xc-header/div[2]/div/ul/li[1]/div/div/form/div[2]/button");
    private static final By ResultsText = By.xpath("//p[@class = 'summary']");
    private static final By IrrelevantText = By.xpath("//*[@id='search-results']//p[1]");
    private static final By SignInLink = By.name("Sign In");
    //private static final By SignInToMyAccount = By.xpath("//a[contains(., 'Sign in to My Account')]");
    private static final By UserName = By.id("user");
    private static final By LetsGoButton = By.id("sign_in");
    private static final By LogInError = By.id("user-hint");
    private static final By InternetLink = By.name("Internet");
    private static final By InternetDealsLink = By.linkText("Internet Deals");
    private static final By InternetDealsPage = By.xpath("//h2[text()='Xfinity Internet Deals']");
    private static final By FindAStoreIcon = By.xpath("//ul[@class='xc-flex xc-items-center xc-m-0 xc-p-0']//child::li[2]//span");
    private static final By FindAStoreLink = By.linkText("Find a Store");
    private static final By AddressInputField = By.id("q");
    private static final By SearchIconTwo = By.xpath("//*[@class='icon icon-search']");
    private static final By AvailableLocation = By.xpath("//div[text()='All Locations']");
    private static final By CareersLink = By.linkText("Careers");
    private static final By JoinOurCommunity = By.linkText("Join Our Community");
    private static final By SitemapLink = By.xpath("//a[contains(@href, 'https://www.xfinity.com/site-map')]");
    private static final By CustomerServiceLink = By.linkText("Customer Service");
    private static final By AccessibilityLink = By.xpath("//a[contains(@href, '/support/accessibility')]");
    private static final By Accessibility = By.id("accessibility");
    private static final By ServiceInMyArea = By.linkText("Service in my Area");
    private static final By IllinoisLink = By.linkText("Illinois");
    private static final By VernonHillsLink = By.linkText("Vernon Hills");
    private static final By InternetServices = By.xpath("//h1[text()='Internet Services Vernon Hills']");
    private static final By XfinityPassword = By.id("passwd");
    private static final By LogInSuccess = By.xpath("//h2[text()='Need to change your plan?']");
    private static final By InvalidAddress = By.xpath("//div[@class='Locator-noResults']");
    WebDriver driver = Hooks.driver;

    @Given("User is on the xfinity.com homepage")
    public void navigateToHomePage(){
        ActOn.browser(driver).openBrowser(ReadConfigFiles.getPropertyValues("TestAppURL"));
        LOGGER.info("User is on the homepage");

    }
    @When("User uses the search icon to look for a specific content")
    public void enterContent(){
        ActOn.element(driver, SearchIcon).click();
        ActOn.element(driver, SearchBarInputField).setValue("iPhone 15");
        LOGGER.info ("User entered the name of the content");
    }
    @And("User clicks on search bar")
    public void clicksOnSearch(){
        ActOn.element(driver, SearchBar).click();
        LOGGER.info("User clicked on search bar");
    }
    @Then("The search results should be relevant and displayed accurately")
    public void validateSuccess() throws Exception {
        AssertThat.elementAssertions(driver, ResultsText).elementIsDisplayed();
        LOGGER.info("The search results should be shown");
    }
    @When("User uses the search icon to look for a invalid content")
    public void enterInvalidContent() {
        ActOn.element(driver, SearchIcon).click();
        ActOn.element(driver, SearchBarInputField).setValue("Tomato");
        LOGGER.info("User entered the name of the content");
    }
    @Then("The search results should be irrelevant")
    public void validateFailure(){
        String Text = ActOn.element(driver, IrrelevantText).getTextValue();
        Assert.assertEquals("We were unable to find any results matching your query.", Text);
        LOGGER.info("The search results should be unable to show");

    }
    @When("User clicks the Sign In link")
    public void clickOnSignIn(){
        ActOn.element(driver, SignInLink).click();
        //ActOn.element(driver, SignInToMyAccount).click();
        LOGGER.info("User clicked on the sign In link");
    }
    @And("^User enters username \"(.+?)\" and clicks Let's go button$")
    public void enterUsername(String username){
        ActOn.element(driver, UserName).setValue(username);
        ActOn.element(driver, LetsGoButton).click();
        LOGGER.info("user entered username and clicks Let's go button");
    }

    @Then("User is failed to login")
    public void validateUserIsFailedToLogin(){
        boolean present;
        present = ActOn.element(driver, LogInError).getElement().isDisplayed();
        Assert.assertTrue("The Xfinity ID or password you entered was incorrect. Please try again.", present);
        LOGGER.info("User is failed to login");
    }
    @When("User hover overs the mouse on Internet link")
    public void mouseOnInternetLink(){
        ActOn.element(driver, InternetLink).mouseHover();
        LOGGER.info("User hover overs the mouse on Internet link");

    }
    @And("User clicks on Internet Deals")
    public void clicksInternetDeals(){
        ActOn.element(driver, InternetDealsLink).click();
        LOGGER.info("User clicked on Internet Deals");

    }
    @Then("User should lead to the corresponding page without errors")
    public void internetDealsHomePage(){
        String TextValue = ActOn.element(driver, InternetDealsPage).getTextValue();
        Assert.assertEquals("Xfinity Internet Deals", TextValue);
        LOGGER.info("User should lead to the internet deals page");
    }

    @When("User hover overs the mouse on Find a Store icon and clicks on the link")
    public void findAStore(){
        ActOn.element(driver, FindAStoreIcon).mouseHover();
        ActOn.element(driver, FindAStoreLink).click();
        LOGGER.info("User hover overs the mouse on Find a Store icon and clicks on the link");
    }
    @And("User enters the address in the service availability checker and clicks it")
    public void enterTheAddress(){
        ActOn.element(driver, AddressInputField).setValue("Buffalo Grove, IL, USA");
        ActOn.element(driver, SearchIconTwo).click();
        LOGGER.info("User entered the address");
    }
    @Then("User should see information about the available location in the area")
    public void validateLocation() throws Exception {
        AssertThat.elementAssertions(driver, AvailableLocation).elementIsDisplayed();
        LOGGER.info("User should see information about the available location");
    }

    @When("User clicks on Careers link")
    public void clicksOnCareers(){
        ActOn.element(driver, CareersLink).click();
        LOGGER.info("User clicked on Careers link");
    }
    @Then("User should lead to the corresponding webpage")
    public void validateCareerPage(){
        boolean present1 = ActOn.element(driver, JoinOurCommunity).getElement().isDisplayed();
        Assert.assertTrue("Join Our Community", present1);
        LOGGER.info("User should lead to comcast careers");

    }
    @When("User clicks on Sitemap")
    public void clicksSitemap(){
        ActOn.element(driver, SitemapLink).click();
        LOGGER.info("User clicked on Sitemap");
    }
    @And("User clicks on Customer Service")
    public void clicksCustomerService(){
        ActOn.element(driver, CustomerServiceLink).click();
        LOGGER.info("User clicked Customer Service link");
    }
    @And("User clicks on Accessibility link")
    public void clicksAccessibility(){
        ActOn.element(driver, AccessibilityLink).click();
        LOGGER.info("User clicked on Accessibility link");
    }

    @Then("User navigates to Accessibility page")
    public void verifyAccessibilityPage() throws Exception {
        AssertThat.elementAssertions(driver, Accessibility).elementIsDisplayed();
        LOGGER.info("User should lead to Accessibility page");
    }
    @When("User clicks on Service in my Area link")
    public void clicksServiceInMyArea(){
        ActOn.element(driver, ServiceInMyArea).click();
        LOGGER.info("User clicked on Service in my Area link");
    }
    @And("User clicks on Illinois link")
    public void clicksOnIllinoisLink(){
        ActOn.element(driver, IllinoisLink).click();
        LOGGER.info("User clicked on Illinois");
    }
    @And("User clicks on Vernon Hills link")
    public void clicksOnVernonHills(){
        ActOn.element(driver, VernonHillsLink).click();
        LOGGER.info("User clicked on Vernon Hills link");
    }
    @Then("User navigates to Internet Services Vernon Hills page")
    public void validateInternetService() throws Exception {
        AssertThat.elementAssertions(driver, InternetServices).elementIsDisplayed();
        LOGGER.info("User navigated to Internet Services Vernon Hills page");
    }
    @And("User enters Email and clicks Let's go button")
    public void enterEmail(){
        ActOn.element(driver, UserName).setValue("tanzin.mnc@gmail.com");
        ActOn.element(driver, LetsGoButton).click();
        LOGGER.info("User entered Email and clicks Let's go button");
    }
    @And("User enters Password and clicks sign in")
    public void enterPassword() {
        ActOn.element(driver, XfinityPassword).setValue("Password123");
        ActOn.element(driver, LetsGoButton).click();
        LOGGER.info("User entered Password and clicks sign in");
    }
    @Then("User should be successfully logged in")
    public void validateSuccessfulLLoggedIn(){
        boolean Present = ActOn.element(driver, LogInSuccess).getElement().isDisplayed();
        Assert.assertTrue("Need to change your plan?", Present);
        LOGGER.info("User should be successfully logged in");
    }
    @And("User enters the invalid address in the service availability checker and clicks it")
    public void enterInvalidAddress(){
        ActOn.element(driver, AddressInputField).setValue("Dhaka, Bangladesh");
        ActOn.element(driver, SearchIconTwo).click();
        LOGGER.info("User entered the invalid address ");
    }
    @Then("The search results should be irrelevant for the current address")
    public void validateInvalidAddress() throws Exception {
        AssertThat.elementAssertions(driver, InvalidAddress).elementIsDisplayed();
        LOGGER.info("User should not see any information for the current address");

    }


}
