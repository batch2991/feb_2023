package pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import base.Base;

public class SingleProductPage extends Base
{

	By prname=By.cssSelector(".B_NuCI");
	By price=By.cssSelector("._30jeq3");
	
	public String[] getProductNameandprice()
	{
	   String second[]=new String[2];	
	   driver.switchTo().window(new ArrayList<String>(driver.getWindowHandles()).get(1));
	   second[0]=driver.findElement(prname).getText();
	   second[1]=driver.findElement(price).getText();
	   driver.close();
	   driver.switchTo().window(new ArrayList<String>(driver.getWindowHandles()).get(0));
	   return second;	   
	}
}
