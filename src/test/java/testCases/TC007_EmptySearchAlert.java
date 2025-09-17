package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import baseClass.BaseClass;
import pageObjects.HomePage;

public class TC007_EmptySearchAlert extends BaseClass{
	
	//Test case will look for items according to data provider. 
		@Test(priority=1,groups={"Master","Smoke"})
		public void verifyemptysearchalert(){
			logger.info("TestCase to verify alert started");
			try {
				HomePage hp = new HomePage(driver);
				hp.clickSearchButton();
				
				if(hp.isSearchAlertAccepted()) {
					logger.info("Alert is accepted");
					Assert.assertTrue(true);
				}
				else
				{
					logger.info("Alert is Not present");
					Assert.assertTrue(false);
				}
			
			logger.info("TestCase to verify alert finished");
			}
		catch(Exception e) {
			System.out.println(e.toString());
			Assert.fail("Exception occured");
		}
	}
}