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
package au.com.dw.testdatacapturej.log.constructor;

import au.com.dw.testdatacapturej.builder.LineBuilder;
import au.com.dw.testdatacapturej.meta.ObjectInfo;

/**
 * Base class for implementations of ConstructorGenerator.
 * 
 * @author David Wong
 *
 */
public abstract class BaseConstructorGenerator implements ConstructorGenerator {

	private LineBuilder lineBuilder = new LineBuilder();

	/* (non-Javadoc)
	 * @see au.com.dw.testdatacapturej.log.constructor.ConstructorGenerator#generateConstructor(java.lang.StringBuilder, au.com.dw.testdatacapturej.meta.ObjectInfo)
	 */
	public abstract void generateConstructor(StringBuilder builder, ObjectInfo info);
	
	/* (non-Javadoc)
	 * @see au.com.dw.testdatacapturej.log.constructor.ConstructorGenerator#addConstructorComment(java.lang.StringBuilder, au.com.dw.testdatacapturej.meta.ObjectInfo)
	 */
	public void addConstructorComment(StringBuilder builder, ObjectInfo info)
	{
		// Default do-nothing adaptor method.
	}

	public LineBuilder getLineBuilder() {
		return lineBuilder;
	}

}
