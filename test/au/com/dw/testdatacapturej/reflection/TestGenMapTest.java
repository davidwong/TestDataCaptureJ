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

import static au.com.dw.testing.AssertUtil.assertEqualsAny;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


import org.apache.commons.lang.SystemUtils;
import org.junit.Before;
import org.junit.Test;

import au.com.dw.testdatacapturej.log.FormatConstants;
import au.com.dw.testdatacapturej.log.ObjectLogger;
import au.com.dw.testdatacapturej.mock.dataholder.MapClassHolder;
import au.com.dw.testdatacapturej.mock.dataholder.MapHolder;
import au.com.dw.testdatacapturej.reflection.MetadataGenerationHandler;
import au.com.dw.testdatacapturej.reflection.ReflectionHandler;
import au.com.dw.testdatacapturej.util.Messages;


/**
 * Note that the result checking has to take into account that the iteration over collections may
 * not necessarily guarantee the order of the elements returned by the iterator.
 * 
 * Therefore use assertEqualsAny() instead of just assertEquals().
 * 
 * @author David Wong
 *
 */
public class TestGenMapTest extends BaseReflectionTest {

	private ReflectionHandler handler;
	private ObjectLogger logger;
	private StringBuilder builder;
	
	@Before
	public void setUp() throws Exception {
		handler = new MetadataGenerationHandler();
		logger = new ObjectLogger();
		builder = new StringBuilder();
	}

	// Testing for the Map interface, both as the logged object and as a field of a logged object.
	// *****************************

	@Test
	public void testMapEmpty()
	{
		Map<?, ?> map = createEmptyMap();
		
		try {
			logger.logObject(builder, handler.handle(map));
			String result = builder.toString();
		
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.TreeMap treeMap0 = new java.util.TreeMap();" +
			SystemUtils.LINE_SEPARATOR;

			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testMapBuiltin()
	{
		Map<?, ?> map = createBuiltinMap();
		
		try {
			logger.logObject(builder, handler.handle(map));
			String result = builder.toString();
		
			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = SystemUtils.LINE_SEPARATOR +
			"java.util.TreeMap treeMap0 = new java.util.TreeMap();" +
			SystemUtils.LINE_SEPARATOR +
			"treeMap0.put(\"key1\", \"value1\");" +
			SystemUtils.LINE_SEPARATOR +
			"treeMap0.put(\"key2\", \"value2\");" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected1);

			String expected2 = SystemUtils.LINE_SEPARATOR +
			"java.util.TreeMap treeMap0 = new java.util.TreeMap();" +
			SystemUtils.LINE_SEPARATOR +
			"treeMap0.put(\"key2\", \"value2\");" +
			SystemUtils.LINE_SEPARATOR +
			"treeMap0.put(\"key1\", \"value1\");" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected2);
			
			System.out.println(result);
			assertEqualsAny(expectedValues, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testMapObject()
	{
		Map<?, ?> map = createObjectMap();
		
		try {
			logger.logObject(builder, handler.handle(map));
			String result = builder.toString();

			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = SystemUtils.LINE_SEPARATOR +
			"java.util.TreeMap treeMap0 = new java.util.TreeMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"key-1\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImNumber(1.1d);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"immutable-10\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImNumber(10.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"treeMap0.put(innerDataHolder0, innerDataHolder1);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder2 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder2.setImText(\"key-2\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder2.setImNumber(2.1d);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder3 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder3.setImText(\"immutable-20\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder3.setImNumber(20.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"treeMap0.put(innerDataHolder2, innerDataHolder3);" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected1);

			String expected2 = SystemUtils.LINE_SEPARATOR +
			"java.util.TreeMap treeMap0 = new java.util.TreeMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"key-2\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImNumber(2.1d);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"immutable-20\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImNumber(20.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"treeMap0.put(innerDataHolder0, innerDataHolder1);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder2 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder2.setImText(\"key-1\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder2.setImNumber(1.1d);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder3 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder3.setImText(\"immutable-10\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder3.setImNumber(10.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"treeMap0.put(innerDataHolder2, innerDataHolder3);" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected2);
		
			System.out.println(result);
			assertEqualsAny(expectedValues, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testMapMixed()
	{
		Map<?, ?> map = createMixedMap();
		
		try {
			logger.logObject(builder, handler.handle(map));
			String result = builder.toString();
			
			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = SystemUtils.LINE_SEPARATOR +
			"java.util.TreeMap treeMap0 = new java.util.TreeMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"immutable-10\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImNumber(10.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"treeMap0.put(\"key1\", innerDataHolder0);" +
			SystemUtils.LINE_SEPARATOR +
			"treeMap0.put(\"key2\", 20);" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected1);

			String expected2 = SystemUtils.LINE_SEPARATOR +
			"java.util.TreeMap treeMap0 = new java.util.TreeMap();" +
			SystemUtils.LINE_SEPARATOR +
			"treeMap0.put(\"key2\", 20);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"immutable-10\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImNumber(10.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"treeMap0.put(\"key1\", innerDataHolder0);" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected2);
			
			System.out.println(result);
			assertEqualsAny(expectedValues, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testMapHolderEmpty()
	{
		Map<?, ?> map = createEmptyMap();
		MapHolder holder = new MapHolder(map);

	   	String constructorComment = FormatConstants.commentLinePrefix + Messages.getMessage(Messages.DEFAULT_CONSTRUCTOR_NOT_EXIST, MapHolder.class.getName());

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
		
			String expected = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.MapHolder mapHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.MapHolder();" +
			SystemUtils.LINE_SEPARATOR +
			constructorComment +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.TreeMap treeMap0 = new java.util.TreeMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"mapHolder0.setMap(treeMap0);" +
			SystemUtils.LINE_SEPARATOR;

			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testMapHolderBuiltin()
	{
		Map<?, ?> map = createBuiltinMap();
		MapHolder holder = new MapHolder(map);

	   	String constructorComment = FormatConstants.commentLinePrefix + Messages.getMessage(Messages.DEFAULT_CONSTRUCTOR_NOT_EXIST, MapHolder.class.getName());

	   	try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
			
			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.MapHolder mapHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.MapHolder();" +
			SystemUtils.LINE_SEPARATOR +
			constructorComment +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.TreeMap treeMap0 = new java.util.TreeMap();" +
			SystemUtils.LINE_SEPARATOR +
			"treeMap0.put(\"key1\", \"value1\");" +
			SystemUtils.LINE_SEPARATOR +
			"treeMap0.put(\"key2\", \"value2\");" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"mapHolder0.setMap(treeMap0);" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected1);

			String expected2 = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.MapHolder mapHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.MapHolder();" +
			SystemUtils.LINE_SEPARATOR +
			constructorComment +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.TreeMap treeMap0 = new java.util.TreeMap();" +
			SystemUtils.LINE_SEPARATOR +
			"treeMap0.put(\"key2\", \"value2\");" +
			SystemUtils.LINE_SEPARATOR +
			"treeMap0.put(\"key1\", \"value1\");" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"mapHolder0.setMap(treeMap0);" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected2);
		
			System.out.println(result);
			assertEqualsAny(expectedValues, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testMapHolderObject()
	{
		Map<?, ?> map = createObjectMap();
		MapHolder holder = new MapHolder(map);
		
	   	String constructorComment = FormatConstants.commentLinePrefix + Messages.getMessage(Messages.DEFAULT_CONSTRUCTOR_NOT_EXIST, MapHolder.class.getName());

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
			
			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.MapHolder mapHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.MapHolder();" +
			SystemUtils.LINE_SEPARATOR +
			constructorComment +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.TreeMap treeMap0 = new java.util.TreeMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"key-1\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImNumber(1.1d);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder2 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder2.setImText(\"immutable-10\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder2.setImNumber(10.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"treeMap0.put(innerDataHolder1, innerDataHolder2);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder3 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder3.setImText(\"key-2\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder3.setImNumber(2.1d);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder4 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder4.setImText(\"immutable-20\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder4.setImNumber(20.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"treeMap0.put(innerDataHolder3, innerDataHolder4);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"mapHolder0.setMap(treeMap0);" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected1);

			String expected2 = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.MapHolder mapHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.MapHolder();" +
			SystemUtils.LINE_SEPARATOR +
			constructorComment +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.TreeMap treeMap0 = new java.util.TreeMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"key-2\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImNumber(2.1d);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder2 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder2.setImText(\"immutable-20\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder2.setImNumber(20.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"treeMap0.put(innerDataHolder1, innerDataHolder2);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder3 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder3.setImText(\"key-1\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder3.setImNumber(1.1d);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder4 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder4.setImText(\"immutable-10\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder4.setImNumber(10.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"treeMap0.put(innerDataHolder3, innerDataHolder4);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"mapHolder0.setMap(treeMap0);" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected2);
			
			System.out.println(result);
			assertEqualsAny(expectedValues, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testMapHolderMixed()
	{
		Map<?, ?> map = createMixedMap();
		MapHolder holder = new MapHolder(map);
		
	   	String constructorComment = FormatConstants.commentLinePrefix + Messages.getMessage(Messages.DEFAULT_CONSTRUCTOR_NOT_EXIST, MapHolder.class.getName());

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
			
			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.MapHolder mapHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.MapHolder();" +
			SystemUtils.LINE_SEPARATOR +
			constructorComment +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.TreeMap treeMap0 = new java.util.TreeMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"immutable-10\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImNumber(10.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"treeMap0.put(\"key1\", innerDataHolder1);" +
			SystemUtils.LINE_SEPARATOR +
			"treeMap0.put(\"key2\", 20);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"mapHolder0.setMap(treeMap0);" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected1);

			String expected2 = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.MapHolder mapHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.MapHolder();" +
			SystemUtils.LINE_SEPARATOR +
			constructorComment +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.TreeMap treeMap0 = new java.util.TreeMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"treeMap0.put(\"key2\", 20);" +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"immutable-10\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImNumber(10.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"treeMap0.put(\"key1\", innerDataHolder1);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"mapHolder0.setMap(treeMap0);" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected2);
			
			System.out.println(result);
			assertEqualsAny(expectedValues, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	// Testing for specific Map class, both as the logged object and as a field of a logged object.
	// ******************************

	@Test
	public void testMapClassEmpty()
	{
		HashMap<?, ?> hashMap = createEmptyMapClass();
		
		try {
			logger.logObject(builder, handler.handle(hashMap));
			String result = builder.toString();
		
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.HashMap hashMap0 = new java.util.HashMap();" +
			SystemUtils.LINE_SEPARATOR;

			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testMapClassBuiltin()
	{	
		HashMap<?, ?> hashMap = createBuiltinMapClass();
		
		try {
			logger.logObject(builder, handler.handle(hashMap));
			String result = builder.toString();
			
			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = SystemUtils.LINE_SEPARATOR +
			"java.util.HashMap hashMap0 = new java.util.HashMap();" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap0.put(\"key1\", \"value1\");" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap0.put(\"key2\", \"value2\");" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected1);

			String expected2 = SystemUtils.LINE_SEPARATOR +
			"java.util.HashMap hashMap0 = new java.util.HashMap();" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap0.put(\"key2\", \"value2\");" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap0.put(\"key1\", \"value1\");" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected2);
			
			System.out.println(result);
			assertEqualsAny(expectedValues, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testMapClassObject()
	{
		HashMap<?, ?> hashMap = createObjectMapClass();
		
		try {
			logger.logObject(builder, handler.handle(hashMap));
			String result = builder.toString();

			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = SystemUtils.LINE_SEPARATOR +
			"java.util.HashMap hashMap0 = new java.util.HashMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"key-1\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImNumber(1.1d);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"immutable-10\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImNumber(10.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap0.put(innerDataHolder0, innerDataHolder1);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder2 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder2.setImText(\"key-2\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder2.setImNumber(2.1d);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder3 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder3.setImText(\"immutable-20\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder3.setImNumber(20.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap0.put(innerDataHolder2, innerDataHolder3);" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected1);

			String expected2 = SystemUtils.LINE_SEPARATOR +
			"java.util.HashMap hashMap0 = new java.util.HashMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"key-2\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImNumber(2.1d);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"immutable-20\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImNumber(20.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap0.put(innerDataHolder0, innerDataHolder1);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder2 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder2.setImText(\"key-1\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder2.setImNumber(1.1d);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder3 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder3.setImText(\"immutable-10\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder3.setImNumber(10.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap0.put(innerDataHolder2, innerDataHolder3);" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected2);
			
			System.out.println(result);
			assertEqualsAny(expectedValues, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testMapClassMixed()
	{
		HashMap<?, ?> hashMap = createMixedMapClass();
		
		try {
			logger.logObject(builder, handler.handle(hashMap));
			String result = builder.toString();
			
			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = SystemUtils.LINE_SEPARATOR +
			"java.util.HashMap hashMap0 = new java.util.HashMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"immutable-10\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImNumber(10.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap0.put(\"key1\", innerDataHolder0);" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap0.put(\"key2\", 20);" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected1);

			String expected2 = SystemUtils.LINE_SEPARATOR +
			"java.util.HashMap hashMap0 = new java.util.HashMap();" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap0.put(\"key2\", 20);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"immutable-10\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImNumber(10.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap0.put(\"key1\", innerDataHolder0);" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected2);
			
			System.out.println(result);
			assertEqualsAny(expectedValues, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testMapHolderClassEmpty()
	{
		HashMap<?, ?> hashMap = createEmptyMapClass();
		MapClassHolder holder = new MapClassHolder(hashMap);

	   	String constructorComment = FormatConstants.commentLinePrefix + Messages.getMessage(Messages.DEFAULT_CONSTRUCTOR_NOT_EXIST, MapClassHolder.class.getName());

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
		
			String expected = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.MapClassHolder mapClassHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.MapClassHolder();" +
			SystemUtils.LINE_SEPARATOR +
			constructorComment +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.HashMap hashMap0 = new java.util.HashMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"mapClassHolder0.setMap(hashMap0);" +
			SystemUtils.LINE_SEPARATOR;

			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testMapHolderClassBuiltin()
	{
		HashMap<?, ?> hashMap = createBuiltinMapClass();
		MapClassHolder holder = new MapClassHolder(hashMap);

	   	String constructorComment = FormatConstants.commentLinePrefix + Messages.getMessage(Messages.DEFAULT_CONSTRUCTOR_NOT_EXIST, MapClassHolder.class.getName());

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
			
			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.MapClassHolder mapClassHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.MapClassHolder();" +
			SystemUtils.LINE_SEPARATOR +
			constructorComment +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.HashMap hashMap0 = new java.util.HashMap();" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap0.put(\"key1\", \"value1\");" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap0.put(\"key2\", \"value2\");" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"mapClassHolder0.setMap(hashMap0);" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected1);

			String expected2 = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.MapClassHolder mapClassHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.MapClassHolder();" +
			SystemUtils.LINE_SEPARATOR +
			constructorComment +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.HashMap hashMap0 = new java.util.HashMap();" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap0.put(\"key2\", \"value2\");" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap0.put(\"key1\", \"value1\");" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"mapClassHolder0.setMap(hashMap0);" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected2);
		
			System.out.println(result);
			assertEqualsAny(expectedValues, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testMapHolderClassObject()
	{
		HashMap<?, ?> hashMap = createObjectMapClass();
		MapClassHolder holder = new MapClassHolder(hashMap);

	   	String constructorComment = FormatConstants.commentLinePrefix + Messages.getMessage(Messages.DEFAULT_CONSTRUCTOR_NOT_EXIST, MapClassHolder.class.getName());

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
			
			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.MapClassHolder mapClassHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.MapClassHolder();" +
			SystemUtils.LINE_SEPARATOR +
			constructorComment +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.HashMap hashMap0 = new java.util.HashMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"key-1\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImNumber(1.1d);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder2 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder2.setImText(\"immutable-10\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder2.setImNumber(10.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap0.put(innerDataHolder1, innerDataHolder2);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder3 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder3.setImText(\"key-2\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder3.setImNumber(2.1d);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder4 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder4.setImText(\"immutable-20\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder4.setImNumber(20.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap0.put(innerDataHolder3, innerDataHolder4);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"mapClassHolder0.setMap(hashMap0);" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected1);

			String expected2 = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.MapClassHolder mapClassHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.MapClassHolder();" +
			SystemUtils.LINE_SEPARATOR +
			constructorComment +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.HashMap hashMap0 = new java.util.HashMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"key-2\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImNumber(2.1d);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder2 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder2.setImText(\"immutable-20\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder2.setImNumber(20.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap0.put(innerDataHolder1, innerDataHolder2);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder3 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder3.setImText(\"key-1\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder3.setImNumber(1.1d);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder4 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder4.setImText(\"immutable-10\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder4.setImNumber(10.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap0.put(innerDataHolder3, innerDataHolder4);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"mapClassHolder0.setMap(hashMap0);" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected2);
			
			System.out.println(result);
			assertEqualsAny(expectedValues, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testMapHolderClassMixed()
	{
		HashMap<?, ?> hashMap = createMixedMapClass();
		MapClassHolder holder = new MapClassHolder(hashMap);
		
	   	String constructorComment = FormatConstants.commentLinePrefix + Messages.getMessage(Messages.DEFAULT_CONSTRUCTOR_NOT_EXIST, MapClassHolder.class.getName());

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
			
			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.MapClassHolder mapClassHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.MapClassHolder();" +
			SystemUtils.LINE_SEPARATOR +
			constructorComment +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.HashMap hashMap0 = new java.util.HashMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"immutable-10\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImNumber(10.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap0.put(\"key1\", innerDataHolder1);" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap0.put(\"key2\", 20);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"mapClassHolder0.setMap(hashMap0);" +			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected1);

			String expected2 = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.MapClassHolder mapClassHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.MapClassHolder();" +
			SystemUtils.LINE_SEPARATOR +
			constructorComment +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.HashMap hashMap0 = new java.util.HashMap();" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap0.put(\"key2\", 20);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"immutable-10\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImNumber(10.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap0.put(\"key1\", innerDataHolder1);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"mapClassHolder0.setMap(hashMap0);" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected2);			
			System.out.println(result);
			assertEqualsAny(expectedValues, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
