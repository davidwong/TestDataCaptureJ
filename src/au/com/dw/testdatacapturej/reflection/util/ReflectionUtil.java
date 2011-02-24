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
package au.com.dw.testdatacapturej.reflection.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.apache.commons.lang.WordUtils;

import au.com.dw.testdatacapturej.builder.LineBuilder;
import au.com.dw.testdatacapturej.log.FormatConstants;



/**
 * Utility methods for using reflection to determine the fields and methods of the object to be logged.
 * 
 * @author David Wong
 *
 */
public class ReflectionUtil {
	
	// default field names for arguments for which we don't know the field name, note that to make
	// the field names unique, should add some prefix or suffix (e.g. an index number)
	public static final String ARGUMENT_OBJECT_FIELD_NAME = "argumentObject";
	public static final String ARGUMENT_ARRAY_FIELD_NAME = "argumentArray";	
	public static final String ARGUMENT_COLLECTION_FIELD_NAME = "argumentCollection";
	public static final String ARGUMENT_MAP_FIELD_NAME = "argumentMap";
	
	/**
	 * Check if the class of an object has a default no parameter constructor.
	 * 
	 * @param object
	 * @return
	 */
	public static boolean hasDefaultConstructor(Object object)
	{
		return hasDefaultConstructor(object.getClass());
	}
	
	/**
	 * Check if a class has a default no parameter constructor.
	 * 
	 * @param clazz
	 * @return
	 */
	public static boolean hasDefaultConstructor(Class<?> clazz)
	{
		boolean hasDefaultConstructor = false;
		
		try {
			Constructor<?> constructor = clazz.getDeclaredConstructor();
			if (constructor != null)
			{
				hasDefaultConstructor = true;
			}
		} catch (NoSuchMethodException nsme) {
			// no need to do anything, since this is what we are checking for
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return hasDefaultConstructor;
	}

	/**
	 * Check if an object has a setter method.
	 * 
	 * @param object
	 * @param fieldName
	 * @param parameter
	 * @return
	 */
	public static boolean hasSetterMethod(Object object, String fieldName, Object parameter)
	{
		boolean hasSetterMethod = false;
		
		if (object != null && parameter != null)
		{
			hasSetterMethod = hasSetterMethod(object.getClass(), fieldName, parameter);
		}
		return hasSetterMethod;
	}

	/**
	 * Check if a class has a default setter method for a field. Will also try the superclasses and implemented interfaces for the
	 * setter method parameter signature.
	 * 
	 * @param clazz
	 * @param fieldName
	 * @param parameter
	 * @return
	 */
	public static boolean hasSetterMethod(Class<?> clazz, String fieldName, Object parameter)
	{
		boolean hasSetterMethod = false;
		
		LineBuilder styleUtil = new LineBuilder();
		Class<?> parameterClass = parameter.getClass();
		String methodName = styleUtil.getSetterMethodName(fieldName);
		
		// try to find the setter with the specific parameter class
		hasSetterMethod = hasSetterMethodForParameterClass(clazz, methodName, parameterClass);
		
		// try to find setter that has superclass of the parameter, going through the inheritance chain
		if (!hasSetterMethod)
		{
			hasSetterMethod = hasSetterMethodForSuperClass(clazz, methodName, parameterClass);
		}
		
		// try to find setter that implemented interface of the parameter
		if (!hasSetterMethod)
		{
		    hasSetterMethod = hasSetterMethodForInterfaces(clazz, methodName, parameterClass);
		}
		
		return hasSetterMethod;
	}

	/**
	 * Check if a class has a setter method with the parameter signature for the interface that may be implemented by a field.
	 * e.g. instead of 'class.setField(class type)', would look for 'class.setField(interface)'
	 * 
	 * @param clazz
	 * @param methodName
	 * @param implementationClass
	 * @return
	 */
	private static boolean hasSetterMethodForInterfaces(Class<?> clazz, String methodName, Class<?> implementationClass) {
		boolean hasSetterMethodForInterfaces = false;
		
		Class<?>[] theInterfaces = implementationClass.getInterfaces();
		for (int i = 0; i < theInterfaces.length; i++) {
		  hasSetterMethodForInterfaces = hasSetterMethodForParameterClass(clazz, methodName, theInterfaces[i]);
		  if (hasSetterMethodForInterfaces)
		  {
			  break;
		  }
		  else
		  {
			  // try to find setter in super-interfaces, note that getSuperClass() doesn't return super interfaces
			  // due to possible multiple inheritance for interfaces
			  hasSetterMethodForInterfaces = hasSetterMethodForInterfaces(clazz, methodName, theInterfaces[i]);
			  if (hasSetterMethodForInterfaces)
			  {
				  break;
			  }
		  }
		}
		return hasSetterMethodForInterfaces;
	}

	/**
	 * Check if a class has a setter method with parameter signature for the superclass for a field.
	 * 
	 * @param clazz
	 * @param methodName
	 * @param subclass
	 * @return
	 */
	private static boolean hasSetterMethodForSuperClass(Class<?> clazz, String methodName, Class<?> subclass) {
		boolean hasSetterMethodForSuperClass = false;
		
		Class<?> superclass = subclass.getSuperclass();
		while (superclass != null) {
			hasSetterMethodForSuperClass = hasSetterMethodForParameterClass(clazz, methodName, superclass);
		  if (hasSetterMethodForSuperClass)
			  break;
		  
		  // handle next superclass in chain or super interfaces
		  if (superclass.isInterface()){
			  hasSetterMethodForSuperClass = hasSetterMethodForInterfaces(clazz, methodName, superclass);

			  // need to break out of the loop
			  superclass = null;			  
		  }
		  else
		  {
			  // handle interfaces for superclass
			  hasSetterMethodForSuperClass = hasSetterMethodForInterfaces(clazz, methodName, superclass);
			  if (!hasSetterMethodForSuperClass)
			  {
				  subclass = superclass;
				  superclass = subclass.getSuperclass();
			  }
			  else
			  {
				  superclass = null;
			  }
		  }
		}
		return hasSetterMethodForSuperClass;
	}

	/**
	 * Check if a class has a default setter method for a field, using the specific class of the field only.
	 * i.e. no superclasses or interfaces.
	 * 
	 * Since we may want to invoke the setter method, abstract methods are not included (i.e. will return
	 * false).
	 *  
	 * @param clazz
	 * @param parameterClass
	 * @param setterMethodName
	 * @return
	 */
	private static boolean hasSetterMethodForParameterClass(Class<?> clazz, String setterMethodName, Class<?> parameterClass) {
		boolean hasSetterMethodForParameterClass = false;
		
		try {		
			Method method = clazz.getDeclaredMethod(setterMethodName, parameterClass);
			if (method != null)
			{
				int mod = method.getModifiers();
	            // ignore abstract methods
	            if (!Modifier.isAbstract(mod))
	            {
	            	hasSetterMethodForParameterClass = true;
	            }
			}
		} catch (NoSuchMethodException nsme) {
			// no need to do anything, since this is what we are checking for
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hasSetterMethodForParameterClass;
	}
    
    /**
     * Using reflection to get the class name will not work properly on arrays, it will return something like:
     * 
     * Array of String's
     * [Ljava.lang.String;
     * 
     * Array of primitive int's
     * [I
     * 
     * Array of Object's
     * [Ljava.lang.Object;
     * 
     * Some we need to do some special handling to get it into the format we want for test data generation:
     * 
     * java.lang.String[]
     * int[]
     * java.lang.Object[]
     * 
     * @param array Should be an array
     * @return
     */
    public static String getArrayClassName(Object array)
    {
    	// sanity check that it is an array
    	if (array.getClass().isArray())
    	{
    		Class<?> clazz = array.getClass();
    		Class<?> stringArrayComponentType = clazz.getComponentType();
    				
    		return stringArrayComponentType.getName() + FormatConstants.arraySuffix;
    	}
    	else
    	{
    		// shouldn't get here, but just return normal class name for non-arrays
    		return array.getClass().getName();
    	}
    }
    
    /**
     * Get the class name from a reflection Field, used when object isn't available, e.g. null value.
     * 
     * @param field
     * @return
     */
    public static String getClassNameFromField(Field field)
    {
    	return field.getType().getName();
    }
    
	/**
	 * Generate a field name fragment for the class of an object by extracting the non-qualified
	 * class name of the object.
	 * 
	 * If the object's class is:
	 *   com.test.TestClass
	 * Then the field name fragment would be:
	 *   testClass
	 *   
	 * This fragment is meant to be used as the basis for building the full field name.
	 * 
	 * @param object
	 * @return
	 */
	public static String getObjectFieldName(Object object)
	{
		String className = object.getClass().getName();
		String fieldName = WordUtils.uncapitalize(className.substring(className.lastIndexOf(".")+1));	
		
		return fieldName;
	}
	
	/**
	 * Generate a field name fragment for the class of an object by extracting the non-qualified
	 * class name.
	 * 
	 * If the class is:
	 *   com.test.TestClass
	 * Then the field name fragment would be:
	 *   testClass
	 *   
	 * This fragment is meant to be used as the basis for building the full field name.
	 * 
	 * @param clazz
	 * @return
	 */
	public static String getObjectFieldName(Class<?> clazz)
	{
		String className = clazz.getName();
		String fieldName = WordUtils.uncapitalize(className.substring(className.lastIndexOf(".")+1));	
		
		return fieldName;
	}

}
