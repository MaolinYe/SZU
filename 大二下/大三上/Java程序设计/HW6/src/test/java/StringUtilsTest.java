import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {
//    @ParameterizedTest
//    @MethodSource
//    void testCapitalize(String input, String result) {
//        assertEquals(result, StringUtils.capitalize(input));
//    }
//
//    static List<Arguments> testCapitalize() {
//        return List.of( // arguments:
//                Arguments.of("abc", "Abc"), //
//                Arguments.of("APPLE", "Apple"), //
//                Arguments.of("gooD", "Good"));
//    }

    //    @ParameterizedTest
//    @CsvSource({"abc, Abc", "APPLE, Apple", "gooD, Good"})
//    void testCapitalize(String input, String result) {
//        assertEquals(result, StringUtils.capitalize(input));
//    }
    @ParameterizedTest
    @CsvFileSource(resources = {"test_capitalize.csv"})
    void testCapitalizeUsingCsvFile(String input, String result) {
        assertEquals(result, StringUtils.capitalize(input));
    }
}