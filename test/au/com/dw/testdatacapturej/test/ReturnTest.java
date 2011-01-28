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
 * Tests for logging methods with return values in order to generate test data from it.
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
public class ReturnTest {

	private AllDataHolder data;

	
    @Before
    public void setUp() throws Exception
    {
    	TestData testData = new TestData();
    	data = testData.createTestDataHolder();
    }

    /**
     * This is just the wrapper to run the unit test, the required test output should be from the
     * parameter from innerReturnTest().
     * 
     * Test for return value.
     * 
     * @throws TargetException
     */
    @Test
    public void returnWrapperTest() throws TargetException
    {
        AllDataHolder result = innerReturnTest();
    }

    public AllDataHolder innerReturnTest() throws TargetException
    {
         return data;
    }
    
    /**
     * This is just the wrapper to run the unit test, the required test output should be from the
     * parameter from nullInnerReturnTest().
     * 
     * Test for null return value.
     * 
     * @throws TargetException
     */
    @Test
    public void nullReturnWrapperTest() throws TargetException
    {
        AllDataHolder result = nullInnerReturnTest();
    }

    public AllDataHolder nullInnerReturnTest() throws TargetException
    {
         return null;
    }    
}
