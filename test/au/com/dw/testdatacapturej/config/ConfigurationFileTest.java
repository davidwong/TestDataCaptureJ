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


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.junit.Before;
import org.junit.Test;

/**
 * Test reading configuration files.
 * 
 * @author David Wong
 *
 */
public class ConfigurationFileTest {

	private XMLConfiguration xmlConstructorConfig;
	private XMLConfiguration xmlSetterConfig;
	
	@Before
	public void setUp() throws Exception {	
		try {
			xmlConstructorConfig = new XMLConfiguration("test-constructor-config.xml");
			xmlSetterConfig = new XMLConfiguration("test-setter-config.xml");
		} catch (ConfigurationException cex) {
			cex.printStackTrace();
		}
	}
 
    /**
     * Test for getting constructor parameters from the Configuration.
     * 
     */
    @Test
    public void constructorFileReadTest()
    {
		try {		
			/*
			// get the first constructor block
			HierarchicalConfiguration sub = xmlConfig.configurationAt("constructor(0)");
			String className = xmlConfig.getString("constructor(0)[@class]");
			System.out.println(className);
			List<String> paramFieldNames = (List<String>)sub.getList("argument.field-name");
			assertEquals(1, paramFieldNames.size());
			
			String fieldName = paramFieldNames.get(0);
			assertEquals(noSetterFieldName, fieldName);

			// next constructor block
			sub = xmlConfig.configurationAt("constructor(1)");
			className = xmlConfig.getString("constructor(1)[@class]");
			System.out.println(className);
			paramFieldNames = (List<String>)sub.getList("argument.field-name");
			assertEquals(2, paramFieldNames.size());
			
			fieldName = paramFieldNames.get(0);
			assertEquals(noSetterFieldName, fieldName);
			fieldName = paramFieldNames.get(1);
			assertEquals(noSetterFieldName2, fieldName);
*/
			List<?> constructors = xmlConstructorConfig.configurationsAt("constructor");
			for (Iterator<?> it = constructors.iterator(); it.hasNext();)
			{
				HierarchicalConfiguration sub = (HierarchicalConfiguration) it.next();
			    // sub contains now all data about a single field
				
				String className = sub.getString("[@class]");
				System.out.println(className);
				
				List<String> paramFieldNames = (List<String>)sub.getList("argument.field-name");
				assertNotNull(paramFieldNames);
				assertFalse(paramFieldNames.isEmpty());
				
			    for (String fieldName : paramFieldNames)
			    {
			    	System.out.println(fieldName);
			    	assertNotNull(fieldName);
			    }
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

    /**
     * Test for getting setter method config from the Configuration.
     * 
     */
    @Test
    public void setterFileReadTest()
    {
		try {		

			List<?> setters = xmlSetterConfig.configurationsAt("setter");
			for (Iterator<?> it = setters.iterator(); it.hasNext();)
			{
				HierarchicalConfiguration sub = (HierarchicalConfiguration) it.next();
			    // sub contains now all data about a single field
				
				String className = sub.getString("[@class]");
				System.out.println(className);
				
				List<String> fieldNames = (List<String>)sub.getList("field.field-name");
				assertNotNull(fieldNames);
				assertFalse(fieldNames.isEmpty());
				
			    for (String fieldName : fieldNames)
			    {
			    	System.out.println(fieldName);
			    	assertNotNull(fieldName);
			    }
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

}
