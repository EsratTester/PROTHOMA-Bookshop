package pages;

import basedrivers.PageDriver;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Screenshots;
import java.io.IOException;
import java.time.Duration;

import static basedrivers.BaseDriver.driver;

public class Menu_Page {
    ExtentTest test;
    public Menu_Page(ExtentTest test) {
        PageFactory.initElements(PageDriver.getCurrentDriver(), this);
        this.test = test;
    }

    @FindBys({@FindBy(xpath = "(//li[@id='writer'])[1]")

    })
    WebElement dropdown;

    @FindBys({@FindBy(xpath = "//a[contains(text(),'সৈয়দ শামসুল হক')]")

    })
    WebElement author;

    @FindBys({@FindBy(xpath = "//label[text()='কবিতা']")

    })
    WebElement checkbox;





    // Report and Screenshot
    public void passCase(String message) {
        test.pass("<p style='color:#85BC63; font-size:14px'><b>" + message + "</b></p>");
    }

    public void passCaseWithSC(String message, String screenshotname) throws IOException {
        test.pass("<p style='color:#22dd3e; font-size:14px'><b>" + message + "</b></p>");
        String screenshotPath = Screenshots.capture(PageDriver.getCurrentDriver(), "" + screenshotname + "");
        String dest = System.getProperty("user.dir") + "\\screenshots\\" + screenshotname + ".png";
        test.info(dest);
        test.pass(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
    }

    public void failCase(String message, String screenshotname) throws IOException {
        test.fail("<p style='color:Red; font-size:14px'><b>" + message + "</b></p>");
        Throwable t = new InterruptedException("Exception");
        test.fail(t);

        String screenshotPath = Screenshots.capture(PageDriver.getCurrentDriver(), "" + screenshotname + "");
        String dest = System.getProperty("user.dir") + "\\screenshots\\" + screenshotname + ".png";
        test.fail(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());


        PageDriver.getCurrentDriver().quit();
    }


    public void menu() throws IOException {
        try{
                WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

                test.info("Please click your Writer options");
                new Actions(driver).moveToElement(dropdown).build().perform();
                passCaseWithSC("You have successfully click your  Writer from the Menu","writer_click_success");

                try {
                        test.info("Please select the author Name");
                        author.click();
                        passCaseWithSC("You have successfully select the author Name","author_success");


                        try{
                            test.info("Please click the Checkbox");
                            Actions actions = new Actions(driver);
                            actions.moveToElement(checkbox).perform();
                            checkbox.click();
                            passCaseWithSC("You Successfully click the checkbox","checkbox_success");

                        }catch (Exception e) {
                            failCase("Checkbox was not Locate ","checkbox_success_fail");
                        }


                } catch (Exception e) {
                    failCase("Author was not Locate ","author_fail");
            }

        }catch (Exception e){
            failCase("Writer was not Locate ","writer_fail");

        }

    }


}

