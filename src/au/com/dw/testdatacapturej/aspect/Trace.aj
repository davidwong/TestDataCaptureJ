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

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

import au.com.dw.testdatacapturej.builder.MethodBuilder;
import au.com.dw.testdatacapturej.log.FormatConstants;
import au.com.dw.testdatacapturej.log.ObjectLogger;
import au.com.dw.testdatacapturej.reflection.ReflectionHandler;
import au.com.dw.testdatacapturej.reflection.MetadataGenerationHandler;

/**
 * Base aspect for tracing, similar to many other aspectj tracing modules available.
 * 
 * This version is meant for code generation logging for au.com.dw.testing.
 * The log should be in a java code format that can be used for unit au.com.dw.testing, instead of just
 * showing the fields and values.
 * i.e. 
 * The aspect can be used for recursive trace logging of method parameters and/or return values in code generation format.
 * In order to use, this aspect must have the pointcut and logging method overriden in a concrete
 * subaspect.
 * 
 * Note that there are separate pointcuts for logging of parameters and for logging of return values.
 * Since both must be overridden in a concrete subaspect, if only one is required then just do a dummy
 * pointcut for the one that is not required.
 *
 * @author David Wong
 */
public abstract aspect Trace {

	private ReflectionHandler reflectionHandler;
	private ObjectLogger objectLogger;

	public Trace() {
		reflectionHandler = new MetadataGenerationHandler();
		objectLogger = new ObjectLogger();
	}

	/**
	 * Override to provide actual logging.
	 */
	protected abstract void log(String msg);

	/**
	 * Override to determine which methods are to be logged to generate test data from parameters.
	 */
	protected abstract pointcut loggedParamOperations();

	/**
	 * Override to determine which methods are to be logged to generate test data from return value.
	 */
	protected abstract pointcut loggedReturnOperations();
	
	/**
	 * Log the start of the method, but avoid recursion that may be caused by
	 * the execution of toString().
	 */
	before() : loggedParamOperations() && !execution(String *.toString()) {		
		Signature sig = thisJoinPointStaticPart.getSignature();
		String methodName = sig.getDeclaringType().getName() + "." + sig.getName();
		log(createParameterMessage(thisJoinPoint, methodName));
	}

	/**
	 * Log the end of the method, but avoid recursion that may be caused by the
	 * execution of toString().
	 */
	after() returning (Object o):  loggedReturnOperations() && !execution(String *.toString()) {
		Signature sig = thisJoinPointStaticPart.getSignature();
		String methodName = sig.getDeclaringType().getName() + "." + sig.getName();
		log(createReturnMessage(thisJoinPoint, methodName, o));
	}

	/**
	 * Format the trace message for method parameters.
	 */
	protected String createParameterMessage(JoinPoint joinPoint, String methodName) {
		StringBuilder message = new StringBuilder();
		Object[] arguments = joinPoint.getArgs();
		
		if (arguments.length == 0)
		{
			// this shouldn't happen if the pointcut is correct, but do a sanity check in case
			message.append(FormatConstants.commentLinePrefix + "No parameters for " + methodName);
		}
		else
		{
			// add a comment to track the method name
			message.append(FormatConstants.commentLinePrefix + methodName + "\n");
			
			MethodBuilder methodBuilder = new MethodBuilder();
			
			for (int length = arguments.length, i = 0; i < length; ++i) {
				Object argument = arguments[i];
	
				if (argument != null)
				{
					// start of method
					message.append(methodBuilder.createMethodLine(methodName, argument, i+1));
					
					// method body
					logObject(message, argument);
					resetLogNameCounters();
					
					// end of method
					message.append(methodBuilder.createMethodCompletion(argument));
				}
				else
				{
					// null parameter
					message.append(FormatConstants.commentLinePrefix + "Parameter " + (i+1) + " is null.");
				}
			}
		}
		return message.toString();
	}

	/**
	 * Format the trace message for method return value.
	 */
	protected String createReturnMessage(JoinPoint joinPoint, String methodName, Object returnValue) {
		StringBuilder message = new StringBuilder();
		
		if (returnValue != null)
		{
			MethodBuilder methodBuilder = new MethodBuilder();

			// start of method
			message.append(methodBuilder.createMethodLine(methodName, returnValue, 0));
			
			// method body
			logObject(message, returnValue);
			resetLogNameCounters();
			
			// end of method
			message.append(methodBuilder.createMethodCompletion(returnValue));
		}
		else
		{
			// null return value
			message.append(FormatConstants.commentLinePrefix + "Return value for " + methodName + " is null");
		}
			
		return message.toString();
	}
	
	/**
	 * Delegate the logging of the object (and fields) to ObjectLogger.
	 * 
	 * @param message
	 * @param object
	 */
	protected void logObject(StringBuilder message, Object object) {
		try {
			getObjectLogger().logObject(message, getReflectionHandler().handle(object));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reset the numerical counters used as field name suffixes in the log. This should be done after
	 * the logging of each initial object, i.e. parameter or return value
	 */
	protected void resetLogNameCounters() {
		getObjectLogger().resetNameCounters();
	}
	
	// Accessors
	// ---------

	public ObjectLogger getObjectLogger() {
		return objectLogger;
	}

	public ReflectionHandler getReflectionHandler() {
		return reflectionHandler;
	}

}
