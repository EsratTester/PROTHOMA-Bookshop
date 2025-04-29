package tests;

import basedrivers.PageDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.Cart_Page;
import pages.Menu_Page;
import utilities.CommonMethods;
import utilities.ExtentFactory;

import java.io.IOException;

public class Cart_Test extends CommonMethods {

    ExtentReports extent;
    ExtentTest parentTest;
    ExtentTest childTest;

    @BeforeClass
    public void open_url() throws InterruptedException {
        //PageDriver.getCurrentDriver().get(url);
        sleep();
        extent = ExtentFactory.getInstance();
        parentTest = extent.createTest("<p style=\"color:Green; font-size:14px\"><b>Cart Page</b></p>").assignAuthor("Esrat").assignDevice("Windows");
    }

    @Test
    public void prothoma() throws IOException, InterruptedException {
        childTest = parentTest.createNode("<p style=\"color:Green; font-size:14px\"><b>Details Cart Page</b></p>");
        Cart_Page cartPage = new Cart_Page(childTest);
        cartPage.cart();

    }

   @AfterClass
    public void report(){
        extent.flush();
    }
}
