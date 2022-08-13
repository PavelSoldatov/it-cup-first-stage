package ru.vk.competition.minbenchmark.util;

import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class QueryUtils {

    public static void validateCharacter(String input) {
        Pattern pattern =
                Pattern.compile("[a-zA-Z]+");

        Matcher matcher = pattern.matcher(input);
        if(!matcher.matches()) {
            throw new RuntimeException();
        }
    }
}
