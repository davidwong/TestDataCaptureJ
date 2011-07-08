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
package au.com.dw.testdatacapturej.test;


import org.junit.Before;
import org.junit.Test;

import au.com.dw.testdatacapturej.mock.dataholder.AllDataHolder;
import au.com.dw.testdatacapturej.mock.dataholder.TargetException;
import au.com.dw.testdatacapturej.mock.dataholder.TestData;


/**
 * Tests for logging methods with parameters in order to generate test data from parameters.
 * 
 * Need to point the appropriate aspects to the test method and run to generate various logging.
 * 
 * There are no asserts here yet, just checking the output from the TraceTest or TraceAdaptorTest aspects.
 * If running from eclipse IDE with AJDT, then also verify first that the inner methods are properly advised
 * by the trace test aspects.
 * 
 * This is basically a copy of the ParamTest test case, but with the intercepted methods having varargs as
 * the parameters.
 * 
 * @author David Wong
 *
 */
public class VarargParamTest {

	private AllDataHolder data;

	
    @Before
    public void setUp() throws Exception
    {
    	TestData testData = new TestData();
    	data = testData.createTestDataHolder();
    }

    /**
     * This is just the wrapper to run the unit test, the required test output should be from the
     * parameter from innerSingleVarargParamTest().
     * 
     * Test for a single parameter only.
     * 
     * @throws TargetException
     */
    @Test
    public void singleVarargParamWrapperTest() throws TargetException
    {
    	innerSingleVarargParamTest(data);
    }

    public void innerSingleVarargParamTest(Object... args) throws TargetException
    {
    }

    /**
     * This is just the wrapper to run the unit test, the required test output should be from the
     * parameter from nullInnerVarargParamTest().
     * 
     * Test for a single parameter that is null.
     * 
     * @throws TargetException
     */
    @Test
    public void nullVarargParamWrapperTest() throws TargetException
    {
    	nullInnerVarargParamTest(null);
    }

    public void nullInnerVarargParamTest(Object... args) throws TargetException
    {
    }
    
    /**
     * This is just the wrapper to run the unit test, the required test output should be from the
     * parameter from multipleInnerVarargParamTest().
     * 
     * Test for multiple parameters.
     * 
     * @throws TargetException
     */
    @Test
    public void multipleVarargParamWrapperTest() throws TargetException
    {
    	TestData testData = new TestData();
    	AllDataHolder data2 = testData.createTestDataHolder2();
    	
    	multipleInnerVarargParamTest(data, data2);
    }

    public void multipleInnerVarargParamTest(Object... args) throws TargetException
    {
    }
    
    /**
     * This is just the wrapper to run the unit test, the required test output should be from the
     * parameter from multipleAndNullInnerVarargParamTest().
     * 
     * Test for multiple parameters, of which one is null.
     * 
     * @throws TargetException
     */
    @Test
    public void multipleAndNullVarargParamWrapperTest() throws TargetException
    {
    	multipleAndNullInnerVarargParamTest(data, null);
    }

    public void multipleAndNullInnerVarargParamTest(Object... args) throws TargetException
    {
    }
   
}
