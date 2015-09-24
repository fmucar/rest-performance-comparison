package com.cooldatasoft.sampler.util;

/**
 * @author Fatih Mehmet UCAR - fmucar@cooldatasoft.com
 * @version
 * 
 * Generates random Strings with its static utility methods
 * 
 * 
 * TODO: add another getString equivalent that generates only special chars and leaves com.cooldatasoft.rest as is instead of raising exception
 * TODO: add another getString method that generates a list of strings via expression + list of strings above
 * TODO: completely random string just with length
 * TODO: completely random string list just with length and number
 * TODO: Generate random char
 * TODO: generate include chars other than letters & numbers
 * TODO: user wants to generate random string including char that is used as special chars
 * TODO: return array of random strings
 * TODO: return List of random strings
 * TODO: return Set of random strings
 * 
 */

public class RandomString {

	private static final int NUM_OF_NUMERIC = 10;
	private static final int NUM_OF_LOWERCASE = 26;
	private static final int NUM_OF_UPPERCASE = 26;

	private RandomString() {
	}

	/**
	 * Generate random string using special expression. The length of the string will be exactly
	 * same as the expression. Each char of random string can be determined with the expression
	 * 
	 * # gives numeric char * gives Numeric or Uppercase letter or lowercase letters > gives
	 * Uppercase letter only < gives Lowercase letter only $ gives alphabetic letters only
	 * (uppercase or lowercase) ] gives numeric or lowercase letter [ gives numeric or uppercase
	 * letter
	 * 
	 * 
	 * Throws RuntimeException if expression contains invalid chars that are not expected
	 * 
	 * @param expression
	 *            Special expression that contains special chars for deciding what kind of char is
	 *            expected.
	 * @return randomly generated string using the expression passed as parameter
	 */
	public static String getString(String expression) {
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) == '#') {
				result.append(getRandomString(true, false, false, 1));
			} else if (expression.charAt(i) == '*') {
				result.append(getRandomString(true, true, true, 1));
			} else if (expression.charAt(i) == '>') {
				result.append(getRandomString(false, false, true, 1));
			} else if (expression.charAt(i) == '<') {
				result.append(getRandomString(false, true, false, 1));
			} else if (expression.charAt(i) == '$') {
				result.append(getRandomString(false, true, true, 1));
			} else if (expression.charAt(i) == ']') {
				result.append(getRandomString(true, true, false, 1));
			} else if (expression.charAt(i) == '[') {
				result.append(getRandomString(true, false, true, 1));
			} else {
				throw new RuntimeException("Invalid expression : " + expression);
			}
		}

		return result.toString();
	}

	/**
	 * Generate several random strings at once.
	 * 
	 * @param numeric
	 *            true if you want possibility of numeric chars to appear in the final string and
	 *            false otherwise
	 * @param lowercase
	 *            true if you want possibility of lowercase chars to appear in the final string and
	 *            false otherwise
	 * @param uppercase
	 *            true if you want possibility of uppercase chars to appear in the final string and
	 *            false otherwise
	 * @param stringLength
	 *            length of string
	 * @param numOfStrings
	 *            number of strings required
	 * @return Array of randomly generated strings
	 */
	public static String[] getRandomString(boolean numeric, boolean lowercase, boolean uppercase, int stringLength, int numOfStrings) {
		String[] result = new String[numOfStrings];

		for (int i = 0; i < numOfStrings; i++) {
			result[i] = getRandomString(numeric, lowercase, uppercase, stringLength);
		}
		return result;
	}

	public static String getRandomString(boolean numeric, boolean lowercase, boolean uppercase, int stringLength) throws NullPointerException {
		if (!numeric && !lowercase && !uppercase) {
			throw new RuntimeException("Zero length string cannot be generated!");
		}
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < stringLength; i++) {
			result.append(getRandomChar(numeric, lowercase, uppercase));
		}

		return result.toString();
	}

	public static char getRandomChar(boolean numeric, boolean lowercase, boolean uppercase) {
		if (!numeric && !lowercase && !uppercase) {
			throw new RuntimeException("Zero length char cannot be generated!");
		}

		int totalSetSize = getTotalSet(numeric, lowercase, uppercase);
		char[] charArray = initCharArray(numeric, lowercase, uppercase, totalSetSize);

		return charArray[(int) (Math.random() * totalSetSize)];
	}

	private static char[] initCharArray(boolean numeric, boolean lowercase, boolean uppercase, int totalSetSize) {
		char[] result = new char[totalSetSize];
		int lastIndex = 0;
		int i = 0;
		if (numeric) {
			i = 0;
			for (i = 0; i < 10; i++) {
				result[i] = (char) (48 + i - lastIndex);
			}
			lastIndex = i;
		}
		if (lowercase) {
			i = lastIndex;
			for (; i < 26 + lastIndex; i++) {
				result[i] = (char) (97 + i - lastIndex);
			}
			lastIndex += 26;
		}
		if (uppercase) {
			i = lastIndex;
			for (; i < 26 + lastIndex; i++) {
				result[i] = (char) (65 + i - lastIndex);
			}
			lastIndex += 26;
		}
		return result;
	}

	private static int getTotalSet(boolean numeric, boolean lowercase, boolean uppercase) {
		int result = 0;

		if (numeric) {
			result += NUM_OF_NUMERIC;
		}
		if (lowercase) {
			result += NUM_OF_LOWERCASE;
		}
		if (uppercase) {
			result += NUM_OF_UPPERCASE;
		}
		return result;
	}
}
