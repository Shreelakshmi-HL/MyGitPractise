package Training_FW.SeleniumFramework.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Training_FW.SeleniumFramework.abstractcomponents.AbstractComponents;


public class ProductCataloguePage extends AbstractComponents
{
   public WebDriver driver;
   
   public ProductCataloguePage(WebDriver driver)
   {
	   super(driver);
	   this.driver=driver;
	   PageFactory.initElements(driver, this);
   }
   
   @FindBy(css=".mb-3")
    List<WebElement> productList;

   @FindBy(css=".ng-animating")
    WebElement msg_toaster;
   
   @FindBy(xpath="//li/button[@routerlink='/dashboard/cart']")
    WebElement go_to_cart_btn;
   
   @FindBy(css=".cartSection")
    List<WebElement> items_inCart_list;
   
   @FindBy(css=".cartSection h3")
   List<WebElement> item_names_in_cart_list;
   
   @FindBy(xpath="//button[text()='Checkout']")
    WebElement checkOut_btn;
   
   @FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
   WebElement orders_btn;
   
  
   public By itemNameBy= By.cssSelector("b");
   public By addTocartBtn_Of_FoundProduct= By.cssSelector(".card-body button:last-of-type");
   public By successMsg_toaster= By.cssSelector("#toast-container");
  
   public  List<WebElement> getProductList()
   {
	   return productList;
   }
   
   public void selectItem(String itemName)
   {
	   //find the item-iphone in the product list and extract its text->compare with itemName passed from TC
	   //->if found get the product
	  
	   WebElement prodFound =	getProductList().stream().filter(product->
	   product.findElement(itemNameBy).getText().equalsIgnoreCase(itemName)).findFirst().orElse(null);
	   
	   //using product got, get add to cart button and click on it
	   prodFound.findElement(addTocartBtn_Of_FoundProduct).click();
   }
	public WebElement getMsg_toaster() {
		return msg_toaster;
	}
	public void clickOnGoToCartButton()
	{
		go_to_cart_btn.click();
	}
	public List<WebElement> getItems_inCart_list()
	{
		//get the list of items in cart
		return this.items_inCart_list;
	}
	 public boolean verifyItemInCart(String itemName)
	 {
		 //get item names in cart
		 List<WebElement> cartItems= item_names_in_cart_list;
		   
		 //verify if name-iphone is present in list
		 Boolean match =cartItems.stream().anyMatch(addedItem->
		   addedItem.getText().equalsIgnoreCase(itemName));
		 return match;
	 }
	 public CheckOutProductPage clickOnCheckOutBtn()
	 {
		 this.checkOut_btn.click();
		 
		 //CALL CHECKOUTPAGE
		 CheckOutProductPage cop=new CheckOutProductPage(driver);
		 return cop;
	 }
	 public OrdersPage clickOnOrdersButton()
	 {
	   this.orders_btn.click();
	   OrdersPage orp=new OrdersPage(driver);
	   return orp;
	 }
}
