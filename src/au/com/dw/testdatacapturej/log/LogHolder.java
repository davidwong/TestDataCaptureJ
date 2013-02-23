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
package au.com.dw.testdatacapturej.log;

/**
 * Container for the generated log for an intercepted object. This would be for a method parameter or return
 * value object, for instance.
 * 
 * As well as the generated log (which is the code that could be used to recreate the object), it also contains
 * additional data related to the actual logging process for whichever logging framework is being used to handle
 * the actual logging.
 * 
 * @author David Wong
 *
 */
public class LogHolder {
	
	/** Key value to be store for use by the logging framework, i.e. as part of file name */
	private final String fileKey;
	
	/** Any additional logging that comes before the log contents */
	private final String preLog;
	
	/** The actual log contents that were generated */
	private final String log;

	public LogHolder(String fileKey, String preLog, String log) {
		super();
		this.fileKey = fileKey;
		this.preLog = preLog == null ? FormatConstants.EMPTY_STRING : preLog;
		this.log = log == null ? FormatConstants.EMPTY_STRING : log;
	}

	public String getFileKey() {
		return fileKey;
	}

	public String getPreLog() {
		return preLog;
	}

	public String getLog() {
		return log;
	}
		
}
