/*******************************************************************************
 * Copyright () 2009, 2013 David Wong
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

import au.com.dw.testdatacapturej.log.FormatConstants;
import au.com.dw.testdatacapturej.meta.ObjectInfo;

/**
 * Display for an object field.
 * e.g. class.setField(value);
 * 
 * @author David Wong
 *
 */
public class ClassFieldGenerator extends BaseFieldGenerator {
	
	@Override
	public String log(ObjectInfo info)
	{
		StringBuilder builder = new StringBuilder();

		if (!info.isInitalObject())
		{
			// check if configured to ignore a field for setter method generation
			if (!info.isSetterIgnoreType())
			{
				boolean literal = !info.isSimpleType();
				
				// same as BaseFieldDisplay.generateSetter() except don't have new line here
				
				builder.append(info.getContainingClassFieldName());
				builder.append(getLineBuilder().createSetterLine(info.getFieldName(), info.getFullFieldName(), literal));
				builder.append(FormatConstants.newLine);
			}
		}
		return builder.toString();
	}
}
