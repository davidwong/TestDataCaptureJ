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


import org.apache.commons.lang.SystemUtils;
import org.junit.Before;
import org.junit.Test;

import au.com.dw.testdatacapturej.log.FormatConstants;
import au.com.dw.testdatacapturej.log.ObjectLogger;
import au.com.dw.testdatacapturej.mock.dataholder.TestData;
import au.com.dw.testdatacapturej.mock.type.reflection.NoMethodContainer;
import au.com.dw.testdatacapturej.mock.type.reflection.NoMethodDataHolder;
import au.com.dw.testdatacapturej.reflection.MetadataGenerationHandler;
import au.com.dw.testdatacapturej.reflection.ReflectionHandler;
import au.com.dw.testdatacapturej.util.Messages;



/**
 * Regression tests for classes that don't have the appropriate constructors and setters. This is to test
 * how to handle code generation for test data logging, when the generated constructors and setters don't
 * actually exist in the class.
 * 
 * @author David Wong
 *
 */
public class TestGenNegativeReflectionTest {

	private ReflectionHandler handler;
	private ObjectLogger logger;
	private StringBuilder builder;
	
	@Before
	public void setUp() throws Exception {
		handler = new MetadataGenerationHandler();
		logger = new ObjectLogger();
		builder = new StringBuilder();
	}
 
    /**
     * Test for no default constructor and no setter methods.
     */
	// @Ignore
    @Test
    public void noMethodTest()
    {
    	TestData testData = new TestData();
    	NoMethodDataHolder data = testData.createNoMethodDataHolder();
    	NoMethodContainer container = new NoMethodContainer(data);

	   	String constructorComment = FormatConstants.commentLinePrefix + Messages.getMessage(Messages.DEFAULT_CONSTRUCTOR_NOT_EXIST, NoMethodContainer.class.getName());
	   	String constructorComment2 = FormatConstants.commentLinePrefix + Messages.getMessage(Messages.DEFAULT_CONSTRUCTOR_NOT_EXIST, NoMethodDataHolder.class.getName());

		try {
			logger.logObject(builder, handler.handle(container));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.type.reflection.NoMethodContainer noMethodContainer0 = new au.com.dw.testdatacapturej.mock.type.reflection.NoMethodContainer();" +
			SystemUtils.LINE_SEPARATOR +
			constructorComment +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.type.reflection.NoMethodDataHolder noMethodDataHolder1 = new au.com.dw.testdatacapturej.mock.type.reflection.NoMethodDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			constructorComment2 +
			SystemUtils.LINE_SEPARATOR +
			"noMethodDataHolder1.setNoSetterField(\"no setter\");" +
			SystemUtils.LINE_SEPARATOR +
			"noMethodContainer0.setHolder(noMethodDataHolder1);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for nested container classes.
     * e.g. map inside array, inside collection, etc
     */
    @Test
    public void nestedContainerTest()
    {
    	TestData testData = new TestData();
    	Collection<?> data = testData.createNestedDataHolderLevel5();

		try {
			logger.logObject(builder, handler.handle(data));
			String result = builder.toString();

			String expected = SystemUtils.LINE_SEPARATOR +
			"java.util.HashSet hashSet0 = new java.util.HashSet();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.lang.Object[] objectArray0 = new java.lang.Object[1];" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.TreeMap treeMap0 = new java.util.TreeMap();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.ArrayList arrayList1 = new java.util.ArrayList();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.HashMap[] hashMapArray1 = new java.util.HashMap[1];" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.HashMap hashMap1 = new java.util.HashMap();" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap1.put(\"inner-map-key\", \"inner-map-value\");" +
			SystemUtils.LINE_SEPARATOR +
			"hashMapArray1[0] = hashMap1;" +
			SystemUtils.LINE_SEPARATOR +
			"arrayList1.add(hashMapArray1);" +
			SystemUtils.LINE_SEPARATOR +
			"treeMap0.put(\"out-map-key\", arrayList1);" +
			SystemUtils.LINE_SEPARATOR +
			"objectArray0[0] = treeMap0;" +
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
}
