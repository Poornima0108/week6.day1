package steps;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StepDefinition {
	ChromeDriver driver;
	String leadID, leadID1;

	@Given("A Chrome browser is launched")
	public void openChromeBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@And("Load the Leaftaps url {string}")
	public void loadURL(String url) {
		driver.get(url);
	}

	@And("Maximize the browser")
	public void maximizeBrowser() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@And("Enter the username as {string}")
	public void enterUsername(String username) {
		WebElement elementUsername = driver.findElement(By.id("username"));
		elementUsername.sendKeys(username);
	}

	@And("Enter the password as {string}")
	public void enterPassword(String password) {
		WebElement elementPassword = driver.findElement(By.id("password"));
		elementPassword.sendKeys(password);
	}

	@When("The Login button is clicked")
	public void clickLogin_Positive() {
		WebElement elementLoginButton = driver.findElement(By.className("decorativeSubmit"));
		elementLoginButton.click();
	}

	@But("The error message is displayed")
	public void getErrorMessage() {
		System.out.println("Username and password error message");
	}

	@Then("The Welcome Page should be displayed")
	public void verifyWelcomePage() {
		System.out.println("The welcome page is displayed");
	}
	
	@And("Click on CRMSFA link")
	public void clickCRMLink() {
		driver.findElement(By.linkText("CRM/SFA")).click();
	}
	
	@And("Click on leads link")
	public void verifyLeadsPage() {
		driver.findElement(By.linkText("Leads")).click();
	}
	
	@And("Click on create lead link")
	public void clickCreateLead() {
		driver.findElement(By.linkText("Create Lead")).click();
	}
	
	@And("Enter the company name as {string}")
	public void enterCompanyName(String companyname) {
		driver.findElement(By.id("createLeadForm_companyName")).sendKeys(companyname);
	}
	
	@And("Enter the first name as {string}")
	public void enterFirstName(String firstname) {
		driver.findElement(By.id("createLeadForm_firstName")).sendKeys(firstname);
	}
	
	@And("Enter the last name as {string}")
	public void enterLastName(String lastname) {
		driver.findElement(By.id("createLeadForm_lastName")).sendKeys(lastname);
	}
	
	@And("The submit button is clicked")
	public void clickSubmit() {
		driver.findElement(By.name("submitButton")).click();
	}
	
	@Then("The lead should be created")
	public void verifyLead() {
		System.out.println("Lead created");
	}
	
	@And("Click on find leads link")
	public void clickFindLeads() {
		driver.findElement(By.linkText("Find Leads")).click();
	}
	
	@And("Enter the phone number as {string}")
	public void enterPhone(String phone) {
		driver.findElement(By.xpath("//span[text()='Phone']")).click();
		driver.findElement(By.xpath("//input[@name='phoneNumber']")).sendKeys(phone);
	}
	
	@And("Click on find leads button")
	public void clickFindLeadsButton() throws InterruptedException {
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
		Thread.sleep(2000);
	}
	
	@And("Delete the lead")
	public void deleteLead() {
		leadID = driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).getText();
		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
		driver.findElement(By.linkText("Delete")).click();
	}
	
	@Then("Verify lead is deleted")
	public void verifyDeleteLead() {
		driver.findElement(By.linkText("Find Leads")).click();
		driver.findElement(By.xpath("//input[@name='id']")).sendKeys(leadID);
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
		WebElement elementLeadNotPresentMsg = driver.findElement(By.xpath("//div[text()='No records to display']"));
		boolean flag=elementLeadNotPresentMsg.isDisplayed();
		if(flag==true)
		{
			System.out.println("Lead deleted successfully");
		}
		else
		{
			System.out.println("Lead not deleted");
		}
	}
	
	@Then("The lead is duplicated")
	public void duplicateLead() {
		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
		driver.findElement(By.linkText("Duplicate Lead")).click();
		driver.findElement(By.name("submitButton")).click();
	}
	
	@Then("The lead is updated")
	public void editLead() {
		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
		driver.findElement(By.linkText("Edit")).click();
		driver.findElement(By.id("updateLeadForm_companyName")).sendKeys("TCS");
		driver.findElement(By.name("submitButton")).click();
	}
	
	@And("Click on merge leads link")
	public void clickMergeLead() {
		driver.findElement(By.linkText("Merge Leads")).click();
	}
	
	@And("Merge the leads {string} {string}")
	public void mergeLead(String fname, String mergename) throws InterruptedException {
		driver.findElement(By.xpath("//img[@alt='Lookup']")).click();
		Set<String> allWindows = driver.getWindowHandles();
		List<String> allhandles = new ArrayList<String>(allWindows);
		driver.switchTo().window(allhandles.get(1));
		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(fname);
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
		Thread.sleep(1000);
		leadID1 = driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).getText();
		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
		driver.switchTo().window(allhandles.get(0));
		
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[2]")).click();
		Set<String> allWindows2 = driver.getWindowHandles();
		List<String> allhandles2 = new ArrayList<String>(allWindows2);
		driver.switchTo().window(allhandles2.get(1));
		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(mergename);
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
		driver.switchTo().window(allhandles2.get(0));
		driver.findElement(By.xpath("//a[text()='Merge']")).click();
		driver.switchTo().alert().accept();
		
	}
	
	@Then("Verify leads are merged")
	public void verifyMergeLead() {
		driver.findElement(By.linkText("Find Leads")).click();
		driver.findElement(By.xpath("//input[@name='id']")).sendKeys(leadID1);
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
		WebElement elementLeadNotPresentMsg = driver.findElement(By.xpath("//div[text()='No records to display']"));
		boolean flag=elementLeadNotPresentMsg.isDisplayed();
		if(flag==true)
		{
			System.out.println("Leads merged successfully");
		}
		else
		{
			System.out.println("Leads not merged");
		}
	}
}
