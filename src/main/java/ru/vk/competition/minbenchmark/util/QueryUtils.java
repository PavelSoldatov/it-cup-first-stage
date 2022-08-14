package ru.vk.competition.minbenchmark.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class QueryUtils {

    public static void validateCharacter(String input) {
//        Pattern pattern =
//                Pattern.compile("[a-zA-Z]+");
//
//        Matcher matcher = pattern.matcher(input);
//        if(!matcher.matches()) {
//            throw new RuntimeException();
//        }
        if(isCyrillic(input)) {
            throw new RuntimeException();
        }
    }

    public static void validateSizeQuery(String input) {
        if(input.length() > 120 || input.length() == 0) {
            throw new RuntimeException("wrong size table query");
        }
    }

    public static void validateSizeTableName(String input) {
        if(input.length() > 50 || input.length() == 0) {
            throw new RuntimeException("wrong table name size");
        }
    }

    public boolean isCyrillic(String s) {
        boolean result = false;
        for (char a : s.toCharArray()) {
            if (Character.UnicodeBlock.of(a) == Character.UnicodeBlock.CYRILLIC) {
                result = !result;
                break;
            }
        }
        return result;
    }
}
