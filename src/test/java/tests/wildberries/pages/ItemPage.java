package tests.wildberries.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ItemPage extends BasePage {

    private final By itemHeaderName = By.xpath("//div[@class='product-page__header']//h1");
    private final By itemPrice = By.xpath("//span[@class='price-block__price']");

    public ItemPage(WebDriver driver) {
        super(driver);
    }

    public String getItemName() {
        return driver.findElement(itemHeaderName).getText();
    }

    public Integer getItemPrice() {

        String priceText = getTextJs(itemPrice);
        priceText = priceText.replaceAll("[^0-9.]", "");
        return Integer.parseInt(priceText);
    }



}
