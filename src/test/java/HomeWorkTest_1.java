import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.*;
import quru.qa.data.Locale;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;




import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class HomeWorkTest_1 {
    @BeforeEach
    void setUp() {
        open("https://yandex.by");
    }
@Disabled
    @CsvSource({
            "vk,vk.com",
            "ok,ok.ru"
    })
    @ParameterizedTest(name = "В адресе запроса {0} должен быть {1}  ")
    @Tags({@Tag("BLOCKER"), @Tag("UI_TEST")})
    void searchWebSite(
            String productUrl,
            String productName
    ) {
        $("[name= 'text']").setValue(productUrl).pressEnter();
        $("#search-result").shouldHave(text(productName));
    }
@Disabled
    @ValueSource(
            strings = {"vk","ok"}
    )
    @ParameterizedTest(name = "В адресе запроса {0} должен быть {1}  ")
    @Tags({@Tag("BLOCKER"), @Tag("UI_TEST")})
    void countWebSiteInSite(String productName){
        $("[name= 'text']").setValue(productName).pressEnter();
        $$("[data-cid]:not([data-fast-name])").shouldHave(CollectionCondition.sizeGreaterThan(5));
    }
}
