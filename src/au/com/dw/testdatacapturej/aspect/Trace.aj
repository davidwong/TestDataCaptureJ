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

import java.util.ArrayList;
import java.util.Collection;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

import au.com.dw.testdatacapturej.builder.FileNameKeyBuilder;
import au.com.dw.testdatacapturej.builder.MethodBuilder;
import au.com.dw.testdatacapturej.log.FormatConstants;
import au.com.dw.testdatacapturej.log.LogHolder;
import au.com.dw.testdatacapturej.log.ObjectLogger;
import au.com.dw.testdatacapturej.reflection.MetadataGenerationHandler;
import au.com.dw.testdatacapturej.reflection.ReflectionHandler;

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
 * Since both must be overridden in a concrete subaspect, if only one is required then just do a empty
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
	 * The actual logging to the logging framework.
	 * 
	 * @param logContents The contents of the log to send to the logging framework
	 */
	protected abstract void doLog(String logContents);
	//Override to provide actual logging.
	
	/**
	 * Any logging config required before an object is actually logged.
	 * 
	 * @param log
	 */
	protected void preLog(LogHolder log)
	{
		// default do-nothing implementation
	}

	/**
	 * Any logging config required after an object is logged.
	 * 
	 * @param log
	 */
	protected void postLog(LogHolder log)
	{
		// default do-nothing implementation
	}

	/**
	 * Log the generated code for an object. Just a template method pattern method to allow setting
	 * up of pre- and post-conditions.
	 * 
	 * @param log
	 */
	private void log(LogHolder log)
	{
		preLog(log);
		doLog(log.getLog());
		postLog(log);
	}
	
	/**
	 * Log the generated code for multiple objects.
	 * 
	 * @param logs
	 */
	private void log(Collection<LogHolder> logs)
	{
		for (LogHolder log : logs)
		{
			log(log);
		}
	}

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
	protected Collection<LogHolder> createParameterMessage(JoinPoint joinPoint, String methodName) {
		Object[] arguments = joinPoint.getArgs();
		Collection<LogHolder> logs = new ArrayList<LogHolder>();
		FileNameKeyBuilder keyBuilder = new FileNameKeyBuilder();
		String fileKey = null;
		
		if (arguments.length == 0)
		{
			StringBuilder logBuilder = new StringBuilder();
			
			// this shouldn't happen if the pointcut is correct, but do a sanity check in case
			logBuilder.append(FormatConstants.commentLinePrefix + "No parameters for " + methodName);
			
			fileKey = keyBuilder.createParameterFileKey(methodName, 0, "None", true);
			
			LogHolder log = new LogHolder(fileKey, logBuilder.toString());
			logs.add(log);
		}
		else
		{
			
			MethodBuilder methodBuilder = new MethodBuilder();
			
			for (int length = arguments.length, i = 0; i < length; ++i) {
				Object argument = arguments[i];
				int paramNum = i+1;
				StringBuilder logBuilder = new StringBuilder();
				
				// add a comment to track the method name and parameter number
				logBuilder.append(FormatConstants.commentLinePrefix);
				logBuilder.append(methodName);
				logBuilder.append(":Parameter");
				logBuilder.append(paramNum);
				logBuilder.append(FormatConstants.newLine);
				
				if (argument != null)
				{
					// start of method
					logBuilder.append(methodBuilder.createMethodLine(methodName, argument, i+1));
					
					// method body
					logObject(logBuilder, methodName, argument);
					resetLogNameCounters();
					
					// end of method
					logBuilder.append(methodBuilder.createMethodCompletion(argument));

					fileKey = keyBuilder.createParameterFileKey(methodName, paramNum, argument.getClass().getName(), true);
				}
				else
				{
					// null parameter
					logBuilder.append(FormatConstants.commentLinePrefix + "Parameter " + (i+1) + " is null.");
					
					fileKey = keyBuilder.createParameterFileKey(methodName, paramNum, "Null", true);
				}


				LogHolder log = new LogHolder(fileKey, logBuilder.toString());
				logs.add(log);				
			}
		}
		return logs;
	}

	/**
	 * Format the trace message for method return value.
	 */
	protected LogHolder createReturnMessage(JoinPoint joinPoint, String methodName, Object returnValue) {
		StringBuilder logBuilder = new StringBuilder();
		FileNameKeyBuilder keyBuilder = new FileNameKeyBuilder();
		String fileKey = null;
		
		if (returnValue != null)
		{
			// add a comment to track the method name
			logBuilder.append(FormatConstants.commentLinePrefix);
			logBuilder.append(methodName);
			logBuilder.append(":Return");
			logBuilder.append(FormatConstants.newLine);

			// start of method
			MethodBuilder methodBuilder = new MethodBuilder();
			logBuilder.append(methodBuilder.createMethodLine(methodName, returnValue, 0));
			
			// method body
			logObject(logBuilder, methodName, returnValue);
			resetLogNameCounters();
			
			// end of method
			logBuilder.append(methodBuilder.createMethodCompletion(returnValue));

			fileKey = keyBuilder.createReturnFileKey(methodName, returnValue.getClass().getName(), true);
		}
		else
		{
			// null return value
			logBuilder.append(FormatConstants.commentLinePrefix);
			logBuilder.append("Return value for ");
			logBuilder.append(methodName);
			logBuilder.append(" is null");

			fileKey = keyBuilder.createReturnFileKey(methodName, "Null", true);
		}
			
		LogHolder log = new LogHolder(fileKey, logBuilder.toString());
		return log;
	}
	
	/**
	 * Delegate the logging of the object (and fields) to ObjectLogger.
	 * 
	 * @param message
	 * @param object
	 */
	protected void logObject(StringBuilder message, String methodName, Object object) {
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
