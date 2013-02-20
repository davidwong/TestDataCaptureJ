/*******************************************************************************
 * Copyright () 2009, 2011, 2012 David Wong
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
package au.com.dw.testdatacapturej.config;

import java.util.ArrayList;
import java.util.List;

import au.com.dw.testdatacapturej.meta.ObjectInfo;
import au.com.dw.testdatacapturej.util.Messages;


/**
 * Configuration utilities.
 * 
 * @author David Wong
 *
 */
public class ConfigUtil {

	/**
	 * Check configuration for constructor mapping to override using the default constructor.
	 * If found, will be used regardless of whether the default constructor exists or not.
	 * 
	 * Note: currently doesn't check that object that the particular constructor with that parameter field
	 * list actually exists.
	 * i.e.
	 * Object is configured with parameter field1 for the constructor, what is the only constructor for the
	 * real object is for field1 and field2?
	 * 
	 * @param info
	 * @return List of parameter field names if found, else empty list
	 */
	public List<String> getConstructionParameters(ObjectInfo info)
	{
		Configuration config = Configuration.getConfiguration();
		List<String> emptyList = new ArrayList<String>();
		
		// get the parameter field name list
		List<String> paramFieldNames = config.getConstructor(info.getClassName());
		
		if (paramFieldNames != null)
		{
			// do a check that the parameters in the list exist in the field list
			List<String> nonExistentParams = checkConstructorParametersForNonExistent(info, paramFieldNames);
			
			if (nonExistentParams.isEmpty())
			{
				// if all the parameters are found in the field list, return the parameter list
				return paramFieldNames;
			}
			else
			{
				// If there is a problem with the parameter list, return empty list to indicate no to use the
				// parameter list, i.e. just use the default constructor instead
				
				info.addError(Messages.getMessage(Messages.CONSTRUCTOR_PARAM_FIELD_NAME_NOT_FOUND) + nonExistentParams);
				
				return emptyList;
			}
		}
		else
		{
			return emptyList;
		}
	}
	
	/**
	 * Check the parameter field name list against the field list for parameter field names that don't have
	 * a matching field.
	 * i.e. a field with that field name does not exist.
	 * 
	 * @param info
	 * @param parameters
	 * @return
	 */
	private List<String> checkConstructorParametersForNonExistent(ObjectInfo info, List<String> parameters)
	{
		List<String> nonExistentParams = new ArrayList<String>();
		
		for (String param : parameters)
		{
			if (info.getField(param) == null)
			{
				nonExistentParams.add(param);
			}
		}
		
		return nonExistentParams;
	}

	/**
	 * Check configuration for setter mapping to override generating a setter method with default naming.
	 * 
	 * Note: currently doesn't check that object that the particular default setter method actually exists
	 * 
	 * @param info
	 * @return List of field names to ignore for setter line generation, if found, else empty list
	 */
	public List<String> getIgnoredSetters(ObjectInfo info)
	{
		Configuration config = Configuration.getConfiguration();
		
		// get the parameter field name list
		List<String> ignoredSetterFieldNames = config.getIgnoredSetter(info.getClassName());
		
		if (ignoredSetterFieldNames != null)
		{
			return ignoredSetterFieldNames;
		}
		else
		{
			List<String> emptyList = new ArrayList<String>();
			return emptyList;
		}
	}
	
	/**
	 * Check configuration for classes that have collection fields that are only accessed through
	 * adder methods.
	 * 
	 * @param info
	 * @return List of collection field names and the adder method names to access them, if found, else empty list
	 */
	public List<CollectionAdderConfig> getAddedCollections(ObjectInfo info)
	{
		Configuration config = Configuration.getConfiguration();
		
		// get the collection field name and adder method name list
		List<CollectionAdderConfig> collectionConfigs = config.getAdderCollection(info.getClassName());
		
		if (collectionConfigs != null)
		{
			return collectionConfigs;
		}
		else
		{
			List<CollectionAdderConfig> emptyList = new ArrayList<CollectionAdderConfig>();
			return emptyList;
		}
	}
	
	/**
	 * Utility method to find the CollectionAdderConfig that matches a collection field name.
	 * 
	 * @param collectionConfigs
	 * @param fieldName
	 * @return Null if no match is found.
	 */
	public CollectionAdderConfig getCollectionAdderConfig(List<CollectionAdderConfig> collectionConfigs, String fieldName)
	{
		for (CollectionAdderConfig collectionConfig : collectionConfigs)
		{
			if (collectionConfig.getFieldName().equals(fieldName))
			{
				return collectionConfig;
			}
		}
		
		// not found
		return null;
	}
	
	
	/**
	 * Converts a list of objects retrieved from the Configuration to a list of strings.
	 * 
	 * @param objList
	 * @return
	 */
	public List<String> toStringList(List<Object> objList)
	{
		List<String> stringList = new ArrayList<String>();
		
		if (objList != null)
		{
			for (Object obj : objList)
			{
				stringList.add((String)obj);
			}
		}
		return stringList;
	}
}
