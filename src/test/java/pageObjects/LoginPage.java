package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageObjects.BasePage;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy (css="a[href='/login']")
	WebElement linkLogin;

	public WebElement btnLogin() {
		return linkLogin;
	}

	public void clickLoginLink() {
		linkLogin.click();
	}

	@FindBy (id="Email")
	WebElement txtUserName;

	public void setUserName(String username) {
		txtUserName.sendKeys(username);
	}

	@FindBy (id="Password")
	WebElement txtPassword;

	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}

	@FindBy(xpath="//input[@value='Log in']")
	WebElement btnLogin;

	public void clickLoginButton() {
		btnLogin.click();
	}

	@FindBy (xpath="//div[@class='validation-summary-errors']")
	WebElement errMessage;

	public String errorMessageLoginFailed() {

		return errMessage.getText();
	}

	@FindBy (css="a[href='/logout']")
	WebElement linkLogout;

	public void clickLogoutLink() {
		linkLogout.click();
	}

	@FindBy (xpath="//div[@class='message-error']/div/ul")
	WebElement errorMessage;

	public String getErrorMessage() {
		return errorMessage.getText();
	}

	public void login(String username,String password) {
		LoginPage lp = new LoginPage(driver);
		lp.clickLoginLink();
		System.out.println("Login Clicked");

		lp.setUserName(username);
		lp.setPassword(password);
		lp.clickLoginButton();
	}

	public void logout() {
		if(isLogoutAvailable()==true) {
			clickLogoutLink();
		}
	}
	public boolean isLoginAvailable() {
		if(btnLogin.isDisplayed()) {
			return true;
		}else return false;
	}

	public boolean isLogoutAvailable() {
		if(linkLogout.isDisplayed()) {
			return true;
		}else return false;
	}

	public boolean isLoginSuccessful() {
		if(isLogoutAvailable()==true) {
			return true;
		}
		if(getErrorMessage().equalsIgnoreCase("The credentials provided are incorrect")) {

			clickLoginLink();
			return false;
		}
		else return false;

	}
}
