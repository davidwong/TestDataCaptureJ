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
package au.com.dw.testdatacapturej.log;

import static au.com.dw.testing.AssertUtil.assertEqualsWithoutFormatting;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import au.com.dw.testdatacapturej.mock.classcheck.Holder;
import au.com.dw.testdatacapturej.mock.classcheck.Setter_Object;
import au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredCollection;
import au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredMap;
import au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredObject;
import au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredObjectArray;
import au.com.dw.testdatacapturej.mock.dataholder.TestData;
import au.com.dw.testdatacapturej.reflection.BaseReflectionTest;
import au.com.dw.testdatacapturej.reflection.MetadataGenerationHandler;
import au.com.dw.testdatacapturej.reflection.ReflectionHandler;


/**
 * Tests for logging of classes with non-standard setter method configured that are nested within each other.
 * 
 * The test classes must be configured in the test setter configuration XML file.
 * 
 * @author David Wong
 *
 */
public class SetterMethodNestedTest extends BaseReflectionTest {

	private ReflectionHandler handler;
	private ObjectLogger logger;
	private StringBuilder builder;
	
	private Holder holder;
	
	@Before
	public void setUp() throws Exception {
		handler = new MetadataGenerationHandler();
		logger = new ObjectLogger();
		builder = new StringBuilder();
		holder = new Holder();
	}
 
    
    /**
     * Test for configured object as a field of an unconfigured object.
     * Should generate constructor and setter method for unconfigured object.
     * Should generate constructor but no setter method for the configured object.
     */
    @Test
    public void setterObjectInsideUnconfiguredObjectTest()
    {
		Setter_UnconfiguredObject unconfigured = new Setter_UnconfiguredObject();
		Setter_Object configured = new Setter_Object();
		
		TestData testData = new TestData();
		configured.setSetterField(testData.createSimpleDataHolder());
		
		unconfigured.setSetterField(configured);
		
		try {
			logger.logObject(builder, handler.handle(unconfigured));
			String result = builder.toString();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredObject setter_UnconfiguredObject0 = new au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredObject();" +
			"au.com.dw.testdatacapturej.mock.classcheck.Setter_Object setter_Object0 = new au.com.dw.testdatacapturej.mock.classcheck.Setter_Object();" +
			"setter_UnconfiguredObject0.setSetterField(setter_Object0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
 
    /**
     * Test for unconfigured object as a field of an configured object.
     * Should generate constructor but no setter method for the configured object.
     * Should not generate constructor or setter method for unconfigured object.
     */
    @Test
    public void setterUnconfiguredObjectInsideConfiguredObjectTest()
    {
    	Setter_Object configured = new Setter_Object();
		Setter_UnconfiguredObject unconfigured = new Setter_UnconfiguredObject();
		
		TestData testData = new TestData();
		unconfigured.setSetterField(testData.createSimpleDataHolder());
		
		configured.setSetterField(unconfigured);
		
		try {
			logger.logObject(builder, handler.handle(configured));
			String result = builder.toString();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.Setter_Object setter_Object0 = new au.com.dw.testdatacapturej.mock.classcheck.Setter_Object();";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for configured object as an element of an unconfigured Collection.
     * Should generate constructor and setter method for the unconfigured collection.
     * Should generate constructor and add method for the unconfigured collection field.
     * Should generate constructor but no setter method for configured object.
     */
    @Test
    public void setterConfiguredObjectInsideUnconfiguredCollectionTest()
    {
		Setter_UnconfiguredCollection collectionField = new Setter_UnconfiguredCollection();
    	Setter_Object configured = new Setter_Object();

		TestData testData = new TestData();
		configured.setSetterField(testData.createSimpleDataHolder());

		Collection<Object> collection = new ArrayList<Object>();
		collection.add(configured);
		
		collectionField.setSetterField(collection);
		
		try {
			logger.logObject(builder, handler.handle(collectionField));
			String result = builder.toString();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredCollection setter_UnconfiguredCollection0 = new au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredCollection();" +
			"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			"au.com.dw.testdatacapturej.mock.classcheck.Setter_Object setter_Object0 = new au.com.dw.testdatacapturej.mock.classcheck.Setter_Object();" +
			"arrayList0.add(setter_Object0);" +
			"setter_UnconfiguredCollection0.setSetterField(arrayList0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    
    /**
     * Test for configured object as an element of an unconfigured Array.
     * Should generate constructor and setter method for the unconfigured array.
     * Should generate constructor and assignment for the unconfigured array field.
     * Should generate constructor but no setter method for configured object.
     */
    @Test
    public void setterConfiguredObjectInsideUnconfiguredArrayTest()
    {
		Setter_UnconfiguredObjectArray array = new Setter_UnconfiguredObjectArray();
    	Setter_Object configured = new Setter_Object();

		TestData testData = new TestData();
		configured.setSetterField(testData.createSimpleDataHolder());
		
		Object[] objectArray = new Object[1];
		objectArray[0] = configured;
		
		array.setSetterField(objectArray);

		try {
			logger.logObject(builder, handler.handle(array));
			String result = builder.toString();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredObjectArray setter_UnconfiguredObjectArray0 = new au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredObjectArray();" +
			"java.lang.Object[] objectArray0 = new java.lang.Object[1];" +
			"au.com.dw.testdatacapturej.mock.classcheck.Setter_Object setter_Object0 = new au.com.dw.testdatacapturej.mock.classcheck.Setter_Object();" +
			"objectArray0[0] = setter_Object0;" +
			"setter_UnconfiguredObjectArray0.setSetterField(objectArray0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

     
    /**
     * Test for configured object a value of an entry of an unconfigured Map.
     * Should generate constructor and setter method for the unconfigured map.
     * Should generate constructor and put method for the unconfigured map field.
     * Should generate constructor but no setter method for configured object.
     */
    @Test
    public void setterConfiguredObjectInsideUnconfiguredMapTest()
    {
		Setter_UnconfiguredMap map = new Setter_UnconfiguredMap();
    	Setter_Object configured = new Setter_Object();

		TestData testData = new TestData();
		configured.setSetterField(testData.createSimpleDataHolder());
		
		HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
		hashMap.put(KEY_1, configured);
		
		map.setSetterField(hashMap);
		
		try {
			logger.logObject(builder, handler.handle(map));
			String result = builder.toString();

			String expected = "au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredMap setter_UnconfiguredMap0 = new au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredMap();" +
			"java.util.HashMap hashMap0 = new java.util.HashMap();" +
			"au.com.dw.testdatacapturej.mock.classcheck.Setter_Object setter_Object0 = new au.com.dw.testdatacapturej.mock.classcheck.Setter_Object();" +
			"hashMap0.put(\"key1\", setter_Object0);" +
			"setter_UnconfiguredMap0.setSetterField(hashMap0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

    /**
     * Test for configured object a key of an entry of an unconfigured Map.
     * Should generate constructor and setter method for the unconfigured map.
     * Should generate constructor and put method for the unconfigured map field.
     * Should generate constructor but no setter method for configured object.
     */
    @Test
    public void setterConfiguredObjectKeyInsideUnconfiguredMapTest()
    {
		Setter_UnconfiguredMap map = new Setter_UnconfiguredMap();
    	Setter_Object configured = new Setter_Object();

		TestData testData = new TestData();
		configured.setSetterField(testData.createSimpleDataHolder());
		
		HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
		hashMap.put(configured, "value1");
		
		map.setSetterField(hashMap);
		
		try {
			logger.logObject(builder, handler.handle(map));
			String result = builder.toString();

			String expected = "au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredMap setter_UnconfiguredMap0 = new au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredMap();" +
			"java.util.HashMap hashMap0 = new java.util.HashMap();" +
			"au.com.dw.testdatacapturej.mock.classcheck.Setter_Object setter_Object0 = new au.com.dw.testdatacapturej.mock.classcheck.Setter_Object();" +
			"hashMap0.put(setter_Object0, \"value1\");" +
			"setter_UnconfiguredMap0.setSetterField(hashMap0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

 }

