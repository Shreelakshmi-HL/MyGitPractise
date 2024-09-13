package Training_FW.SeleniumFramework.abstractcomponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents 
{

	public WebDriver driver;
	public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));;
	
	public AbstractComponents(WebDriver driver) {
		
		this.driver=driver;
	}
	
	//waitfor visiblityof elements
	public void waitForTheVisiblityOfElementLocatedBy(By findby)
	{
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findby));
	}
	
	public void waitForVisiblityOfAllElements(List<WebElement> list)
	{
		 wait.until(ExpectedConditions.visibilityOfAllElements(list));
	}
	
	public void waitForVisiblityOfElement(WebElement element)
	{
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	//wait for invisiblity of elements
	public void waitForInvisibilityOfElementLocatedBy(By findby)
	{
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findby));
	}
	public void waitForInvisiblityOfElement(WebElement element)
	{
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
    
}
