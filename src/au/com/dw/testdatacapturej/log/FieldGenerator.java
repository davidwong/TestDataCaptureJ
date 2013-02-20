/*******************************************************************************
 * Copyright () 2009, 2011, 2013 David Wong
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

import au.com.dw.testdatacapturej.meta.ObjectInfo;

/**
 * Interface for implementations to display the logging for a field in particular formats for test data
 * generation. The format will depend on the type of field.
 * 
 * @author David Wong
 *
 */
public interface FieldGenerator {

	/**
	 * Pre-requisite logging before the child objects of the object are logged.
	 * e.g. to create a constructor line of the appropriate type for an object, if required.
	 * 
	 * Note:
	 * - Simple types don't need to be constructed.
	 * - Arrays use a different constructor format than other objects.
	 * 
	 * @param info
	 * @return
	 */
	public String preLog(ObjectInfo info);
	
	/**
	 * Logging the object after the child objects are logged.
	 * 
	 * @param info
	 * @return
	 */
	public String log(ObjectInfo info);
}
