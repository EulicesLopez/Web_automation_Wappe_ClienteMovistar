package com.tsoft.bot.frontend.utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class MyScreenshot {
    public static void capture(WebDriver webDriver, String interacction){
        TakesScreenshot takesScreenshot =  (TakesScreenshot) webDriver;
        File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
        System.out.println(screenshot.getAbsolutePath());

        try {
            FileUtils.copyFile(screenshot, new File("resources/screenshots/"+interacction+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
