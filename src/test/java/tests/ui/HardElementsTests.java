package tests.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HardElementsTests {
    private WebDriver driver;
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }

    @Test
    public void authTest(){
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
        String h3 = driver.findElement(By.xpath("//h3")).getText();
        Assertions.assertEquals("Basic Auth", h3);
    }

    @Test
    public void alertOk(){
        String expectedText = "I am a JS Alert";
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        String actualText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        String expectedResult = "You successfully clicked an alert";
        String result = driver.findElement(By.id("result")).getText();

        Assertions.assertEquals(expectedText, actualText);
        Assertions.assertEquals(expectedResult, result);

    }

    @Test
    public void alertCancel(){
        String expectedText = "I am a JS Confirm";
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        String actualText = driver.switchTo().alert().getText();
        driver.switchTo().alert().dismiss();

        String expectedResult = "You clicked: Cancel";
        String actualResult = driver.findElement(By.id("result")).getText();

        Assertions.assertEquals(expectedText, actualText);
        Assertions.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void alertSendKey(){
        String expectedText = "I am a JS prompt";
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        String actualText = driver.switchTo().alert().getText();
        driver.switchTo().alert().sendKeys("ALOOOO");
        driver.switchTo().alert().accept();

        String expectedResult = "You entered: ALOOOO";
        String result = driver.findElement(By.id("result")).getText();

        Assertions.assertEquals(expectedText, actualText);
        Assertions.assertEquals(expectedResult, result);

    }

    @Test
    public void iframeTest(){
        driver.get("https://mail.ru/");
        driver.findElement(By.xpath("//button[contains(@class, 'resplash-btn')]")).click();

        WebElement ifame = driver.findElement(By.xpath(" //iframe[@class='ag-popup__frame__layout__iframe']"));
        driver.switchTo().frame(ifame);

        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Baydoo");
        int a = 1;
    }

    @Test
    public void sliderTest(){
        driver.get("http://85.192.34.140:8081/");

        WebElement elementsCard = driver.findElement(By.xpath("//div[@class='card-body']//h5[text()='Widgets']"));
        elementsCard.click();

        WebElement elementsTextBox = driver.findElement(By.xpath("//span[text()='Slider']"));
        elementsTextBox.click();

        WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));

        //Actions actions = new Actions(driver);
        //actions.dragAndDropBy(slider, 50, 0).build().perform();

        int expectedValue = 85;
        int currentValue = Integer.parseInt(slider.getAttribute("value"));
        int valueToMove = expectedValue - currentValue;

        for (int i = 0; i < valueToMove; i++) {
            slider.sendKeys(Keys.ARROW_RIGHT);
        }

        WebElement sliderValue = driver.findElement(By.id("sliderValue"));
        int sliderValueInteger = Integer.parseInt(sliderValue.getAttribute("value"));

        Assertions.assertEquals(expectedValue, sliderValueInteger);

    }

    @Test
    public void hoverTest(){
        driver.get("http://85.192.34.140:8081/");

        WebElement elementsCard = driver.findElement(By.xpath("//div[@class='card-body']//h5[text()='Widgets']"));
        elementsCard.click();

        WebElement elementsTextBox = driver.findElement(By.xpath("//span[text()='Menu']"));
        elementsTextBox.click();

        WebElement menuItemMiddle = driver.findElement(By.xpath("//a[text()='Main Item 2']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(menuItemMiddle).build().perform();

        WebElement subSubList = driver.findElement(By.xpath("//a[text()='SUB SUB LIST »']"));
        actions.moveToElement(subSubList).build().perform();

        List<WebElement> subSubItem = driver.findElements(By.xpath("//a[contains(text(), 'Sub Sub Item')]"));

        Assertions.assertTrue(subSubItem.size() == 2);
    }
}
