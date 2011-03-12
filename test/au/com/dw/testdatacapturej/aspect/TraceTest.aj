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
package au.com.dw.testdatacapturej.aspect;

/**
 * SubAspect of Trace where the pointcuts are for unit test cases instead of a real classes.
 * The output is also just logged to System.out instead of a file.
 * 
 * Run the unit tests that the pointcuts are pointing to and the test data should be generated and output
 * to the console.
 * 
 * To use this aspect, make sure the debugOn flag is set to true (and that the debugOn flag of the TraceAdaptorTest aspect
 * is set to false).
 * 
 * @see au.com.dw.testdatacapturej.aspect.TraceAdaptorTest
 * 
 * @author David Wong
 */
public aspect TraceTest extends Trace {
	
	// debug flag to enable/disable aspectj weaving
	private final static boolean debugOn = false;
 
	/**
	 * Which test methods to log for parameters
	 */
	protected pointcut loggedParamOperations()
	: execution(* au.com.dw.testdatacapturej.test.*.*ParamTest(..))
	   && if(TraceTest.debugOn);

	/**
	 * Which test methods to log for return values
	 */
	protected pointcut loggedReturnOperations()
	: execution(* au.com.dw.testdatacapturej.test.*.*ReturnTest(..))
	   && if(TraceTest.debugOn);


	@Override
	protected void doLog(String logContents) {
		System.out.println(logContents);
	}

}
