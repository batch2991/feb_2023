package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import base.Base;

public class HomePage extends Base
{
	By searchtextbox=By.name("q");
	By closebtn=By.cssSelector("._2doB4z");

	public void closelogin()
	{
		try{driver.findElement(closebtn).click();}catch(Exception e) {}
	}
	public void search(String prodname)
	{
		driver.findElement(searchtextbox).sendKeys(prodname);
		driver.findElement(searchtextbox).sendKeys(Keys.ENTER);
		try {Thread.sleep(2000);}catch(Exception e) {}
	}
}
