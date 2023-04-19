package base;

import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;


import org.apache.commons.io.FileUtils;
import org.bouncycastle.util.encoders.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base 
{
	public static WebDriver driver;
	public static ExtentSparkReporter spark;
	public static ExtentReports report;
	public static ExtentTest test;

	@BeforeSuite
	public void reportIntialize()
	{
		spark=new ExtentSparkReporter("./Reports/flipkart.html");
		spark.config().setDocumentTitle("Flipkart");
		spark.config().setReportName("Flipkart Functional Testing");
		
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("ApplicationName","Flipkart.com");
		report.setSystemInfo("Tester","venkat");
		report.setSystemInfo("Environment","TestEnv");
	}
	@BeforeTest
	@Parameters({"browser"})
	public void setUp(String br)
	{

		if(br.matches("firefox"))
		{
			driver=new FirefoxDriver();
		}
		if(br.matches("chrome"))
		{
			ChromeOptions opt=new ChromeOptions();
			opt.addArguments("--remote-allow-origins=*");

			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver(opt);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));		
	}
	public void openUrl(String url)
	{
		try
		{
			HttpURLConnection huc;
			int respCode=200;
			huc = (HttpURLConnection)(new URL(url).openConnection());            //try to connect to the URL
			huc.setRequestMethod("HEAD");
			huc.connect();
			respCode = huc.getResponseCode();
			System.out.println(respCode);
			if(respCode >= 400)
			{
				System.exit(0);
			}
		}
		catch(Exception e)
		{
			System.out.println("wrong url");
			driver.close();
			System.exit(0);
		}
		driver.get(url);
		//Assert.assertEquals(driver.getTitle(),"Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!");
	}
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
	
	@AfterSuite
	public void saveReport()
	{
		report.flush(); //save the report
	}
	
	public void takescreenshot(String filename)
	{
		try {
			File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File d=new File("./Reports/images/"+filename);
			
			FileUtils.copyFile(f,d);
			test.addScreenCaptureFromPath(d.getAbsolutePath());}catch(Exception e) {}
		
	}
}
