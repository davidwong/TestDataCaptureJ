/*******************************************************************************
 * Copyright () 2009, 2011 David Wong
 *
 * This file is part of TestDataCaptureJ.
 *
 * TestDataCaptureJ is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * TestDataCaptureJ is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Afferro General Public License for more details.
 *
 * You should have received a copy of the GNU Afferro General Public License
 * along with TestDataCaptureJ.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package au.com.dw.testing;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.SystemUtils;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;

/**
 * Additional asserts for use with JUnit.
 *
 */
public class AssertUtil {
	/**
	 * Asserts that two objects are equal. If they are not, an
	 * {@link AssertionError} is thrown with the given message. If
	 * <code>expected</code> and <code>actual</code> are <code>null</code>,
	 * they are considered equal.
	 * 
	 * Allows a collection of expected values instead of just one. Will pass if matches against
	 * any one of the objects in the collection.
	 * 
	 * @param message
	 *            the identifying message for the {@link AssertionError} (<code>null</code>
	 *            okay)
	 * @param expected
	 *            collection of expected values
	 * @param actual
	 *            actual value
	 */
	public static void assertEqualsAny(String message, Collection<?> expected,
			Object actual) {
		for (Object expectedObject : expected)
		{
			if (expectedObject == null && actual == null)
			{
				return;
			}
			if (expectedObject != null && isEquals(expectedObject, actual))
				return;
			
		}
		failNotEqualsAny(message, expected, actual);
	}

	/**
	 * Asserts that two objects are not equal. If they are, an
	 * {@link AssertionError} without a message is thrown. If
	 * <code>expected</code> and <code>actual</code> are <code>null</code>,
	 * they are considered equal.
	 * 
	 * Allows a collection of expected values instead of just one. Will fail if matches against
	 * any one of the objects in the collection.
	 * 
	 * @param message
	 *            the identifying message for the {@link AssertionError} (<code>null</code>
	 *            okay)
	 * @param expected
	 *            collection of expected values
	 * @param actual
	 *            actual value
	 */
	public static void assertNotEqualsAny(String message, Collection<?> expected,
			Object actual) {
		for (Object expectedObject : expected)
		{
			if (expectedObject == null && actual == null)
			{
				failNotEqualsAny(message, expected, actual);
			}
			if (expectedObject != null && isEquals(expectedObject, actual))
			{
				failNotEqualsAny(message, expected, actual);
			}
		}
	}
	
	/**
	 * Check for equality.
	 * 
	 * @param expected
	 * @param actual
	 * @return
	 */
	private static boolean isEquals(Object expected, Object actual) {
		return expected.equals(actual);
	}

	/**
	 * Check for presence of value in collection.
	 * 
	 * @param message
	 * @param expected
	 * @param actual
	 */
	private static void failNotEqualsAny(String message, Collection<?> expected,
			Object actual) {
		for (Object expectedObject : expected)
		{
			failNotEquals(message, expectedObject, actual);
		}
	}

	/**
	 * Check for non-equality.
	 * 
	 * @param message
	 * @param expected
	 * @param actual
	 */
	private static void failNotEquals(String message, Object expected,
			Object actual) {
		Assert.fail(format(message, expected, actual));
	}

	/**
	 * Formatting of error message.
	 * 
	 * @param message
	 * @param expected
	 * @param actual
	 * @return
	 */
	private static String format(String message, Object expected, Object actual) {
		String formatted= "";
		if (message != null && !message.equals(""))
			formatted= message + " ";
		String expectedString= String.valueOf(expected);
		String actualString= String.valueOf(actual);
		if (expectedString.equals(actualString))
			return formatted + "expected: "
					+ formatClassAndValue(expected, expectedString)
					+ " but was: " + formatClassAndValue(actual, actualString);
		else
			return formatted + "expected:<" + expectedString + "> but was:<"
					+ actualString + ">";
	}
	
	/**
	 * Formatting of class and value for error message.
	 * 
	 * @param value
	 * @param valueString
	 * @return
	 */
	private static String formatClassAndValue(Object value, String valueString) {
		String className= value == null ? "null" : value.getClass().getName();
		return className + "<" + valueString + ">";
	}
	
	/**
	 * Asserts that two objects are equal. If they are not, an
	 * {@link AssertionError} without a message is thrown. If
	 * <code>expected</code> and <code>actual</code> are <code>null</code>,
	 * they are considered equal.
	 * 
	 * @param expected
	 *            collection of expected values
	 * @param actual
	 *            the value to check against <code>expected</code>
	 */
	public static void assertEqualsAny(Collection<?> expected, Object actual) {
		assertEqualsAny(null, expected, actual);
	}
	
	/**
	 * Asserts that two objects are not equal. If they are, an
	 * {@link AssertionError} without a message is thrown. If
	 * <code>expected</code> and <code>actual</code> are <code>null</code>,
	 * they are considered equal.
	 * 
	 * @param expected
	 *            collection of expected values
	 * @param actual
	 *            the value to check against <code>expected</code>
	 */
	public static void assertNotEqualsAny(Collection<?> expected, Object actual) {
		assertNotEqualsAny(null, expected, actual);
	}	
	
	/**
	 * Version of assertEquals() for comparing strings with the new line formatting
	 * removed.
	 * 
	 * This should make the comparison of generated logs more robust since don't have to
	 * worry about changes in new line formatting.
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertEqualsWithoutFormatting(String expected, String actual)
	{
		String expectedWithoutFormatting = null;
		String actualWithoutFormatting = null;
		
		if (expected != null)
		{
			expectedWithoutFormatting = expected.replaceAll(SystemUtils.LINE_SEPARATOR, "");
		}
		if (actual != null)
		{
			actualWithoutFormatting = actual.replaceAll(SystemUtils.LINE_SEPARATOR, "");
		}
		assertEquals(expectedWithoutFormatting, actualWithoutFormatting);
	}

	/**
	 * Version of assertEqualsAny() for comparing strings with the new line formatting
	 * removed.
	 * 
	 * This should make the comparison of generated logs more robust since don't have to
	 * worry about changes in new line formatting.
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertEqualsAnyWithoutFormatting(String message, Collection<String> expected,
			String actual)
	{
		Collection<String> expectedWithoutFormatting = new ArrayList<String>();
		String actualWithoutFormatting = null;
		
		if (expected != null)
		{
			for (String expectedElement : expected)
			{
				expectedWithoutFormatting.add(expectedElement.replaceAll(SystemUtils.LINE_SEPARATOR, ""));
			}
		}
		if (actual != null)
		{
			actualWithoutFormatting = actual.replaceAll(SystemUtils.LINE_SEPARATOR, "");
		}
		assertEqualsAny(message, expectedWithoutFormatting, actualWithoutFormatting);
	}
	
	/**
	 * Version of assertEqualsAny() for comparing strings with the new line formatting
	 * removed.
	 * 
	 * This should make the comparison of generated logs more robust since don't have to
	 * worry about changes in new line formatting.
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertEqualsAnyWithoutFormatting(Collection<String> expected,
			String actual)
	{
		assertEqualsAnyWithoutFormatting(null, expected, actual);
	}
}
