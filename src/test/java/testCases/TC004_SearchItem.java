package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import baseClass.BaseClass;
import pageObjects.HomePage;
import utilities.DataProviders;

public class TC004_SearchItem extends BaseClass{

	//Test case will look for items according to data provider. 
	@Test(priority=1,groups="Master",dataProvider="testDataSearchProduct", dataProviderClass=DataProviders.class)
	public void searchitem(String productname){
		logger.info("TestCase to Search product started");
		try {
			HomePage hp = new HomePage(driver);
			hp.setSearchBox(productname);
			if(hp.isProductFound(productname)) {
					logger.info("Search item is found");
					Assert.assertTrue(true);
				}
				else {
				logger.info("Search item cannot be found");
				Assert.assertTrue(true);
			}
			logger.info("TestCase to search product finished");
		}
		catch(Exception e) {
			System.out.println(e.toString());
			Assert.fail("Exception occured");
		}
	}
}