package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import baseClass.BaseClass;
import pageObjects.HomePage;



public class TC003_VerifyFooterLinks extends BaseClass{
	@Test(priority=1,groups="Master")
	public void verifyfooter(){
		logger.info("TestCase to verify broken links from footer started");
		try {
			HomePage hp = new HomePage(driver);
			hp.verifyLinkBroken();
			logger.info("TestCase to verify broken links from footer finished");
		}
		catch(Exception e) {
			System.out.println(e.toString());
			Assert.fail("Exception occured");
		}

	}
}