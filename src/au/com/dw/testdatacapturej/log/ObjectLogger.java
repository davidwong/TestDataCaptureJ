/*******************************************************************************
 * Copyright () 2009, 2011, 2013 David Wong
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
package au.com.dw.testdatacapturej.log;

import java.util.List;

import au.com.dw.testdatacapturej.builder.FieldNameIndex;
import au.com.dw.testdatacapturej.log.gen.JavaOutputGeneratorFactory;
import au.com.dw.testdatacapturej.log.gen.ParameterGenerator;
import au.com.dw.testdatacapturej.meta.ContainmentType;
import au.com.dw.testdatacapturej.meta.ObjectInfo;
import au.com.dw.testdatacapturej.meta.ObjectType;

/**
 * Logging for the test code generation. Note that the actually logging itself is delegated to FieldDisplay
 * classes for the various types of objects.
 * 
 * @author David Wong
 *
 */
public class ObjectLogger {

	/** The generation of the numerical index is delegated to the FieldNameIndex */
	private final FieldNameIndex nameIndex = new FieldNameIndex();
	
	private OutputGeneratorFactory outputGeneratorFactory;
	
	public ObjectLogger()
	{
		outputGeneratorFactory = new JavaOutputGeneratorFactory();
	}
	
	/**
	 * Log an object that is passed as a parameter to a method, can also be used to log return values. This doesn't
	 * log the object directly, but the ObjectInfo metadata holder that was created from the object beforehand.
	 * 
	 * The actual logging is done by a FieldDisplay implementation in this basic structure:
	 * 
	 * 1. FieldDisplay.preLog()
	 * 
	 * 2. recursively call logObject() on all child objects
	 * 
	 * 3. FieldDisplay.Log()
	 * 
	 * NOTE:
	 * May need to call resetNameCounters() after this method has run from the caller of logObject(). Shouldn't
	 * call it at the end of this method since it is recursive.
	 * 
	 * @param builder StringBuilder to hold the log.
	 * @param info The Object metadata to use for the logging.
	 */
	public void logObject(StringBuilder builder, ObjectInfo info)
	{
		if (!info.isAlreadyLogged())
		{
			// get the appropriate FieldDisplay for the type of object
			FieldGenerator fieldDisplay = getFieldDisplayForType(info.getType(), info.getContainmentType());
	
			// maps require different handling to process both the key and the value in the correct order, the
			// presence of the keyInfo field indicates that the object is a map entry
			ObjectInfo keyInfo = info.getKeyInfo();
	
			// start the logging ...
			
			// log the key for a map first, if it exists
			if (keyInfo != null)
			{
				// log construction parameters for the key
				logParameters(builder, keyInfo);
				
				setClassNameIndex(keyInfo);
				builder.append(fieldDisplay.preLog(keyInfo));
				
				for (ObjectInfo fieldInfo : keyInfo.getFieldList())
				{
					logObject(builder, fieldInfo);
				}
			}
			// log construction parameters for the value
			logParameters(builder, info);
			
			// get the appropriate numerical suffix for creating the class field name(s)
			setClassNameIndex(info);
			// log the object or map value
			builder.append(fieldDisplay.preLog(info));
			
			// log child objects, e.g. fields, contained elements, etc
			for (ObjectInfo fieldInfo : info.getFieldList())
			{
				logObject(builder, fieldInfo);
			}
			
			builder.append(fieldDisplay.log(info));
		}
	}

	/**
	 * Log any construction parameters for objects that need to have a parameterized constructor
	 * line generated, instead of the default no-parameter constructor.
	 * 
	 * @param builder StringBuilder to hold the log.
	 * @param info The Object metadata to use for the logging.
	 */
	private void logParameters(StringBuilder builder, ObjectInfo info)
	{
		List<String> paramFieldNames = info.getConstructorInfo().getConstructorParamFieldNames();
		if (!paramFieldNames.isEmpty())
		{
			FieldGenerator fieldDisplay = new ParameterGenerator();
			
			for (String paramFieldName : paramFieldNames)
			{
				// this should always return a value, since the parameter field name list should already
				// have been checked against the field list
				ObjectInfo paramFieldInfo = info.getField(paramFieldName);

				// log construction parameters for the parameter
				logParameters(builder, paramFieldInfo);
				
				setClassNameIndex(paramFieldInfo);
				builder.append(fieldDisplay.preLog(paramFieldInfo));
				
				for (ObjectInfo fieldInfo : paramFieldInfo.getFieldList())
				{
					logObject(builder, fieldInfo);
				}
				
				builder.append(fieldDisplay.log(paramFieldInfo));
				
				// mark the object as already logged
				paramFieldInfo.setAlreadyLogged(true);
			}
		}
	}
	
	/**
	 * Set the numerical suffix to append to that class field name for the object. This should be done before
	 * the object is constructed.
	 * 
	 * @param info
	 */
	private void setClassNameIndex(ObjectInfo info)
	{
		int nameIndex = getFieldNameIndexForField(info.getClassFieldName());
		info.setClassFieldNameIndex(nameIndex);
	}
	
	/**
	 * Get the next counter for the field name of a particular type to use to append to that class field name.
	 * 
	 * @param fieldName The field name fragment, without any numerical index
	 * @return The numerical index to be used to append to the field name
	 */
	private int getFieldNameIndexForField(String fieldName)
	{
		return nameIndex.getAndIncrementIndexForField(fieldName);
	}
	
	/**
	 * Get the correct implementation of FieldDisplay for the type of object. The FieldDisplay will be
	 * responsible for the actual generation of the test code.
	 * 
	 * @param type The ObjectType enum for the object
	 * @param containmentType The ContainmentType enum, if the object is an element in an array or collection, etc
	 * @return
	 */
	private FieldGenerator getFieldDisplayForType(ObjectType type, ContainmentType containmentType)
	{
		FieldGenerator fieldDisplay;
		
		// need to check whether the object is an element in a container or just a field
		
		if (containmentType == ContainmentType.NONE || containmentType == ContainmentType.FIELD)
		{
			switch (type)
			{
				case ARRAY:
					fieldDisplay = outputGeneratorFactory.getArrayFieldGenerator();
					break;
					
				case COLLECTION:
					fieldDisplay = outputGeneratorFactory.getCollectionFieldGenerator();
					break;
		
				case MAP:
					fieldDisplay = outputGeneratorFactory.getMapFieldGenerator();
					break;
	
				case SIMPLE:
					fieldDisplay = outputGeneratorFactory.getSimpleFieldGenerator();
					break;
				
				case OBJECT:
				default:
					fieldDisplay = outputGeneratorFactory.getClassFieldGenerator();
					break;
			}
		}
		else
		{
			switch (containmentType)
			{
				case COLLECTION_ELEMENT:
					fieldDisplay = outputGeneratorFactory.getCollectionElementGenerator();
					break;
	
				case ARRAY_ELEMENT:
					fieldDisplay = outputGeneratorFactory.getArrayElementGenerator();
					break;
	
				case MAP_ENTRY:
					fieldDisplay = outputGeneratorFactory.getMapEntryGenerator();
					break;

				case ADDED_COLLECTION_ELEMENT:
					fieldDisplay = outputGeneratorFactory.getAddedElementGenerator();
					break;

				default:
					// shouldn't fall through here
					fieldDisplay = outputGeneratorFactory.getClassFieldGenerator();
					break;
			}
		}
		
		return fieldDisplay;
	}

	/**
	 * Reset the numerical counters used as field name suffixes in the log for generated class field names
	 * This should be done after the logging of each initial object, but may need to be called outside of this
	 * class instead of at the end of logObject() since logObject() is recursive.
	 */
	public void resetNameCounters()
	{
		nameIndex.resetIndices();
	}
}
