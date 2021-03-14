package com.homeoffice.points.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.homeoffice.points.model.ErrorMessages;
import com.homeoffice.points.model.TaxBracket;
import com.homeoffice.points.model.TaxBrackets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class JsonStringConverterTest {

    private static String json = "{\"tax_brackets\":[{\"max\":48535,\"min\":0,\"rate\":0.15},{\"max\":97069,\"min\":48535,\"rate\":0.205},{\"max\":150473,\"min\":97069,\"rate\":0.26}," +
            "{\"max\":214368,\"min\":150473,\"rate\":0.29},{\"min\":214368,\"rate\":0.33}]}";

    private static String jsonBad = "{{\"max\":48535,\"min\":0,\"rate\":0.15},{\"max\":97069,\"min\":48535,\"rate\":0.205},{\"max\":150473,\"min\":97069,\"rate\":0.26}," +
            "{\"max\":214368,\"min\":150473,\"rate\":0.29},{\"min\":214368,\"rate\":0.33}}";

    static Stream<Arguments> exceptionData() {

        return Stream.of(
                arguments(json, ErrorMessages.class),
                arguments(jsonBad, TaxBrackets.class),
                arguments("", TaxBrackets.class),
                arguments("badString", TaxBrackets.class)
        );
    }

    @Test
    void test_valid_string() throws JsonProcessingException {
        assertEquals(JsonStringConverter.convert(json, TaxBrackets.class).getTax_brackets().size(),5);
    }

    @ParameterizedTest(name = "#{index} - Test JsonProcessingException.")
    @MethodSource("exceptionData")
    void test_JsonProcessingException(String data, Class type) {
        Assertions.assertThrows(JsonProcessingException.class, () -> JsonStringConverter.convert(data, type));
    }

    @Test
    void test_IllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> JsonStringConverter.convert(null, TaxBrackets.class));
    }
}