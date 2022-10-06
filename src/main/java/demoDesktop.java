
import com.lambdatest.tunnel.Tunnel;
import cucumber.api.java.eo.Se;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class demoDesktop {

    public String username = System.getenv("LT_USERNAME");
    public String accesskey = System.getenv("LT_ACCESS_KEY");
    public RemoteWebDriver driver;
    public String gridURL = "hub.lambdatest.com";
    String status="failed";
    String hub;
    SessionId sessionId;


    @org.testng.annotations.Parameters(value = {"browser", "version", "platform"})

    @BeforeTest
    public void setUp(String browser, String version, String platform) throws Exception {
        try {

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("build", "Trepp Issue reproduce Jenkins");
            caps.setCapability("name", "Test 1");
            caps.setCapability("platform", platform);
            caps.setCapability("browserName", browser);
            caps.setCapability("version", version);
            caps.setCapability("resolution", "1920x1080");
            caps.setCapability("idleTimeout","1800");
            caps.setCapability("acceptSslCerts", true);
            caps.setCapability("acceptInsecureCerts", true);

            StopWatch driverStart = new StopWatch();
            driverStart.start();

            hub = "https://" + username + ":" + accesskey + "@" + gridURL + "/wd/hub";
            driver = new RemoteWebDriver(new URL(hub), caps);

            sessionId = driver.getSessionId();
            System.out.println(sessionId);
            driverStart.stop();
            float timeElapsed = driverStart.getTime() / 1000f;
            System.out.println("Driver initiate time" + "   " + timeElapsed);
            ArrayList<Float> TotalTimeDriverSetup = new ArrayList<Float>();
            TotalTimeDriverSetup.add(timeElapsed);
            System.out.println(TotalTimeDriverSetup);


        } catch (
                MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception f) {
            System.out.println(f);

        }

    }

    @Test
    public void DesktopScript() {
        try {
//         driver.get("https://testfiledownload.com/");
//         Thread.sleep(4000);

//         driver.manage().window().maximize();
//         Thread.sleep(4000);

//         JavascriptExecutor js = (JavascriptExecutor) driver;
//         js.executeScript("window.scrollBy(0,400)", "");
            
//          Thread.sleep(4000);



//         driver.findElementByXPath("//*[@id=\"main\"]/article/div/div[1]/div[1]/div[3]/a").click();
//         Thread.sleep(450000);
            
          driver.get("https://treppftp.ftptoday.com/");


          driver.manage().window().maximize();
          Thread.sleep(4000);

//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0,400)", "");


        driver.findElementById("u").sendKeys("trepp-qa\n");
        Thread.sleep(4000);
        driver.findElementById("p").sendKeys("TreppWelcome7!\n");
        Thread.sleep(4000);
        driver.findElementByXPath("//*[@id=\"gfiles-grid-table\"]/tbody/tr[7]/td[12]").click();
        Thread.sleep(4000);
        driver.findElementByXPath("//*[@id=\"gfiles-grid-table\"]/tbody/tr[7]/td[3]/a").click();



        Thread.sleep(4000);
        driver.get("chrome://downloads/");
        Thread.sleep(300000);
        System.out.println("Before Lambda file content");    
// Download file from remote to local machine
        String base64EncodedFile = ((JavascriptExecutor) driver).executeScript("lambda-file-content=feed_014.zip").toString(); // file content download
        System.out.println("After lambda File content");   
        byte[] byteArray = Base64.decodeBase64(base64EncodedFile.getBytes());
//creates a file on your local system
        FileOutputStream fos;
        File file=new File("D:\\Code Stuff\\Test1\\trepp\\feed_014_1.zip");
        fos = new FileOutputStream(file);
        fos.write(byteArray);
        fos.close();
        System.out.println("fos file writing done");    
        System.out.println(fos);
            status="passed";
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
    }


    @AfterTest
    public void tearDown() throws Exception {
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}

