package pageObjects;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import pageObjects.BasePage;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy (className="topic-html-content-header")
	WebElement welcomeText;

	public String getWelcomeText() {
		return welcomeText.getText();
	}

	@FindBy (className="footer-menu-wrapper")
	WebElement footer;

	public int numberofLinks() {
		return (footer.findElements(By.tagName("a")).size());
	}

	public void verifyLinkBroken() {
		//open links in different window and print title
		//		for(int i=0;i<numberofLinks();i++) {
		//			footer.findElements(By.tagName("a")).get(i).sendKeys(Keys.chord(Keys.CONTROL,Keys.ENTER));
		//		}
		//		Set<String> winhandle= driver.getWindowHandles();
		//		Iterator<String> it = winhandle.iterator();
		//		while(it.hasNext()) {
		//			driver.switchTo().window(it.next());
		//			System.out.println(driver.getTitle());
		//		}

		try {
			List<WebElement> listlink = footer.findElements(By.tagName("a"));
			SoftAssert a = new SoftAssert();
			for (WebElement links : listlink) {
				URL url = new URI(links.getAttribute("href")).toURL();

				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("HEAD");
				conn.connect();
				int rescode = conn.getResponseCode();
				System.out.println(rescode);

				a.assertTrue(rescode<400, links.getText()+ " is broken with response code "+ rescode);
			}
			a.assertAll();
		}
		catch(URISyntaxException | IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@FindBy (xpath="//ul[@class='top-menu']/li") 
	List<WebElement> topmenu;


	public void verifyTopMenuwithURL() throws InterruptedException {
		SoftAssert a = new SoftAssert();
		for(WebElement menu : topmenu) {
			String menuname = menu.getText().toLowerCase();
			System.out.println(menuname);
			String firstword = getfirstwordoftabmenu(menuname);
			System.out.println(firstword);
			menu.click();
			if(firstword.contains(menuname)) {
				System.out.println("Found it");
			}
			boolean contains = driver.getCurrentUrl().contains(firstword);
			a.assertTrue(contains, "Tab Verified");
			driver.navigate().back();

		}
		a.assertAll();
	}

	public String getfirstwordoftabmenu(String topmenuname) {
		String firstword = "";
		for(int i=0;i<topmenuname.length();i++) {
			if(topmenuname.charAt(i)!=' ') {
				firstword=firstword + topmenuname.charAt(i);
			}else return firstword;
		}
		return firstword;
	}

	@FindBy (xpath = "//div[@class='page-title']/h1")
	WebElement headerTopMenu;

	public String getTopMenuHeader() {
		return headerTopMenu.getText();
	}

	public void verifyTopMenuByHeader() {
		SoftAssert a = new SoftAssert();
		for(WebElement menu : topmenu) {
			String menuname = menu.getText().toLowerCase();
			String firstword = getfirstwordoftabmenu(menuname);
			menu.click();
			System.out.println(getTopMenuHeader().toLowerCase());
			boolean iscontain = getTopMenuHeader().toLowerCase().contains(firstword);
			a.assertTrue(iscontain, "Tab Verified");
			driver.navigate().back();
		}
		a.assertAll();
	}

	@FindBy (xpath="//div[@class='header-links']/ul/li") 
	List<WebElement> headerLinks;

	public void verifyHeaderLinks() {
		SoftAssert a = new SoftAssert();
		for(WebElement headerlink : headerLinks ) {
			String headerlnkname = headerlink.getText().toLowerCase();
			System.out.println(headerlnkname);
			headerlink.click();
			String urltext = driver.getCurrentUrl();
			System.out.println(urltext);
			boolean isurlcontainheaderlink = urltext.contains(headerlnkname);
			a.assertTrue(isurlcontainheaderlink,"Header Link Varified");
			driver.navigate().back();
		}
		a.assertAll();
	}


	@FindBy(id="small-searchterms")
	WebElement getSearchBox;

	public void setSearchBox(String searchItem) {
		getSearchBox.clear();
		getSearchBox.sendKeys(searchItem);	
	}

	@FindBy (className="product-name")
	WebElement txtProductName;

	public String getProductName() {
		return txtProductName.getText();
	}

	@FindBy (xpath="//ul[@id='ui-id-1']/li")
	List<WebElement> listSuggestions;

	@FindBy (xpath="//ul[@id='ui-id-1']")
	WebElement listdropdown;

	public WebElement selectsorting() {
		return listdropdown;
	}

	public boolean isSortingSuggestionListDisplayed() throws InterruptedException {
		Thread.sleep(2000);
		//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		//		wait.until(ExpectedConditions.visibilityOfAllElements(listSuggestions));

		if(listdropdown.isDisplayed()) {
			return true;
		}
		else return false;
	}


	//Find Item from Auto Suggested drop down and click. 
	public boolean isProductFound(String productname) throws InterruptedException{

		Thread.sleep(2000);
		if(listdropdown.isDisplayed()) {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfAllElements(listSuggestions));

			for(WebElement product: listSuggestions) {
				System.out.println(listSuggestions.size());
				String s= product.getText();
				if(s.equalsIgnoreCase(productname)) {
					product.click();
					if(productname.equals(getProductName())) {
						System.out.println("found the product");
						return true;
					}
					else return false;
				}
			}
		}
		else return false;

		return false;
	}

	public boolean isProductFoundInTheList(String product) {
		if(product.equals(getProductName())) {
			return true;
		}
		else return false;
	}

	@FindBy (xpath="//input[@value='Search']")
	WebElement btnSearch;

	public void clickSearchButton() {
		btnSearch.click();
	}

	public boolean isSearchAlertAccepted() {
		if(isAlertPresent()) {
			driver.switchTo().alert().accept();
			return true;
		}
		else return false;
	}
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}
}

