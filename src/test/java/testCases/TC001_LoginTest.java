package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import baseClass.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import utilities.DataProviders;



public class TC001_LoginTest extends BaseClass{
	@Test(priority=1,dataProvider="testDataLogin", dataProviderClass=DataProviders.class,groups="Master")
	public void login(String username, String password) {
		try {
			logger.info("Test case to test login started");

			LoginPage lp=new LoginPage(driver);
			lp.clickLoginLink();
			lp.login(username, password);
			
			
			if(lp.isLoginSuccessful()) {
				Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(false);
			}
			logger.info("Test case to test login finished");
			lp.logout();
		}
		catch(Exception e) {
			System.out.println(e.toString());
			Assert.fail("Exception occured");
		}
	}

}