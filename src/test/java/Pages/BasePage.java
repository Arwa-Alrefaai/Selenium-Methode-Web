package Pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Label;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import javax.lang.model.element.Element;
import java.io.File;
import java.time.Duration;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.fluentlenium.assertj.FluentLeniumAssertions.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class BasePage extends FluentPage {

    //*************************** Attributes **************************************
    public final String URL_MAIN = "https://www.google.com/";
    public final String NEW_URL = "https://www.saucedemo.com/";
    public final String ABOUT ="https://saucelabs.com/";
    public Logger logger = LogManager.getLogger();

    public final String USERNAME = "standard_user";
    public final String PASSWORD = "secret_sauce";
    public final String SortText = "Price (low to high)";
    public final String UNVALIDUSERNAME = " ";
    public final String COLOR = "  ";
    public  final Integer WIDTH_BEREIT = 14190;
    public final String text = " Products ";
    public final String Text = " About";
    public final String HEADER = "Swag Labs";
    //*************************** Find section  **************************************

    @FindBy(id = "user-name")
    @Label
    public FluentWebElement UserName;

    @FindBy(id = "password")
    @Label
    public FluentWebElement PassWord;

    @FindBy(xpath = "//*[@id=\"login-button\"]")
    @Label
    public FluentWebElement Submit;


    @FindBy(xpath = "/html/body/div/div/div/div[1]/div[2]/div/span/select")
    @Label
    public FluentWebElement Select;
    @FindBy(css = "/html/body/div/div/div/div[1]/div[2]")
    @Label
    public FluentWebElement Product;
    @FindBy (xpath = "/html/body/div/div/div/div[1]/div[1]/div[2]/div")
    @Label
    public FluentWebElement Header;

@FindBy (xpath = "//*[@id=\"about_sidebar_link\"]")
@Label
public FluentWebElement About;
    //************************************* Actions *******************************


    //******************************** Pages Action ***********************************
    public BasePage checkUrl(String expectedUrl) {
        String actualUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
        logger.info("actualURL'{}' is equal to expectedUrl'{}'", actualUrl, expectedUrl);
        return this;
    }

    public BasePage wait3seconds() {
        await().explicitlyFor(3, SECONDS);
        logger.info("Waited 3 second");
        return this;
    }
    public BasePage wait5seconds() {
        await().explicitlyFor(3, SECONDS);
        logger.info("Waited 5 second");
        return this;
    }
    public BasePage wait10seconds() {
        await().explicitlyFor(3, SECONDS);
        logger.info("Waited 10 second");
        return this;
    }
    public BasePage openNewTab() {
        executeScript("window.open()");
        logger.info("New Tab was open.");
        return this;
    }

    public BasePage switchToNewTab() {
        Set<String> windowHandles = getDriver().getWindowHandles();
        String[] handles = windowHandles.toArray(new String[0]);
        getDriver().switchTo().window(handles[handles.length - 1]);
        logger.info("Switched to New Tab.");
        return this;
    }


    public BasePage switchToOldTab() {
        Set<String> windowHandles = getDriver().getWindowHandles();
        String[] handles = windowHandles.toArray(new String[0]);
        getDriver().switchTo().window(handles[0]);
        logger.info("Switched to Old Tab.");
        return this;
    }

    public BasePage switchToSecondTab() {
        String currentUrl = getDriver().getCurrentUrl();
        logger.info("new tab currentUrl is: '{}'", currentUrl);
        Set<String> windowHandles = getDriver().getWindowHandles();
        String[] handles = windowHandles.toArray(new String[0]);
        getDriver().switchTo().window(handles[1]);
        logger.info("switched to the second browser tab");
        String currentUrl2 = getDriver().getCurrentUrl();
        logger.info("new tab currentUrl is: '{}'", currentUrl2);
        return this;
    }

    public BasePage closeThisTab() {
        getDriver().close();
        logger.info("Tab was closed.");
        return this;
    }

    public BasePage goToNewPage(String Url) {
        openNewTab();
        switchToNewTab();
        goTo(Url);
        return this;
    }

    public BasePage refreshThisPage() {
        getDriver().navigate().refresh();
        wait3seconds();
        return this;
    }

    public BasePage closeTab() {
        getDriver().close();
        logger.info("Tab closed successfully ");
        return this;
    }


    //********************************************** Element Action ***************************
    public BasePage waitForDisplayedElement(final FluentWebElement element) {
        await().atMost(15, TimeUnit.SECONDS).until(element).displayed();
        logger.info("Element is now displayed: {}", element);
        return this;
    }

    public BasePage isClickable(FluentWebElement element) {
        waitForDisplayedElement(element);
        await().atMost(15, SECONDS).until(element).clickable();
        assertThat(element).isClickable();
        logger.info("Element is clickable: '{}'", element);
        return this;
    }

    public BasePage waitForPresentElement(final FluentWebElement element, final int seconds) {
        await().atMost(seconds, TimeUnit.SECONDS).until(element).present();
        logger.info("Waited for element: {}", element);
        return this;
    }


    public BasePage clickElement(FluentWebElement element) {
        isClickable(element);
        element.click();
        logger.info("Clicked on element: '{}'", element);
        return this;
    }
    public BasePage waitForElement(final FluentWebElement element, final int seconds) {
        await().atMost(seconds, TimeUnit.SECONDS).until(element).present();
        logger.info("Waited for element: {}", element);
        return this;
    }

    public BasePage isDisplayed(FluentWebElement element) {
        waitForElement(element,10);
        assertThat(element).isPresent();
        logger.info("Element is displayed: '{}'", element);
        return this;
    }


    public String randomXDigits() {
        Random random = new Random();
        int randomNumber = 10000 + random.nextInt(90000);
        logger.info("Random 5-digit number: " + randomNumber);
        return String.valueOf(randomNumber);
    }


    public BasePage clearInput(FluentWebElement element) {
        waitForDisplayedElement(element);
        element.clear();
        logger.info("Input was emptied of Element: '{}'", element);
        return this;
    }

    public BasePage uploadDocument() {
        File uploadFIle = new File("src/Path/");
        WebElement fileInput = getDriver().findElement(By.id("element id"));
        fileInput.sendKeys(uploadFIle.getAbsolutePath());
        wait3seconds();
        return this;
    }

    public boolean compareStringEqual(String actualString, String expectedString) {
        assertEquals(actualString, expectedString);
        logger.info("Strings are equal: {} and {}", actualString, expectedString);
        return true;
    }

    public BasePage compareIntEqual(int actualInt, int expectedInt) {
        assertEquals(actualInt, expectedInt);
        logger.info("The Variables are equal : {} and {}", actualInt, expectedInt);
        return this;
    }

    public BasePage compareStringNotEquals(String actualString, String expectedString) {
        assertNotEquals(actualString, expectedString);
        logger.info("The Strings are not equal: {} and {]", actualString, expectedString);
        return this;
    }

    public BasePage compareNotEqualInt(int actualInt, int expectedInt) {
        assertNotEquals(actualInt, expectedInt);
        logger.info("The Values are not equal {} and {}", actualInt, expectedInt);
        return this;
    }

    public BasePage isPresent(FluentWebElement element) {
        waitForDisplayedElement(element);
        await().atMost(1, SECONDS).until(element).present();
        assertThat(element).isPresent();
        logger.info("Element is selected: '{}'", element);
        return this;
    }

    public BasePage hasAttribute(FluentWebElement element, String attribute, String value) {
        Assertions.assertThat(element.cssValue(attribute)).isEqualTo(value);
        logger.info("Attribute'{}' has the value of '{}' in Element: '{}'", attribute, value, element);
        return this;
    }

    public BasePage hasText(FluentWebElement element, String text) {
        isDisplayed(element);
        assertThat(element).hasText(text);
        logger.info("The text '{}' is in element: {}", text, element);
        return this;
    }

    public BasePage selectValue(String Value) {
        el("#The value").click().selected();
        return this;
    }

    public BasePage getText(FluentWebElement element) {
        String text = element.text();
        logger.info("The text is: '{}'", text);
        return this;
    }


    public BasePage selectDropDown(FluentWebElement dropdown, String textOption) {
        Select select = new Select(dropdown.getElement());
        select.selectByVisibleText(textOption);
        logger.info("The option '{}' is selected ", textOption);
        return this;
    }


    public BasePage fillUserName(String username) {
        clearInput(UserName);
        clickElement(UserName);
        el("#user-name").fill().with(username);
        return this;
    }


    public BasePage fillPassWord(String password) {
        clearInput(PassWord);
        clickElement(PassWord);
        el("#password").fill().with(password);
        return this;
    }


    public BasePage clickSubmit(FluentWebElement submit) {
        isClickable(submit);
        submit.click();
        logger.info("Clicked on element: '{}'", submit);
        return this;
    }


    public BasePage checkAlertMessageText(FluentWebElement element, String message) {
        hasText(element, message);
        logger.info("Element'{}' has the Message '{}'", element, message);
        return this;
    }






    public BasePage selectDropdownOption(FluentWebElement dropdown, String optionText) {
        Select select = new Select(dropdown.getElement());
        select.selectByVisibleText(optionText);
        logger.info("The selected option is {} ", optionText);
        return this;
    }


    public BasePage clickEnter() {
        Actions actions = new Actions(getDriver());
        actions.sendKeys(Keys.RETURN).perform();
        logger.info("Pressed the Enter key.");
        return this;
    }

    public BasePage clickTab() {
        Actions actions = new Actions(getDriver());
        actions.sendKeys(Keys.TAB).perform();
        logger.info("Pressed the TAB key.");
        return this;
    }


    public BasePage checkMessageText(FluentWebElement element, String message) {
        hasText(element, message);
        logger.info("Element'{}' has the Message '{}'", element, message);
        return this;
    }


    public BasePage checkErrorText(FluentWebElement element, String errorText) {
        wait3seconds();
        hasText(element, errorText);
        hasAttribute(element, "color", errorText);
        logger.info("Element'{}' has '{}' Message", element, errorText);
        return this;
    }


    public BasePage waitUntilVisible(FluentWebElement element, String Selector, int timeoutInSeconds) {
        await().atMost(Duration.ofSeconds(timeoutInSeconds)).until(el(Selector)).displayed();
        logger.info("Element is selected: '{}'", element);
        return this;
    }


    public BasePage checkColor() {

        return this;
    }

    public BasePage fillUnValidUsername(String userName) {
        clearInput(UserName);
        clickElement(UserName);
        fillUserName(UNVALIDUSERNAME);
        return this;
    }

    public BasePage checkColor(FluentWebElement element, String elementcolor) {
        wait3seconds();
        hasAttribute(element, "color", COLOR);
        logger.info("Element '{}' has '{}' color", element, elementcolor);
        return this;
    }



/*    public int checkWidth(FluentWebElement element, ) {
        // Locate the element and get its dimensions (width and height)
        Dimension size = el(cssSelector).getElement().getSize();
        // Return the width of the element
        return size.getWidth();



    }*/
    public int  checkWidthElement(String element){
        //isDisplayed(element);
        Dimension size = el(By.xpath(element)).getElement().getSize();
        return size.getWidth();
    }

    public int  checkHeightElement(String element){
        Dimension size = el(By.xpath(element)).getElement().getSize();
        return size.getHeight();
    }


public BasePage scrollToElement(FluentWebElement element){
        isDisplayed(element);
        scrollToElement(element);
        logger.info("Scroll to the element '{}', element");
        return this;

}

}




























