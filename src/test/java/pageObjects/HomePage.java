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
		// TODO Auto-generated constructor stub
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
		//System.out.println(topmenuname);
		for(int i=0;i<topmenuname.length();i++) {
			if(topmenuname.charAt(i)!=' ') {
				firstword=firstword + topmenuname.charAt(i);
			}else return firstword;
		}
		//	System.out.println(firstword);
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
	}
}

