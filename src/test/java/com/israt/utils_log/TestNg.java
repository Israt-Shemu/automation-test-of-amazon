package com.israt.utils_log;
import com.israt.base.TestBase;
import org.openqa.selenium.WebDriver;
import com.israt.amazon.AmazonAccountRegisterSigninOrder;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestNg extends TestBase {


    @BeforeTest
    public void tc001(){
        System.out.println("hello shemu");
    }
    @BeforeSuite
    public void tc002(){
        System.out.println("hello rimu");
    }
    @Test
    public void tc004(){
        System.out.println("hiiii");
    }

    }

