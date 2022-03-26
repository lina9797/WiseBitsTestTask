package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class ProductPage {
    @FindBy(how = How.ID, using = "add-to-cart-button")
    public SelenideElement addToCartButton;

    @FindBy(how = How.ID, using = "sw-gtc")
    public SelenideElement cartButton;

    @FindBy(how = How.ID, using = "buy-now-button")
    public SelenideElement buyNowButton;

    public ProductPage addProductToCart() {
        addToCartButton.click();
        return this;
    }

    public CartPage goToCart(){
        cartButton.click();
        return page(CartPage.class);
    }
}
