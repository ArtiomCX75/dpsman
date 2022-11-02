package com.faa1192.dpsman;

import java.util.ArrayList;
import java.util.HashSet;

public class RegNumberGenerator {
    private static final ArrayList<String> letterList = new ArrayList<>() {{
        add("А");
        add("Е");
        add("Т");
        add("О");
        add("Р");
        add("Н");
        add("У");
        add("К");
        add("Х");
        add("С");
        add("В");
        add("М");
    }};
    private static final int size = letterList.size();
    private static int letterIndex1 = 0;
    private static int letterIndex2 = 0;
    private static int letterIndex3 = 0;
    private static int digits = -1;

    private static final HashSet<String> db = new HashSet<>();

    public static String getNext() throws Exception {
        String regNumber = next();
        while (db.size() < 1728000) { //1_728_000 - amount of all possible variants. A111AA -> 12*10*10*10*12*12
            if (db.add(regNumber)) {
                return regNumber;
            }
            regNumber = next();
        }
        throw new Exception("No more license numbers available");
    }

    public static String getRandom() throws Exception {
        String regNumber = random();
        while (db.size() < 1728000) {
            if (db.add(regNumber)) {
                return regNumber;
            }
            regNumber = random();
        }
        throw new Exception("No more license numbers available");
    }

    private static void updateLetter() {
        letterIndex3++;
        if (letterIndex3 == size) {
            letterIndex3 -= size;
            letterIndex2++;
            if (letterIndex2 == size) {
                letterIndex2 -= size;
                letterIndex1++;
                if (letterIndex1 == size) {
                    letterIndex1 -= size;
                }
            }
        }
    }

    private static String next() {
        digits = digits + 1;
        if (digits == 1000) {
            digits = 0;
            updateLetter();
        }
        return getRegNumberFormatted();
    }
    private static String random() {
        digits = (int) (Math.random() * 1000);
        letterIndex1 = getRandomLetterIndex();
        letterIndex2 = getRandomLetterIndex();
        letterIndex3 = getRandomLetterIndex();
        return getRegNumberFormatted();
    }

    private static int getRandomLetterIndex() {
        return (int) (Math.random() * size);
    }

    private static String getRegNumberFormatted() {
        String postfix = "116 RUS";
        return String.format("%s%03d%s%s %s", letterList.get(letterIndex1), digits, letterList.get(letterIndex2), letterList.get(letterIndex3), postfix);
    }
}
