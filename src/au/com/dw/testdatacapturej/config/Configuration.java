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
package au.com.dw.testdatacapturej.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

public class Configuration {
	
	/** Singleton instance */
	private static Configuration config;
	
	/**
	 * Non default constructors list, used for classes that can't be constructed by using a default
	 * no-argument constructor and setters. That is either the class doesn't have a default constructor or
	 * some fields don't have setters and need to be set in the constructor.
	 * e.g. objects that are meant to be immutable
	 * 
	 * public class Immutable {
	 *   private Object immutableField;
	 *   
	 *   public Immutable(Object immutableField)
	 *   {
	 *     this.immutableField = immutableField;
	 *   }
	 *   
	 *   public Object getImmutableField()
	 *   {
	 *     return immutableField;
	 *   }
	 * }
	 * 
	 * The Map is keyed by the fully qualified class name of the object, and the list contains the field names
	 * of the parameters in order.
	 * 
	 * Note: currently does not handle extra parameters that are not fields.
	 */
	private Map<String, List<String>> constructors;
	
	/**
	 * Non-standard setter method list, used for fields of classes that are not to have the a setter method
	 * generated for the field.
	 * i.e. either no setter method is to be generated or in future dev, a setter method with an alternative
	 * name.
	 * 
	 * The Map is keyed by the fully qualified class name of the object that contains the field.
	 */
	private Map<String, List<String>> setters;

	/**
	 * Flag in the setter config file to indicate that the setter for a field should be ignored.
	 * e.g. do not generate a setter for that field
	 * 
	 * 	<setter class="java.util.Date">
	 * 	  <argument>
	 * 		<field-name>cDate</field-name>
	 * 	    <alternative>ignore</alternative>
	 * 	  </argument>
	 * 	</setter>
	 * 
	 * This indicates that for classes of java.util.Date, should not generate the line:
	 * 
	 *   date0.setCDate(..);
	 *   
	 * Currently this is not used and all configurations will be set to 'ignore'.
	 */
	static public final String SETTER_IGNORE = "ignore";

	/**
	 * List of classes that contain collection field(s) that are only accessed through adder
	 * methods.
	 * 
	 * public class ClassWithAdder {
	 *   private Collection<?> collectionField = new ArrayList<Object>();
	 *   
	 *   public ClassWithAdder()
	 *   {
	 *   }
	 *   
	 *     public void addElement(Object element)
	 *     {
	 *        collectionField.add(element);
	 *     }
	 *   }
	 * 
	 * The Map is keyed by the fully qualified class name of the object containing the collection
	 * field, and the inner map contains the collection field name and adder method name.
	 */
	private Map<String, List<CollectionAdderConfig>> adderCollections;

	private Configuration()
	{
		try {
			// read in the list of configuration file names
			org.apache.commons.configuration.Configuration propConfig = new PropertiesConfiguration("configuration.properties");
		
			String[] constructorConfigFiles = propConfig.getStringArray("constructor.config.files");
			constructors = new HashMap<String, List<String>>();
		    
		    readConstructorConfigFile(constructorConfigFiles);
		    	    
		    setters = new HashMap<String, List<String>>();
		    
			String[] setterConfigFiles = propConfig.getStringArray("setter.config.files");
		    readSetterConfigFile(setterConfigFiles);
		    
		    adderCollections = new HashMap<String, List<CollectionAdderConfig>>();
			String[] collectionConfigFiles = propConfig.getStringArray("collection.config.files");
		    readCollectionConfigFile(collectionConfigFiles);		    
		    
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static Configuration getConfiguration()
	{
		if (config == null)
			config = new Configuration();		
		return config;
	}
	
	/**
	 * Load the constructor configurations from file. 
	 * 
	 * An example of the XML structure:
	 * 
	 * <constructor-config>
	 *
	 *   <constructor class="dummy.ClassName">
	 *	   <argument>
	 *		  <field-name>paramFieldName</field-name>
	 *	   </argument>
	 *	   <argument>
	 *		  <field-name>param2FieldName</field-name>
	 *	   </argument>
	 *   </constructor>
	 *   .
	 *   .
	 *   .
	 * 
	 */
	private void readConstructorConfigFile(String[] configFileNames)
	{
		XMLConfiguration xmlConfig = null;
		
		try {
			for (String fileName : configFileNames)
			{
				xmlConfig = new XMLConfiguration(fileName);
				
				if (xmlConfig != null)
				{
					// get all the constructor nodes and iterate through them
					List<?> constructorNodes = xmlConfig.configurationsAt("constructor");
					for (Iterator<?> it = constructorNodes.iterator(); it.hasNext();)
					{
						HierarchicalConfiguration sub = (HierarchicalConfiguration) it.next();
					    // sub contains now all data about a single field
						
						String className = sub.getString("[@class]");
						
						List<String> paramFieldNames = (List<String>)sub.getList("argument.field-name");
						
						if (paramFieldNames != null && !paramFieldNames.isEmpty())
						{
							constructors.put(className, paramFieldNames);
						}
					}
				}
			}
		} catch (ConfigurationException cex) {
			cex.printStackTrace();
		}
	}
	
	/**
	 * Load the setter configurations from file. 
	 * 
	 * An example of the XML structure:
	 * 
	 * 	<setter class="dummy.Classname">
	 *	   <argument>
	 *		  <field-name>paramFieldName</field-name>
	 * 	      <alternative>ignore</alternative>
	 *	   </argument>
	 *	   <argument>
	 *		  <field-name>param2FieldName</field-name>
	 * 	      <alternative>ignore</alternative>
	 *	   </argument>
	 * 	
	 *   .
	 *   .
	 *   .
	 *   
	 *   Note that the <alternative> tag is currently unused, it is a placeholder for future dev is we
	 *   want to substitute a setter method name.
	 * 
	 */
	private void readSetterConfigFile(String[] configFileNames)
	{
		XMLConfiguration xmlConfig = null;
		
		try {
			for (String fileName : configFileNames)
			{
				xmlConfig = new XMLConfiguration(fileName);
				
				if (xmlConfig != null)
				{
					// get all the setter nodes and iterate through them
					List<?> constructorNodes = xmlConfig.configurationsAt("setter");
					for (Iterator<?> it = constructorNodes.iterator(); it.hasNext();)
					{
						HierarchicalConfiguration sub = (HierarchicalConfiguration) it.next();
					    // sub contains now all data about a single field
						
						String className = sub.getString("[@class]");
						
						List<String> paramFieldNames = (List<String>)sub.getList("field.field-name");
						
						if (paramFieldNames != null && !paramFieldNames.isEmpty())
						{				
							setters.put(className, paramFieldNames);
						}
					}
				}
			}
		} catch (ConfigurationException cex) {
			cex.printStackTrace();
		}
	}

	/**
	 * Load the collection configurations from file. 
	 * 
	 * An example of the XML structure:
	 * 
	 * <collection-config>
	 *
	 *   <container class="dummy.ClassName">
	 *	   <argument>
	 *		  <field-name>collectionFieldName</field-name>
	 *        <adder-method>adderMethodName</adder-method>
	 *	   </argument>
	 *   </container>
	 *   .
	 *   .
	 *   .
	 * 
	 */
	private void readCollectionConfigFile(String[] configFileNames)
	{
		XMLConfiguration xmlConfig = null;
		
		try {
			for (String fileName : configFileNames)
			{
				xmlConfig = new XMLConfiguration(fileName);
				
				if (xmlConfig != null)
				{
					// get all the collection nodes and iterate through them
					List<?> collectionNodes = xmlConfig.configurationsAt("container");
					for (Iterator<?> it = collectionNodes.iterator(); it.hasNext();)
					{
						HierarchicalConfiguration sub = (HierarchicalConfiguration) it.next();
					    // sub contains now all data about a single field
						
						String className = sub.getString("[@class]");
						
						List<String> collectionFieldNames = (List<String>)sub.getList("argument.field-name");
						// not sure if this is the best way to handle multiple sub elements
						List<String> adderMethodNames = (List<String>)sub.getList("argument.adder-method");
						
						// TODO need more error checking here in case of incorrect configuration
						if (collectionFieldNames != null && !collectionFieldNames.isEmpty() && adderMethodNames != null && !adderMethodNames.isEmpty())
						{
							List<CollectionAdderConfig> collectionConfigs = new ArrayList<CollectionAdderConfig>();
							for (int i = 0; i < collectionFieldNames.size(); i++)
							{
								CollectionAdderConfig collectionConfig = new CollectionAdderConfig();
								collectionConfig.setFieldName(collectionFieldNames.get(i));
								collectionConfig.setAdderMethodName(adderMethodNames.get(i));
										
								collectionConfigs.add(collectionConfig);
							}
							
							adderCollections.put(className, collectionConfigs);
						}
					}
				}
			}
		} catch (ConfigurationException cex) {
			cex.printStackTrace();
		}
	}

	public Object clone()	throws CloneNotSupportedException
	{
	    throw new CloneNotSupportedException(); 
	}

	/**
	 * Retrieve a list of field names for a class of fields that are to be used as parameters to a
	 * constructor.
	 * 
	 * @param className
	 * @return
	 */
	public List<String> getConstructor(String className) {
		List<String> paramFieldNames = constructors.get(className);
		
		if (paramFieldNames != null)
		{
			return Collections.unmodifiableList(paramFieldNames);
		}
		else
		{
			return paramFieldNames;
		}
	}

	/**
	 * Retrieve a list of field names for a class of fields that are to have non-standard setter method
	 * generation.
	 * 
	 * @param className
	 * @return
	 */
	public List<String> getIgnoredSetter(String className) {
		List<String> paramFieldNames = setters.get(className);
		
		if (paramFieldNames != null)
		{
			return Collections.unmodifiableList(paramFieldNames);
		}
		else
		{
			return paramFieldNames;
		}
	}

	/**
	 * Retrieve a list of collection field names and adder method names for a class that
	 * only accesses the collection(s) through adder methods.
	 * 
	 * @param className
	 * @return
	 */
	public List<CollectionAdderConfig> getAdderCollection(String className) {
		List<CollectionAdderConfig> collectionConfigs = adderCollections.get(className);
		
		if (collectionConfigs != null)
		{
			return Collections.unmodifiableList(collectionConfigs);
		}
		else
		{
			return collectionConfigs;
		}
	}
}
