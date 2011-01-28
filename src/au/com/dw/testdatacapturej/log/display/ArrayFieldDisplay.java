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
package au.com.dw.testdatacapturej.log.display;

import au.com.dw.testdatacapturej.meta.ObjectInfo;

/**
 * Display for an array field.
 * 
 * @author David Wong
 *
 */
public class ArrayFieldDisplay extends BaseFieldDisplay {

	/**
	 * Display a field for test data generation.
	 * 
	 * @param classFieldName Field name of the class (not class name) that contains the field
	 * @param index Optional index for arrays
	 * @param fieldName
	 * @param fieldValue
	 * @param literal Whether the fieldValue param is interpreted as a literal string
	 * @return
	 */
	@Override
	public String log(ObjectInfo info) {
		StringBuilder builder = new StringBuilder();
				
		// check if configured to ignore a field for setter method generation
		if (!info.isSetterIgnoreType())
		{
			generateSetter(builder, info);
		}
       	
		return builder.toString();
	}
}
