package calculator.tests;

import calculator.base.BaseTest;
import io.qameta.allure.Epic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Калькулятор")
public class CalculatorTest extends BaseTest {

    @Test
    @DisplayName("Проверка: 2 + 2 = 4 в калькуляторе")
    public void additionTest() {
        mobileObjects.clickBtn2();
        mobileObjects.clickPlus();
        mobileObjects.clickBtn2();
        mobileObjects.clickEquals();
        mobileObjects.checkResult("4");
    }
}
