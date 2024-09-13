package Training_FW.SeleniumFramework.tests;

import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import Training_FW.SeleniumFramework.testComponents.BasicTest;

public class ErrorValidation_TC2 extends BasicTest{

	//@Test(retryAnalyzer=Training_FW.SeleniumFramework.testComponents.Retry.class)
	@Test
	public void errorValidationForLogin()
	{
		String id="shreelakshmi@gmail.com";
		String pwd="Practise1234";
				
		lp.loginToApplication(id, pwd);
		String expectedErrorMsg="Incorrect email or password.";
	
		Assert.assertEquals(expectedErrorMsg,lp.getErrorMsg() );
		
		System.out.println("Error validation for invalid username/password Done successfully");
	}
	
	
}
