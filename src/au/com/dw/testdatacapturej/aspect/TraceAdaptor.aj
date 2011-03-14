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

import au.com.dw.testdatacapturej.log.LogHolder;
import au.com.dw.testdatacapturej.log.LoggingConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * Adaptor for the Trace aspect to leave only the pointcut as abstract so that it can be defined in aop.xml.
 * This uses a logging library to do the logging, so will need to setup the logging configuration as well.
 * 
 * The aop.xml would contain the Trace aspect and the TraceAdaptor as aspects, and would then define
 * a concrete-aspect which extends the TraceAdaptor and includes a pointcut for 'loggedOperations'.
 *
 * e.g.
 * 
 * 	<aspect name="au.com.dw.testdatacapturej.aspect.Trace"/>
 *	<aspect name="au.com.dw.testdatacapturej.aspect.TraceAdaptor"/>
 *
 *	<concrete-aspect name="au.com.dw.testdatacapturej.aspect.TestTrace"
 *                           extends="au.com.dw.testdatacapturej.aspect.TraceAdaptor">
 *            <pointcut name="loggedParamOperations" expression="[the concrete pointcut here]"/>
 *  </concrete-aspect>
 *  
 * Since there are separate pointcuts for logging of parameters and for logging of return values, if only
 * one is required then just do a empty pointcut for the other one that is not required when subclassing
 * this adaptor.
 * e.g.
 *
 *	<concrete-aspect name="au.com.dw.testdatacapturej.aspect.DummyTestTrace"
 *                           extends="au.com.dw.testdatacapturej.aspect.TraceAdaptor">
 *            <pointcut name="loggedReturnOperations" expression="if(false)"/>
 *  </concrete-aspect>
 *    
 * @author David Wong
 */
public abstract aspect TraceAdaptor extends Trace {
	private Logger logger = LoggerFactory.getLogger(LoggingConstants.TRACE_LOGGER);

	@Override
	protected void preLog(LogHolder log)
	{
		// add a unique MDC value to be used by a sifting appender so that each logged object will
		// be logged to a separate log file
		
		String key = log.getFileKey();
		MDC.put(LoggingConstants.LOGGER_MDC_KEY, key);
	}
	
	@Override
	protected void postLog(LogHolder log)
	{
		// clean up MDC after logging each logged object
		
		MDC.remove(LoggingConstants.LOGGER_MDC_KEY);
	}
	
	@Override
	protected void doLog(String logContents) {
		logger.info(logContents);
	}

}
