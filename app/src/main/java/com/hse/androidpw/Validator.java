package com.hse.androidpw;

import java.util.regex.Pattern;
import java.util.stream.Stream;

import androidx.core.util.Supplier;

public class Validator {
    static Pattern emailRegexPattern = Pattern.compile("\\A[^@\\s]+@[^@\\s.]+(\\.[^@.\\s]+)+\\Z", Pattern.CASE_INSENSITIVE);

    public static boolean checkEmail(String email) {
        return  emailRegexPattern.matcher(email).matches();
    }

    public static boolean checkPassword(String pass) {
        Supplier<Stream<Character>> p = () -> pass.codePoints().mapToObj(c -> (char) c);
        return pass.length() >= 6 &&
                p.get().anyMatch(Character::isDigit) &&
                p.get().anyMatch(Character::isLowerCase) &&
                p.get().anyMatch(Character::isUpperCase) &&
                p.get().anyMatch(c -> "!@#$%^&*.,".contains(String.valueOf(c)));
    }
}
