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
 * Aspect which is used for the examples for the explanation page in the documentation.
 * 
 * Actually a subAspect of Trace where the pointcut is for a unit test instead of a real class.
 * The output is also just logged to System.out instead of a file.
 * 
 * Run the unit tests that are used as examples for the explanation page. The pointcuts are pointing
 * the test cases that will generate test data to be used as samples (output to the console so that can
 * be copied as program listings for the documentation). Make sure the debugOn flag is set to true.
 * 
 * @author David Wong
 */
public aspect TraceExplanation extends Trace {
	
	// debug flag to enable/disable aspectj weaving
	private final static boolean debugOn = true;
 
	/**
	 * Which test methods to log for handling parameters
	 */
	protected pointcut loggedParamOperations()
	: execution(* au.com.dw.testdatacapturej.explanation.*Test.joinPointParam*(..))
	   && if(TraceExplanation.debugOn);

	/**
	 * Which test methods to log for handling return values
	 */
	protected pointcut loggedReturnOperations()
	: execution(* au.com.dw.testdatacapturej.explanation.*Test.joinPointReturn*())
	   && if(TraceExplanation.debugOn);


	@Override
	protected void doLog(String logContents) {
		System.out.println(logContents);
	}

}
