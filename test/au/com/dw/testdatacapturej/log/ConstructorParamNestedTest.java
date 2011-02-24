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

import org.junit.Before;
import org.junit.Test;

import au.com.dw.testdatacapturej.mock.classcheck.Holder;
import au.com.dw.testdatacapturej.mock.classcheck.NDCNS_BooleanFloat;
import au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Int;
import au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Object;
import au.com.dw.testdatacapturej.mock.classcheck.NDCNS_UnconfiguredObject;
import au.com.dw.testdatacapturej.reflection.BaseReflectionTest;
import au.com.dw.testdatacapturej.reflection.MetadataGenerationHandler;
import au.com.dw.testdatacapturej.reflection.ReflectionHandler;
import au.com.dw.testdatacapturej.util.Messages;


/**
 * Tests for logging of classes with constructor parameter(s) that are nested within each other. Also these
 * tests use the NDCNS (No Default Constructor No Setter) test objects as the initial logger object instead
 * of being inside a holder class.
 * 
 * The test classes must be configured in the constructor configuration XML file.
 * 
 * @author David Wong
 *
 */
public class ConstructorParamNestedTest extends BaseReflectionTest {

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
     * Test for 2 objects, both with parameterized constructors configured in the XML file. One object is a
     * field (and constructor parameter) of the other.
     */
    @Test
    public void configuredConstructorInsideConfiguredConstructorTest()
    {
		NDCNS_BooleanFloat booleanFloatFieldObject = new NDCNS_BooleanFloat(createBoolean(), createFloat());

		try {
			logger.logObject(builder, handler.handle(new NDCNS_Object(booleanFloatFieldObject)));
			String result = builder.toString();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.NDCNS_BooleanFloat nDCNS_BooleanFloat0 = new au.com.dw.testdatacapturej.mock.classcheck.NDCNS_BooleanFloat(true, 10.1f);" +
			"au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Object nDCNS_Object0 = new au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Object(nDCNS_BooleanFloat0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for 2 objects, one with parameterized constructors configured in the XML file and the other without.
     * One object is a field (and constructor parameter) of the other.
     * 
     * The outer object is the one that is not configured.
     */
    @Test
    public void configuredConstructorInsideUnconfiguredConstructorTest()
    {
		NDCNS_BooleanFloat booleanFloatFieldObject = new NDCNS_BooleanFloat(createBoolean(), createFloat());

	   	String constructorComment = FormatConstants.commentLinePrefix + Messages.getMessage(Messages.DEFAULT_CONSTRUCTOR_NOT_EXIST, NDCNS_UnconfiguredObject.class.getName());

		try {
			logger.logObject(builder, handler.handle(new NDCNS_UnconfiguredObject(booleanFloatFieldObject)));
			String result = builder.toString();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.NDCNS_UnconfiguredObject nDCNS_UnconfiguredObject0 = new au.com.dw.testdatacapturej.mock.classcheck.NDCNS_UnconfiguredObject();" +
			constructorComment +
			"au.com.dw.testdatacapturej.mock.classcheck.NDCNS_BooleanFloat nDCNS_BooleanFloat0 = new au.com.dw.testdatacapturej.mock.classcheck.NDCNS_BooleanFloat(true, 10.1f);" +
			"nDCNS_UnconfiguredObject0.setNoSetterField(nDCNS_BooleanFloat0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for 2 objects, one with parameterized constructors configured in the XML file and the other without.
     * One object is a field (and constructor parameter) of the other.
     * 
     * The inner object (the field and construction parameter) is the one that is not configured.
     */
    @Test
    public void UnconfiguredConstructorInsideConfiguredConstructorTest()
    {
		NDCNS_Int intFieldObject = new NDCNS_Int(createInt());

	   	String constructorComment = FormatConstants.commentLinePrefix + Messages.getMessage(Messages.DEFAULT_CONSTRUCTOR_NOT_EXIST, NDCNS_Int.class.getName());

		try {
			logger.logObject(builder, handler.handle(new NDCNS_Object(intFieldObject)));
			String result = builder.toString();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Int nDCNS_Int0 = new au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Int();" +
			constructorComment +
			"nDCNS_Int0.setNoSetterField(2);" +
			"au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Object nDCNS_Object0 = new au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Object(nDCNS_Int0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for 2 objects, neither of which have parameterized constructors configured in the XML file and the other without.
     * One object is a field (and constructor parameter) of the other.
     * 
     */
    @Test
    public void UnconfiguredConstructorInsideUnconfiguredConstructorTest()
    {
		NDCNS_Int intFieldObject = new NDCNS_Int(createInt());

	   	String constructorComment = FormatConstants.commentLinePrefix + Messages.getMessage(Messages.DEFAULT_CONSTRUCTOR_NOT_EXIST, NDCNS_UnconfiguredObject.class.getName());
	   	String constructorComment2 = FormatConstants.commentLinePrefix + Messages.getMessage(Messages.DEFAULT_CONSTRUCTOR_NOT_EXIST, NDCNS_Int.class.getName());

		try {
			logger.logObject(builder, handler.handle(new NDCNS_UnconfiguredObject(intFieldObject)));
			String result = builder.toString();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.NDCNS_UnconfiguredObject nDCNS_UnconfiguredObject0 = new au.com.dw.testdatacapturej.mock.classcheck.NDCNS_UnconfiguredObject();" +
			constructorComment +
			"au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Int nDCNS_Int0 = new au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Int();" +
			constructorComment2 +
			"nDCNS_Int0.setNoSetterField(2);" +
			"nDCNS_UnconfiguredObject0.setNoSetterField(nDCNS_Int0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
 }

