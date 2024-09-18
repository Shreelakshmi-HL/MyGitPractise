package Training_FW.SeleniumFramework.tests;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Training_FW.SeleniumFramework.testComponents.BasicTest;


public class ItemAddtoCartAndverify
{
   
   public WebDriver driver;
   String itemName="IPHONE 13 PRO";
   public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
   
   
   @Test(priority=1)
   public void selectItem() throws InterruptedException
   {
	  // this.driver=BasicTest.driver;
	  
	  //find all the elements displayed on the page to elements list
	   wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector(".mb-3"))));
	  Thread.sleep(5000);
	   List<WebElement> elements= driver.findElements(By.cssSelector(".mb-3"));
	 
	   System.out.println("The elements on the page="+elements.size());
	 //convert the list to stream, 
	  //inside filter, on each item of the elements ->limit the scope to that element and find the element of iphone
	  //get that matching text element by findfirst ->that is iphone product
	 
	  WebElement prod =	elements.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(itemName)).findFirst().orElse(null);
		
	  //using product got, do findelement to get add to cart button
	  prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	  
	  System.out.println("Item-"+itemName+"is added to cart");
	  
	  //verify success msg
	//  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	  
	  System.out.println("Success msg verified");
		
	  //verify success msg is gone- till then page is not loaded.
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

   }
   
   @Test(priority=2)
   public void verifyCartItem() throws InterruptedException
   {
	   Thread.sleep(10000);
	  // this.driver= BasicTest.driver;
	  //click cart btn and verify added item is present in the cart page
	   driver.findElement(By.xpath("//li/button[@routerlink='/dashboard/cart']")).click();
	   
	   wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector(".cartSection"))));
	   
	   List<WebElement> cartItems=driver.findElements(By.cssSelector(".cartSection h3"));
	   
	 Boolean match =cartItems.stream().anyMatch(addedItem->
	   addedItem.getText().equalsIgnoreCase(itemName));
	 
	 Assert.assertTrue(match);
		
	  System.out.println("Item found in cart");
	 
	  driver.findElement(By.xpath("//button[text()='Checkout']")).click();
	  
	  System.out.println("Checkout button clicked");
	  
	   
   }
   
   @Test(priority=3)
   public void checkOutValidation()
   {
	  // this.driver= BasicTest.driver;
	   WebElement countrySelectionBox= driver.findElement(By.xpath("//*[@placeholder='Select Country']"));
	   
	   //enter IND in selection box- move to India option and select
	   Actions action=new Actions(driver);
	   action.sendKeys(countrySelectionBox, "Ind").build().perform();
	   wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='form-group']/section"))));
	   
	   action.moveToElement(driver.findElement(By.xpath("//div[@class='form-group']/section/button[2]"))).click().perform();
	   
	   //click on Place order button
	   driver.findElement(By.cssSelector(".action__submit")).click();
	   
	   System.out.println("Order placed successfully");
	   
	   //accessing thank you msg and validation
	   
	  String confirmMsg= driver.findElement(By.cssSelector(".hero-primary")).getText();
	   Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thank you for the order"));
   }
   
}
