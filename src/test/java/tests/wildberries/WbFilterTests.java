package tests.wildberries;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.wildberries.pages.ItemPage;
import tests.wildberries.pages.MainPage;
import tests.wildberries.pages.SearchResultPage;

import java.net.SocketException;
import java.util.Locale;

public class WbFilterTests extends BaseTest{

    @Test
    public void searchResultTest (){
        String expectedItem = "iphone";
        Integer expectedMaxPrice = 60000;
        Integer expectedMinPrice = 30000;

        ItemPage mainPage = new MainPage(driver)
        .searchItem(expectedItem)
                .openFilters()
                .setMinPrice(expectedMinPrice)
                .setMaxPrice(expectedMaxPrice)
                .applyFilters()
                .openItem();

        String actualName = mainPage.getItemName();
        Integer actualPrice = mainPage.getItemPrice();

        Assertions.assertTrue(actualName.toLowerCase().contains(expectedItem.toLowerCase()));
        Assertions.assertTrue(actualPrice >= expectedMinPrice && actualPrice <= expectedMaxPrice);


    }
}
