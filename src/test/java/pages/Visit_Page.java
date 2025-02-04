package pages;

import basedrivers.PageDriver;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Screenshots;
import java.io.IOException;
import java.time.Duration;

import static basedrivers.BaseDriver.driver;

public class Visit_Page {
    ExtentTest test;

    public Visit_Page(ExtentTest test) {
        PageFactory.initElements(PageDriver.getCurrentDriver(),this);
        this.test = test;
    }




    // Report and Screenshot
    public void passCase(String message) {
        test.pass("<p style='color:#85BC63; font-size:14px'><b>" + message + "</b></p>");
    }

    public void passCaseWithSC(String message, String screenshotname) throws IOException {
        test.pass("<p style='color:#22dd3e; font-size:14px'><b>" + message + "</b></p>");
        String screenshotPath = Screenshots.capture(PageDriver.getCurrentDriver(),""+ screenshotname +"");
        String dest = System.getProperty("user.dir") + "\\screenshots\\" + screenshotname + ".png";
        test.info(dest);
        test.pass(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
    }

    public void failCase(String message,String screenshotname) throws IOException{
        test.fail("<p style='color:Red; font-size:14px'><b>" + message + "</b></p>");
        Throwable t = new InterruptedException("Exception");
        test.fail(t);

        String screenshotPath = Screenshots.capture(PageDriver.getCurrentDriver(),""+ screenshotname +"");
        String dest = System.getProperty("user.dir") + "\\screenshots\\" + screenshotname + ".png";
        test.fail(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());


        PageDriver.getCurrentDriver().quit();
    }



    public void visit() throws IOException, InterruptedException {
        test.info("Visit Website");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "window.scrollTo(0,document.body.scrollHeight);";
        js.executeScript(script);
        Thread.sleep(5000);
        js.executeScript("window.scrollTo(0,0)");
        passCaseWithSC("Visit the website is successful", "visit_success");


    }

}
