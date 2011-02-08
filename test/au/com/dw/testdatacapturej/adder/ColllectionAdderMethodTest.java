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

import static au.com.dw.testing.AssertUtil.assertEqualsAny;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;


import org.apache.commons.lang.SystemUtils;
import org.junit.Before;
import org.junit.Test;

import au.com.dw.testdatacapturej.log.ObjectLogger;
import au.com.dw.testdatacapturej.mock.adder.CollectionHolder;
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
 * Tests for logging of classes with that use adder methods for adding elements to
 * a collection field, instead of using a setter method for the collection.
 * 
 * The test classes must be configured in the test setter configuration XML file.
 * 
 * @author David Wong
 *
 */
public class ColllectionAdderMethodTest extends BaseReflectionTest {

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
     * 
     */
    @Test
    public void collectionHolderTest()
    {
		CollectionHolder holder = new CollectionHolder();
		holder.addCollectionElement("test1");

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.adder.CollectionHolder collectionHolder0 = new au.com.dw.testdatacapturej.mock.adder.CollectionHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"collectionHolder0.addCollectionElement(\"test1\");" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

}

