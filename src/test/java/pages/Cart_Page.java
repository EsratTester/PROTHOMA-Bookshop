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

public class Cart_Page {
    ExtentTest test;
    public Cart_Page(ExtentTest test) {
        PageFactory.initElements(PageDriver.getCurrentDriver(), this);
        this.test = test;
    }

    //Xpath

    @FindBys({@FindBy(xpath = "//body/div[@id='appMain']/div[1]/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[3]/a[1]")

    })
    WebElement details;

    @FindBys({@FindBy(xpath = "//a[@id='viewBuyURL']")

    })
    WebElement add_cart;

    @FindBys({@FindBy(xpath = "(//a[@href='#']//img)[2]")

    })
    WebElement cart_icon;






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


    public void cart() throws IOException {
        try{
                WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

                test.info("Please click your Details options");
                details.click();
                passCaseWithSC("Click on the Details options","details_click_success");

               try {
                       test.info("Please Click Add to Cart options");
                       add_cart.click();
                       passCaseWithSC("Click on the Add to Cart options","addToCart_success");


                        try{
                            test.info("Please Click Add to Cart Icon");
                            cart_icon.click();
                            passCaseWithSC("Click on the Cart Icon","carticon_success");

                        }catch (Exception e) {
                            failCase("Cart Icon was not Locate ","carticon_success_fail");
                        }


              } catch (Exception e) {
                    failCase("Add to Cart was not Locate ","addCart_fail");
            }

        }catch (Exception e){
            failCase("Details was not Locate ","details_fail");

        }

    }


}

