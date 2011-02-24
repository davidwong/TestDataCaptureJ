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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for storing the numerical indices that are added to generated class field names
 * to prevent duplicate field names, e.g. if there are several fields that are HashMaps then we would want
 * unique field names for each of them such as hashMap0, hashMap1, hashMap2, etc.
 * 
 * @author David Wong
 *
 */
public class FieldNameIndex {

	private final Map<String, Integer> nameIndex = Collections.synchronizedMap(new HashMap<String, Integer>());
	
	/**
	 * Get the index for a class to use as the suffix for the field name of that class when the field
	 * name is generated. This is to ensure the field name is unique to prevent name clashes.
	 * 
	 * Note the the field name passed in the parameter is usually the non-qualified class name, with only the name of
	 * the class without the package name portion. This is to ensure that if we have 2 classes with the
	 * same name, but from different packages, that the field names for them will still have different
	 * indices to use as the suffixes.
	 * e.g.
	 * If we have the classes:
	 *   org.Item and org.test.Item
	 * and the field name parameter passed in is:
	 *   item
	 * then we would want the field names for those to be:
	 *   item0 and item1
	 * even though they are actually different classes, because the field name generation only
	 * uses the class name portion and ignores the packages.
	 * 
	 * @param fieldName Usually the non-qualified class name, i.e. no package names
	 * @return
	 */
	public synchronized Integer getAndIncrementIndexForField(String fieldName)
	{
		Integer initialIndex = new Integer(0);
		
		Integer nextIndex = nameIndex.get(fieldName);
		if (nextIndex == null)
		{
			nameIndex.put(fieldName, new Integer(initialIndex + 1));			
		}
		else
		{
			initialIndex = nextIndex;
			nameIndex.put(fieldName, new Integer(nextIndex + 1));
		}
		return initialIndex;
	}
	
	/**
	 * Clear all of the indices, so that next time an index is retrieved it will start
	 * again from the initial value.
	 */
	public synchronized void resetIndices()
	{
		nameIndex.clear();
	}
}
