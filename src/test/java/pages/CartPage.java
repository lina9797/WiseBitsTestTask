package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CartPage {
    @FindBy(how = How.NAME, using = "quantity")
    public SelenideElement quantityDropdown;

    @FindBy(how = How.XPATH, using = "//div[@data-asin]")
    public ElementsCollection productsInTheCart;

    @FindBy(how = How.TAG_NAME, using = "h1")
    public SelenideElement messageAboutEmptyCart;

    public CartPage deleteProduct(){
        quantityDropdown.selectOption("0 (Delete)");
        return this;
    }
}
