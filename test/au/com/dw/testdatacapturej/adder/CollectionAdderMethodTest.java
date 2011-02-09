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
package au.com.dw.testdatacapturej.adder;

import static au.com.dw.testing.AssertUtil.assertEqualsWithoutFormatting;
import static org.junit.Assert.fail;

import org.apache.commons.lang.SystemUtils;
import org.junit.Before;
import org.junit.Test;

import au.com.dw.testdatacapturej.log.ObjectLogger;
import au.com.dw.testdatacapturej.mock.adder.CollectionClassHolder;
import au.com.dw.testdatacapturej.mock.adder.CollectionHolder;
import au.com.dw.testdatacapturej.mock.adder.CollectionHolderWithSetter;
import au.com.dw.testdatacapturej.mock.adder.CollectionHolder_Unconfigured;
import au.com.dw.testdatacapturej.mock.classcheck.Holder;
import au.com.dw.testdatacapturej.mock.dataholder.TestData;
import au.com.dw.testdatacapturej.reflection.BaseReflectionTest;
import au.com.dw.testdatacapturej.reflection.MetadataGenerationHandler;
import au.com.dw.testdatacapturej.reflection.ReflectionHandler;


/**
 * Tests for logging of classes with that use adder methods for adding elements to
 * a collection field, instead of using a setter method for the collection.
 * 
 * The test classes must be configured in the test collection configuration XML file.
 * 
 * @author David Wong
 *
 */
public class CollectionAdderMethodTest extends BaseReflectionTest {

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
     * Test for adder method for Collection field configured in XML config file.
     * Should not generate any constructor or setter method for the collection field,
     * but use the adder method instead.
     * 
     * Note that it should only generate logging for the adder method if the collection
     * contains elements.
     */
    @Test
    public void collectionHolderSimpleTest()
    {
		CollectionHolder holder = new CollectionHolder();
		holder.addCollectionElement("test1");

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
			
			String expected = "au.com.dw.testdatacapturej.mock.adder.CollectionHolder collectionHolder0 = new au.com.dw.testdatacapturej.mock.adder.CollectionHolder();" +
			"collectionHolder0.addCollectionElement(\"test1\");";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

    /**
     * Test for adder method for Collection field configured in XML config file.
     * Should not generate any constructor or setter method for the collection field,
     * but use the adder method instead.
     * 
     * This version adds an object to the collection instead of a simple value.
     */
    @Test
    public void collectionHolderObjectTest()
    {
		CollectionHolder holder = new CollectionHolder();
		
		TestData testData = new TestData();
		holder.addCollectionElement(testData.createSimpleDataHolder());

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
			
			String expected = "au.com.dw.testdatacapturej.mock.adder.CollectionHolder collectionHolder0 = new au.com.dw.testdatacapturej.mock.adder.CollectionHolder();" +
			"au.com.dw.testdatacapturej.mock.dataholder.SimpleDataHolder simpleDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.SimpleDataHolder();" +
			"simpleDataHolder1.setText(\"aaa\");" +
			"simpleDataHolder1.setNumber(1);" +
			"simpleDataHolder1.setCharacter('a');" +
			"simpleDataHolder1.setBool(true);" +
			"simpleDataHolder1.setLongNumber(100L);" +
			"simpleDataHolder1.setPrimitiveFraction(0.1f);" +
			"simpleDataHolder1.setFraction(100.1d);" +
			"collectionHolder0.addCollectionElement(simpleDataHolder1);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for adder method for Collection field configured in XML config file.
     * 
     * This uses an empty collection, so should not generated the adder method in the logging.
     */
    @Test
    public void collectionHolderNullTest()
    {
		CollectionHolder holder = new CollectionHolder();

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
			
			String expected = "au.com.dw.testdatacapturej.mock.adder.CollectionHolder collectionHolder0 = new au.com.dw.testdatacapturej.mock.adder.CollectionHolder();";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

    /**
     * Test for adder method for Collection field configured in XML config file.
     * Should not generate any constructor or setter method for the collection field,
     * but use the adder method instead.
     * 
     * This version uses a test class that has an implementation class for the collection field instead of just
     * the Collection interface and has 2 collection fields, both of which are configured.
     * However only one of the collection fields will have element(s) added to it.
     * 
     * The collection field that contains elements should generated logging that uses the adder
     * method.
     * The empty collection field should not generate any logging.
     */
    @Test
    public void collectionClassHolderTest()
    {
		CollectionClassHolder holder = new CollectionClassHolder();
		holder.addCollectionElement("test1");

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
			
			String expected = "au.com.dw.testdatacapturej.mock.adder.CollectionClassHolder collectionClassHolder0 = new au.com.dw.testdatacapturej.mock.adder.CollectionClassHolder();" +
			"collectionClassHolder0.addCollectionElementToClass(\"test1\");";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for adder method for Collection field configured in XML config file.
     * Should not generate any constructor or setter method for the collection field,
     * but use the adder method instead.
     * 
     * This version uses a test class where the collection field has both adder and setter
     * methods.
     * 
     * The collection field should still generated logging that uses the adder
     * method, since it has been configured.
     */
    @Test
    public void collectionHolderWithSetterTest()
    {
		CollectionHolderWithSetter holder = new CollectionHolderWithSetter();
		holder.addCollectionElement("test1");

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
			
			String expected = "au.com.dw.testdatacapturej.mock.adder.CollectionHolderWithSetter collectionHolderWithSetter0 = new au.com.dw.testdatacapturej.mock.adder.CollectionHolderWithSetter();" +
			"collectionHolderWithSetter0.addCollectionElementInsteadOfSetter(\"test1\");";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Negative test for class that has not been configured.
     * 
     * Should generate the default logging instead of using the adder method.
     */
    @Test
    public void collectionHolderTestNotConfigured()
    {
		CollectionHolder_Unconfigured holder = new CollectionHolder_Unconfigured();
		holder.addCollectionElement("test1");

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
			
			String expected = "au.com.dw.testdatacapturej.mock.adder.CollectionHolder_Unconfigured collectionHolder_Unconfigured0 = new au.com.dw.testdatacapturej.mock.adder.CollectionHolder_Unconfigured();" +
			"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			"arrayList0.add(\"test1\");"+
			"collectionHolder_Unconfigured0.setCollection(arrayList0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
}

