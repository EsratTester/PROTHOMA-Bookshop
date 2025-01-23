package tests;

import basedrivers.PageDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.Menu_Page;
import pages.Visit_Page;
import utilities.CommonMethods;
import utilities.ExtentFactory;

import java.io.IOException;

public class Visit_Test extends CommonMethods {

    ExtentReports extent;
    ExtentTest parentTest;
    ExtentTest childTest;

    @BeforeClass
    public void open_url() throws InterruptedException {
        PageDriver.getCurrentDriver().get(url);
        sleep();
        extent = ExtentFactory.getInstance();
        parentTest = extent.createTest("<p style=\"color:Green; font-size:14px\"><b>Visit Website</b></p>").assignAuthor("Esrat").assignDevice("Windows");
    }

    @Test
    public void prothoma() throws IOException {
        childTest = parentTest.createNode("<p style=\"color:Green; font-size:14px\"><b>Visit</b></p>");
        Visit_Page visit_page = new Visit_Page(childTest);
        visit_page.visit();

    }

   @AfterClass
    public void report(){
        extent.flush();
    }
}
