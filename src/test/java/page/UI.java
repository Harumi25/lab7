package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UI {

    private ChromeDriver webdriver;
    private static final String ERROR_MESSAGE = "An account using this email address has already been registered. Please enter a valid password or request a new one.";

    @BeforeClass
    public void open_browser() {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        webdriver = new ChromeDriver(options);
    }
    @Test
    public void openUrlTest() {
        webdriver.get("http://automationpractice.com/");
    }
    @Test
    public void PageTitleTest() {
        webdriver.get("http://automationpractice.com/");
        String pageTitle = webdriver.getTitle();
        System.out.println("Title site : " + pageTitle);
        Assert.assertEquals(pageTitle, "My Store");
    }
    @DataProvider(name = "create_new_user")
    public Object[][] create_new_user()
    {
        return new Object[][] {{"cqu2@gmail.com", "Ekaterina", "Tozik","hgg655w", "prospect miraa", "MSK", "Alaska", "76513", "United States", "+47874259810"}};
    }

    @Test(dataProvider = "create_new_user")
    public void ex_1(String email_create, String customer_firstname, String customer_lastname, String passwd, String address1, String city, String id_state, String postcode, String id_country, String phone_mobile) throws InterruptedException {

        webdriver.get("http://automationpractice.com/");

        WebDriverWait wait = new WebDriverWait(webdriver, 60 );

        webdriver.findElement(By.cssSelector(".login")).click();

        wait.until(new PageLoaded());

        webdriver.findElement(By.id("email_create")).sendKeys(email_create);

        webdriver.findElement(By.id("SubmitCreate")).click();

        Thread.sleep(4000);

        webdriver.findElement(By.id("customer_firstname")).sendKeys(customer_firstname);

        webdriver.findElement(By.id("customer_lastname")).sendKeys(customer_lastname);

        webdriver.findElement(By.id("passwd")).sendKeys(passwd);

        webdriver.findElement(By.id("address1")).sendKeys(address1);

        webdriver.findElement(By.id("city")).sendKeys(city);

        Select slState = new Select( webdriver.findElement(By.id("id_state")));
        slState.selectByVisibleText(id_state);
        slState.selectByIndex(32);

        webdriver.findElement(By.id("postcode")).sendKeys(postcode);

        Select slCountry = new Select( webdriver.findElement(By.id("id_country")));
        slCountry.selectByVisibleText(id_country);
        slCountry.selectByIndex(1);

        webdriver.findElement(By.id("phone_mobile")).sendKeys(phone_mobile);

        webdriver.findElement(By.id("submitAccount")).click();
    }

    @DataProvider(name = "create_new_user_negative")
    public Object[][] create_new_user_negative()
    {
        return new Object[][] {{"mau2@gmail.com", "Katya","hgg655w", "Alaska", "76513", "United States", "+47874259810"}};
    }

    @Test(dataProvider = "create_new_user_negative")
    public void ex_2 (String email_create,  String customer_firstname, String passwd,String id_state, String postcode, String id_country, String phone_mobile) throws InterruptedException {

        webdriver.get("http://automationpractice.com/");

        WebDriverWait wait = new WebDriverWait(webdriver, 60 );

        webdriver.findElement(By.cssSelector(".login")).click();

        wait.until(new PageLoaded());

        webdriver.findElement(By.id("email_create")).sendKeys(email_create);

        webdriver.findElement(By.id("SubmitCreate")).click();

        Thread.sleep(4000);

        webdriver.findElement(By.id("customer_firstname")).sendKeys(customer_firstname);

        webdriver.findElement(By.id("passwd")).sendKeys(passwd);

        Select slState = new Select( webdriver.findElement(By.id("id_state")));
        slState.selectByVisibleText(id_state);
        slState.selectByIndex(32);

        webdriver.findElement(By.id("postcode")).sendKeys(postcode);

        Select slCountry = new Select( webdriver.findElement(By.id("id_country")));
        slCountry.selectByVisibleText(id_country);
        slCountry.selectByIndex(1);

        webdriver.findElement(By.id("phone_mobile")).sendKeys(phone_mobile);
        webdriver.findElement(By.id("submitAccount")).click();
    }
    @AfterClass
    public void closeBrowser(){
        webdriver.quit();
    }
}