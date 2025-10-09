package pageObjects;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends BasePage{

	public ProductPage(WebDriver driver) {
		super(driver);
	}

	@FindBy (xpath = "//ul[@class='top-menu']/li[1]")
	WebElement getBooksMenu;

	public void clickBooksMenu()  {
		getBooksMenu.click();
	}
	@FindBy (xpath="//ul[@class='price-range-selector']/li[1]/a")
	WebElement under25Filter;

	public void clickUnder25()  {
		under25Filter.click();
	}

	@FindBy (xpath ="//div[@class='prices']/span[2]")
	List<WebElement> originalpricelist;

	public List<WebElement> pricelist(){

		return originalpricelist;
	}

	public boolean ispricemorethan25() {
		for(int i=0;i<originalpricelist.size();i++) {
			Double k= Double.valueOf(originalpricelist.get(i).getText());
			if(k>25) {
				return false;
			}
			else 
			{
				return true;
			}
		}

		return true;
	}

	public ArrayList<Double> sortedpricelist() {
		ArrayList<Double> sortedprice = new ArrayList<Double>();

		for(int i=0;i<originalpricelist.size();i++ ) {
			Double k= Double.valueOf(originalpricelist.get(i).getText());
			sortedprice.add(k);
		}
		Collections.sort(sortedprice);

		for (Double i : sortedprice) {
			System.out.println(i);
		}

		return sortedprice;
	}

	public ArrayList<Double> sortedpricelistresult() {
		ArrayList<Double> sortedpriceresult = new ArrayList<Double>();
		for(int i=0;i<originalpricelist.size();i++ ) {
			Double k= Double.valueOf(originalpricelist.get(i).getText());
			sortedpriceresult.add(k);
		}
		return sortedpriceresult;
	}

	public boolean isBookPriceSorted() {
		if(sortedpricelist().equals(sortedpricelistresult())) {
			return true;
		}
		return false;
	}


	@FindBy (id = "products-orderby")
	WebElement selectSortBy;

	public Select getSelectSortBy() {
		return new Select(selectSortBy);
	}

	public void setSelectSortBy() throws InterruptedException {
		getSelectSortBy().selectByIndex(3);
	}
}