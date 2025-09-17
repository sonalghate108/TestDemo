package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import baseClass.BaseClass;
import pageObjects.HomePage;



public class TC002_VerifyHomePageTabs extends BaseClass{
	@Test(priority=1,groups="Master")
	public void verifytopmenu(){
		logger.info("TestCase to verify HomePage tabs has started");
		try {
			HomePage hp = new HomePage(driver);
			//Menu item can be verified with the similar text in the URL or with the Header 
			//hp.verifyTopMenuwithURL();
			hp.verifyTopMenuByHeader();
			logger.info("TestCase to verify HomePage Tab has finished");
		}
		catch(Exception e) {
			System.out.println(e.toString());
			Assert.fail("Exception occured");
		}

	}
}