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


import org.apache.commons.lang.SystemUtils;
import org.junit.Before;
import org.junit.Test;

import au.com.dw.testdatacapturej.log.FormatConstants;
import au.com.dw.testdatacapturej.log.ObjectLogger;
import au.com.dw.testdatacapturej.mock.dataholder.CollectionClassHolder;
import au.com.dw.testdatacapturej.mock.dataholder.CollectionHolder;
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
public class TestGenCollectionTest extends BaseReflectionTest {

	private ReflectionHandler handler;
	private ObjectLogger logger;
	private StringBuilder builder;
	
	@Before
	public void setUp() throws Exception {
		handler = new MetadataGenerationHandler();
		logger = new ObjectLogger();
		builder = new StringBuilder();
	}

	// Testing for the Collection interface, both as the logged object and as a field of a logged object.
	// ************************************

	@Test
	public void testCollectionEmpty()
	{
		Collection<?> collection = createEmptyCollection();
		
		try {
			logger.logObject(builder, handler.handle(collection));
			String result = builder.toString();
		
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.HashSet hashSet0 = new java.util.HashSet();" +
			SystemUtils.LINE_SEPARATOR;
	
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testCollectionBuiltin()
	{
		Collection<?> collection = createBuiltinCollection();
		
		try {
			logger.logObject(builder, handler.handle(collection));
			String result = builder.toString();
		
			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = SystemUtils.LINE_SEPARATOR +
			"java.util.HashSet hashSet0 = new java.util.HashSet();" +
			SystemUtils.LINE_SEPARATOR +
			"hashSet0.add(\"value-2\");" +
			SystemUtils.LINE_SEPARATOR +
			"hashSet0.add(\"value-1\");" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected1);

			String expected2 = SystemUtils.LINE_SEPARATOR +
			"java.util.HashSet hashSet0 = new java.util.HashSet();" +
			SystemUtils.LINE_SEPARATOR +
			"hashSet0.add(\"value-1\");" +
			SystemUtils.LINE_SEPARATOR +
			"hashSet0.add(\"value-2\");" +
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
	public void testCollectionObject()
	{
		Collection<?> collection = createObjectCollection();
		
		try {
			logger.logObject(builder, handler.handle(collection));
			String result = builder.toString();

			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = SystemUtils.LINE_SEPARATOR +
			"java.util.HashSet hashSet0 = new java.util.HashSet();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"immutable-2\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImNumber(2.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"hashSet0.add(innerDataHolder0);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"immutable-1\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImNumber(1.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"hashSet0.add(innerDataHolder1);" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected1);

			String expected2 = SystemUtils.LINE_SEPARATOR +
			"java.util.HashSet hashSet0 = new java.util.HashSet();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"immutable-1\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImNumber(1.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"hashSet0.add(innerDataHolder0);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"immutable-2\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImNumber(2.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"hashSet0.add(innerDataHolder1);" +
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
	public void testCollectionMixed()
	{
		Collection<?> collection = createMixedCollection();
		
		try {
			logger.logObject(builder, handler.handle(collection));
			String result = builder.toString();
			
			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = SystemUtils.LINE_SEPARATOR +
			"java.util.HashSet hashSet0 = new java.util.HashSet();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"immutable-2\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImNumber(2.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"hashSet0.add(innerDataHolder0);" +
			SystemUtils.LINE_SEPARATOR +
			"hashSet0.add(1);" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected1);

			String expected2 = SystemUtils.LINE_SEPARATOR +
			"java.util.HashSet hashSet0 = new java.util.HashSet();" +
			SystemUtils.LINE_SEPARATOR +
			"hashSet0.add(1);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"immutable-2\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImNumber(2.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"hashSet0.add(innerDataHolder0);" +
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
	public void testCollectionHolderEmpty()
	{
		Collection<?> collection = createEmptyCollection();
		CollectionHolder holder = new CollectionHolder(collection);

	   	String constructorComment = FormatConstants.commentLinePrefix + Messages.getMessage(Messages.DEFAULT_CONSTRUCTOR_NOT_EXIST, CollectionHolder.class.getName());
		
		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
		
			String expected = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.CollectionHolder collectionHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.CollectionHolder();" +
			SystemUtils.LINE_SEPARATOR +
			constructorComment +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.HashSet hashSet0 = new java.util.HashSet();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"collectionHolder0.setCollection(hashSet0);" +
			SystemUtils.LINE_SEPARATOR;
	
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testCollectionHolderBuiltin()
	{
		Collection<?> collection = createBuiltinCollection();
		CollectionHolder holder = new CollectionHolder(collection);

	   	String constructorComment = FormatConstants.commentLinePrefix + Messages.getMessage(Messages.DEFAULT_CONSTRUCTOR_NOT_EXIST, CollectionHolder.class.getName());

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
			
			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.CollectionHolder collectionHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.CollectionHolder();" +
			SystemUtils.LINE_SEPARATOR +
			constructorComment +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.HashSet hashSet0 = new java.util.HashSet();" +
			SystemUtils.LINE_SEPARATOR +
			"hashSet0.add(\"value-2\");" +
			SystemUtils.LINE_SEPARATOR +
			"hashSet0.add(\"value-1\");" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"collectionHolder0.setCollection(hashSet0);" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected1);

			String expected2 = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.CollectionHolder collectionHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.CollectionHolder();" +
			SystemUtils.LINE_SEPARATOR +
			constructorComment +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.HashSet hashSet0 = new java.util.HashSet();" +
			SystemUtils.LINE_SEPARATOR +
			"hashSet0.add(\"value-1\");" +
			SystemUtils.LINE_SEPARATOR +
			"hashSet0.add(\"value-2\");" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"collectionHolder0.setCollection(hashSet0);" +
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
	public void testCollectionHolderObject()
	{
		Collection<?> collection = createObjectCollection();
		CollectionHolder holder = new CollectionHolder(collection);

	   	String constructorComment = FormatConstants.commentLinePrefix + Messages.getMessage(Messages.DEFAULT_CONSTRUCTOR_NOT_EXIST, CollectionHolder.class.getName());

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
			
			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.CollectionHolder collectionHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.CollectionHolder();" +
			SystemUtils.LINE_SEPARATOR +
			constructorComment +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.HashSet hashSet0 = new java.util.HashSet();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"immutable-1\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImNumber(1.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"hashSet0.add(innerDataHolder1);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder2 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder2.setImText(\"immutable-2\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder2.setImNumber(2.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"hashSet0.add(innerDataHolder2);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"collectionHolder0.setCollection(hashSet0);" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected1);

			String expected2 = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.CollectionHolder collectionHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.CollectionHolder();" +
			SystemUtils.LINE_SEPARATOR +
			constructorComment +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.HashSet hashSet0 = new java.util.HashSet();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"immutable-2\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImNumber(2.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"hashSet0.add(innerDataHolder1);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder2 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder2.setImText(\"immutable-1\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder2.setImNumber(1.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"hashSet0.add(innerDataHolder2);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"collectionHolder0.setCollection(hashSet0);" +
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
	public void testCollectionHolderMixed()
	{
		Collection<?> collection = createMixedCollection();
		CollectionHolder holder = new CollectionHolder(collection);

	   	String constructorComment = FormatConstants.commentLinePrefix + Messages.getMessage(Messages.DEFAULT_CONSTRUCTOR_NOT_EXIST, CollectionHolder.class.getName());

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
			
			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.CollectionHolder collectionHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.CollectionHolder();" +
			SystemUtils.LINE_SEPARATOR +
			constructorComment +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.HashSet hashSet0 = new java.util.HashSet();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"immutable-2\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImNumber(2.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"hashSet0.add(innerDataHolder1);" +
			SystemUtils.LINE_SEPARATOR +
			"hashSet0.add(1);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"collectionHolder0.setCollection(hashSet0);" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected1);

			String expected2 = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.CollectionHolder collectionHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.CollectionHolder();" +
			SystemUtils.LINE_SEPARATOR +
			constructorComment +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.HashSet hashSet0 = new java.util.HashSet();" +
			SystemUtils.LINE_SEPARATOR +
			"hashSet0.add(1);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"immutable-2\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImNumber(2.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"hashSet0.add(innerDataHolder1);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"collectionHolder0.setCollection(hashSet0);" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected2);
			
			System.out.println(result);
			assertEqualsAny(expectedValues, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	// Testing for specific Collection class, both as the logged object and as a field of a logged object.
	// *************************************

	@Test
	public void testCollectionClassEmpty()
	{
		ArrayList<?> arrayList = createEmptyCollectionClass();
		
		try {
			logger.logObject(builder, handler.handle(arrayList));
			String result = builder.toString();
		
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			SystemUtils.LINE_SEPARATOR;
	
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testCollectionClassBuiltin()
	{	
		ArrayList<?> arrayList = createBuiltinCollectionClass();
		
		try {
			logger.logObject(builder, handler.handle(arrayList));
			String result = builder.toString();
			
			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = SystemUtils.LINE_SEPARATOR +
			"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			SystemUtils.LINE_SEPARATOR +
			"arrayList0.add(\"value-2\");" +
			SystemUtils.LINE_SEPARATOR +
			"arrayList0.add(\"value-1\");" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected1);

			String expected2 = SystemUtils.LINE_SEPARATOR +
			"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			SystemUtils.LINE_SEPARATOR +
			"arrayList0.add(\"value-1\");" +
			SystemUtils.LINE_SEPARATOR +
			"arrayList0.add(\"value-2\");" +
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
	public void testCollectionClassObject()
	{
		ArrayList<?> arrayList = createObjectCollectionClass();
		
		try {
			logger.logObject(builder, handler.handle(arrayList));
			String result = builder.toString();

			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = SystemUtils.LINE_SEPARATOR +
			"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"immutable-2\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImNumber(2.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"arrayList0.add(innerDataHolder0);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"immutable-1\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImNumber(1.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"arrayList0.add(innerDataHolder1);" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected1);

			String expected2 = SystemUtils.LINE_SEPARATOR +
			"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"immutable-1\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImNumber(1.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"arrayList0.add(innerDataHolder0);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"immutable-2\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImNumber(2.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"arrayList0.add(innerDataHolder1);" +
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
	public void testCollectionClassMixed()
	{
		ArrayList<?> arrayList = createMixedCollectionClass();
		
		try {
			logger.logObject(builder, handler.handle(arrayList));
			String result = builder.toString();
			
			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = SystemUtils.LINE_SEPARATOR +
			"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"immutable-2\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImNumber(2.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"arrayList0.add(innerDataHolder0);" +
			SystemUtils.LINE_SEPARATOR +
			"arrayList0.add(1);" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected1);

			String expected2 = SystemUtils.LINE_SEPARATOR +
			"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			SystemUtils.LINE_SEPARATOR +
			"arrayList0.add(1);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImText(\"immutable-2\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder0.setImNumber(2.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"arrayList0.add(innerDataHolder0);" +
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
	public void testCollectionHolderClassEmpty()
	{
		ArrayList<?> arrayList = createEmptyCollectionClass();
		CollectionClassHolder holder = new CollectionClassHolder(arrayList);

	   	String constructorComment = FormatConstants.commentLinePrefix + Messages.getMessage(Messages.DEFAULT_CONSTRUCTOR_NOT_EXIST, CollectionClassHolder.class.getName());

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
		
			String expected = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.CollectionClassHolder collectionClassHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.CollectionClassHolder();" +
			SystemUtils.LINE_SEPARATOR +
			constructorComment +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"collectionClassHolder0.setArrayList(arrayList0);" +
			SystemUtils.LINE_SEPARATOR;
	
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testCollectionHolderClassBuiltin()
	{
		ArrayList<?> arrayList = createBuiltinCollectionClass();
		CollectionClassHolder holder = new CollectionClassHolder(arrayList);

	   	String constructorComment = FormatConstants.commentLinePrefix + Messages.getMessage(Messages.DEFAULT_CONSTRUCTOR_NOT_EXIST, CollectionClassHolder.class.getName());

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
			
			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = SystemUtils.LINE_SEPARATOR +
				"au.com.dw.testdatacapturej.mock.dataholder.CollectionClassHolder collectionClassHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.CollectionClassHolder();" +
				SystemUtils.LINE_SEPARATOR +
				constructorComment +
				SystemUtils.LINE_SEPARATOR +
				SystemUtils.LINE_SEPARATOR +
				"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
				SystemUtils.LINE_SEPARATOR +
				"arrayList0.add(\"value-2\");" +
				SystemUtils.LINE_SEPARATOR +
				"arrayList0.add(\"value-1\");" +
				SystemUtils.LINE_SEPARATOR +
				SystemUtils.LINE_SEPARATOR +
				"collectionClassHolder0.setArrayList(arrayList0);" +
				SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected1);

			String expected2 = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.CollectionClassHolder collectionClassHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.CollectionClassHolder();" +
			SystemUtils.LINE_SEPARATOR +
			constructorComment +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			SystemUtils.LINE_SEPARATOR +
			"arrayList0.add(\"value-1\");" +
			SystemUtils.LINE_SEPARATOR +
			"arrayList0.add(\"value-2\");" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"collectionClassHolder0.setArrayList(arrayList0);" +
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
	public void testCollectionHolderClassObject()
	{
		ArrayList<?> arrayList = createObjectCollectionClass();
		CollectionClassHolder holder = new CollectionClassHolder(arrayList);

	   	String constructorComment = FormatConstants.commentLinePrefix + Messages.getMessage(Messages.DEFAULT_CONSTRUCTOR_NOT_EXIST, CollectionClassHolder.class.getName());

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
			
			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.CollectionClassHolder collectionClassHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.CollectionClassHolder();" +
			SystemUtils.LINE_SEPARATOR +
			constructorComment +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"immutable-1\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImNumber(1.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"arrayList0.add(innerDataHolder1);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder2 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder2.setImText(\"immutable-2\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder2.setImNumber(2.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"arrayList0.add(innerDataHolder2);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"collectionClassHolder0.setArrayList(arrayList0);" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected1);

			String expected2 = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.CollectionClassHolder collectionClassHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.CollectionClassHolder();" +
			SystemUtils.LINE_SEPARATOR +
			constructorComment +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"immutable-2\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImNumber(2.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"arrayList0.add(innerDataHolder1);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder2 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder2.setImText(\"immutable-1\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder2.setImNumber(1.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"arrayList0.add(innerDataHolder2);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"collectionClassHolder0.setArrayList(arrayList0);" +
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
	public void testCollectionHolderClassMixed()
	{
		ArrayList<?> arrayList = createMixedCollectionClass();
		CollectionClassHolder holder = new CollectionClassHolder(arrayList);

	   	String constructorComment = FormatConstants.commentLinePrefix + Messages.getMessage(Messages.DEFAULT_CONSTRUCTOR_NOT_EXIST, CollectionClassHolder.class.getName());

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
			
			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.CollectionClassHolder collectionClassHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.CollectionClassHolder();" +
			SystemUtils.LINE_SEPARATOR +
			constructorComment +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"immutable-2\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImNumber(2.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"arrayList0.add(innerDataHolder1);" +
			SystemUtils.LINE_SEPARATOR +
			"arrayList0.add(1);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"collectionClassHolder0.setArrayList(arrayList0);" +
			SystemUtils.LINE_SEPARATOR;
			expectedValues.add(expected1);

			String expected2 = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.CollectionClassHolder collectionClassHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.CollectionClassHolder();" +
			SystemUtils.LINE_SEPARATOR +
			constructorComment +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			SystemUtils.LINE_SEPARATOR +
			"arrayList0.add(1);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImText(\"immutable-2\");" +
			SystemUtils.LINE_SEPARATOR +
			"innerDataHolder1.setImNumber(2.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"arrayList0.add(innerDataHolder1);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"collectionClassHolder0.setArrayList(arrayList0);" +
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
