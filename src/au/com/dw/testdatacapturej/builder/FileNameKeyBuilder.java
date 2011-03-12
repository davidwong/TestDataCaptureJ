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
package au.com.dw.testdatacapturej.builder;

import au.com.dw.testdatacapturej.log.FormatConstants;

/**
 * Utility class to build strings that are used by the logging framework. In particular, for creating
 * unique file names that can be stored in a MDC.
 * 
 * The methods are used to generate the file name key field of LogHolder.
 * 
 * @see au.com.dw.testdatacapturej.log.LogHolder
 * 
 * @author David Wong
 *
 */
public class FileNameKeyBuilder {
	
	/**
	 * Create the file name logging key to be used to store the logging for a method parameter.
	 * 
	 * @param methodName Fully qualified name of the method for which the logged object is a parameter
	 * @param paramNum The parameter index for the parameter, e.g. the 1st parameter would have paramNum of 1
	 * @param paramType Fully qualified class name for the logged object, or an alternative if no available, e.g. 'Null'
	 * @return
	 */
	public String createParameterFileKey(String methodName, int paramNum, String paramType)
	{
		StringBuilder keyBuilder = new StringBuilder();
		
		keyBuilder.append(methodName);
		keyBuilder.append(FormatConstants.FILE_NAME_SEPARATOR);
		keyBuilder.append(FormatConstants.PARAMETER);
		if (paramNum != 0)
		{
			keyBuilder.append(paramNum);
		}	
		keyBuilder.append(FormatConstants.FILE_NAME_SEPARATOR);
		keyBuilder.append(paramType);
		
		return keyBuilder.toString();
	}

	/**
	 * Create the file name logging key to be used to store the logging for a method return value.
	 * 
	 * @param methodName Fully qualified name of the method for which the logged object is a parameter
	 * @param returnType Fully qualified class name for the logged object, or an alternative if no available, e.g. 'Null'
	 * @return
	 */
	public String createReturnFileKey(String methodName, String returnType)
	{
		StringBuilder keyBuilder = new StringBuilder();
		
		keyBuilder.append(methodName);
		keyBuilder.append(FormatConstants.FILE_NAME_SEPARATOR);
		keyBuilder.append(FormatConstants.RETURN);
		keyBuilder.append(FormatConstants.FILE_NAME_SEPARATOR);
		keyBuilder.append(returnType);
		
		return keyBuilder.toString();
	}

}
