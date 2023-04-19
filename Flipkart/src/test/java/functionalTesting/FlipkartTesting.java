package functionalTesting;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import base.Base;
import pages.HomePage;
import pages.ProductsListPage;
import pages.SingleProductPage;

public class FlipkartTesting extends Base
{
	String valuetosearch="mobiles";
	
	@Test
	public void verifyTitleandHeading()
	{
		
		
		HomePage homepage=new HomePage();
		ProductsListPage productslistpage=new ProductsListPage();
				
		homepage.openUrl("http://www.flipkart.com");
		homepage.closelogin();
		homepage.search(valuetosearch);
		
		String title=productslistpage.getTitle();
		String heading=productslistpage.getHeading();
		
		test=report.createTest("Validate Title and Heading");
		if(title.contains(valuetosearch) && heading.matches(valuetosearch))
		{		
			test.log(Status.PASS, "Title and heading are matching as expected");
			takescreenshot("Heading.png");
		}			
		else
		{			
			test.log(Status.FAIL, "Title or heading NOT matching as expected");
			takescreenshot("Heading.png");
		}
	}
	@Test
	public void verifyPricesSorted()
	{
		HomePage homepage=new HomePage();
		ProductsListPage productslistpage=new ProductsListPage();
		
		homepage.openUrl("http://www.flipkart.com");
		homepage.closelogin();
		homepage.search(valuetosearch);
		productslistpage.clickSortingOption();
		List<Integer> actprices= productslistpage.getPrices();
		List<Integer> expprices= actprices.stream().sorted().collect(Collectors.toList());
		
		test=report.createTest("Validate prices are sorted");
		if(actprices.equals(expprices))
		{
			test.log(Status.PASS, "All prices are in sorting order as expected "+actprices);
			takescreenshot("Prices.png");
		}
		else
		{
			test.log(Status.FAIL, "All prices are NOT sorting order as expected "+actprices);
			takescreenshot("Prices.png");
		}
		
	}
	@Test
	public void verifyProductNameandPrice()
	{
		HomePage homepage=new HomePage();
		ProductsListPage productslistpage=new ProductsListPage();
		SingleProductPage singleproductpage=new SingleProductPage();
		
		homepage.openUrl("http://www.flipkart.com");
		homepage.closelogin();
		homepage.search(valuetosearch);
		String first[]=productslistpage.clickOneProduct(3);
		String second[]=singleproductpage.getProductNameandprice();
		
		test=report.createTest("Validate ProductName & Price");
		if(second[0].contains(first[0])&& first[1].matches(second[1]))
		{
			test.log(Status.PASS,"Product name and price are matched in both pages");
			takescreenshot("ProductName_Price.png");
		}
		else
		{
			test.log(Status.FAIL,"Product name or price NOT matched in both pages");
			takescreenshot("ProductName_Price.png");
		}
	}
}
