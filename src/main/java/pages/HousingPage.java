package pages;


import com.microsoft.playwright.Page;
import java.util.List;

public class HousingPage extends HomePage {

    private static final String PRICE_ELEMENTS = "span.priceinfo";
    private static final String SEARCH_BOX = "//input[@placeholder='Search housing']";
    private static final String DROPDOWN_LIST = "div.items>button";
    private static final String DROPDOWN_ARROW= ".left-group > div > span";

    public HousingPage(Page page) {
        super(page);
    }
    public void sortBy() {
        page.locator(DROPDOWN_ARROW).click();

    }
    public void sortBy(String value) {
        page.locator(DROPDOWN_ARROW).click();
        page.getByText(value).dblclick();
        //page.selectOption(SORT_DROPDOWN1, value);

    }

    public List<Double> getPrices() {
        page.waitForSelector(PRICE_ELEMENTS);
        List<Double> priceList = page.locator(PRICE_ELEMENTS)
                .allTextContents()
                .stream()
                .map(p -> p.replaceAll("[^\\d,\\.]", "").trim())
                .map(Double::parseDouble)
                .toList();
        return priceList;
    }

    public List<String> getSortOptions() {
        page.locator(DROPDOWN_ARROW).click();
        List<String> drpdnList = page.locator(DROPDOWN_LIST)
                .allTextContents()
                .stream()
                .toList();
        return drpdnList;
    }

    public void search(String query) {
     //   page.waitForSelector(SEARCH_BOX);
        type(SEARCH_BOX, query);
        page.keyboard().press("Enter");
    }


}