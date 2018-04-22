package com.payAm.core.util;

import com.payAm.core.i18n.PersianUtil;

import java.util.*;

/**
 * Created by Payam Mostafaei
 * Creation Time: 2017/Jan/05 - 05:13 PM
 */
public class StringUtil {

	public static final String EMPTY_STRING = "";
	public static final String SPACE = " ";
	public static final String DOT = ".";
	public static final String COMMA = ",";
	public static final String STAR = "*";
	public static final String COLON = ":";
	public static final String PERCENT = "%";
	public static final String SLASH = "/";
	public static final String BACKSLASH = "\\";
	public static final String QUOTE = "'";
	public static final String DOUBLE_QUOTE = "\"";
	public static final String DASH = "-";
	public static final String INVISIBLE_CONTROL = "\u0011"; // Can be used as delimiter or end-of-word in ChosenComboBox option titles.
	public static final String COMMA_CONTROL = "," + INVISIBLE_CONTROL; // Can be used as delimiter in convertCollectionToString() and convertStringToStringSet|List() when we may have comma in some strings.
	public static final String UNDER_LINE = "_";
	public static final String NULL = "null";
	public static final String PX = "px";

	public static final String NUMBER_RANGE = "0-9\u06f0-\u06f9"; // '\u06f0' is persian 0 and '\u06f9' is persian 9.
	public static final String POSITIVE_INTEGER_NUMBER_REGEX = "[" + NUMBER_RANGE + "]+";
	public static final String NEGATIVE_INTEGER_NUMBER_REGEX = "\\-[" + NUMBER_RANGE + "]+";
	public static final String POSITIVE_DECIMAL_NUMBER_REGEX = "[" + NUMBER_RANGE + "]+\\.?[" + NUMBER_RANGE + "]*";
	public static final String NEGATIVE_DECIMAL_NUMBER_REGEX = "\\-[" + NUMBER_RANGE + "]+\\.?[" + NUMBER_RANGE + "]*";
	public static final String EMAIL_REGEX = "^[\\w\\.-_]+@([\\w\\-]+\\.)+[A-Za-z]{2,4}$";

	public static final Set<String> REGEX_SPECIAL_CHARACTERS = new HashSet<String>();

	static {
		REGEX_SPECIAL_CHARACTERS.add("[");
		REGEX_SPECIAL_CHARACTERS.add("]");
		REGEX_SPECIAL_CHARACTERS.add("{");
		REGEX_SPECIAL_CHARACTERS.add("}");
		REGEX_SPECIAL_CHARACTERS.add("^");
		REGEX_SPECIAL_CHARACTERS.add("$");
		REGEX_SPECIAL_CHARACTERS.add("|");
		REGEX_SPECIAL_CHARACTERS.add(".");
		REGEX_SPECIAL_CHARACTERS.add("?");
		REGEX_SPECIAL_CHARACTERS.add("*");
		REGEX_SPECIAL_CHARACTERS.add("+");
		REGEX_SPECIAL_CHARACTERS.add("(");
		REGEX_SPECIAL_CHARACTERS.add(")");
		REGEX_SPECIAL_CHARACTERS.add("\\");
	}

	public static boolean hasText(Object object) {
		return object != null && object.toString().trim().length() > 0;
	}

	public static String getClassNameFromFullPackageName(String fullPackageName) {
		if (fullPackageName != null && fullPackageName.length() > 0) {
			String[] array = fullPackageName.split("\\.");
			if (array.length > 0) {
				return array[array.length - 1];
			}
		}
		return fullPackageName;
	}

	public static String convertStringArrayToString(String[] array, String seperator) {
		if (array == null || array.length == 0) {
			return null;
		}
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			result.append(array[i]);
			if (i < array.length - 1) {
				result.append(seperator);
			}
		}
		return result.toString();
	}

	public static String convertStringArrayToString(String[] array) {
		return convertStringArrayToString(array, COMMA);
	}

	public static String refineSeparator(String separator) {
		if (REGEX_SPECIAL_CHARACTERS.contains(separator)) {
			return "\\" + separator;
		}
		return separator;
	}

	public static String[] convertStringToStringArray(String input, String seperator) {
		if (input == null || input.trim().length() == 0) {
			return new String[] {};
		}
		return input.split(refineSeparator(seperator));
	}

	public static String[] convertStringToStringArray(String input) {
		return convertStringToStringArray(input, COMMA);
	}

	public static String convertCollectionToString(Collection<? extends Object> collection, String seperator) {
		if (collection == null || collection.isEmpty()) {
			return null;
		}
		StringBuilder result = new StringBuilder();
		for (Iterator<? extends Object> iterator = collection.iterator(); iterator.hasNext();) {
			String item = iterator.next().toString();
			result.append(item);
			if (iterator.hasNext()) {
				result.append(seperator);
			}
		}
		return result.toString();
	}

	public static String convertCollectionToString(Collection<? extends Object> collection) {
		return convertCollectionToString(collection, COMMA);
	}

	public static HashSet<String> convertStringToStringSet(String input, String seperator) {
		HashSet<String> result = new HashSet<String>();
		String[] array = convertStringToStringArray(input, seperator);
		for (String string : array) {
			if (StringUtil.hasText(string.trim())) {
				result.add(string.trim());
			}
		}
		return result;
	}

	public static HashSet<String> convertStringToStringSet(String input) {
		return convertStringToStringSet(input, COMMA);
	}

	public static HashSet<Long> convertStringToLongSet(String input, String seperator) {
		HashSet<Long> result = new HashSet<Long>();
		String[] array = convertStringToStringArray(input, seperator);
		for (String string : array) {
			if (StringUtil.hasText(string.trim())) {
				String trimmedString = PersianUtil.replacePersianWithEnglishNumbers(string.trim());
				result.add(Long.valueOf(trimmedString));
			}
		}
		return result;
	}

	public static HashSet<Long> convertStringToLongSet(String input) {
		return convertStringToLongSet(input, COMMA);
	}

	public static HashSet<Integer> convertStringToIntegerSet(String input, String seperator) {
		HashSet<Integer> result = new HashSet<Integer>();
		String[] array = convertStringToStringArray(input, seperator);
		for (String string : array) {
			if (StringUtil.hasText(string.trim())) {
				String trimmedString = PersianUtil.replacePersianWithEnglishNumbers(string.trim());
				result.add(Integer.valueOf(trimmedString));
			}
		}
		return result;
	}

	public static Set<Integer> convertStringToIntegerSet(String input) {
		return convertStringToIntegerSet(input, COMMA);
	}

	public static ArrayList<String> convertStringToStringList(String input, String seperator) {
		ArrayList<String> result = new ArrayList<String>();
		String[] array = convertStringToStringArray(input, seperator);
		for (String string : array) {
			if (StringUtil.hasText(string.trim())) {
				result.add(string.trim());
			}
		}
		return result;
	}

	public static ArrayList<String> convertStringToStringList(String input) {
		return convertStringToStringList(input, COMMA);
	}

	public static ArrayList<Long> convertStringToLongList(String input, String seperator) {
		ArrayList<Long> result = new ArrayList<Long>();
		String[] array = convertStringToStringArray(input, seperator);
		for (String string : array) {
			if (StringUtil.hasText(string.trim())) {
				String trimmedString = PersianUtil.replacePersianWithEnglishNumbers(string.trim());
				result.add(Long.valueOf(trimmedString));
			}
		}
		return result;
	}

	public static ArrayList<Long> convertStringToLongList(String input) {
		return convertStringToLongList(input, COMMA);
	}

	public static ArrayList<Integer> convertStringToIntegerList(String input, String seperator) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		String[] array = convertStringToStringArray(input, seperator);
		for (String string : array) {
			if (StringUtil.hasText(string.trim())) {
				String trimmedString = PersianUtil.replacePersianWithEnglishNumbers(string.trim());
				result.add(Integer.valueOf(trimmedString));
			}
		}
		return result;
	}

	public static ArrayList<Integer> convertStringToIntegerList(String input) {
		return convertStringToIntegerList(input, COMMA);
	}

	public static String toFirstUpper(String input) {
		if (!hasText(input)) {
			return input;
		}
		return input.substring(0, 1).toUpperCase() + (input.length() >= 1 ? input.substring(1) : "");
	}

	public static String toFirstLower(String input) {
		if (!hasText(input)) {
			return input;
		}
		return input.substring(0, 1).toLowerCase() + (input.length() >= 1 ? input.substring(1) : "");
	}

	public static String convertFullyUpperCaseToUpperCamelCase(String fullyUpperCase) {
		if (!hasText(fullyUpperCase)) {
			return fullyUpperCase;
		}
		String[] parts = fullyUpperCase.split("_");
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < parts.length; i++) {
			result.append(toFirstUpper(parts[i].toLowerCase()));
		}
		return result.toString();
	}

	public static String convertFullyUpperCaseToLowerCamelCase(String fullyUpperCase) {
		return toFirstLower(convertFullyUpperCaseToUpperCamelCase(fullyUpperCase));
	}

	public static String convertUpperCamelCaseToFullyUpperCase(String upperCamelCase) {
		if (!hasText(upperCamelCase)) {
			return upperCamelCase;
		}
		StringBuilder fullyUpperCase = new StringBuilder("");
		for (int i = 0; i < upperCamelCase.length(); i++) {
			Character currentChar = upperCamelCase.charAt(i);
			if (i > 0 && Character.isUpperCase(currentChar) && (i + 1) < upperCamelCase.length() && Character.isLowerCase(upperCamelCase.charAt(i + 1))) {
				fullyUpperCase.append('_');
			}
			fullyUpperCase.append(Character.toUpperCase(currentChar));
		}
		return fullyUpperCase.toString();
	}

	public static String convertLowerCamelCaseToFullyUpperCase(String lowerCamelCase) {
		return convertUpperCamelCaseToFullyUpperCase(toFirstUpper(lowerCamelCase));
	}

	public static String getCorrectedFileName(String fileName) {
		return fileName.replace('/', '_').replace('\\', '_').replace(':', '_').replace('?', '_').replace('*', '_').replace('"', '_').replace('<', '_')
				.replace('>', '_').replace('|', '_');
	}

	public static String convertStringToCurrency(String input) {
		if (!StringUtil.hasText(input)) {
			return null;
		}
		input = input.replace(COMMA, "");
		String currency = "";
		if (input.startsWith(DASH)) {
			currency += DASH;
			input = input.substring(1);
		}
		int nonDecimalPartLength = input.contains(DOT) ? input.indexOf(DOT) : input.length();
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '.') {
				currency += input.substring(i);
				break;
			}
			if ((currency.length() > 0 && !DASH.equals(currency)) && (nonDecimalPartLength - i) % 3 == 0) {
				currency += StringUtil.COMMA;
			}
			currency += input.charAt(i);
		}
		return currency;
	}

	public static String breakText(String input, int breakSize) {
		if (!StringUtil.hasText(input)) {
			return "";
		}
		if (input.length() > breakSize) {
			return input.substring(0, breakSize) + "...";
		}
		return input;
	}

	public static String getPackagePrefix(String fullyQualifiedClassName, int numberOfPackagePrefixParts) {
		int dotIndex = -1;
		for (int i = 0; i < numberOfPackagePrefixParts; i++) {
			dotIndex = fullyQualifiedClassName.indexOf('.', dotIndex + 1);
		}
		return fullyQualifiedClassName.substring(0, dotIndex);
	}

	public static String getFormattedSize(long sizeInBytes, boolean showFraction) {
		long fraction = 0L;
		if (sizeInBytes < 1024L) {
			return sizeInBytes + "B";
		}
		fraction = sizeInBytes % 1024L;
		sizeInBytes /= 1024L;
		if (sizeInBytes < 1024L) {
			return (showFraction ? sizeInBytes + DOT + fraction : sizeInBytes + 1) + "KB";
		}
		fraction = sizeInBytes % 1024L;
		sizeInBytes /= 1024L;
		if (sizeInBytes < 1024L) {
			return (showFraction ? sizeInBytes + DOT + fraction : sizeInBytes + 1) + "MB";
		}
		fraction = sizeInBytes % 1024L;
		sizeInBytes /= 1024L;
		return (showFraction ? sizeInBytes + DOT + fraction : sizeInBytes + 1) + "GB";
	}

	public static List<String> sortByLength(Collection<String> collection, boolean ascending) {
		List<String> sorted = new ArrayList<String>();
		boolean isFirst = true;
		if (collection != null) {
			for (String item : collection) {
				if (isFirst) {
					sorted.add(item);
					isFirst = false;
				} else {
					boolean wasInserted = false;
					for (int i = 0; i < sorted.size(); i++) {
						if ((ascending && item.length() < sorted.get(i).length()) || (!ascending && item.length() > sorted.get(i).length())) {
							sorted.add(i, item);
							wasInserted = true;
							break;
						}
					}
					if (!wasInserted) {
						sorted.add(item);
					}
				}
			}
		}
		return sorted;
	}

	public static boolean isValidIPAddress(String ip, boolean acceptStar) {
		try {
			ip = PersianUtil.replacePersianWithEnglishNumbers(ip);
			List<String> ipTokens = Arrays.asList(ip.split("\\."));
			if (ipTokens.size() != 4) {
				return false;
			}
			if (Integer.valueOf(ipTokens.get(0).trim()) > 255) {
				return false;
			}
			if (Integer.valueOf(ipTokens.get(1).trim()) > 255) {
				return false;
			}
			if (Integer.valueOf(ipTokens.get(2).trim()) > 255) {
				return false;
			}
			if (acceptStar) {
				String[] part4 = ipTokens.get(3).trim().split("\\*");
				for (int i = 0; i < part4.length; i++) {
					if ("*".equals(part4[i])) {
						return true;
					} else if (!"".equals(part4[i]) && Integer.valueOf(part4[i]) > 255) {
						return false;
					}
				}
			} else {
				if (Integer.valueOf(ipTokens.get(3).trim()) > 255) {
					return false;
				}
			}
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static Long convertStringIpToNumber(String ip) {
		if (isValidIPAddress(ip, false)) {
			String[] ipOctets = ip.split("\\.");
			return (Long.valueOf(ipOctets[0]) * 256L * 256L * 256L) + (Long.valueOf(ipOctets[1]) * 256L * 256L) + (Long.valueOf(ipOctets[2]) * 256L)
					+ Long.valueOf(ipOctets[3]);
		} else {
			return null;
		}
	}

//	public static List<String> filterStringListByString(List<String> list, String filterValue, StringOperator operator) {
//		List<String> result = new ArrayList<>();
//		for (String value : list) {
//			Method method = ReflectionUtil.getMethod(value.getClass(), operator.getTitle(), String.class);
//			if ((Boolean) ReflectionUtil.invokeMethod(method, value, filterValue))
//				result.add(value);
//		}
//		return result;
//	}

//	public static List<String> filterStringListByNotString(List<String> list, String filterValue, StringOperator operator) {
//		List<String> result = new ArrayList<>();
//		for (String value : list) {
//			Method method = ReflectionUtil.getMethod(value.getClass(), operator.getTitle(), String.class);
//			if (!(Boolean) ReflectionUtil.invokeMethod(method, value, filterValue))
//				result.add(value);
//		}
//		return result;
//	}

	public enum StringOperator {

		EQ(1, "equals"),
		LIKE(2, "contains"),
		STARTS_WITH(3, "startsWith"),
		ENDS_WITH(4, "endsWith"),
		CONTAINS(5, "contains");

		private static HashMap<Integer, String> options;

		private Integer code;
		private String title;

		StringOperator(Integer code, String title) {
			this.code = code;
			this.title = title;
		}

		public Integer getCode() {
			return code;
		}

		public String getTitle() {
			return title;
		}

		public static HashMap<Integer, String> getOptions() {
			if (options == null) {
				options = new HashMap<Integer, String>();
				for (StringOperator operator : values()) {
					options.put(operator.getCode(), operator.getTitle());
				}
			}
			return options;
		}
	}

}
