package com.payAm.core.i18n;

/**
 * Created by Payam Mostafaei
 * Creation Time: 2017/Jan/09 - 07:48 PM
 */
public class NumberToWordConverter {

    public static final String EMPTY_WORD = "---";

    public static String convertNumberToWord(String numberAsString) {
        Long number = Long.decode(numberAsString);
        if (number > 999_999_999_999L) {
            throw new IllegalArgumentException(numberAsString + " is greater than supported number.");
        }

        String result = "";
        Long i = 0L;
        while (number > 0) {
            result = convertNumberToWord(number % 1000, (number / 1000 > 0)) + (number % 1000 > 0 ? " " + NumberToWord.convert(-i) : " ") + result;
            number = number / 1000;
            i++;
        }
        return result;
    }

    private static String convertNumberToWord(Long number, boolean addSeparator) {
        String word = "";
        try {
            int length = number.toString().length();
            for (int i = 0; i < length; i++) {
                if (number / 100 >= 1) {
                    word += " " + addSeparatorWord(addSeparator) + NumberToWord.convert(number / 100 * 100);
                    number = number % 100;
                } else if (number / 10 >= 1) {
                    if (number < 20)
                        return word += " " + addSeparatorWord(addSeparator) + NumberToWord.convert(number);
                    else {
                        word += " " + addSeparatorWord(addSeparator) + NumberToWord.convert(number / 10 * 10);
                        number = number % 10;
                    }
                } else if (number > 0) {
                    word += " " + addSeparatorWord(addSeparator) + NumberToWord.convert(number);
                    number = number % 1;
                }
                addSeparator = true; // It used once.
            }
        } catch (NumberFormatException e) {
            word = EMPTY_WORD;
        }
        return word;
    }

    private static String addSeparatorWord(boolean addSeparator) {
        return addSeparator ? CoreMessagesCodes.SEPARATOR + " " : "";
    }
}
