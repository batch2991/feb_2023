package pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;

import base.Base;

public class ProductsListPage extends Base
{

	By heading=By.cssSelector("._10Ermr span");
	By pricesorting=By.xpath("//div[text()='Price -- Low to High']");
	By prices=By.cssSelector("._30jeq3");
	By productnames=By.cssSelector("._4rR01T");
	
	
	public String getTitle()
	{
		String title=driver.getTitle().toLowerCase();
		return title;
	}
	public String getHeading()
	{
		String str=driver.findElement(heading).getText().toLowerCase();
		return str;
	}
	public void clickSortingOption()
	{
		driver.findElement(pricesorting).click();
		try {Thread.sleep(2000);}catch(Exception e) {}
	}
	public List<Integer> getPrices()
	{
	  List<Integer> prPrices=driver.findElements(prices).stream().map(w->Integer.parseInt(w.getText().substring(1).replace(",",""))).collect(Collectors.toList());
	  return prPrices;
	}
	public String[] clickOneProduct(int index)
	{
		String first[]=new String[2];
		first[0]=driver.findElements(productnames).get(index).getText();
		first[1]=driver.findElements(prices).get(index).getText();
		driver.findElements(productnames).get(index).click();
		try {Thread.sleep(1500);}catch(Exception e) {}
		return first;
	}
	
}
