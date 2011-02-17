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
 * For au.com.dw.testing TraceAdaptor, using a pointcut for a JUnit test instead of a real class.
 * 
 * Run the JUnit test that the pointcut is pointing to and the test data should be generated and output
 * to the logging library logger specified in TraceAdaptor.
 */
public aspect TraceAdaptorTest extends TraceAdaptor {
	private final static boolean debugOn = false;

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
