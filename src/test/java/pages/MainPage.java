package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class MainPage {
    @FindBy(how = How.ID, using = "twotabsearchtextbox")
    public SelenideElement searchBox;

    public ResultsPage search(String query) {
        searchBox.setValue(query).pressEnter();
        return page(ResultsPage.class);
    }
}
