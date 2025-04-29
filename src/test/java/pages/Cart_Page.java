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

    @FindBys({@FindBy(xpath = "//a[text()='অধোগামী দিনের পঞ্জিকা']")

    })
    WebElement details;

    @FindBys({@FindBy(xpath = "//a[@id='viewBuyURL']")

    })
    WebElement add_cart;

    @FindBys({@FindBy(xpath = "(//img[@src='https://www.prothoma.com/images/frontend/cart_bag.png'])[1]")

    })
    WebElement cart_icon;



    @FindBys({@FindBy(xpath = "//a[contains(text(),'কার্ট দেখুন')]")     ///

    })
    WebElement cart;

    @FindBys({@FindBy(xpath = "(//a[contains(@class,'order-btn-ct')][contains(text(),'অর্ডার দিন')])[1]")     ///

    })
    WebElement order ;






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
                            test.info("Please show Add to Cart Icon");
                            new Actions(driver).moveToElement(cart_icon).build().perform();
                            passCaseWithSC("Show  the Cart Icon","carticon_success");

                        try {
                            test.info("Please Click  Cart Options");
                            cart.click();
                            passCaseWithSC("Click on the Cart Options","cart_success");

                           try {
                                test.info("Please Click  Order Options");
                                order.click();
                                passCaseWithSC("Click on the Cart Options","order_success");

                            }catch (Exception e){
                                failCase("Cart Options was not Locate ","order_success_fail");
                            }
                            }catch (Exception e){
                                failCase("Cart Options was not Locate ","cart_success_fail");
                            }
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

