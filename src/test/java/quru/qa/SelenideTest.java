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
import quru.qa.data.Locale;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;


public class SelenideTest {
    //должен вернуть список аргументов
    static Stream<Arguments> selenideLocaleDataProvider() {  // метод, который вернет аргументы он должен быть статик, и должен вернуть Stream с типом Arguments
        return Stream.of(
                Arguments.of(Locale.EN, List.of("Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes")),
                Arguments.of(Locale.RU, List.of("С чего начать?", "Док", "ЧАВО", "Блог", "Javadoc", "Пользователи", "Отзывы"))
        );
    }

    // дата провайдер
    @MethodSource("selenideLocaleDataProvider")
    //тут можно имея не указывать но тогда метод с именем должен тогда называться как и выше  если нет то тут пишем название
    @ParameterizedTest(name = "Для локали {0} отображаются кнопки {1}")
    @Tag("BLOCKER")
    void selenideSiteShouldContainAllOfButtonsForGivenLocale(
            Locale locale,
            List<String> buttons
    ) {
        open("https://ru.selenide.org/");
        $$("#languages a").find(text(locale.name())).click(); // где locale.name поиск по имени в enum
        $$(".main-menu-pages a")
                .filter(visible)
                .shouldHave(CollectionCondition.texts(buttons)); // баттон выше так где аргументы это обычный скелет метода сорсе

    }


    /*
    @MethodSource("exampleTest")
    static Stream<Arguments> exampleTest() {
        {
            return Stream.of(
                    Arguments.of(),
                            Arguments.of(),
                            Arguments.of());
        }

    }*/
}