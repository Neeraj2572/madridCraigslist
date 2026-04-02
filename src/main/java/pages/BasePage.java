package pages;

import com.microsoft.playwright.Page;
import java.util.List;

public abstract class BasePage {

    protected final Page page;

    protected BasePage(Page page) {
        this.page = page;
    }

    protected void click(String selector) {
        page.locator(selector).click();
    }

    protected void type(String selector, String text) {
        page.locator(selector).fill(text);
    }

    protected List<String> texts(String selector) {
        return page.locator(selector).allTextContents();
    }
}