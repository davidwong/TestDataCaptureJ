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
package au.com.dw.testdatacapturej.log.java;

import au.com.dw.testdatacapturej.meta.ObjectInfo;

/**
 * Interface for classes that generate test code for a constructor.
 * 
 * @author David Wong
 *
 */
public interface ConstructorGenerator {

	/**
	 * Generate the constructor line for an object. The test code generated should be able to be used to
	 * instantiate the object.
	 * 
	 * @param builder
	 * @param info
	 */
	public void generateConstructor(StringBuilder builder, ObjectInfo info);
	
	/**
	 * Add a comment for the constructor line.
	 * 
	 * @param builder
	 * @param info
	 */
	public void addConstructorComment(StringBuilder builder, ObjectInfo info);
}
