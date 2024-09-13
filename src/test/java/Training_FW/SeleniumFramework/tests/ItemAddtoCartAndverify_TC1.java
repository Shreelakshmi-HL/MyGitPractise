package Training_FW.SeleniumFramework.tests;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Training_FW.SeleniumFramework.pageObjects.CheckOutProductPage;
import Training_FW.SeleniumFramework.pageObjects.OrdersPage;
import Training_FW.SeleniumFramework.pageObjects.ProductCataloguePage;
import Training_FW.SeleniumFramework.testComponents.BasicTest;


public class ItemAddtoCartAndverify_TC1  extends BasicTest
{
   
   //String itemName="IPHONE 13 PRO";
   
   //@Test(dataProvider="getData")
   //public void selectItem(String id,String pwd, String itemName) throws InterruptedException, IOException
 
	@Test(dataProvider="getData")
	public void selectItem(HashMap<String,String> input) throws InterruptedException, IOException
   {
	   	//String id=prop.getProperty("userName");
		//String pwd=prop.getProperty("password");
		
		//login
		 ProductCataloguePage icp=lp.loginToApplication(input.get("id"), input.get("pwd"));
		 
	   //wait all the elements displayed on the page- list
	   icp.waitForVisiblityOfAllElements(icp.getProductList());
	   //get the list of items
	   List<WebElement> elements= icp.getProductList();
	 
	   System.out.println("The elements on the page="+elements.size());
	 
	   //select item and click to add to cart btn
	   icp.selectItem(input.get("itemName"));
	   
	  System.out.println("Item-"+input.get("itemName")+"is added to cart");
	  
	  //verify success msg
	  //icp.waitForTheVisiblityOfElementLocatedBy(icp.successMsg_toaster);
	  
	 // System.out.println("Success msg verified");
		
	  //verify success msg is gone- till then page is not loaded.
	 // icp.waitForVisiblityOfElement(icp.getMsg_toaster());
	  
	  //VERIFY THE CART
	   Thread.sleep(10000);
	  //click gotocart btn and verify added item is present in the cart page
	   icp.clickOnGoToCartButton();
	   
	   //wait for items in cart to get loaded and find item in the cart
	   icp.waitForVisiblityOfAllElements(icp.getItems_inCart_list());
	   
	  //verify in the names- iphone is present 
	   Boolean match= icp.verifyItemInCart(input.get("itemName"));
	 
	   Assert.assertTrue(match);
		
	  System.out.println("Item found in cart");
	 
	 //click on checkout button
	  CheckOutProductPage cop= icp.clickOnCheckOutBtn();
	  
	  System.out.println("Checkout button clicked");
	  
	  //CHECKOUT VALIDATION
	  
	   cop.select_Country("Ind");
	  
	   //click on Place order button
	   cop.click_on_placeOrder_btn();
	   
	   System.out.println("Order placed successfully");
	   
	   //accessing thank you msg and validate
	 
	   String confirmMsg= cop.confirm_msg_verification();
	  System.out.println(confirmMsg);
	   Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
   }
   
  // @Test(dependsOnMethods= {"selectItem"})
   @Test(dataProvider="getData")
  // public void verifyOrderHistoryTest(String id, String pwd, String itemName) throws InterruptedException
   public void verifyOrderHistoryTest(HashMap<String,String> input) throws InterruptedException
   {	
	  // String id=prop.getProperty("userName");
		//String pwd=prop.getProperty("password");
		
	   
		//login
		 ProductCataloguePage icp=lp.loginToApplication(input.get("id"), input.get("pwd"));
		 OrdersPage orp= icp.clickOnOrdersButton();
		 
		 Thread.sleep(5000);
		 Assert.assertTrue(orp.verifyProductPresentInOrders(input.get("itemName")));
		 
		 System.out.println("Item"+input.get("itemName")+" is present in the order history page");
   }
   
   /*@DataProvider
   public Object[][] getData()
   {
	  return new Object[][] {{"shreelakshmi@gmail.com","Practise123","IPHONE 13 PRO"},{"shreelakshmi@gmail.com","Practise123","ADIDAS ORIGINAL"}};
   }*/
   
  // using hashmap key value pair
   @DataProvider
   public Object[][] getData() throws IOException
   {
	  /* HashMap<String,String> map1=new HashMap<String,String>();
	   map1.put("id", "shreelakshmi@gmail.com");
	   map1.put("pwd", "Practise123");
	   map1.put("itemName", "IPHONE 13 PRO");
	   
	   HashMap<String,String> map2=new HashMap<String,String>();
	   map2.put("id", "shreelakshmi@gmail.com");
	   map2.put("pwd", "Practise123");
	   map2.put("itemName", "ADIDAS ORIGINAL");
	   
	    return new Object[][] {{map1},{map2}};
	   */
	   
	   //
	   
	   //getting data from json file-using jsonutility file
	   
	   List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//Training_FW//SeleniumFramework//data//PurchaseOrder.json");
		return new Object[][]  {{data.get(0)}, {data.get(1) } };

	   
	  
   }
   
   
}
