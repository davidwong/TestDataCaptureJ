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

import static au.com.dw.testing.AssertUtil.assertEqualsAnyWithoutFormatting;
import static au.com.dw.testing.AssertUtil.assertEqualsWithoutFormatting;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import au.com.dw.testdatacapturej.mock.classcheck.Holder;
import au.com.dw.testdatacapturej.mock.classcheck.Setter_Array;
import au.com.dw.testdatacapturej.mock.classcheck.Setter_Collection;
import au.com.dw.testdatacapturej.mock.classcheck.Setter_Map;
import au.com.dw.testdatacapturej.mock.classcheck.Setter_Object;
import au.com.dw.testdatacapturej.mock.classcheck.Setter_Simple;
import au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredArray;
import au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredCollection;
import au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredMap;
import au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredObject;
import au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredSimple;
import au.com.dw.testdatacapturej.mock.dataholder.TestData;
import au.com.dw.testdatacapturej.reflection.BaseReflectionTest;
import au.com.dw.testdatacapturej.reflection.MetadataGenerationHandler;
import au.com.dw.testdatacapturej.reflection.ReflectionHandler;


/**
 * Tests for logging of classes with non-standard setter method configured.
 * 
 * The test classes must be configured in the test setter configuration XML file.
 * 
 * @author David Wong
 *
 */
public class SetterMethodTest extends BaseReflectionTest {

	private ReflectionHandler handler;
	private ObjectLogger logger;
	private LogBuilder builder;
	
	private Holder holder;
	
	@Before
	public void setUp() throws Exception {
		handler = new MetadataGenerationHandler();
		logger = new ObjectLogger();
		builder = new RawLogBuilder();
		holder = new Holder();
	}
 
    /**
     * Test for setter with simple field configured in XML config file.
     * Should not generate any setter method for the field.
     */
    @Test
    public void setterSimpleTest()
    {
		Setter_Simple simple = new Setter_Simple();
		simple.setSetterField(1);
		
		holder.setTestField(simple);

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.getLog();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.Holder holder0 = new au.com.dw.testdatacapturej.mock.classcheck.Holder();" +
			"au.com.dw.testdatacapturej.mock.classcheck.Setter_Simple setter_Simple0 = new au.com.dw.testdatacapturej.mock.classcheck.Setter_Simple();" +
			"holder0.setTestField(setter_Simple0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

    /**
     * Test for setter with simple field not configured in XML config file.
     * Should generate the default setter method.
     */
    @Test
    public void setterSimpleNotConfiguredTest()
    {
		Setter_UnconfiguredSimple simple = new Setter_UnconfiguredSimple();
		simple.setSetterField(1);
		
		holder.setTestField(simple);

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.getLog();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.Holder holder0 = new au.com.dw.testdatacapturej.mock.classcheck.Holder();" +
			"au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredSimple setter_UnconfiguredSimple0 = new au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredSimple();" +
			"setter_UnconfiguredSimple0.setSetterField(1);" +
			"holder0.setTestField(setter_UnconfiguredSimple0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for setter with Object field configured in XML config file.
     * Should not generate any constructor or setter method for the field.
     */
    @Test
    public void setterObjectTest()
    {
		Setter_Object objectField = new Setter_Object();
		
		TestData testData = new TestData();
		objectField.setSetterField(testData.createSimpleDataHolder());
		
		holder.setTestField(objectField);
		
		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.getLog();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.Holder holder0 = new au.com.dw.testdatacapturej.mock.classcheck.Holder();" +
			"au.com.dw.testdatacapturej.mock.classcheck.Setter_Object setter_Object0 = new au.com.dw.testdatacapturej.mock.classcheck.Setter_Object();" +
			"holder0.setTestField(setter_Object0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
 
    /**
     * Test for setter with Object field not configured in XML config file.
     * Should generate the constructor for the field and the default setter method.
     */
    @Test
    public void setterObjectNotConfiguredTest()
    {
		Setter_UnconfiguredObject objectField = new Setter_UnconfiguredObject();
		
		TestData testData = new TestData();
		objectField.setSetterField(testData.createSimpleDataHolder());
		
		holder.setTestField(objectField);
		
		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.getLog();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.Holder holder0 = new au.com.dw.testdatacapturej.mock.classcheck.Holder();" +
			"au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredObject setter_UnconfiguredObject0 = new au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredObject();" +
			"au.com.dw.testdatacapturej.mock.dataholder.SimpleDataHolder simpleDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.SimpleDataHolder();" +
			"simpleDataHolder0.setText(\"aaa\");" +
			"simpleDataHolder0.setNumber(1);" +
			"simpleDataHolder0.setCharacter('a');" +
			"simpleDataHolder0.setBool(true);" +
			"simpleDataHolder0.setLongNumber(100L);" +
			"simpleDataHolder0.setPrimitiveFraction(0.1f);" +
			"simpleDataHolder0.setFraction(100.1d);" +
			"setter_UnconfiguredObject0.setSetterField(simpleDataHolder0);" +
			"holder0.setTestField(setter_UnconfiguredObject0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for setter with Collection field configured in XML config file.
     * Should not generate any constructor or setter method for the field.
     */
    @Test
    public void setterCollectionTest()
    {
		Setter_Collection collectionField = new Setter_Collection();
		
		collectionField.setSetterField(createObjectCollection());
		
		holder.setTestField(collectionField);
		
		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.getLog();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.Holder holder0 = new au.com.dw.testdatacapturej.mock.classcheck.Holder();" +
			"au.com.dw.testdatacapturej.mock.classcheck.Setter_Collection setter_Collection0 = new au.com.dw.testdatacapturej.mock.classcheck.Setter_Collection();" +
			"holder0.setTestField(setter_Collection0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for setter with Collection field not configured in XML config file.
     * Should generate the constructor for the field and the default setter method and the Collection add
     * for the collection elements.
     */
    @Test
    public void setterCollectionNotConfiguredTest()
    {
		Setter_UnconfiguredCollection collectionField = new Setter_UnconfiguredCollection();
		
		collectionField.setSetterField(createLongCollection());
		
		holder.setTestField(collectionField);
		
		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.getLog();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.Holder holder0 = new au.com.dw.testdatacapturej.mock.classcheck.Holder();" +
			"au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredCollection setter_UnconfiguredCollection0 = new au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredCollection();" +
			"java.util.HashSet hashSet0 = new java.util.HashSet();" +
			"hashSet0.add(100L);" +
			"hashSet0.add(200L);" +
			"setter_UnconfiguredCollection0.setSetterField(hashSet0);" +
			"holder0.setTestField(setter_UnconfiguredCollection0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for setter with Array field configured in XML config file.
     * Should not generate any constructor or setter method for the field.
     */
    @Test
    public void setterArrayTest()
    {
		Setter_Array arrayField = new Setter_Array();
		
		arrayField.setSetterField(createObjectArray());
		
		holder.setTestField(arrayField);
		
		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.getLog();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.Holder holder0 = new au.com.dw.testdatacapturej.mock.classcheck.Holder();" +
			"au.com.dw.testdatacapturej.mock.classcheck.Setter_Array setter_Array0 = new au.com.dw.testdatacapturej.mock.classcheck.Setter_Array();" +
			"holder0.setTestField(setter_Array0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

    /**
     * Test for setter with Array field not configured in XML config file.
     * Should generate the constructor for the field and the default setter method and the assignment
     * of the array elements.
     */
    @Test
    public void setterArrayNotConfiguredTest()
    {
		Setter_UnconfiguredArray arrayField = new Setter_UnconfiguredArray();
		
		arrayField.setSetterField(createCharacterArray());
		
		holder.setTestField(arrayField);
		
		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.getLog();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.Holder holder0 = new au.com.dw.testdatacapturej.mock.classcheck.Holder();" +
			"au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredArray setter_UnconfiguredArray0 = new au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredArray();" +
			"java.lang.Character[] characterArray0 = new java.lang.Character[2];" +
			"characterArray0[0] = 'c';" +
			"characterArray0[1] = 'd';" +
			"setter_UnconfiguredArray0.setSetterField(characterArray0);" +
			"holder0.setTestField(setter_UnconfiguredArray0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

    /**
     * Test for setter with Map field configured in XML config file.
     * Should not generate any constructor or setter method for the field.
     */
    @Test
    public void setterMapTest()
    {
		Setter_Map mapField = new Setter_Map();
		
		mapField.setSetterField(createObjectMapClass());
		
		holder.setTestField(mapField);
		
		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.getLog();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.Holder holder0 = new au.com.dw.testdatacapturej.mock.classcheck.Holder();" +
			"au.com.dw.testdatacapturej.mock.classcheck.Setter_Map setter_Map0 = new au.com.dw.testdatacapturej.mock.classcheck.Setter_Map();" +
			"holder0.setTestField(setter_Map0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for setter with Map field not configured in XML config file.
     * Should generate the constructor for the field and the default setter method and the Map put
     * for the map entries.
     */
    @Test
    public void setterMapNotConfiguredTest()
    {
		Setter_UnconfiguredMap mapField = new Setter_UnconfiguredMap();
		
		mapField.setSetterField(createBuiltinMapClass());
		
		holder.setTestField(mapField);
		
		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.getLog();

			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = "au.com.dw.testdatacapturej.mock.classcheck.Holder holder0 = new au.com.dw.testdatacapturej.mock.classcheck.Holder();" +
			"au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredMap setter_UnconfiguredMap0 = new au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredMap();" +
			"java.util.HashMap hashMap0 = new java.util.HashMap();" +
			"hashMap0.put(\"key1\", \"value1\");" +
			"hashMap0.put(\"key2\", \"value2\");" +
			"setter_UnconfiguredMap0.setSetterField(hashMap0);" +
			"holder0.setTestField(setter_UnconfiguredMap0);";
			expectedValues.add(expected1);

			String expected2 = "au.com.dw.testdatacapturej.mock.classcheck.Holder holder0 = new au.com.dw.testdatacapturej.mock.classcheck.Holder();" +
			"au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredMap setter_UnconfiguredMap0 = new au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredMap();" +
			"java.util.HashMap hashMap0 = new java.util.HashMap();" +
			"hashMap0.put(\"key2\", \"value2\");" +
			"hashMap0.put(\"key1\", \"value1\");" +
			"setter_UnconfiguredMap0.setSetterField(hashMap0);" +
			"holder0.setTestField(setter_UnconfiguredMap0);";
			expectedValues.add(expected2);
			
			System.out.println(result);
			assertEqualsAnyWithoutFormatting(expectedValues, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for setter for the initial object with Object field configured in XML config file.
     * Should not generate any constructor or setter method for the field.
     */
    @Test
    public void setterInitialObjectTest()
    {
		Setter_Object objectField = new Setter_Object();
		
		TestData testData = new TestData();
		objectField.setSetterField(testData.createSimpleDataHolder());
		
		try {
			logger.logObject(builder, handler.handle(objectField));
			String result = builder.getLog();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.Setter_Object setter_Object0 = new au.com.dw.testdatacapturej.mock.classcheck.Setter_Object();";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

    /**
     * Test for setter for the initial object with Object field not configured in XML config file.
     * Should generate the constructor for the field and the default setter method.
     */
    @Test
    public void setterInitialObjectNotConfiguredTest()
    {
		Setter_UnconfiguredObject objectField = new Setter_UnconfiguredObject();
		
		TestData testData = new TestData();
		objectField.setSetterField(testData.createSimpleDataHolder());
		
		try {
			logger.logObject(builder, handler.handle(objectField));
			String result = builder.getLog();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredObject setter_UnconfiguredObject0 = new au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredObject();" +
			"au.com.dw.testdatacapturej.mock.dataholder.SimpleDataHolder simpleDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.SimpleDataHolder();" +
			"simpleDataHolder0.setText(\"aaa\");" +
			"simpleDataHolder0.setNumber(1);" +
			"simpleDataHolder0.setCharacter('a');" +
			"simpleDataHolder0.setBool(true);" +
			"simpleDataHolder0.setLongNumber(100L);" +
			"simpleDataHolder0.setPrimitiveFraction(0.1f);" +
			"simpleDataHolder0.setFraction(100.1d);" +
			"setter_UnconfiguredObject0.setSetterField(simpleDataHolder0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

 }

