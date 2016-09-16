package com.mysite.SelTest2;

import io.ddavison.conductor.Browser;
import io.ddavison.conductor.Config;
import io.ddavison.conductor.Locomotive;
import org.junit.Test;
import java.awt.*;



/*  Project Goals:
    1. Loop through different cities                             +
    2. Scramble text body to reduce chance of getting removed
    3. read from csv file to create inventory
    4. store inventory in xml file
    5. Make many email accounts
    6. use constructor to create quickly
*/



public class Main extends Locomotive {

    @Test
    public void test() throws AWTException, InterruptedException{
        ClPost cl = new ClPost();
        cl.navigateTo("http://craigslist.org");
        cl.login();
        CreateUnit ctx700 = new CreateUnit("2015 Honda CTX700N Automatic", "5999", "Get this 2015 Honda CTX700N at a low price for a short time at Kirk's Cycle!" +
        " This bike is fully automatic with a dct transmission and 5 gears. You can also shift using buttons on the handlebar", "Honda","700", "new", "2015","silver", "C:\\Users\\Kirk Cycle LLC\\Documents\\assets\\productImages\\2015CTX700");

        postOnAll(ctx700, cl); //post ctx700 on all craigslist directories
    }
    private void postOnAll(CreateUnit unit, ClPost cl) throws AWTException, InterruptedException{
        for (int i=0;i<cl.sites.length;i++){ //creates unit post on each site
            cl.navigateTo(cl.sites[i]);
            cl.category();
            cl.fillFields(unit);
            cl.maps();
            cl.upload(unit);
            cl.publish();
        }
    }

}

