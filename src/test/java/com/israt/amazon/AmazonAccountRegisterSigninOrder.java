package com.israt.amazon;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.israt.base.TestBase;
import com.israt.utils_log.LogInstance;
import com.israt.xls_reader.Xls_Reader;
import jdk.internal.instrumentation.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;

public class AmazonAccountRegisterSigninOrder extends TestBase {
//    public static final org.apache.logging.log4j.Logger logger=LogManager.getLogger(LogInstance.class);
//    public static void main(String[] args) throws IOException {
//        chromeLaunch();
//       openTestURL("https://www.amazon.com/");
//        //registerAccountWithValidCredentials();
//       logInWithValidCredentials();
//        //orderProduct();
//       // selectByValue();
//       // signOut();
//        //openTestURL("https://www.bbc.com/");
//        //captureFullScreenshot();
//
//    }

    //Extent reporting
    ExtentHtmlReporter extentHtmlReporter;
    ExtentReports extentReports;
    ExtentTest logger;

    public static final String BASE_URL =  "https://www.amazon.com/";

    @BeforeMethod

    public void initSetUp() {
        chromeLaunch();
        LogInstance.info("Chrome Launched Successfully");
        driver.get(BASE_URL);


    }

    @AfterMethod(enabled = false)
    public void teardown(){
        chromeClose();
        LogInstance.info("Chrome closed successfully");
    }


//    @Test
//    public static void signinWithValidCredentials() throws IOException {
//        logInWithValidCredentials();
//    }

//    @DataProvider(name = "LoginData")
//    public Object[][] data() {
//        Object data[][] = new Object[2][2];
//
//        data[0][0]="contact.eleganceapparel@gmail.com";
//        data[0][1]="123456";
//
//        data[1][0]="contact.eleganceapparel@gmail.com";
//        data[1][1]="123";
//
//        return data;
//    }



    @Test(enabled = false)
    public static void registerAccountWithValidCredentials(){
        //driver.get("https://www.amazon.com/");
       // driver.get(BASE_URL);

        getElementByXpathAndClick("//*[@id=\"nav-link-accountList\"]/span");

        getElementByIdAndClick("createAccountSubmit");

        getElementByIdAndType("ap_customer_name","Elegance Apparel");

//        getElementByIdAndType("ap_email","contact.eleganceapparel@gmail.com");
        getElementByIdAndType("ap_email","contact.eleganceapparel@gmail.com");

        getElementByIdAndType("ap_password","123456");

        getElementByIdAndType("ap_password_check","123456");

        getElementByIdAndClick("continue");
    }

    @Test(enabled = false)
    public static void registerAccountWithInvalidCredentials(){
 //       driver.get("https://www.amazon.com/");
        driver.get(BASE_URL);

        getElementByXpathAndClick("//*[@id=\"nav-link-accountList\"]/span");

        getElementByIdAndClick("createAccountSubmit");

        getElementByIdAndType("ap_customer_name","Elegance Apparel");

        getElementByIdAndType("ap_email","contact.eleganceapparel@gmail.com");

        getElementByIdAndType("ap_password","123");

        getElementByIdAndType("ap_password_check","123");

        getElementByIdAndClick("continue");

    }

    @Test
    public void loginTest(){

        //Extent Rport
        extentHtmlReporter=new ExtentHtmlReporter("./Extent-Report-For-Login/LoginTestReport.html");
        extentReports =new ExtentReports();
        extentReports.attachReporter(extentHtmlReporter);

         //Extent optional
        extentReports.setSystemInfo("OS","Windows 10");
        extentReports.setSystemInfo("Tester","Unknown");
        extentReports.setSystemInfo("Environment","Test");

        logger=extentReports.createTest("Login Test");

        getElementByXpathAndClick("//*[@id=\"nav-link-accountList\"]/span");

       //To read data from Excel

        Xls_Reader reader=new Xls_Reader("./src/test/resources/LoginData.xlsx");
        String sheetName="Sheet1";

        int rowCount= reader.getRowCount(sheetName);

        for(int rowNum=2; rowNum<rowCount;rowNum++) {
            String email = reader.getCellData(sheetName, "Email", rowNum);
            String pass = reader.getCellData(sheetName, "Password", rowNum);
            getElementByIdAndType("ap_email",email);
//            report by log4j
//            LogInstance.info("Type Email" +email);
//            report by Extent Report
            logger.log(Status.INFO,"Type Email " +email);

            getElementByIdAndClick("continue");

            getElementByIdAndType("ap_password",pass);
//            LogInstance.info("Type Email"+pass);
            logger.log(Status.INFO,"Type Password " +pass);


            getElementByIdAndClick("signInSubmit");
//            LogInstance.warn("If Sign in then Successful otherwise test failed");
            logger.log(Status.INFO,"If Sign in then Successful otherwise test failed");

            signOut();
//            LogInstance.info("Signed out successfully");
            logger.log(Status.INFO,"Signed out successfully");

        }
        extentReports.flush();

    }


//    @Test(dataProvider = "LoginData")
//    public static void logInWithValidCredentials(String DP_Email, String DP_Password) throws IOException {
////        driver.get("https://www.amazon.com/");
////        driver.get(BASE_URL);
//
//        getElementByXpathAndClick("//*[@id=\"nav-link-accountList\"]/span");
//        LogInstance.info("Click to Account and Lists");
//
////        getElementByIdAndType("ap_email","contact.eleganceapparel@gmail.com");
//        getElementByIdAndType("ap_email",DP_Email);
//        LogInstance.info("Type Email");
//
//        getElementByIdAndClick("continue");
//
////        getElementByIdAndType("ap_password","123456");
//        getElementByIdAndType("ap_password",DP_Password);
//
//        getElementByIdAndClick("signInSubmit");
//         LogInstance.warn("If Sign in then Successful otherwise test failed");
//
//        signOut();
//        LogInstance.info("Signed out successfully");
////    }


    @Test(enabled = false)
    public static void logInWithInvalidCredentials(){
//        driver.get("https://www.amazon.com/");
//        driver.get(BASE_URL);

        getElementByXpathAndClick("//*[@id=\"nav-link-accountList\"]/span");

        getElementByIdAndType("ap_email","contact.eleganceapparel");

        getElementByIdAndClick("continue");

        getElementByIdAndType("ap_password","123");

        getElementByIdAndClick("signInSubmit");
//
//        String expectedTitle="Buy Again";
//        String actualTitle= driver.getTitle();
//        if(!expectedTitle.equals(actualTitle)){
//            System.out.println("Test passed for Invalid data");
//        }
//        else{
//            System.out.println("Test Failed for Invalid data : Yahoo, Bug Found");
//        }
    }

    public static void signOut(){
       // driver.get(BASE_URL);
        // Locating the Main Menu (Parent element)
        WebElement AccountandLists = driver.findElement(By.xpath("//*[@id=\"nav-link-accountList\"]/span"));

//Instantiating Actions class
        Actions actions = new Actions(driver);

//Hovering on main menu
        actions.moveToElement(AccountandLists);

// Locating the element from Sub Menu
        // WebElement SignOut = driver.findElement(By.xpath("//*[@id=\"nav-item-signout\"]"));
        WebElement SignOut=driver.findElement(By.id("nav-item-signout"));

//To mouseover on sub menu
        actions.moveToElement(SignOut);

//build()- used to compile all the actions into a single step
        actions.click().build().perform();
    }


    @Test
    public void orderProduct(){
       // driver.get(BASE_URL);

        extentHtmlReporter=new ExtentHtmlReporter("./ExtentReport-For-Order/OrderProductTestReport.html");
        extentReports =new ExtentReports();
        extentReports.attachReporter(extentHtmlReporter);

        logger=extentReports.createTest("Order a product from Amazon");

        getElementByIdAndType("twotabsearchtextbox","La Roche Posay");
        getElementByIdAndClick("nav-search-submit-button");
        driver.findElement(By.linkText("La Roche-Posay Toleriane Double Repair Face Moisturizer, Daily Moisturizer Face Cream with Ceramide and Niacinamide for All Skin Types, Oil Free, Fragrance Free")).click();

//        select quantity.
        Select quantity=new Select(driver.findElement(By.id("rcxsubsQuan")));
        quantity.selectByValue("2");
        logger.log(Status.INFO,"Select quantity");

//        set up now
        getElementByIdAndClick("rcx-subscribe-submit-button-announce");
        logger.log(Status.INFO,"Setup now");

//        proceed to checkout
        getElementByNameAndClick("proceedToRetailCheckout");
        logger.log(Status.INFO,"Check out");

        LogInstance.info("Product has been ordered");
        logger.log(Status.INFO,"Order has benn completed");

        extentReports.flush();


    }

}


