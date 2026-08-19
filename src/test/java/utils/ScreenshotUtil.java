package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {
    public static String captureScreenshot(WebDriver driver, String screenshotName){
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = String.valueOf(System.currentTimeMillis());
        String destPath = System.getProperty("user.dir")+ "/screenshots/" + screenshotName + "_" + timestamp + ".png";
        File destination = new File(destPath);
        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            System.out.println("Screenshot  failed: " + e.getMessage());
        }
        return destPath;

    }

}
