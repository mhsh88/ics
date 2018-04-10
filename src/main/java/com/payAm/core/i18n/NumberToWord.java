package com.payAm.core.i18n;

/**
 * Created by Payam Mostafaei
 * Creation Time: 2017/Jan/09 - 07:47 PM
 */
public enum NumberToWord {

    ZERO(0L, CoreMessagesCodes.ZERO),
    ONE(1L, CoreMessagesCodes.ONE),
    TWO(2L, CoreMessagesCodes.TWO),
    THREE(3L, CoreMessagesCodes.THREE),
    FOUR(4L, CoreMessagesCodes.FOUR),
    FIVE(5L, CoreMessagesCodes.FIVE),
    SIX(6L, CoreMessagesCodes.SIX),
    SEVEN(7L, CoreMessagesCodes.SEVEN),
    EIGHT(8L, CoreMessagesCodes.EIGHT),
    NINE(9L, CoreMessagesCodes.NINE),
    TEN(10L, CoreMessagesCodes.TEN),
    ELEVEN(11L, CoreMessagesCodes.ELEVEN),
    TWELVE(12L, CoreMessagesCodes.TWELVE),
    THIRTEEN(13L, CoreMessagesCodes.THIRTEEN),
    FOURTEEN(14L, CoreMessagesCodes.FOURTEEN),
    FIFTEEN(15L, CoreMessagesCodes.FIFTEEN),
    SIXTEEN(16L, CoreMessagesCodes.SIXTEEN),
    SEVENTEEN(17L, CoreMessagesCodes.SEVENTEEN),
    EIGHTEEN(18L, CoreMessagesCodes.EIGHTEEN),
    NINETEEN(19L, CoreMessagesCodes.NINETEEN),
    TWENTY(20L, CoreMessagesCodes.TWENTY),
    THIRTY(30L, CoreMessagesCodes.THIRTY),
    FORTY(40L, CoreMessagesCodes.FORTY),
    FIFTY(50L, CoreMessagesCodes.FIFTY),
    SIXTY(60L, CoreMessagesCodes.SIXTY),
    SEVENTY(70L, CoreMessagesCodes.SEVENTY),
    ENGHTY(80L, CoreMessagesCodes.EIGHTY),
    NINETY(90L, CoreMessagesCodes.NINETY),
    HUNDRED(100L, CoreMessagesCodes.HUNDRED),
    TWO_HUNDRED(200L, CoreMessagesCodes.TWO_HUNDRED),
    THREE_HUNDRED(300L, CoreMessagesCodes.THREE_HUNDRED),
    FOURE_HUNDRED(400L, CoreMessagesCodes.FOUR_HUNDRED),
    FIVE_HUNDRED(500L, CoreMessagesCodes.FIVE_HUNDRED),
    SIX_HUNDRED(600L, CoreMessagesCodes.SIX_HUNDRED),
    SEVEN_HUNDRED(700L, CoreMessagesCodes.SEVEN_HUNDRED),
    EIGHT_HUNDRED(800L, CoreMessagesCodes.EIGHT_HUNDRED),
    NINE_HUNDRED(900L, CoreMessagesCodes.NINE_HUNDRED),
    THOUSAND(-1L, CoreMessagesCodes.THOUSAND),
    MILION(-2L, CoreMessagesCodes.MILLION),
    BILION(-3L, CoreMessagesCodes.BILLION);

    private Long number;
    private String word;

    private NumberToWord(Long number, String word) {
        this.number = number;
        this.word = word;
    }

    public Long getNumber() {
        return number;
    }

    public String getWord() {
        return word;
    }

    public static String convert(Long number) {
        for (NumberToWord numberToWord : values()) {
            if (numberToWord.getNumber().equals(number)) {
                return numberToWord.getWord();
            }
        }
        return NumberToWordConverter.EMPTY_WORD;
    }
}
