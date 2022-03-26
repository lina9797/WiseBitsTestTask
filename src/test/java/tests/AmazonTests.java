package tests;

import com.codeborne.selenide.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.MainPage;
import pages.ProductPage;
import pages.ResultsPage;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class AmazonTests {

    @Parameters("browserName")
    @BeforeTest
    public void setUp(String browserName){
        if(browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            WebDriverRunner.setWebDriver(new ChromeDriver());
        }else if(browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            WebDriverRunner.setWebDriver(new EdgeDriver());
        }
    }

    @Test
    public void test(){
        MainPage mainPage = open("https://www.amazon.com/", MainPage.class);
        ResultsPage resultsPage = mainPage.search("iphone");
        $$(resultsPage.resultItems).shouldHave(sizeGreaterThan(0));
        resultsPage.sortByHighToLow();
        $(resultsPage.sortByDropdownPrompt).shouldHave(exactText("Price: High to Low"));
        ProductPage productPage = resultsPage.openFirstAvailableProductPage();
        $(productPage.buyNowButton).shouldBe(enabled);
        productPage.addProductToCart();
        CartPage cartPage = productPage.goToCart();
        $$(cartPage.productsInTheCart).shouldHave(size(1));
        cartPage.deleteProduct();
        $(cartPage.messageAboutEmptyCart).shouldHave(text("Your Amazon Cart is empty."));
    }

    @AfterTest
    public void tearDown() {
        WebDriverRunner.getWebDriver().quit();
    }
}
