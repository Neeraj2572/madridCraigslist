package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class HomePage extends BasePage {

    private static final String URL = "https://madrid.craigslist.org/";
    private static final String HOUSING_LINK = "a.hhh";
    private static final String LANGUAGE_LINK = "#langlinks > a";
    private static final String LANGUAGE_CURRENT = "#chlang";

    public HomePage(Page page) {
        super(page);
    }

    public HomePage open() {
        page.navigate(URL);
        return this;
    }

    public HomePage changeLangToEnglish() {
        page.waitForSelector(LANGUAGE_CURRENT);
        Locator currentLanguage = page.locator(LANGUAGE_CURRENT);
        Locator langLink = page.locator(LANGUAGE_LINK);

        // Use a more resilient check for the current language
        String currentText = currentLanguage.innerText().trim();

        // Only click if we aren't already on the English version
        // Tip: Ensure "English" matches exactly what is displayed on the UI toggle
        if (!currentText.equalsIgnoreCase("english")) {
            langLink.click();

            // Wait for the URL or a specific element to confirm the language swap
            page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        }
        return this; // Or new HomePage(page) if state changes significantly
    }

    public HomePage goToHousing() {
        click(HOUSING_LINK);
        return new HomePage(page);
    }
}