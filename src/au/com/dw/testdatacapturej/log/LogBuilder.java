/*******************************************************************************
 * Copyright () 2013 David Wong
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
 * Builder for object logging, allows pre- and post- processing of log elements.
 *  
 * @author David Wong
 *
 */
public interface LogBuilder {

	/** Add a raw element to the logging.
	 * 
	 * @param str
	 */
	public void append(String str);
	
	/** Add an element to the logging with some processing.
	 * 
	 * @param str
	 */
	public void process(String str);

	/**
	 * Get the object logging.
	 * 
	 * @return
	 */
	public String getLog();
	
	/**
	 * Get any logging that is required before the log of the actual objects.
	 * 
	 * @return
	 */
	public String getPreLog();
	
	//public String getPostLog();
}
