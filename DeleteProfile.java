import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteProfile {
	
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

    public void chooseProfile() {   
        driver.get(chooseProfileUrl);                
        WebElement profile = driver.findElement(By.xpath("//*[@id="app"]/div/div/div[1]/div[2]/div"));                
        profile.click(); 
    }

    public void deleteProfile() {
        login();
        chooseProfileUrl();
        WebElement delete = driver.findElement(By.cssSelector("//*[@id="app"]/div/div/div/div/div[2]/button[1]"));
        delete.click();

    }

    @Test
    public void isProfileDisplayed() {
        driver.get(chooseProfileUrl);                
        WebElement profile = driver.findElement(By.xpath("//*[@id="app"]/div/div/div[1]/div[2]/div"));
        Assert.assertEquals(true, profile.isDisplayed());
    }


    @Test
    public void isProfileDeleted() {
        // create profile is called before test
        deleteProfile();
        // driver.get(profileCreate);                
        WebElement profile = driver.findElement(By.xpath("//*[@id="app"]/div/div/div[1]/div[2]/div"));
        Assert.assertEquals(false, profile.isDisplayed());
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }
}