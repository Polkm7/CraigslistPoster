package com.mysite.SelTest2;

import io.ddavison.conductor.Browser;
import io.ddavison.conductor.Config;
import io.ddavison.conductor.Locomotive;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;




@Config(browser = Browser.CHROME,
        url="http://craigslist.org")
public class ClPost extends Locomotive {


    String[] sites = new String[]{
            "http://atlanta.craigslist.org/atl/",
            "http://atlanta.craigslist.org/nat/",
            "http://atlanta.craigslist.org/eat/",
            "http://atlanta.craigslist.org/sat/",
            "http://atlanta.craigslist.org/wat/",
            "http://chattanooga.craigslist.org/",
            "http://athensga.craigslist.org/",
            "http://knoxville.craigslist.org/",
            "http://nashville.craigslist.org/",
            "http://nwga.craigslist.org/"
    };

    /* extra step required
            "http://atlanta.craigslist.org/atl/",
            "http://atlanta.craigslist.org/nat/",
            "http://atlanta.craigslist.org/eat/",
            "http://atlanta.craigslist.org/sat/",
            "http://atlanta.craigslist.org/wat/",
     */

    //fields
    private String zipCode = "30721";
    private String location = "Dalton GA";


    int i = 1; //used to iterate through atlanta's extra category menu

    /*
    public ClPost(String unitName, String unitPrice, String body, String unitMake, String unitCC, String condition, String unitYear, String unitColor) {
        this.unitName = unitName;
        this.unitPrice = unitPrice;
        this.body = body + closing;
        this.unitMake = unitMake;
        this.unitCC = unitCC;
        this.condition = condition;
        this.unitYear = unitYear;
        this.unitColor = unitColor;
    }
    */


    public void login() throws InterruptedException{
        //logs in to account
        Thread.sleep(1500);
        click(By.linkText("my account"));
        setText(By.id("inputEmailHandle"), "YOUR EMAIL");
        setText(By.id("inputPassword"), "YOUR PASSWORD");
        click(By.className("accountform-btn"));
        Thread.sleep(1000);
    }



    public void category() throws InterruptedException{
        //goes to correct category
        click(By.id("post"));
        //click(By.xpath("//form[@class=\"new_posting_thing\"]/input")); //clicks post button
        click(By.xpath(("//label[7]"))); //selects for sale by dealer
        click(By.xpath("//label[31]")); //selects motorcycle/scooters
        Thread.sleep(500);
        if (isPresent(By.xpath("//b[text()=\"choose the location that fits best:\"]"))){ //iterates through extra location
            Thread.sleep(500);                                                           //category if detected
            String label = "//label[" + i + "]";
            click(By.xpath(label));
            i++;
        }
    }


    public void fillFields(CreateUnit unit) {
        //-----------------------------------------------------------
        //post page

        //fills many fields
        click(By.id("contact_phone_ok"));
        setText(By.id("contact_phone"), "706-226-4090");
        setText(By.id("contact_name"), "Tanner");
        setText(By.id("Ask"), unit.unitPrice);
        setText(By.id("GeographicArea"), location);
        setText(By.id("postal_code"), zipCode);
        setText(By.id("PostingTitle"), unit.unitName);
        setText(By.id("PostingBody"), unit.body);
        selectOptionByText(By.id("auto_year"), unit.unitYear);
        setText(By.id("auto_make_model"), unit.unitMake);
        setText(By.id("engine_displacement_cc"), unit.unitCC);
        setText(By.id("auto_miles"), "0");
        selectOptionByText(By.id("auto_fuel_type"), "gas");
        selectOptionByText(By.id("condition"), unit.condition);
        selectOptionByText(By.id("auto_paint"), unit.unitColor);
        selectOptionByText(By.id("auto_title_status"), "clean");
        selectOptionByText(By.id("auto_transmission"), "manual");
        setText(By.id("xstreet0"), "929 N Glenwood Ave");
        setText(By.id("city"), "Dalton");
        setText(By.id("region"), "30721");
        click(By.name("go")); //next page

    }


    public void maps() {
        //-----------------------------------------------------------
        //maps page

        click(By.cssSelector("button.continue.bigbutton")); //next page

    }


    public void upload(CreateUnit unit) throws InterruptedException, AWTException {
        //-----------------------------------------------------------
        //image upload page

        StringSelection ss = new StringSelection(unit.folder);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        click(By.id("plupload")); //click upload

        // mimicks keystrokes in order to upload image
        Robot robot = new Robot();
        robot.delay(250);
        robot.keyPress(KeyEvent.VK_CONTROL); //pastes link to filder
        robot.keyPress(KeyEvent.VK_V);
        robot.delay(100);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(100);
        robot.keyPress(KeyEvent.VK_ENTER); //goes to folder
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(500);

        for (int i = 0; i < 9; i++) { //tabs until it selects a file in the folder
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            Thread.sleep(200);
        }

        robot.keyPress(KeyEvent.VK_CONTROL); //now that file is selected, it can use ctr + a to select all
        robot.keyPress(KeyEvent.VK_A);
        Thread.sleep(100);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_A);
        Thread.sleep(500);
        robot.keyPress(KeyEvent.VK_ENTER);  //uploads images
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(20000);

        waitForElement(By.xpath("//form/button[text()=\"done with images\"]"));
        click(By.xpath("//form/button[text()=\"done with images\"]"));


    }


    public void publish() throws InterruptedException {
        //-----------------------------------------------------------
        //publish page

        waitForElement(By.xpath("//form/button[text()=\"publish\"]"));
        Thread.sleep(1000);
        click(By.xpath("//form/button[text()=\"publish\"]"));
        Thread.sleep(3000);
        //post complete
    }
}
