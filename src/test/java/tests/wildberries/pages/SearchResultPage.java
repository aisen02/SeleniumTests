package tests.wildberries.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultPage extends BasePage {

    private final By allFiltersBtn = By.xpath("//button[@class='dropdown-filter__btn dropdown-filter__btn--all']");
    private final By endPrice = By.xpath("//input[@name='endN']");
    private final By startPrice = By.xpath("//input[@name='startN']");
    private final By applyFiltersBtn = By.xpath("//button[@class='filters-desktop__btn-main btn-main']");
    private final By items = By.xpath("//div[@class='product-card-list']//article");

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public ItemPage openItem(){
        driver.findElements(items).get(0).click();
        waitPageLoad();
        return new ItemPage(driver);
    }

    public SearchResultPage openFilters(){
        driver.findElement(allFiltersBtn).click();
        return this;
    }

    public SearchResultPage setMinPrice(Integer minprice){
        driver.findElement(startPrice).clear();
        driver.findElement(startPrice).sendKeys(String.valueOf(minprice));
        return this;
    }

    public SearchResultPage setMaxPrice(Integer maxprice){
        driver.findElement(endPrice).clear();
        driver.findElement(endPrice).sendKeys(String.valueOf(maxprice));
        return this;
    }

    public SearchResultPage applyFilters(){
        driver.findElement(applyFiltersBtn).click();
        waitForElementUpdated(items);
        return this;
    }
}
