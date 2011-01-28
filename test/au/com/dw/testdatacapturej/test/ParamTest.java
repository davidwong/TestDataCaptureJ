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
 * @author David Wong
 *
 */
public class ParamTest {

	private AllDataHolder data;

	
    @Before
    public void setUp() throws Exception
    {
    	TestData testData = new TestData();
    	data = testData.createTestDataHolder();
    }

    /**
     * This is just the wrapper to run the unit test, the required test output should be from the
     * parameter from innerSingleParamTest().
     * 
     * Test for a single parameter only.
     * 
     * @throws TargetException
     */
    @Test
    public void singleParamWrapperTest() throws TargetException
    {
    	innerSingleParamTest(data);
    }

    /**
     * 
     * @param holder
     * @throws TargetException
     */
    public void innerSingleParamTest(AllDataHolder holder) throws TargetException
    {
    }

    /**
     * This is just the wrapper to run the unit test, the required test output should be from the
     * parameter from nullInnerParamTest().
     * 
     * Test for a single parameter that is null.
     * 
     * @throws TargetException
     */
    @Test
    public void nullParamWrapperTest() throws TargetException
    {
    	nullInnerParamTest(null);
    }

    public void nullInnerParamTest(AllDataHolder holder) throws TargetException
    {
    }
    
    /**
     * This is just the wrapper to run the unit test, the required test output should be from the
     * parameter from multipleInnerParamTest().
     * 
     * Test for multiple parameters.
     * 
     * @throws TargetException
     */
    @Test
    public void multipleParamWrapperTest() throws TargetException
    {
    	TestData testData = new TestData();
    	AllDataHolder data2 = testData.createTestDataHolder2();
    	
    	multipleInnerParamTest(data, data2);
    }

    public void multipleInnerParamTest(AllDataHolder holder, AllDataHolder holder2) throws TargetException
    {
    }
    
    /**
     * This is just the wrapper to run the unit test, the required test output should be from the
     * parameter from multipleAndNullInnerParamTest().
     * 
     * Test for multiple parameters, of which one is null.
     * 
     * @throws TargetException
     */
    @Test
    public void multipleAndNullParamWrapperTest() throws TargetException
    {
    	multipleAndNullInnerParamTest(data, null);
    }

    public void multipleAndNullInnerParamTest(AllDataHolder holder, AllDataHolder holder2) throws TargetException
    {
    }
}
