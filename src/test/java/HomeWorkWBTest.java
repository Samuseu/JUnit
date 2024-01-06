import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;

public class HomeWorkWBTest {
    static Stream<Arguments> costDependsOnCurrentСurrency() {
        return Stream.of(
                Arguments.of(Currency.BYN, "Белорусский рубль"),
                Arguments.of(Currency.RUB, "Российский рубль"),
                Arguments.of(Currency.KZT, "Казахстанский тенге"),
                Arguments.of(Currency.AMD, "Армянский драм"),
                Arguments.of(Currency.KGS, "Киргизский сом"),
                Arguments.of(Currency.UZS, "Узбекский сум")
        );
    }

    @MethodSource("costDependsOnCurrentСurrency")
    @ParameterizedTest(name = "Валюта {0} на сайте WB это {1}")
    @Tags({@Tag("CRITICAL"), @Tag("UI_TEST")})
    void firstTest(Currency currency, String description) {
        open("https://www.wildberries.by/");
        $("div[data-tag='currencyList']").click();
        $(".currency-list")
                .$$(".currency-item")
                .filterBy(Condition.text(currency.name()))
                .first()
                .shouldHave(Condition.text(description));
        sleep(2000);

    }
}

