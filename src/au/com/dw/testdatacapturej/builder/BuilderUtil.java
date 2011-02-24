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
package au.com.dw.testdatacapturej.builder;

import org.apache.commons.lang.WordUtils;

import au.com.dw.testdatacapturej.reflection.util.ReflectionUtil;

/**
 * Utility class for building strings to be used in the generated log.
 * 
 * @author David Wong
 *
 */
public class BuilderUtil {

	/**
	 * Create a field name to be used in the generated log for code that is creating an object, e.g.
	 * in a construction line.
	 * 
	 * An example -
	 * We want to generate a line in the log to create an object of the class org.test.Item, a
	 * constructor line may be generated something like this:
	 *   org.test.Item [field name]0 = new org.test.Item();
	 * This method is responsible for creating the [field name] portion of that line, note that
	 * it does not include the numerical index suffix which is generated elsewhere.
	 * e.g.
	 *   org.test.Item item0 = new org.test.Item();
	 * Here the method has created the field name fragment 'item'.
	 *   
	 * @param object The object for which you want to create the field name
	 * @param fieldNamePrefix Any prefix to add to the field name
	 * @param fieldNameSuffix Any suffix to add to the field name (not the index number)
	 * @return The field name to be used for the object in the generated log, without the numerical index
	 */
	public static String createClassFieldName(Object object, String fieldNamePrefix, String fieldNameSuffix) {
		
		// derive the class field name
		String fieldName = ReflectionUtil.getObjectFieldName(object);

		StringBuilder fieldNameBuilder = new StringBuilder();
		
		if (fieldNamePrefix != null)
		{
			fieldNameBuilder.append(fieldNamePrefix);
		}		
		fieldNameBuilder.append(fieldName);
		if (fieldNameSuffix != null)
		{
			fieldNameBuilder.append(fieldNameSuffix);
		}
		
		return fieldNameBuilder.toString();
	}

	/**
	 * Create a field name to be used in the generated log for code that is creating an array, e.g.
	 * in a construction line.
	 * 
	 * An example -
	 * We want to generate a line in the log to create an array of the class org.test.Item, a
	 * constructor line may be generated something like this:
	 *   org.test.Item[] [field name]0 = new org.test.Item[5];
	 * This method is responsible for creating the [field name] portion of that line, note that
	 * it does not include the numerical index suffix which is generated elsewhere.
	 * e.g.
	 *   org.test.Item[] itemArray0 = new org.test.Item[5];
	 * Here the method has created the field name fragment 'itemArray'.
	 *   
	 * @param arrayType The fully qualified class name of the array type
	 * @param fieldNamePrefix Any prefix to add to the field name
	 * @param fieldNameSuffix Any suffix to add to the field name (not the index number)
	 * @return The field name to be used for the array in the generated log, without the numerical index
	 */
	public static String createArrayClassFieldName(String arrayType, String fieldNamePrefix, String fieldNameSuffix) {
		String arrayFieldNameSuffix = "Array";
		String objectType = arrayType;
		
		StringBuilder fieldNameBuilder = new StringBuilder();
		
		// derive the class field name portion
		if (fieldNamePrefix != null)
		{
			fieldNameBuilder.append(fieldNamePrefix);
			objectType = WordUtils.capitalize(objectType.substring(objectType.lastIndexOf(".")+1));	
		}	
		else
		{
			objectType = WordUtils.uncapitalize(objectType.substring(objectType.lastIndexOf(".")+1));				
		}
		fieldNameBuilder.append(objectType);
		fieldNameBuilder.append(arrayFieldNameSuffix);
		if (fieldNameSuffix != null)
		{
			fieldNameBuilder.append(fieldNameSuffix);
		}
		
		return fieldNameBuilder.toString();
	}

}
