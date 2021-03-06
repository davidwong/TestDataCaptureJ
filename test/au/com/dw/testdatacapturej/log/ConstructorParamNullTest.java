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

import au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Object;
import au.com.dw.testdatacapturej.mock.classcheck.NDCNS_String;
import au.com.dw.testdatacapturej.mock.classcheck.NDCNS_UnconfiguredObject;
import au.com.dw.testdatacapturej.reflection.BaseReflectionTest;
import au.com.dw.testdatacapturej.reflection.MetadataGenerationHandler;
import au.com.dw.testdatacapturej.reflection.ReflectionHandler;
import au.com.dw.testdatacapturej.util.Messages;


/**
 * Tests for logging of classes with constructor parameter(s) that have null value.
 * 
 * The test classes must be configured in the constructor configuration XML file.
 * 
 * @author David Wong
 *
 */
public class ConstructorParamNullTest extends BaseReflectionTest {

	private ReflectionHandler handler;
	private ObjectLogger logger;
	private LogBuilder builder;
	
	@Before
	public void setUp() throws Exception {
		handler = new MetadataGenerationHandler();
		logger = new ObjectLogger();
		builder = new RawLogBuilder();
	}
 
    /**
     * Test for constructor with null String parameter configured in XML config file.
     */
    @Test
    public void paramNullStringTest()
    {
		try {
			logger.logObject(builder, handler.handle(new NDCNS_String(null)));
			String result = builder.getLog();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.NDCNS_String nDCNS_String0 = new au.com.dw.testdatacapturej.mock.classcheck.NDCNS_String(null);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for constructor with null Object parameter configured in XML config file.
     */
    @Test
    public void paramNullObjectTest()
    {
		try {
			logger.logObject(builder, handler.handle(new NDCNS_Object(null)));
			String result = builder.getLog();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Object nDCNS_Object0 = new au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Object(null);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }  

    /**
     * Test for constructor with null parameter in nested class.
     */
    @Test
    public void paramNullNestedTest()
    {
    	NDCNS_String nullObjectField = new NDCNS_String(null);
    	
    	try {
			logger.logObject(builder, handler.handle(new NDCNS_Object(nullObjectField)));
			String result = builder.getLog();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.NDCNS_String nDCNS_String0 = new au.com.dw.testdatacapturej.mock.classcheck.NDCNS_String(null);" +
			"au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Object nDCNS_Object0 = new au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Object(nDCNS_String0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for constructor with null parameter in nested class that is not configured.
     */
    @Test
    public void paramNullNotConfiguredNestedTest()
    {
    	NDCNS_UnconfiguredObject nullObjectField = new NDCNS_UnconfiguredObject(null);
    	
    	String constructorComment = FormatConstants.commentLinePrefix + Messages.getMessage(Messages.DEFAULT_CONSTRUCTOR_NOT_EXIST, nullObjectField.getClass().getName());
    	
    	try {
			logger.logObject(builder, handler.handle(new NDCNS_Object(nullObjectField)));
			String result = builder.getLog();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.NDCNS_UnconfiguredObject nDCNS_UnconfiguredObject0 = new au.com.dw.testdatacapturej.mock.classcheck.NDCNS_UnconfiguredObject();" +
			constructorComment +
			"nDCNS_UnconfiguredObject0.setNoSetterField(null);" +
			"au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Object nDCNS_Object0 = new au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Object(nDCNS_UnconfiguredObject0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
 }

