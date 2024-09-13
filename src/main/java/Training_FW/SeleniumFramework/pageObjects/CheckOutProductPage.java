package Training_FW.SeleniumFramework.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Training_FW.SeleniumFramework.abstractcomponents.AbstractComponents;

public class CheckOutProductPage extends AbstractComponents
{
   public WebDriver driver;
   
   public CheckOutProductPage(WebDriver driver)
   {
	   super(driver);
	   this.driver=driver;
	   PageFactory.initElements(driver, this);
   }
   
   @FindBy(xpath="//*[@placeholder='Select Country']")
   WebElement select_country_editBox;
   
   @FindBy(xpath="//div[@class='form-group']/section")
   WebElement country_selection_options;
   
   @FindBy(xpath="//div[@class='form-group']/section/button[2]")
   WebElement India_option;
   
   @FindBy(css=".action__submit")
   WebElement place_order_btn;
   
   @FindBy(css=".hero-primary")
   WebElement confirm_msg;
   
   public void select_Country(String key) {
	  
	   //enter IND in selection box- move to India option and select
	   Actions action=new Actions(driver);
	   action.sendKeys(select_country_editBox, key).build().perform();
	   
	   super.waitForVisiblityOfElement(country_selection_options);
	   
	   action.moveToElement(India_option).click().perform();
	   
   }
   
   public void click_on_placeOrder_btn()
   {
	   place_order_btn.click();
   }
   public String confirm_msg_verification()
   {
	   String confirmMsg= confirm_msg.getText();
	   return confirmMsg;
   }
}
