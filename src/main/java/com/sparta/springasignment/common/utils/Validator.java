package com.sparta.springasignment.common.utils;

import java.util.regex.Pattern;

public class Validator {

    public static boolean isValidEmailAddress(String email) {
        String emailRegexPattern = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        return Pattern.matches(emailRegexPattern, email);
    }
}
