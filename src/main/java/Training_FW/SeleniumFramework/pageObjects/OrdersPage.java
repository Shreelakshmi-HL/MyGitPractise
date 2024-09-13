package Training_FW.SeleniumFramework.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Training_FW.SeleniumFramework.abstractcomponents.AbstractComponents;

public class OrdersPage extends AbstractComponents
{
   public WebDriver driver;
   
   public OrdersPage(WebDriver driver)
   {
	   super(driver);
	   this.driver=driver;
	   PageFactory.initElements(driver, this);
   }
  
   @FindBy(xpath="//tr/td[2]")
   List<WebElement> orders_names_list;
   
 
 public boolean verifyProductPresentInOrders(String itemName)
 {
	   //verify if item name is present in orders
	   Boolean match= orders_names_list.stream().anyMatch(orderItemName->orderItemName.getText().equalsIgnoreCase(itemName));
	   return match;
 }
}
