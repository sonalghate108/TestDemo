package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import baseClass.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ProductPage;
import utilities.DataProviders;



public class TC006_SortProductByPriceLowToHigh extends BaseClass{
	@Test(priority=1,groups="Master")
	public void filterproductlowtohigh() {
		
		logger.info("Test case to sort product by price Low To High has started");
		try {
			ProductPage pp=new ProductPage(driver);
			pp.clickBooksMenu();

			pp.setSelectSortBy();
			pp.isBookPriceSorted();
			
			if(pp.isBookPriceSorted()) {
				logger.info("All the products are sorted by price Low to High");
				Assert.assertTrue(true);
			}
			else {
				logger.info("Products are not sorted by price Low to High");
				Assert.assertTrue(false);
			}
			
			logger.info("Test case to sort product by price Low To High has finished");
		}
		catch(Exception e) {
			System.out.println(e.toString());
			Assert.fail("Exception occured");
		}
	}

}