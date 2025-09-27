package calculator.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MobileObjects {
    private final SelenideElement btn2 = $(By.id("com.google.android.calculator:id/digit_2"));
    private final SelenideElement btnPlus = $(By.id("com.google.android.calculator:id/op_add"));
    private final SelenideElement btnEquals = $(By.id("com.google.android.calculator:id/eq"));
    private final SelenideElement result = $(By.id("com.google.android.calculator:id/result_final"));

    public void clickBtn2() {
        btn2.shouldBe(visible).click();
    }

    public void clickPlus() {
        btnPlus.shouldBe(visible).click();
    }

    public void clickEquals() {
        btnEquals.shouldBe(visible).click();
    }

    public void checkResult(String expected) {
        result.shouldBe(visible).shouldHave(text(expected));
    }
}
