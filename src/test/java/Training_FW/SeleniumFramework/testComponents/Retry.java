package Training_FW.SeleniumFramework.testComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	int count = 0;
	int maxTry = 2;
	
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(count<maxTry)
		{
			//retry test 
			count++;
			return true;
		}
		return false;
	}

	
	
}
