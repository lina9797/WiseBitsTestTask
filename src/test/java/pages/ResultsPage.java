package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ResultsPage {
    @FindBy(how = How.XPATH, using = "//div[@data-component-type]")
    public ElementsCollection resultItems;

    @FindBy(how = How.ID, using = "s-result-sort-select")
    public SelenideElement sortByDropdown;

    @FindBy(how = How.XPATH, using = "//span[@class='a-dropdown-prompt']")
    public SelenideElement sortByDropdownPrompt;

    @FindBy(how = How.XPATH, using = "//a[contains(@class, 's-pagination-next')]/preceding-sibling::*[1]")
    public SelenideElement lastPageButton;

    @FindBy(how = How.XPATH, using = "//a[contains(@aria-label, 'Go to next page')]")
    public SelenideElement nextPageButton;

    @FindBy(how = How.XPATH, using = "//span[@class='s-pagination-item s-pagination-selected']")
    public SelenideElement currentPageButton;

    @FindBy(how = How.XPATH, using = "//div[@data-component-type]//span[not(contains(., 'Currently unavailable')) and contains(text(), 'in stock')]")
    public ElementsCollection availableProductsOnTheCurrentResultPage;

    public int getNumberOfResultsPages(){
        return Integer.parseInt(lastPageButton.getText());
    }

    public ResultsPage sortByHighToLow(){
        sortByDropdown.selectOption("Price: High to Low");
        return this;
    }

    private SelenideElement findFirstAvailableProduct()  {
        SelenideElement firstAvailableProduct = null;
        int numberOfResultsPages = this.getNumberOfResultsPages();
        for (int i=1; i<=numberOfResultsPages-1; i++){
            $(currentPageButton).shouldHave(text(String.valueOf(i)));
            if (availableProductsOnTheCurrentResultPage.size()>0) {
                firstAvailableProduct = availableProductsOnTheCurrentResultPage.first();
                break;
            } else {
                $(nextPageButton).shouldBe(visible, Duration.ofSeconds(3)).click();
            }
        }
        return firstAvailableProduct;
    }

    public ProductPage openFirstAvailableProductPage() {
        SelenideElement firstProductInStock = findFirstAvailableProduct();
        $(firstProductInStock).find(By.xpath("ancestor::div[contains(@class, 'a-section')]//a")).click();
        return page(ProductPage.class);
    }
}
