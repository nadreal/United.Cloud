import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class CreateProfile {
	
    private WebDriver driver;
    private String loginUrl = "https://qa-interview.united.cloud/login";
    private String chooseProfileUrl = "https://qa-interview.united.cloud/choose-profile";
    private String createProfileUrl = "https://qa-interview.united.cloud/create-profile";
    private String profileCreate = "https://qa-interview.united.cloud";

    private String username = "stevan.grubac";
    private String password = "Lozinka123";

    @BeforeTest
    public void setup() {
        driver = getDriver();                
    }

    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "/home/dragonborn/SeleniumAutomaticTesting/United.Cloud/src/main/resources/drivers/chromedriver.exe");
        return new ChromeDriver();
    }

	public void login() {	
		driver.get(loginUrl);
        driver.manage().window().maximize();
		WebElement username = driver.findElement(By.id("user"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement login = driver.findElement(By.xpath("//*[@id="app"]/div/div/div/div[2]/form/button"));
		username.sendKeys(username);
		password.sendKeys(password);
		login.click(); 
	}

	public void createNewProfile() {
		driver.get(chooseProfileUrl);
		WebElement create = driver.findElement(By.cssSelector("#app > div > div > div.create > button"));		
		create.click(); 		
	}

	public void fillForm(String name, String year){		
		WebElement username = driver.findElement(By.id("name"));
		username.sendKeys(name);
		
		WebElement age = driver.findElement(By.cssSelector("#app > div > div > div > form > div:nth-child(2) > div > div:nth-child(5) > label"));
		age.click();

		WebElement year = driver.findElement(By.id("year"));
		year.sendKeys(year);
		
		WebElement avatar = driver.findElement(By.cssSelector("#app > div > div > div > form > div:nth-child(3) > div > div:nth-child(15) > label > div > img"));
		avatar.click();

		WebElement create = driver.findElement(By.cssSelector("#app > div > div > div > form > button"))
		create.click();		
	}

	@Test
	public void isUserLogged() throws InterruptedException {
		login();

		String actualUrl = chooseProfileUrl;
		String expectedUrl = driver.getCurrentUrl();
		Assert.assertEquals(expectedUrl,actualUrl);
	}
		
	@Test
	public void isCreateNewProfileClicked() throws InterruptedException {	
		createNewProfile();
		String actualUrl = createProfileUrl;
		String expectedUrl = driver.getCurrentUrl();
		Assert.assertEquals(expectedUrl,actualUrl);
	}	

	@Test	
	public void isProfileCreated() throws InterruptedException {
				
		login();
		createNewProfile()
		fillForm("SG","2008");

		WebElement delete = driver.findElement(By.cssSelector("//*[@id="app"]/div/div/div/div/div[2]/button[1]"));
		if (delete.isDisplayed()) {
			System.out.println("Profile is created");
		}		
		else 
			System.out.println("Profile is not created");

		String actualUrl = profileCreated;
		String expectedUrl = driver.getCurrentUrl();
		//Number of assertion can depend on UI acceptance criteria not just functionality 
		Assert.assertEquals(expectedUrl,actualUrl);
		Assert.assertEquals(true, delete.isDisplayed());

	}

	@AfterTest
    public void teardown() {
        driver.quit();
    }
}

