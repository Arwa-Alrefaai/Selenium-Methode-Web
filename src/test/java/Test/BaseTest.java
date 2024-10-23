package Test;

import Pages.*;
//import Pages.BasePage;
import org.apache.commons.io.FileUtils;
import org.fluentlenium.adapter.testng.FluentTestNg;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest extends FluentTestNg {


    @Page
    BasePage basepage;

    public String baseUrl, nodeURL;

   /* @Override
    public RemoteWebDriver newWebDriver() {
        baseUrl = "https://a1.net";
        nodeURL = "http://localhost:4444";
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setBrowserName("chrome");
        RemoteWebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(nodeURL), capability);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        driver.manage().window().maximize();
        return driver;
    }*/

    @Override
    public RemoteWebDriver newWebDriver() {
        baseUrl = "https://a1.net";
        nodeURL = "http://localhost:4444/wd/hub"; // Ensure the correct hub URL

        // Use ChromeOptions instead of DesiredCapabilities
        ChromeOptions options = new ChromeOptions();
        options.setCapability("browserName", "chrome");

        RemoteWebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(nodeURL), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Grid URL", e);
        }

        driver.manage().window().maximize();
        return driver;
    }

    /*public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
        ChromeDriver chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();

    }*/


    @AfterMethod
    public void failWatcher(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            String pathScreenshot = System.getProperty("user.dir") + "\\target\\surefire-reports\\screenshots\\" + result.getName() + ".png";
            String pathHtmlDump = System.getProperty("user.dir") + "\\target\\surefire-reports\\htmldumps";

            try {
//Create Screenshot on Failure
                TakesScreenshot screenshot = (TakesScreenshot) getDriver();
                File src = screenshot.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(src, new File(pathScreenshot));
                basepage.logger.info("Successfully captured a screenshot at: " + pathScreenshot);

//Create HTML Dump on Failure
                setHtmlDumpPath(pathHtmlDump);
                takeHtmlDump(result.getName() + "_htmlDump.html");
                basepage.logger.info("Successfully created HTML dumpfile at: " + pathHtmlDump);

            } catch (Exception e) {
                basepage.logger.error("Exception while taking screenshot or creating HTML dumpfile" + e.getMessage());
            }
        }
        getDriver().quit();
    }
}


