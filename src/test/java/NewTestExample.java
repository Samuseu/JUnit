import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class NewTestExample {
    @BeforeEach
    void beforeEach() {
        Configuration.browserSize = "1920x1080";
    }

    static Stream<Arguments> astonSholdHaveOffice() {
        return Stream.of(
                Arguments.of("/",List.of(
                        "г. Москва",
                        "г. Санкт-Петербург",
                        "г. Казань",
                        "г. Алматы")));
    }
@Disabled
    @MethodSource("astonSholdHaveOffice")
    @ParameterizedTest(name = "Для страницы {0} отображаются названия полей {1}")
    @Tags({@Tag("CRITICAL"), @Tag("UI_TEST")})
    void astonSholdHaveOffice(String openUrl, List<String> officeAston) {
        open(openUrl);
        $$("div.css-c9ffe")
                .filter(visible)
                .shouldHave(texts(officeAston));
    }
    @Disabled
    @Test
    void example(){
        open("https://astondevs.ru/");
        System.out.println("Найденные элементы: " + $$(".chakra-stack.css-azggd9 .css-0")
                .filter(visible)
                .texts());
    }
}
