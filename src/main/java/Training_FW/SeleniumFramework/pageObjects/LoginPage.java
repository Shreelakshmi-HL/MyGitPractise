package Training_FW.SeleniumFramework.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Training_FW.SeleniumFramework.abstractcomponents.AbstractComponents;

public class LoginPage extends AbstractComponents
{
   public WebDriver driver;
   
   public LoginPage(WebDriver driver)
   {
	   super(driver);
	   this.driver=driver;
	   PageFactory.initElements(driver, this);
   }
   
   @FindBy(id="userEmail")
   WebElement usermailid;
   
   @FindBy(id="userPassword")
   WebElement password;
  
   @FindBy(id="login")
   WebElement login_btn;
   
   @FindBy(css=".toast-error")
   WebElement error_msg;
   
   public ProductCataloguePage loginToApplication(String id,String pwd)
   {
	   usermailid.sendKeys(id);
	   password.sendKeys(pwd);
	   login_btn.click();
	   
	  System.out.println("Logged in successfully ");
	   
	   ProductCataloguePage icp=new ProductCataloguePage(driver);
	   return icp;
	  
   }
   public String getErrorMsg()
   {
	   waitForVisiblityOfElement(error_msg);
	   
	   String msg=error_msg.getText();
	   return msg;
   }
   
}
