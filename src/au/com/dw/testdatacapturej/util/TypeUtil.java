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
package au.com.dw.testdatacapturej.util;

/**
 * Utility to determine the types of fields.
 * 
 * @author David Wong
 *
 */
public class TypeUtil {
	
	public static boolean isArray(Object object)
	{
		if (object == null)
			return false;

		if (object instanceof long[] || 
				object instanceof int[] || 
				object instanceof short[] || 
				object instanceof byte[] ||
				object instanceof char[] ||
				object instanceof double[] ||
				object instanceof float[] ||
				object instanceof boolean[]) {
			return true;
        }
		else
		{
			Class<?> clazz = object.getClass();
			return clazz.isArray();
		}
	}

	public static boolean isCollection(Object object)
	{
		if (object == null)
			return false;
		
		return object instanceof java.util.Collection<?>;
	}
	
	public static boolean isMap(Object object)
	{
		if (object == null)
			return false;
		
		return object instanceof java.util.Map<?,?>;
	}
	
	/**
	 * Check for java built-in classes.
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isJavaClass(Object object)
	{
		if (object == null)
			return false;
		
		String className = object.getClass().getName();
		return className.startsWith("java.lang");
	}
	
	/**
	 * Check if the given class represents an array of primitives,
	 * i.e. boolean, byte, char, short, int, long, float, or double.
	 * @param clazz the class to check
	 * @return whether the given class is a primitive array class
	 */
	public static boolean isPrimitiveArray(Object object) {
		if (object == null)
			return false;
		
		Class<?> clazz = object.getClass();
		return (clazz.isArray() && clazz.getComponentType().isPrimitive());
	}

}
