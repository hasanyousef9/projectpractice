package automationpractice;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Testpractice {

	WebDriver driver = new ChromeDriver();

	String WebSite = "https://codenboxautomationlab.com/practice/";
	Random rand = new Random();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	Actions action = new Actions(driver);

	@BeforeTest
	public void mySetup() throws InterruptedException {

		driver.manage().window().maximize();
		driver.get(WebSite);

	}

	@Test(priority = 1, description = "radio button", enabled = false)
	public void Radio_Button_Example() {
		List<WebElement> AllRadioButton = driver.findElements(By.className("radioButton"));
//	AllRadioButton.get(2).click();
		int randomindex = rand.nextInt(AllRadioButton.size());
		AllRadioButton.get(randomindex).click();

		boolean expectdresult = true;
		boolean Actualresult = AllRadioButton.get(randomindex).isSelected();
		AssertJUnit.assertEquals(Actualresult, expectdresult);

	}

	@Test(priority = 2, description = "Dynamic Dropdown", enabled = false)
	public void Dynamic_Dropdown() throws InterruptedException {
		String[] countrycode = { "US", "CA", "OM", "BR", "AR", "FR", "DE", "IT", "ES", "AM" };
		int randomindex = rand.nextInt(countrycode.length);

		WebElement dropdownInput = driver.findElement(By.id("autocomplete"));
		dropdownInput.sendKeys(countrycode[randomindex]);
		Thread.sleep(1000);

		dropdownInput.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
		String DataInsideMyInput = (String) js.executeScript("return arguments[0].value", dropdownInput);
		String updateDataInMyInput = DataInsideMyInput.toUpperCase();

		boolean ActualValue = updateDataInMyInput.contains(countrycode[randomindex].toUpperCase());

		boolean ExpectedResult = true;

		Assert.assertEquals(ActualValue, ExpectedResult);

	}

	@Test(priority = 3, description = "Static Dropdown", enabled = false)
	public void Static_Dropdown() {
		WebElement SelectElement = driver.findElement(By.id("dropdown-class-example"));
		Select sel = new Select(SelectElement);
		sel.selectByIndex(2);
//sel.selectByValue("option2");
		// sel.selectByVisibleText("API");

	}

	@Test(priority = 4, description = "Checkbox", enabled = false)
	public void Checkbox() throws InterruptedException {

		List<WebElement> CheckBoxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		int randomIndex = rand.nextInt(CheckBoxes.size());
		System.out.println(CheckBoxes.size());
		Thread.sleep(1000);

		// CheckBoxes.getFirst().click();
		// CheckBoxes.getLast().click();
		// CheckBoxes.get(randomIndex).click();
		for (int i = 0; i < CheckBoxes.size(); i++) {
			CheckBoxes.get(i).click();
			boolean ActaualResult = CheckBoxes.get(i).isSelected();
			boolean expectedResult = true;

			Assert.assertEquals(ActaualResult, expectedResult);

		}
	}

	@Test(priority = 5, description = "Switch Window", enabled = false)
	public void Switch_Window() throws InterruptedException {
		WebElement OpenWindowButton = driver.findElement(By.id("openwindow"));
		OpenWindowButton.click();
		Thread.sleep(1000);
		List<String> windowsHandels = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(windowsHandels.size());
		driver.switchTo().window(windowsHandels.get(1));
		WebElement ContactButton = driver.findElement(By.id("menu-item-9680"));
		ContactButton.click();
		driver.close();
		driver.switchTo().window(windowsHandels.get(0));

	}

	@Test(priority = 6, description = "Switch Tab", enabled = false)
	public void Switch_Tab() throws InterruptedException {
		WebElement OpenTabButton = driver.findElement(By.id("opentab"));
		OpenTabButton.click();
		List<String> windowsHandels = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windowsHandels.get(1));
		Thread.sleep(2000);
		System.out.println(driver.getTitle());
		driver.close();
		driver.switchTo().window(windowsHandels.get(0));

	}

	@Test(priority = 7, description = "Alert and confirm", enabled = false)
	public void Switch_To_Alert() throws InterruptedException {
		WebElement nameBox = driver.findElement(By.id("name"));
		nameBox.sendKeys("hasan");
		// WebElement AlertBox = driver.findElement(By.id("alertbtn"));
		// AlertBox.click();

		Thread.sleep(1000);
		// driver.switchTo().alert().accept();
		// driver.switchTo().alert().dismiss();

		WebElement ConfirmBox = driver.findElement(By.id("confirmbtn"));
		ConfirmBox.click();
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		// driver.switchTo().alert().dismiss();
		// System.out.println(driver.switchTo().alert().getText());

	}

	@Test(priority = 8, description = "play with the data of the column", enabled = false)
	public void Web_Table() {
		WebElement TheTable = driver.findElement(By.id("product"));
		List<WebElement> theDataInsideTheTable = TheTable.findElements(By.tagName("tr"));
		for (int i = 1; i < theDataInsideTheTable.size(); i++) {
			int totalTdInTheRow = theDataInsideTheTable.get(i).findElements(By.tagName("td")).size();
			System.out.println(
					theDataInsideTheTable.get(i).findElements(By.tagName("td")).get(totalTdInTheRow - 1).getText());
		}

	}

	@Test(priority = 9, description = "this is to test hide and show buttons", enabled = false)
	public void Element_Displayed() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		SoftAssert myAssertion = new SoftAssert();
		// softassert if one test failed it will continue to the rest of the code مهم
		// hardassert once failed it will stop all the execution مهم

		js.executeScript("window.scrollTo(0,1500)");
		WebElement HideButton = driver.findElement(By.id("hide-textbox"));
		WebElement ShowButton = driver.findElement(By.id("show-textbox"));
		HideButton.click();
		WebElement theTextinput = driver.findElement(By.id("displayed-text"));

		// Assert.assertEquals(theTextinput.isDisplayed(), true);

		myAssertion.assertEquals(theTextinput.isDisplayed(), false);

		Thread.sleep(4000);
		ShowButton.click();
		Assert.assertEquals(theTextinput.isDisplayed(), true);

		myAssertion.assertAll();

	}

	@Test(priority = 10, description = "check The Both Buttons disable , enable", enabled = false)
	public void Enabled_Disabled() throws InterruptedException {

		WebElement DisabledButton = driver.findElement(By.id("disabled-button"));
		WebElement EnabledButton = driver.findElement(By.id("enabled-button"));
		DisabledButton.click();

		WebElement enabled_input = driver.findElement(By.id("enabled-example-input"));

		boolean ActualResult = enabled_input.isEnabled();

		boolean ExpectedResult = false;
		Assert.assertEquals(ActualResult, ExpectedResult);

		Thread.sleep(1000);

		EnabledButton.click();

		boolean ActualResult2 = enabled_input.isEnabled();
		enabled_input.sendKeys("123");
		boolean ExpectedResult2 = true;
		Assert.assertEquals(ActualResult2, ExpectedResult2);

	}

	@Test(priority = 11, description = "check the hover to certain element ", enabled = false)
	public void Mouse_Hover() throws InterruptedException {
		js.executeScript("window.scrollTo(0,1800)");
		Thread.sleep(2000);
		WebElement MouseHoverElement = driver.findElement(By.id("mousehover"));
		action.moveToElement(MouseHoverElement).perform();

		driver.findElement(By.linkText("Top")).click();
		// driver.findElement(By.partialLinkText("Relo")).click();

	}

	@Test(priority = 12, description = "open calendar in a new tab", enabled = false)
	public void Calendar_Example() throws InterruptedException {
		// this is not the eaistes way
		js.executeScript("window.scrollTo(0,1900)");

		// WebElement Calendar2 = driver.findElement(By.linkText("Booking Calendar"));
		WebElement Calendar2 = driver.findElement(By.partialLinkText("Booking"));

		Calendar2.click();
		Thread.sleep(1000);

		List<String> windowsHandels = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windowsHandels.get(1));

		System.out.println(driver.getTitle());

		int totalAvailbleDates = driver.findElements(By.className("date_available")).size();

		driver.findElements(By.className("date_available")).get(0).click();
		driver.findElements(By.className("date_available")).get(totalAvailbleDates - 1).click();
		driver.switchTo().window(windowsHandels.get(0));

	}

	@Test(priority = 13, description = "switch to frame inside the main page", enabled = false)
	public void iFrame_Example() {

		WebElement TheFrame = driver.findElement(By.id("courses-iframe"));
		driver.switchTo().frame(0);
		String theText = driver.findElement(By.xpath("//*[@id=\"ct_text_editor-be8c5ad\"]/div/div/p")).getText();

		System.out.println(theText);

	}

	@Test(priority = 14, description = "download the file inside the main page", enabled = false)
	public void Download_file_to_test() {

		WebElement TheFile = driver.findElement(By.xpath("//a[@class='wp-block-button__link wp-element-button']"));
		TheFile.click();

	}
	@Test(priority = 15, enabled = false)

	public void CheckTheTitle() {
		String expected = "Automation Practice - CodenBox AutomationLab";

		String ActualTitle = driver.getTitle();

		Assert.assertEquals(ActualTitle, expected);
	}
}