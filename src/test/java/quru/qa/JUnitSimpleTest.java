package quru.qa;
import com.codeborne.selenide.CollectionCondition;
import quru.qa.data.Locale;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;


public class JUnitSimpleTest {
    //виды аннотаций
    @DisplayName("Демонстрационный тест")   // используем для описания теста чтобы читалось в алюр
    @Test
    void simpleTest() {
        Assertions.assertTrue(3 > 2);
    }

    @BeforeEach  //перед каждым тестом
    void setUp() {
        open("https://google.com");
    }

    @CsvSource({ // ввиде массива для нескольких параметров
            "Allure testops, https://qameta.io",
            "Selenide, https://selenide.org"
    })
    // OR!!!
    @CsvFileSource(resources = "/testData.csv")   // данные или отсюда или выше через CsvSource как в таблице ключ значение
    @ParameterizedTest(name = "Адрес {1} должен быть в выдаче гугла по запросу {0}")  // параметризованные тесты name будет в название теста
    //
    @Tags({@Tag("BLOCKER"), @Tag("UI_TEST")})
    void productSiteUrlShouldBePresentInResultsOfSearchInGoggleByProductNameQuery(
            String productName,
            String productUrl
    ) {
        $("[name=q]").setValue(productName).pressEnter();
        $("[id=search]").shouldHave(text(productUrl));
    }

    @ValueSource( // для одно параметра
            strings = {"Allure testops", "Selenide"}
    )
    @ParameterizedTest(name = "Адрес {1} должен быть в выдаче гугла по запросу {0}")
    //
    @Tags({@Tag("BLOCKER"), @Tag("UI_TEST")})
    void searchResultsCountTest(String productName) {
        $("[name=q]").setValue(productName).pressEnter();
        $$("div[class='g']").shouldHave(CollectionCondition.sizeGreaterThan(5));
    }


    void selenideSiteShouldContainAllOfButtonsForGivenLocale(
            Locale locale,
            List<String> buttons
    ) {

    }

}