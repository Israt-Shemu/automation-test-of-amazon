package com.israt.base;

import com.israt.utils_log.LogInstance;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
    public static WebDriver driver;
    private static Object Screenshot;

//    public static void main(String[] args) throws IOException {
//        chromeLaunch();
//        chromeClose();
//    }

    public static void chromeLaunch() {
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        LogInstance.info("Chrome launch successfully");
        // driver.get("https://www.amazon.com/");

    }

    public static void chromeClose() {
        driver.close();
        LogInstance.info("Chrome closed successfully ");
    }

    public static void openTestURL(String URL) {
        driver.get(URL);
    }

    public static WebElement getElementById(String locator) {
        return driver.findElement(By.id(locator));
    }

    public static WebElement getElementByClass(String locator) {
        return driver.findElement(By.className(locator));
    }

    public static WebElement getElementByXpath(String locator) {
        return driver.findElement(By.xpath(locator));
    }

    public static WebElement getElementByCss(String locator) {
        return driver.findElement(By.cssSelector(locator));
    }


    public static void getElementByIdAndClick(String locator) {
        driver.findElement(By.id(locator)).click();
    }

    public static void getElementByNameAndClick(String locator) {
        driver.findElement(By.name(locator)).click();
    }

    public static void getElementByClassAndClick(String locator) {
        driver.findElement(By.className(locator)).click();
    }

    public static void getElementByXpathAndClick(String locator) {
        driver.findElement(By.xpath(locator)).click();
    }


    public static void getElementByIdAndType(String locator, String text) {
        driver.findElement(By.id(locator)).sendKeys(text);
    }

    public static void getElementByXpathAndType(String locator, String text) {
        driver.findElement(By.xpath(locator)).sendKeys(text);
    }

    public static void captureScreenshot(String name, String format) throws IOException {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("./src/test/Screenshots/" + name + format), true);
    }

    public static void captureFullScreenshot() throws IOException {
        ru.yandex.qatools.ashot.Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        ImageIO.write(screenshot.getImage(), "jpg", new File("./src/test/Screenshots/fullimage.jpg"));
    }

    public static void captureSpecificElement(String name, String format) throws IOException {
        WebElement captureElement=driver.findElement(By.className("a-section"));
        File srcFile = ((TakesScreenshot) captureElement).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("./src/test/Screenshots/" + name + format), true);

    }

}


