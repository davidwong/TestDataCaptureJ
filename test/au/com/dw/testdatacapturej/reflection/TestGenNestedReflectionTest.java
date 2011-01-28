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
package au.com.dw.testdatacapturej.reflection;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.Map;


import org.apache.commons.lang.SystemUtils;
import org.junit.Before;
import org.junit.Test;

import au.com.dw.testdatacapturej.log.ObjectLogger;
import au.com.dw.testdatacapturej.mock.dataholder.TestData;
import au.com.dw.testdatacapturej.reflection.MetadataGenerationHandler;
import au.com.dw.testdatacapturej.reflection.ReflectionHandler;



/**
 * Tests for nesting grouping classes, e.g. collections, arrays, maps.
 * 
 * @author David Wong
 *
 */
public class TestGenNestedReflectionTest {

	private ReflectionHandler handler;
	private ObjectLogger logger;
	private StringBuilder builder;
	
	@Before
	public void setUp() throws Exception {
		handler = new MetadataGenerationHandler();
		logger = new ObjectLogger();
		builder = new StringBuilder();
	}
    
	// Tests for collection of array
	// -----------------------------
	
    /**
     * Test for nested collection containing array containing null object.
     */
    @Test
    public void nestedCollectionOfArrayNullTest()
    {
    	TestData testData = new TestData();
    	Collection<?> data = testData.createNestedCollectionOfArrayNull();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.TreeSet treeSet0 = new java.util.TreeSet();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.lang.Object[] objectArray0 = new java.lang.Object[1];" +
			SystemUtils.LINE_SEPARATOR +
			"objectArray0[0] = null;" +
			SystemUtils.LINE_SEPARATOR +
			"treeSet0.add(objectArray0);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for nested collection containing empty array.
     */
    @Test
    public void nestedCollectionOfArrayEmptyTest()
    {
    	TestData testData = new TestData();
    	Collection<?> data = testData.createNestedCollectionOfArrayEmpty();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.HashSet hashSet0 = new java.util.HashSet();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.lang.Object[] objectArray0 = new java.lang.Object[0];" +
			SystemUtils.LINE_SEPARATOR +
			"hashSet0.add(objectArray0);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for nested collection containing primitive array.
     */
   @Test
    public void nestedCollectionOfArrayPrimitiveTest()
    {
    	TestData testData = new TestData();
    	Collection<?> data = testData.createNestedCollectionOfArrayPrimitive();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.LinkedHashSet linkedHashSet0 = new java.util.LinkedHashSet();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"int[] intArray0 = new int[1];" +
			SystemUtils.LINE_SEPARATOR +
			"intArray0[0] = 1;" +
			SystemUtils.LINE_SEPARATOR +
			"linkedHashSet0.add(intArray0);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for nested collection containing object array.
     */
    @Test
    public void nestedCollectionOfArrayObjectTest()
    {
    	TestData testData = new TestData();
    	Collection<?> data = testData.createNestedCollectionOfArrayObject();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder[] innerDataHolderArray0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder[1];" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"one\");" +
			SystemUtils.LINE_SEPARATOR +			
			"innerDataHolder0.setImNumber(1.0d);" +
			SystemUtils.LINE_SEPARATOR +			
			"innerDataHolderArray0[0] = innerDataHolder0;" +
			SystemUtils.LINE_SEPARATOR +			
			"arrayList0.add(innerDataHolderArray0);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
 
    /**
     * Test for nested collection containing multiple object array.
     */
    @Test
    public void nestedCollectionOfArrayObjectsTest()
    {
    	TestData testData = new TestData();
    	Collection<?> data = testData.createNestedCollectionOfArrayObjects();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder[] innerDataHolderArray0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder[2];" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"one\");" +
			SystemUtils.LINE_SEPARATOR +			
			"innerDataHolder0.setImNumber(1.0d);" +
			SystemUtils.LINE_SEPARATOR +			
			"innerDataHolderArray0[0] = innerDataHolder0;" +
			SystemUtils.LINE_SEPARATOR +			
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"two\");" +
			SystemUtils.LINE_SEPARATOR +			
			"innerDataHolder1.setImNumber(2.0d);" +
			SystemUtils.LINE_SEPARATOR +			
			"innerDataHolderArray0[1] = innerDataHolder1;" +
			SystemUtils.LINE_SEPARATOR +			
			"arrayList0.add(innerDataHolderArray0);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

	// Tests for collection of collection
	// ----------------------------------
    
    /**
     * Test for nested collection containing empty collection.
     */
    @Test
    public void nestedCollectionOfCollectionEmptyTest()
    {
    	TestData testData = new TestData();
    	Collection<?> data = testData.createNestedCollectionOfCollectionEmpty();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.LinkedList linkedList0 = new java.util.LinkedList();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.ArrayList arrayList1 = new java.util.ArrayList();" +
			SystemUtils.LINE_SEPARATOR +
			"linkedList0.add(arrayList1);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for nested collection containing collection with null element.
     */
    @Test
    public void nestedCollectionOfCollectionNullTest()
    {
    	TestData testData = new TestData();
    	Collection<?> data = testData.createNestedCollectionOfCollectionNull();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.HashSet hashSet0 = new java.util.HashSet();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.TreeSet treeSet1 = new java.util.TreeSet();" +
			SystemUtils.LINE_SEPARATOR +
			"treeSet1.add(null);" +
			SystemUtils.LINE_SEPARATOR +
			"hashSet0.add(treeSet1);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

    /**
     * Test for nested collection containing collection with object element.
     */
    @Test
    public void nestedCollectionOfCollectionObjectTest()
    {
    	TestData testData = new TestData();
    	Collection<?> data = testData.createNestedCollectionOfCollectionObject();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.LinkedHashSet linkedHashSet0 = new java.util.LinkedHashSet();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.ArrayList arrayList1 = new java.util.ArrayList();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"one\");" +
			SystemUtils.LINE_SEPARATOR +			
			"innerDataHolder0.setImNumber(1.0d);" +
			SystemUtils.LINE_SEPARATOR +			
			"arrayList1.add(innerDataHolder0);" +
			SystemUtils.LINE_SEPARATOR +			
			"linkedHashSet0.add(arrayList1);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

    /**
     * Test for nested collection containing collection with multiple object elements.
     */
    @Test
    public void nestedCollectionOfCollectionObjectsTest()
    {
    	TestData testData = new TestData();
    	Collection<?> data = testData.createNestedCollectionOfCollectionObjects();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.LinkedHashSet linkedHashSet0 = new java.util.LinkedHashSet();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.ArrayList arrayList1 = new java.util.ArrayList();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"one\");" +
			SystemUtils.LINE_SEPARATOR +			
			"innerDataHolder0.setImNumber(1.0d);" +
			SystemUtils.LINE_SEPARATOR +			
			"arrayList1.add(innerDataHolder0);" +
			SystemUtils.LINE_SEPARATOR +			
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"two\");" +
			SystemUtils.LINE_SEPARATOR +			
			"innerDataHolder1.setImNumber(2.0d);" +
			SystemUtils.LINE_SEPARATOR +			
			"arrayList1.add(innerDataHolder1);" +
			SystemUtils.LINE_SEPARATOR +			
			"linkedHashSet0.add(arrayList1);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
   
	// Tests for collection of map
	// ---------------------------
    
    /**
     * Test for nested collection containing empty map.
     */
    @Test
    public void nestedCollectionOfMapEmptyTest()
    {
    	TestData testData = new TestData();
    	Collection<?> data = testData.createNestedCollectionOfMapEmpty();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.LinkedList linkedList0 = new java.util.LinkedList();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.HashMap hashMap0 = new java.util.HashMap();" +
			SystemUtils.LINE_SEPARATOR +
			"linkedList0.add(hashMap0);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

    /**
     * Test for nested collection containing map with null element.
     */
    @Test
    public void nestedCollectionOfMapNullTest()
    {
    	TestData testData = new TestData();
    	Collection<?> data = testData.createNestedCollectionOfMapNull();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.HashSet hashSet0 = new java.util.HashSet();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.TreeMap treeMap0 = new java.util.TreeMap();" +
			SystemUtils.LINE_SEPARATOR +
			"treeMap0.put(\"key1\", null);" +
			SystemUtils.LINE_SEPARATOR +
			"hashSet0.add(treeMap0);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for nested collection containing map with object element.
     */
    @Test
    public void nestedCollectionOfMapObjectTest()
    {
    	TestData testData = new TestData();
    	Collection<?> data = testData.createNestedCollectionOfMapObject();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.LinkedHashSet linkedHashSet0 = new java.util.LinkedHashSet();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.LinkedHashMap linkedHashMap0 = new java.util.LinkedHashMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"one\");" +
			SystemUtils.LINE_SEPARATOR +			
			"innerDataHolder0.setImNumber(1.0d);" +
			SystemUtils.LINE_SEPARATOR +
			"linkedHashMap0.put(\"key1\", innerDataHolder0);" +
			SystemUtils.LINE_SEPARATOR +			
			"linkedHashSet0.add(linkedHashMap0);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

    /**
     * Test for nested collection containing map with multiple object elements.
     */
    @Test
    public void nestedCollectionOfMapObjectsTest()
    {
    	TestData testData = new TestData();
    	Collection<?> data = testData.createNestedCollectionOfMapObjects();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.LinkedHashSet linkedHashSet0 = new java.util.LinkedHashSet();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.LinkedHashMap linkedHashMap0 = new java.util.LinkedHashMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"one\");" +
			SystemUtils.LINE_SEPARATOR +			
			"innerDataHolder0.setImNumber(1.0d);" +
			SystemUtils.LINE_SEPARATOR +
			"linkedHashMap0.put(\"key1\", innerDataHolder0);" +
			SystemUtils.LINE_SEPARATOR +			
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"two\");" +
			SystemUtils.LINE_SEPARATOR +			
			"innerDataHolder1.setImNumber(2.0d);" +
			SystemUtils.LINE_SEPARATOR +
			"linkedHashMap0.put(\"key2\", innerDataHolder1);" +
			SystemUtils.LINE_SEPARATOR +			
			"linkedHashSet0.add(linkedHashMap0);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

	// Tests for array of array
	// ------------------------
    
    /**
     * Test for nested array containing array containing null object.
     */
    @Test
    public void nestedArrayOfArrayNullTest()
    {
    	TestData testData = new TestData();
    	Object[] data = testData.createNestedArrayOfArrayNull();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.lang.Object[] objectArray0 = new java.lang.Object[1];" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.lang.Object[] objectArray1 = new java.lang.Object[1];" +
			SystemUtils.LINE_SEPARATOR +
			"objectArray1[0] = null;" +
			SystemUtils.LINE_SEPARATOR +
			"objectArray0[0] = objectArray1;" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }  
    
    /**
     * Test for nested array containing empty array.
     */
    @Test
    public void nestedArrayOfArrayEmptyTest()
    {
    	TestData testData = new TestData();
    	Object[] data = testData.createNestedArrayOfArrayEmpty();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.lang.Object[] objectArray0 = new java.lang.Object[1];" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.lang.Object[] objectArray1 = new java.lang.Object[0];" +
			SystemUtils.LINE_SEPARATOR +
			"objectArray0[0] = objectArray1;" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    } 
    
    /**
     * Test for nested array containing array containing object.
     */
    @Test
    public void nestedArrayOfArrayObjectTest()
    {
    	TestData testData = new TestData();
    	Object[] data = testData.createNestedArrayOfArrayObject();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.lang.Object[] objectArray0 = new java.lang.Object[1];" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.lang.Object[] objectArray1 = new java.lang.Object[1];" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"one\");" +
			SystemUtils.LINE_SEPARATOR +			
			"innerDataHolder0.setImNumber(1.0d);" +
			SystemUtils.LINE_SEPARATOR +
			"objectArray1[0] = innerDataHolder0;" +
			SystemUtils.LINE_SEPARATOR +
			"objectArray0[0] = objectArray1;" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

    /**
     * Test for nested array containing array containing multiple objects.
     */
    @Test
    public void nestedArrayOfArrayObjectsTest()
    {
    	TestData testData = new TestData();
    	Object[] data = testData.createNestedArrayOfArrayObjects();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.lang.Object[] objectArray0 = new java.lang.Object[1];" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.lang.Object[] objectArray1 = new java.lang.Object[2];" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"one\");" +
			SystemUtils.LINE_SEPARATOR +			
			"innerDataHolder0.setImNumber(1.0d);" +
			SystemUtils.LINE_SEPARATOR +
			"objectArray1[0] = innerDataHolder0;" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"two\");" +
			SystemUtils.LINE_SEPARATOR +			
			"innerDataHolder1.setImNumber(2.0d);" +
			SystemUtils.LINE_SEPARATOR +
			"objectArray1[1] = innerDataHolder1;" +
			SystemUtils.LINE_SEPARATOR +
			"objectArray0[0] = objectArray1;" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

	// Tests for array of collection
	// -----------------------------
    
    /**
     * Test for nested array containing empty collection.
     */
    @Test
    public void nestedArrayOfCollectionEmptyTest()
    {
    	TestData testData = new TestData();
    	Object[] data = testData.createNestedArrayOfCollectionEmpty();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.lang.Object[] objectArray0 = new java.lang.Object[1];" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			SystemUtils.LINE_SEPARATOR +
			"objectArray0[0] = arrayList0;" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for nested array containing collection with null element.
     */
    @Test
    public void nestedArrayOfCollectionNullTest()
    {
    	TestData testData = new TestData();
    	Object[] data = testData.createNestedArrayOfCollectionNull();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.lang.Object[] objectArray0 = new java.lang.Object[1];" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.TreeSet treeSet0 = new java.util.TreeSet();" +
			SystemUtils.LINE_SEPARATOR +
			"treeSet0.add(null);" +
			SystemUtils.LINE_SEPARATOR +
			"objectArray0[0] = treeSet0;" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for nested array containing collection with object element.
     */
    @Test
    public void nestedArrayOfCollectionObjectTest()
    {
    	TestData testData = new TestData();
    	Object[] data = testData.createNestedArrayOfCollectionObject();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.lang.Object[] objectArray0 = new java.lang.Object[1];" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"one\");" +
			SystemUtils.LINE_SEPARATOR +			
			"innerDataHolder0.setImNumber(1.0d);" +
			SystemUtils.LINE_SEPARATOR +			
			"arrayList0.add(innerDataHolder0);" +
			SystemUtils.LINE_SEPARATOR +
			"objectArray0[0] = arrayList0;" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for nested array containing collection with multiple object elements.
     */
    @Test
    public void nestedArrayOfCollectionObjectsTest()
    {
    	TestData testData = new TestData();
    	Object[] data = testData.createNestedArrayOfCollectionObjects();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.lang.Object[] objectArray0 = new java.lang.Object[1];" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"one\");" +
			SystemUtils.LINE_SEPARATOR +			
			"innerDataHolder0.setImNumber(1.0d);" +
			SystemUtils.LINE_SEPARATOR +			
			"arrayList0.add(innerDataHolder0);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"two\");" +
			SystemUtils.LINE_SEPARATOR +			
			"innerDataHolder1.setImNumber(2.0d);" +
			SystemUtils.LINE_SEPARATOR +			
			"arrayList0.add(innerDataHolder1);" +
			SystemUtils.LINE_SEPARATOR +
			"objectArray0[0] = arrayList0;" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

	// Tests for array of map
	// ----------------------
    
    /**
     * Test for nested array containing empty map.
     */
    @Test
    public void nestedArrayOfMapEmptyTest()
    {
    	TestData testData = new TestData();
    	Object[] data = testData.createNestedArrayOfMapEmpty();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.lang.Object[] objectArray0 = new java.lang.Object[1];" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.HashMap hashMap0 = new java.util.HashMap();" +
			SystemUtils.LINE_SEPARATOR +
			"objectArray0[0] = hashMap0;" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for nested array containing map with null element.
     */
    @Test
    public void nestedArrayOfMapNullTest()
    {
    	TestData testData = new TestData();
    	Object[] data = testData.createNestedArrayOfMapNull();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.lang.Object[] objectArray0 = new java.lang.Object[1];" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.TreeMap treeMap0 = new java.util.TreeMap();" +
			SystemUtils.LINE_SEPARATOR +
			"treeMap0.put(\"key1\", null);" +
			SystemUtils.LINE_SEPARATOR +
			"objectArray0[0] = treeMap0;" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for nested array containing map with object element.
     */
    @Test
    public void nestedArrayOfMapObjectTest()
    {
    	TestData testData = new TestData();
    	Object[] data = testData.createNestedArrayOfMapObject();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.lang.Object[] objectArray0 = new java.lang.Object[1];" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.LinkedHashMap linkedHashMap0 = new java.util.LinkedHashMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"one\");" +
			SystemUtils.LINE_SEPARATOR +			
			"innerDataHolder0.setImNumber(1.0d);" +
			SystemUtils.LINE_SEPARATOR +
			"linkedHashMap0.put(\"key1\", innerDataHolder0);" +
			SystemUtils.LINE_SEPARATOR +
			"objectArray0[0] = linkedHashMap0;" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for nested array containing map with multiple object elements.
     */
    @Test
    public void nestedArrayOfMapObjectsTest()
    {
    	TestData testData = new TestData();
    	Object[] data = testData.createNestedArrayOfMapObjects();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.lang.Object[] objectArray0 = new java.lang.Object[1];" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.LinkedHashMap linkedHashMap0 = new java.util.LinkedHashMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"one\");" +
			SystemUtils.LINE_SEPARATOR +			
			"innerDataHolder0.setImNumber(1.0d);" +
			SystemUtils.LINE_SEPARATOR +
			"linkedHashMap0.put(\"key1\", innerDataHolder0);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"two\");" +
			SystemUtils.LINE_SEPARATOR +			
			"innerDataHolder1.setImNumber(2.0d);" +
			SystemUtils.LINE_SEPARATOR +
			"linkedHashMap0.put(\"key2\", innerDataHolder1);" +
			SystemUtils.LINE_SEPARATOR +
			"objectArray0[0] = linkedHashMap0;" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

	// Tests for map of array
	// ----------------------
    
    /**
     * Test for nested map containing array containing null object.
     */
    @Test
    public void nestedMapOfArrayNullTest()
    {
    	TestData testData = new TestData();
    	Map<?, ?> data = testData.createNestedMapOfArrayNull();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.HashMap hashMap0 = new java.util.HashMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.lang.Object[] objectArray0 = new java.lang.Object[1];" +
			SystemUtils.LINE_SEPARATOR +
			"objectArray0[0] = null;" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap0.put(\"key1\", objectArray0);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for nested map containing empty array.
     */
    @Test
    public void nestedMapOfArrayEmptyTest()
    {
    	TestData testData = new TestData();
    	Map<?, ?> data = testData.createNestedMapOfArrayEmpty();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.TreeMap treeMap0 = new java.util.TreeMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.lang.Object[] objectArray0 = new java.lang.Object[0];" +
			SystemUtils.LINE_SEPARATOR +
			"treeMap0.put(\"key1\", objectArray0);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for nested map containing primitive array.
     */
   @Test
    public void nestedMapOfArrayPrimitiveTest()
    {
    	TestData testData = new TestData();
    	Map<?, ?> data = testData.createNestedMapOfArrayPrimitive();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.LinkedHashMap linkedHashMap0 = new java.util.LinkedHashMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"int[] intArray0 = new int[1];" +
			SystemUtils.LINE_SEPARATOR +
			"intArray0[0] = 1;" +
			SystemUtils.LINE_SEPARATOR +
			"linkedHashMap0.put(\"key1\", intArray0);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for nested map containing object array.
     */
    @Test
    public void nestedMapOfArrayObjectTest()
    {
    	TestData testData = new TestData();
    	Map<?, ?> data = testData.createNestedMapOfArrayObject();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.LinkedHashMap linkedHashMap0 = new java.util.LinkedHashMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.lang.Object[] objectArray0 = new java.lang.Object[1];" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"one\");" +
			SystemUtils.LINE_SEPARATOR +			
			"innerDataHolder0.setImNumber(1.0d);" +
			SystemUtils.LINE_SEPARATOR +			
			"objectArray0[0] = innerDataHolder0;" +
			SystemUtils.LINE_SEPARATOR +
			"linkedHashMap0.put(\"key1\", objectArray0);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
 
    /**
     * Test for nested map containing object array with multiple elements.
     */
    @Test
    public void nestedMapOfArrayObjectsTest()
    {
    	TestData testData = new TestData();
    	Map<?, ?> data = testData.createNestedMapOfArrayObjects();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.LinkedHashMap linkedHashMap0 = new java.util.LinkedHashMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.lang.Object[] objectArray0 = new java.lang.Object[2];" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"one\");" +
			SystemUtils.LINE_SEPARATOR +			
			"innerDataHolder0.setImNumber(1.0d);" +
			SystemUtils.LINE_SEPARATOR +			
			"objectArray0[0] = innerDataHolder0;" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"two\");" +
			SystemUtils.LINE_SEPARATOR +			
			"innerDataHolder1.setImNumber(2.0d);" +
			SystemUtils.LINE_SEPARATOR +			
			"objectArray0[1] = innerDataHolder1;" +
			SystemUtils.LINE_SEPARATOR +
			"linkedHashMap0.put(\"key1\", objectArray0);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

	// Tests for map of collection
	// ---------------------------
    
    /**
     * Test for nested map containing empty collection.
     */
    @Test
    public void nestedMapOfCollectionEmptyTest()
    {
    	TestData testData = new TestData();
    	Map<?, ?> data = testData.createNestedMapOfCollectionEmpty();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.HashMap hashMap0 = new java.util.HashMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap0.put(\"key1\", arrayList0);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for nested map containing collection with null element.
     */
    @Test
    public void nestedMapOfCollectionNullTest()
    {
    	TestData testData = new TestData();
    	Map<?, ?> data = testData.createNestedMapOfCollectionNull();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.TreeMap treeMap0 = new java.util.TreeMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.TreeSet treeSet0 = new java.util.TreeSet();" +
			SystemUtils.LINE_SEPARATOR +
			"treeSet0.add(null);" +
			SystemUtils.LINE_SEPARATOR +
			"treeMap0.put(\"key1\", treeSet0);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

    /**
     * Test for nested map containing collection with object element.
     */
    @Test
    public void nestedMapOfCollectionObjectTest()
    {
    	TestData testData = new TestData();
    	Map<?, ?> data = testData.createNestedMapOfCollectionObject();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.LinkedHashMap linkedHashMap0 = new java.util.LinkedHashMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.LinkedList linkedList0 = new java.util.LinkedList();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"one\");" +
			SystemUtils.LINE_SEPARATOR +			
			"innerDataHolder0.setImNumber(1.0d);" +
			SystemUtils.LINE_SEPARATOR +			
			"linkedList0.add(innerDataHolder0);" +
			SystemUtils.LINE_SEPARATOR +
			"linkedHashMap0.put(\"key1\", linkedList0);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

    /**
     * Test for nested map containing collection with multiple object elements.
     */
    @Test
    public void nestedMapOfCollectionObjectsTest()
    {
    	TestData testData = new TestData();
    	Map<?, ?> data = testData.createNestedMapOfCollectionObjects();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.LinkedHashMap linkedHashMap0 = new java.util.LinkedHashMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.LinkedList linkedList0 = new java.util.LinkedList();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"one\");" +
			SystemUtils.LINE_SEPARATOR +			
			"innerDataHolder0.setImNumber(1.0d);" +
			SystemUtils.LINE_SEPARATOR +			
			"linkedList0.add(innerDataHolder0);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"two\");" +
			SystemUtils.LINE_SEPARATOR +			
			"innerDataHolder1.setImNumber(2.0d);" +
			SystemUtils.LINE_SEPARATOR +			
			"linkedList0.add(innerDataHolder1);" +
			SystemUtils.LINE_SEPARATOR +
			"linkedHashMap0.put(\"key1\", linkedList0);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

	// Tests for map of map
	// --------------------
    
    /**
     * Test for nested map containing empty map.
     */
     @Test
    public void nestedMapOfMapEmptyTest()
    {
    	TestData testData = new TestData();
    	Map<?, ?> data = testData.createNestedMapOfMapEmpty();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.HashMap hashMap0 = new java.util.HashMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.HashMap hashMap1 = new java.util.HashMap();" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap0.put(\"key1\", hashMap1);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for nested map containing map with null element.
     */
     @Test
    public void nestedMapOfMapNullTest()
    {
    	TestData testData = new TestData();
    	Map<?, ?> data = testData.createNestedMapOfMapNull();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.TreeMap treeMap0 = new java.util.TreeMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.TreeMap treeMap1 = new java.util.TreeMap();" +
			SystemUtils.LINE_SEPARATOR +
			"treeMap1.put(\"innerkey\", null);" +
			SystemUtils.LINE_SEPARATOR +
			"treeMap0.put(\"key1\", treeMap1);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

    /**
     * Test for nested map containing map with object element.
     */
     @Test
    public void nestedMapOfMapObjectTest()
    {
    	TestData testData = new TestData();
    	Map<?, ?> data = testData.createNestedMapOfMapObject();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.LinkedHashMap linkedHashMap0 = new java.util.LinkedHashMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.LinkedHashMap linkedHashMap1 = new java.util.LinkedHashMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"one\");" +
			SystemUtils.LINE_SEPARATOR +			
			"innerDataHolder0.setImNumber(1.0d);" +
			SystemUtils.LINE_SEPARATOR +
			"linkedHashMap1.put(\"innerkey\", innerDataHolder0);" +
			SystemUtils.LINE_SEPARATOR +
			"linkedHashMap0.put(\"key1\", linkedHashMap1);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for nested map containing map with multiple object elements.
     */
    @Test
    public void nestedMapOfMapObjectsTest()
    {
    	TestData testData = new TestData();
    	Map<?, ?> data = testData.createNestedMapOfMapObjects();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.LinkedHashMap linkedHashMap0 = new java.util.LinkedHashMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.LinkedHashMap linkedHashMap1 = new java.util.LinkedHashMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"one\");" +
			SystemUtils.LINE_SEPARATOR +			
			"innerDataHolder0.setImNumber(1.0d);" +
			SystemUtils.LINE_SEPARATOR +
			"linkedHashMap1.put(\"innerkey\", innerDataHolder0);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"two\");" +
			SystemUtils.LINE_SEPARATOR +			
			"innerDataHolder1.setImNumber(2.0d);" +
			SystemUtils.LINE_SEPARATOR +
			"linkedHashMap1.put(\"innerkey2\", innerDataHolder1);" +
			SystemUtils.LINE_SEPARATOR +
			"linkedHashMap0.put(\"key1\", linkedHashMap1);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
}
