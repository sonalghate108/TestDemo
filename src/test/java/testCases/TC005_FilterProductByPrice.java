package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import baseClass.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ProductPage;
import utilities.DataProviders;



public class TC005_FilterProductByPrice extends BaseClass{
	@Test(priority=1,groups="Master")
	public void filterproduct() {
		
		logger.info("Test case to filter product by price under 25 has started");
		try {
			ProductPage pp=new ProductPage(driver);
			pp.clickBooksMenu();
			pp.clickUnder25();
			if(pp.ispricemorethan25()) {
				logger.info("All the products have prices more than 25");
				Assert.assertTrue(true);
			}
			else {
				logger.info("All the products DO Not have prices more than 25");
				Assert.assertTrue(false);
			}
			
			logger.info("Test case to filter product by price under 25 has finished");
		}
		catch(Exception e) {
			System.out.println(e.toString());
			Assert.fail("Exception occured");
		}
	}

}