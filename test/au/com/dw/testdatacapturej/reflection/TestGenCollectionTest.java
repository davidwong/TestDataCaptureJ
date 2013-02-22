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

import static au.com.dw.testing.AssertUtil.assertEqualsAnyWithoutFormatting;
import static au.com.dw.testing.AssertUtil.assertEqualsWithoutFormatting;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import au.com.dw.testdatacapturej.log.FormatConstants;
import au.com.dw.testdatacapturej.log.LogBuilder;
import au.com.dw.testdatacapturej.log.ObjectLogger;
import au.com.dw.testdatacapturej.log.RawLogBuilder;
import au.com.dw.testdatacapturej.mock.dataholder.CollectionClassHolder;
import au.com.dw.testdatacapturej.mock.dataholder.CollectionHolder;
import au.com.dw.testdatacapturej.util.Messages;

/**
 * Note that the result checking has to take into account that the iteration over collections may
 * not necessarily guarantee the order of the elements returned by the iterator.
 * 
 * Therefore use assertEqualsAnyWithoutFormatting() instead of just assertEqualsWithoutFormatting().
 * 
 * @author David Wong
 *
 */
public class TestGenCollectionTest extends BaseReflectionTest {

	private ReflectionHandler handler;
	private ObjectLogger logger;
	private LogBuilder builder;
	
	@Before
	public void setUp() throws Exception {
		handler = new MetadataGenerationHandler();
		logger = new ObjectLogger();
		builder = new RawLogBuilder();
	}

	// Testing for the Collection interface, both as the logged object and as a field of a logged object.
	// ************************************

	@Test
	public void testCollectionEmpty()
	{
		Collection<?> collection = createEmptyCollection();
		
		try {
			logger.logObject(builder, handler.handle(collection));
			String result = builder.getLog();
		
			String expected = "java.util.HashSet hashSet0 = new java.util.HashSet();";
	
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
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
			String result = builder.getLog();
		
			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = "java.util.HashSet hashSet0 = new java.util.HashSet();" +
			"hashSet0.add(\"value-2\");" +
			"hashSet0.add(\"value-1\");";
			expectedValues.add(expected1);

			String expected2 = "java.util.HashSet hashSet0 = new java.util.HashSet();" +
			"hashSet0.add(\"value-1\");" +
			"hashSet0.add(\"value-2\");";
			expectedValues.add(expected2);
			
			System.out.println(result);
			assertEqualsAnyWithoutFormatting(expectedValues, result);
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
			String result = builder.getLog();

			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = "java.util.HashSet hashSet0 = new java.util.HashSet();" +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			"innerDataHolder0.setImText(\"immutable-2\");" +
			"innerDataHolder0.setImNumber(2.1d);" +
			"hashSet0.add(innerDataHolder0);" +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			"innerDataHolder1.setImText(\"immutable-1\");" +
			"innerDataHolder1.setImNumber(1.1d);" +
			"hashSet0.add(innerDataHolder1);";
			expectedValues.add(expected1);

			String expected2 = "java.util.HashSet hashSet0 = new java.util.HashSet();" +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			"innerDataHolder0.setImText(\"immutable-1\");" +
			"innerDataHolder0.setImNumber(1.1d);" +
			"hashSet0.add(innerDataHolder0);" +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			"innerDataHolder1.setImText(\"immutable-2\");" +
			"innerDataHolder1.setImNumber(2.1d);" +
			"hashSet0.add(innerDataHolder1);";
			expectedValues.add(expected2);
			
			System.out.println(result);
			assertEqualsAnyWithoutFormatting(expectedValues, result);
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
			String result = builder.getLog();
			
			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = "java.util.HashSet hashSet0 = new java.util.HashSet();" +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			"innerDataHolder0.setImText(\"immutable-2\");" +
			"innerDataHolder0.setImNumber(2.1d);" +
			"hashSet0.add(innerDataHolder0);" +
			"hashSet0.add(1);";
			expectedValues.add(expected1);

			String expected2 = "java.util.HashSet hashSet0 = new java.util.HashSet();" +
			"hashSet0.add(1);" +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			"innerDataHolder0.setImText(\"immutable-2\");" +
			"innerDataHolder0.setImNumber(2.1d);" +
			"hashSet0.add(innerDataHolder0);";
			expectedValues.add(expected2);
			
			System.out.println(result);
			assertEqualsAnyWithoutFormatting(expectedValues, result);
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
			String result = builder.getLog();
		
			String expected = "au.com.dw.testdatacapturej.mock.dataholder.CollectionHolder collectionHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.CollectionHolder();" +
			constructorComment +
			"java.util.HashSet hashSet0 = new java.util.HashSet();" +
			"collectionHolder0.setCollection(hashSet0);";
	
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
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
			String result = builder.getLog();
			
			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = "au.com.dw.testdatacapturej.mock.dataholder.CollectionHolder collectionHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.CollectionHolder();" +
			constructorComment +
			"java.util.HashSet hashSet0 = new java.util.HashSet();" +
			"hashSet0.add(\"value-2\");" +
			"hashSet0.add(\"value-1\");" +
			"collectionHolder0.setCollection(hashSet0);";
			expectedValues.add(expected1);

			String expected2 = "au.com.dw.testdatacapturej.mock.dataholder.CollectionHolder collectionHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.CollectionHolder();" +
			constructorComment +
			"java.util.HashSet hashSet0 = new java.util.HashSet();" +
			"hashSet0.add(\"value-1\");" +
			"hashSet0.add(\"value-2\");" +
			"collectionHolder0.setCollection(hashSet0);";
			expectedValues.add(expected2);
		
			System.out.println(result);
			assertEqualsAnyWithoutFormatting(expectedValues, result);
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
			String result = builder.getLog();
			
			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = "au.com.dw.testdatacapturej.mock.dataholder.CollectionHolder collectionHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.CollectionHolder();" +
			constructorComment +
			"java.util.HashSet hashSet0 = new java.util.HashSet();" +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			"innerDataHolder0.setImText(\"immutable-1\");" +
			"innerDataHolder0.setImNumber(1.1d);" +
			"hashSet0.add(innerDataHolder0);" +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			"innerDataHolder1.setImText(\"immutable-2\");" +
			"innerDataHolder1.setImNumber(2.1d);" +
			"hashSet0.add(innerDataHolder1);" +
			"collectionHolder0.setCollection(hashSet0);";
			expectedValues.add(expected1);

			String expected2 = "au.com.dw.testdatacapturej.mock.dataholder.CollectionHolder collectionHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.CollectionHolder();" +
			constructorComment +
			"java.util.HashSet hashSet0 = new java.util.HashSet();" +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			"innerDataHolder0.setImText(\"immutable-2\");" +
			"innerDataHolder0.setImNumber(2.1d);" +
			"hashSet0.add(innerDataHolder0);" +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			"innerDataHolder1.setImText(\"immutable-1\");" +
			"innerDataHolder1.setImNumber(1.1d);" +
			"hashSet0.add(innerDataHolder1);" +
			"collectionHolder0.setCollection(hashSet0);";
			expectedValues.add(expected2);
			
			System.out.println(result);
			assertEqualsAnyWithoutFormatting(expectedValues, result);
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
			String result = builder.getLog();
			
			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = "au.com.dw.testdatacapturej.mock.dataholder.CollectionHolder collectionHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.CollectionHolder();" +
			constructorComment +
			"java.util.HashSet hashSet0 = new java.util.HashSet();" +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			"innerDataHolder0.setImText(\"immutable-2\");" +
			"innerDataHolder0.setImNumber(2.1d);" +
			"hashSet0.add(innerDataHolder0);" +
			"hashSet0.add(1);" +
			"collectionHolder0.setCollection(hashSet0);";
			expectedValues.add(expected1);

			String expected2 = "au.com.dw.testdatacapturej.mock.dataholder.CollectionHolder collectionHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.CollectionHolder();" +
			constructorComment +
			"java.util.HashSet hashSet0 = new java.util.HashSet();" +
			"hashSet0.add(1);" +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			"innerDataHolder0.setImText(\"immutable-2\");" +
			"innerDataHolder0.setImNumber(2.1d);" +
			"hashSet0.add(innerDataHolder0);" +
			"collectionHolder0.setCollection(hashSet0);";
			expectedValues.add(expected2);
			
			System.out.println(result);
			assertEqualsAnyWithoutFormatting(expectedValues, result);
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
			String result = builder.getLog();
		
			String expected = "java.util.ArrayList arrayList0 = new java.util.ArrayList();";
	
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
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
			String result = builder.getLog();
			
			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = "java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			"arrayList0.add(\"value-2\");" +
			"arrayList0.add(\"value-1\");";
			expectedValues.add(expected1);

			String expected2 = "java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			"arrayList0.add(\"value-1\");" +
			"arrayList0.add(\"value-2\");";
			expectedValues.add(expected2);
			
			System.out.println(result);
			assertEqualsAnyWithoutFormatting(expectedValues, result);
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
			String result = builder.getLog();

			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = "java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			"innerDataHolder0.setImText(\"immutable-2\");" +
			"innerDataHolder0.setImNumber(2.1d);" +
			"arrayList0.add(innerDataHolder0);" +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			"innerDataHolder1.setImText(\"immutable-1\");" +
			"innerDataHolder1.setImNumber(1.1d);" +
			"arrayList0.add(innerDataHolder1);";
			expectedValues.add(expected1);

			String expected2 = "java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			"innerDataHolder0.setImText(\"immutable-1\");" +
			"innerDataHolder0.setImNumber(1.1d);" +
			"arrayList0.add(innerDataHolder0);" +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			"innerDataHolder1.setImText(\"immutable-2\");" +
			"innerDataHolder1.setImNumber(2.1d);" +
			"arrayList0.add(innerDataHolder1);";
			expectedValues.add(expected2);
			
			System.out.println(result);
			assertEqualsAnyWithoutFormatting(expectedValues, result);
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
			String result = builder.getLog();
			
			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = "java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			"innerDataHolder0.setImText(\"immutable-2\");" +
			"innerDataHolder0.setImNumber(2.1d);" +
			"arrayList0.add(innerDataHolder0);" +
			"arrayList0.add(1);";
			expectedValues.add(expected1);

			String expected2 = "java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			"arrayList0.add(1);" +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			"innerDataHolder0.setImText(\"immutable-2\");" +
			"innerDataHolder0.setImNumber(2.1d);" +
			"arrayList0.add(innerDataHolder0);";
			expectedValues.add(expected2);
			
			System.out.println(result);
			assertEqualsAnyWithoutFormatting(expectedValues, result);
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
			String result = builder.getLog();
		
			String expected = "au.com.dw.testdatacapturej.mock.dataholder.CollectionClassHolder collectionClassHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.CollectionClassHolder();" +
			constructorComment +
			"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			"collectionClassHolder0.setArrayList(arrayList0);";
	
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
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
			String result = builder.getLog();
			
			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = "au.com.dw.testdatacapturej.mock.dataholder.CollectionClassHolder collectionClassHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.CollectionClassHolder();" +
			constructorComment +
			"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			"arrayList0.add(\"value-2\");" +
			"arrayList0.add(\"value-1\");" +
			"collectionClassHolder0.setArrayList(arrayList0);";
			expectedValues.add(expected1);

			String expected2 = "au.com.dw.testdatacapturej.mock.dataholder.CollectionClassHolder collectionClassHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.CollectionClassHolder();" +
			constructorComment +
			"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			"arrayList0.add(\"value-1\");" +
			"arrayList0.add(\"value-2\");" +
			"collectionClassHolder0.setArrayList(arrayList0);";
			expectedValues.add(expected2);
		
			System.out.println(result);
			assertEqualsAnyWithoutFormatting(expectedValues, result);
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
			String result = builder.getLog();
			
			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = "au.com.dw.testdatacapturej.mock.dataholder.CollectionClassHolder collectionClassHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.CollectionClassHolder();" +
			constructorComment +
			"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			"innerDataHolder0.setImText(\"immutable-1\");" +
			"innerDataHolder0.setImNumber(1.1d);" +
			"arrayList0.add(innerDataHolder0);" +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			"innerDataHolder1.setImText(\"immutable-2\");" +
			"innerDataHolder1.setImNumber(2.1d);" +
			"arrayList0.add(innerDataHolder1);" +
			"collectionClassHolder0.setArrayList(arrayList0);";
			expectedValues.add(expected1);

			String expected2 = "au.com.dw.testdatacapturej.mock.dataholder.CollectionClassHolder collectionClassHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.CollectionClassHolder();" +
			constructorComment +
			"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			"innerDataHolder0.setImText(\"immutable-2\");" +
			"innerDataHolder0.setImNumber(2.1d);" +
			"arrayList0.add(innerDataHolder0);" +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			"innerDataHolder1.setImText(\"immutable-1\");" +
			"innerDataHolder1.setImNumber(1.1d);" +
			"arrayList0.add(innerDataHolder1);" +
			"collectionClassHolder0.setArrayList(arrayList0);";
			expectedValues.add(expected2);
			
			System.out.println(result);
			assertEqualsAnyWithoutFormatting(expectedValues, result);
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
			String result = builder.getLog();
			
			Collection<String> expectedValues = new ArrayList<String>();
			
			String expected1 = "au.com.dw.testdatacapturej.mock.dataholder.CollectionClassHolder collectionClassHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.CollectionClassHolder();" +
			constructorComment +
			"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			"innerDataHolder0.setImText(\"immutable-2\");" +
			"innerDataHolder0.setImNumber(2.1d);" +
			"arrayList0.add(innerDataHolder0);" +
			"arrayList0.add(1);" +
			"collectionClassHolder0.setArrayList(arrayList0);";
			expectedValues.add(expected1);

			String expected2 = "au.com.dw.testdatacapturej.mock.dataholder.CollectionClassHolder collectionClassHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.CollectionClassHolder();" +
			constructorComment +
			"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			"arrayList0.add(1);" +
			"au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder innerDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder();" +
			"innerDataHolder0.setImText(\"immutable-2\");" +
			"innerDataHolder0.setImNumber(2.1d);" +
			"arrayList0.add(innerDataHolder0);" +
			"collectionClassHolder0.setArrayList(arrayList0);";
			expectedValues.add(expected2);
			
			System.out.println(result);
			assertEqualsAnyWithoutFormatting(expectedValues, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
