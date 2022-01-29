package com.hse.androidpw;

import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

class ValidatorTest {

    static Stream<Arguments> emailTestsProvider() {
        return Stream.of(
                arguments("Email.e@mail.site.ru", true),
                arguments("Email@email.ru", true),
                arguments("Email@email.", false),
                arguments("Email@email", false),
                arguments("Email.ru", false),
                arguments("Email@@email.ru", false),
                arguments("Email@email..ru", false)
        );
    }

    @ParameterizedTest
    @MethodSource("emailTestsProvider")
    void checkEmail(String email, boolean isValid) {
        assertEquals(isValid, Validator.checkEmail(email));
    }

    static Stream<Arguments> passwordTestsProvider() {
        return Stream.of(
                arguments("aB1.", false),
                arguments("aB1.qw", true),
                arguments("aB1qwe", false),
                arguments("ab1.we", false),
                arguments("AB1.WE", false)
        );
    }

    @ParameterizedTest
    @MethodSource("passwordTestsProvider")
    void checkPassword(String pass, boolean isValid) {
        assertEquals(isValid, Validator.checkPassword(pass));
    }
}