package test;


import base.BaseTest;
import listeners.TestListener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.HousingPage;
import org.junit.jupiter.api.extension.*;

import java.util.Comparator;
import java.util.List;

@ExtendWith(TestListener.class)
public class HousingSortingTest extends BaseTest {

    public HousingPage housingPage;
    public HomePage homePage;

    @BeforeEach
    void init() {
        housingPage = new HousingPage(page);
        homePage = new HomePage(page);
        homePage.open();
        homePage.changeLangToEnglish();
        homePage.goToHousing();
    }


    @Test
    void verifySortingPriceAscending() {
        housingPage.sortBy("£ → £££");
        List<Double> prices = housingPage.getPrices();
        List<Double> sorted = prices.stream().sorted().toList();
        Assertions.assertEquals(sorted, prices, "Prices are not sorted ascending");
    }

    @Test
    void verifySortingPriceDescending() {
        housingPage.sortBy("£££ → £");
        List<Double> prices = housingPage.getPrices();

        List<Double> sorted = prices.stream().
                sorted(Comparator.reverseOrder())
                .toList();

        Assertions.assertEquals(sorted, prices, "Prices are not sorted descending");
    }

    @Test
    void verifySortingOptionsAfterSearch() {
        housingPage.search("apartment");

        List<String> options = housingPage.getSortOptions();

        Assertions.assertTrue(options.contains("£££ → £"));
        Assertions.assertTrue(options.contains("£ → £££"));
        Assertions.assertTrue(options.contains("newest"));
        Assertions.assertTrue(options.contains("oldest"));
        Assertions.assertTrue(options.contains("upcoming"));
    }

    @Test
    void verifyDefaultSortingOptions() {
        List<String> options = housingPage.getSortOptions();

        Assertions.assertTrue(options.contains("£££ → £"));
        Assertions.assertTrue(options.contains("£ → £££"));
        Assertions.assertTrue(options.contains("newest"));
    }
}


