package quru.qa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JUnitSimpleTest {
    //Нужно помнить про Assertions. Иногда полезны
    @Test
    void simpleTest() {
        Assertions.assertTrue(3 > 2);
    }
}
