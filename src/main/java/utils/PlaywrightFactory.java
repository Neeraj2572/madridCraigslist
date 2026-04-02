package utils;

import com.microsoft.playwright.*;


public class PlaywrightFactory {
    public static Playwright playwright;
    public static Browser browser;
    public static BrowserContext context;
    public static com.microsoft.playwright.Page page;

    public static Page initBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
        return page;
    }

    public static void tearDown() {
        context.close();
        browser.close();
        playwright.close();
    }
}