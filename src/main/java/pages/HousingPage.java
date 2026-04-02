package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HousingPage extends HomePage {

    private static final String SORT_DROPDOWN = "select.sort";
    private static final String PRICE_ELEMENTS = "span.priceinfo";
    private static final String SEARCH_BOX = "//input[@placeholder='Search housing";
    private static final String SORT_DROPDOWN1 = "div.cl-query-bar > div";



    public HousingPage(Page page) {
        super(page);
    }
    public void sortBy() {
        page.locator(".left-group > div > span").click();

    }
    public void sortBy(String value) {
        page.locator(".left-group > div > span").click();
        page.getByText(value).dblclick();
        //page.selectOption(SORT_DROPDOWN1, value);

    }

    public List<Double> getPrices() {
        page.waitForSelector("span.priceinfo");
      List<String> priceTexts = page.locator(PRICE_ELEMENTS).allTextContents();

        page.waitForSelector("span.priceinfo");
        List<Double> priceList = page.locator(PRICE_ELEMENTS)
                .allTextContents()
                .stream()
                .map(p -> p.replaceAll("[^\\d,\\.]", "").trim())
                .map(Double::parseDouble)
                .toList();
        return priceList;
    }

    public List<String> getSortOptions() {
        return texts(SORT_DROPDOWN1 + " option']");
    }

    public void search(String query) {
        page.waitForSelector(SEARCH_BOX);
        type(SEARCH_BOX, query);
        page.keyboard().press("Enter");
    }

//    public void navigateToHousing() {
//
//    }
}