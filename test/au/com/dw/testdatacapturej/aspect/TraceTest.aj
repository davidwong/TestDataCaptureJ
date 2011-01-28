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

import au.com.dw.testdatacapturej.test.ParamTest;
import au.com.dw.testdatacapturej.test.ReturnTest;

/**
 * SubAspect of Trace where the pointcut is for a JUnit test instead of a real class.
 * The output is also just logged to System.out instead of a file.
 * 
 * Run the JUnit test that the pointcut is pointing to and the test data should be generated and output
 * to the console.
 */
public aspect TraceTest extends Trace {
	
	// debug flag to enable/disable aspectj weaving
	private final static boolean debugOn = true;
 
	/**
	 * Which test methods to log
	 */
	protected pointcut loggedParamOperations()
	: execution(* au.com.dw.testdatacapturej.test.*.*ParamTest(..))
	   && if(TraceTest.debugOn);

	/**
	 * Which test methods to log
	 */
	protected pointcut loggedReturnOperations()
	: execution(* au.com.dw.testdatacapturej.test.*.*ReturnTest(..))
	   && if(TraceTest.debugOn);
	
	/**
	 * Default logging method.
	 */
	protected void log(String msg) {
		System.out.println(msg);
	}

}
