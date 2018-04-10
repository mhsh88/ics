package com.payAm.core.i18n;

/**
 * Created by Payam Mostafaei
 * Creation Time: 2017/Jan/05 - 05:14 PM
 */
public class PersianUtil {

	public static String replacePersianWithEnglishNumbers(String input) {
		return input.replace('\u06f0', '0').replace('\u06f1', '1').replace('\u06f2', '2').replace('\u06f3', '3').replace('\u06f4', '4').replace('\u06f5', '5')
				.replace('\u06f6', '6').replace('\u06f7', '7').replace('\u06f8', '8').replace('\u06f9', '9');
	}

	public static String replaceEnglishWithPersianNumbers(String input) {
		return input.replace('0', '\u06f0').replace('1', '\u06f1').replace('2', '\u06f2').replace('3', '\u06f3').replace('4', '\u06f4').replace('5', '\u06f5')
				.replace('6', '\u06f6').replace('7', '\u06f7').replace('8', '\u06f8').replace('9', '\u06f9');
	}

	public static String convertArabicCharactersToPersianCharacters(String input) {
		input = input.replace("\u064a", "\u06cc");
		input = input.replace("\u0643", "\u06a9");
		return input;
	}

	public static String convertPersianCharactersToArabicCharacters(String input) {
		input = input.replace("\u06cc", "\u064a");
		input = input.replace("\u06a9", "\u0643");
		return input;
	}

}
