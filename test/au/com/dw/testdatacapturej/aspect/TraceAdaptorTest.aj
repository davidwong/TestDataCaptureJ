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
 * For testing using the TraceAdaptor, using pointcuts for unit tests instead of a real classes.
 * 
 * Run the unit test cases that the pointcuts are pointing to and the test data should be generated and output
 * to the logging library logger specified in TraceAdaptor.
 * 
 * To use this aspect, make sure the debugOn flag is set to true (and that the debugOn flag of the TraceTest aspect
 * is set to false).
 * 
 * @see au.com.dw.testdatacapturej.aspect.TraceTest
 * 
 * @author David Wong
 */
public aspect TraceAdaptorTest extends TraceAdaptor {
	private final static boolean debugOn = true;

	/**
	 * Which test methods to log for parameters
	 */
	protected pointcut loggedParamOperations()
	: execution(* au.com.dw.testdatacapturej.test.*.*ParamTest(..))
	   && if(TraceAdaptorTest.debugOn);

	/**
	 * Which test methods to log for return values
	 */
	protected pointcut loggedReturnOperations()
	: execution(* au.com.dw.testdatacapturej.test.*.*ReturnTest(..))
	   && if(TraceAdaptorTest.debugOn);
}
