package testing;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyTestCase {
	
	public MyTestCase() throws MalformedURLException {
        super(); // This call can be explicit or left implicit
    }
	
	// 1. Create a ChromeOptions or Firefox instance
	//ChromeOptions options = new ChromeOptions();
	FirefoxOptions options=new FirefoxOptions();

	// 2. Pass ChromeOptions into RemoteWebDriver
	@SuppressWarnings("deprecation")
	URL gridUrl = new URL("http://localhost:4444/wd/hub"); // Replace with your Hub URL
	RemoteWebDriver driver = new RemoteWebDriver(gridUrl, options);   
   final String BASE_URL = "https://www.careerorbits.com/";
    @BeforeClass
    public void setUp() {
        //driver = new ChromeDriver();  
    	//driver = new FirefoxDriver();
    	//driver =new EdgeDriver();
        //driver = new InternetExplorerDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    // Every test starts from the home page
    @BeforeMethod
    public void openHome() {
        driver.get(BASE_URL);
    }
    
    private void clickMenu(String hrefPart) {
        driver.findElement(By.xpath("(//a[contains(@href,'" + hrefPart + "')])[1]")).click();
    }

    // Finds the first link whose href contains the given text (the header menu comes first in the page)
    private void assertUrlContains(String expectedPart, String message) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        boolean ok;
        try {
            ok = wait.until(ExpectedConditions.urlContains(expectedPart));
        } catch (Exception e) {
            ok = false;
        }
        Assert.assertTrue(ok, message + " | actual URL: " + driver.getCurrentUrl());
    }

    @Test
    public void verifyJeeAdvancedPage() {
        clickMenu("/jee-advanced/iit-jee-advanced-preparation-courses");
        assertUrlContains("jee-advanced", "JEE ADV URL mismatch");
    }

    @Test
    public void verifyJeeMainPage() {
        clickMenu("/jee-main/jee-main-prep-courses");
        assertUrlContains("jee-main", "JEE MAIN URL mismatch");
    }

    @Test
    public void verifyNeetUgPage() {
        clickMenu("/neet/neet-ug-aipmt-aiims-pmt-preparation-courses");
        assertUrlContains("neet", "NEET UG URL mismatch");
    }

    @Test
    public void verifyFeaturesPage() {
        clickMenu("/features");
        assertUrlContains("/features", "FEATURES URL mismatch");
    }

    @Test
    public void verifyHelpPage() {
        clickMenu("/help");
        assertUrlContains("/help", "HELP URL mismatch");
    }

    @Test
    public void verifyContactUsPage() {
        clickMenu("/courses/contact-us");
        assertUrlContains("contact-us", "CONTACT US URL mismatch");
    }

    @Test
    public void verifyBuyPage() {
        clickMenu("/courses/buy");
        assertUrlContains("/courses/buy", "BUY URL mismatch");
    }

    @Test
    public void verifyFreeTrialPage() {
        clickMenu("/courses/free-trial");
        assertUrlContains("free-trial", "TRIAL URL mismatch");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}