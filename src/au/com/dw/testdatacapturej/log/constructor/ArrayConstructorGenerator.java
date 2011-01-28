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

import java.lang.reflect.Array;

import au.com.dw.testdatacapturej.log.FormatConstants;
import au.com.dw.testdatacapturej.meta.ObjectInfo;


/**
 * Generate a constructor line for an array.
 * 
 * @author David Wong
 *
 */
public class ArrayConstructorGenerator extends BaseConstructorGenerator {

	@Override
	public void generateConstructor(StringBuilder builder, ObjectInfo info) {
		String fieldIndex = String.valueOf(info.getClassFieldNameIndex());
		
		builder.append(FormatConstants.newLine);
		
		// extra info required for an array constructor as opposed to an object constructor
		Object array = info.getValue();
		String arrayType = array.getClass().getComponentType().getName();
		int initialSize = Array.getLength(array);
		
		// create the constructor line
		String classFieldName = getLineBuilder().createArrayConstructorLine(builder, arrayType, initialSize, null, fieldIndex);
		info.setClassFieldName(classFieldName);
		
		// pass the newly created class field name to child objects
		for (ObjectInfo fieldInfo : info.getFieldList())
		{
			fieldInfo.setContainingClassFieldName(classFieldName);
		}
		
		builder.append(FormatConstants.newLine);
	}

}
