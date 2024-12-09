package com.sainsburys.stepDefs;
import com.sainsburys.utilities.ConfigurationReader;
import com.sainsburys.utilities.DB_Util;
import com.sainsburys.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.time.Duration;

public class Hooks {

    @Before("@ui")
    public void setup() {
        try {
            Driver.getDriver().manage().window().maximize();
            Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


            String url = ConfigurationReader.getProperty("loginUrl");
            if (url == null || url.isEmpty()) {
                throw new IllegalArgumentException("The loginPage URL is missing or invalid in configuration.properties");
            }
            System.out.println("Navigating to URL: " + url);

            // Navigate to URL
            Driver.getDriver().get(url);
        } catch (Exception e) {
            System.err.println("Error in setup method: " + e.getMessage());
            throw e; // Fail the test if setup fails
        }
    }

    @After("@ui")
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()){
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png","screenshot");
        }

        Driver.closeDriver();

    }

    @Before("@db")
    public void setUpDB(){
        System.out.println("Connecting to database...");
        DB_Util.createConnection();
    }

    @After("@db")
    public void tearDownDB(){
        System.out.println("close database connection...");
        DB_Util.destroy();
    }

}
