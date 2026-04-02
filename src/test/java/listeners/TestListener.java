package listeners;

import base.BaseTest;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.extension.ExtensionContext; // JUnit 5
import org.junit.jupiter.api.extension.TestWatcher;      // JUnit 5
import java.nio.file.Paths;


public class TestListener implements TestWatcher {
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        context.getTestInstance().ifPresent(instance -> {
            if (instance instanceof BaseTest) {
                Page page = ((BaseTest) instance).page;
                // Check if page exists and isn't already closed
                if (page != null && !page.isClosed()) {
                    String path = "screenshots/" + context.getDisplayName() + ".png";
                    page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)));
                    System.out.println("Screenshot captured: " + path);
                }
            }
        });
    }
}