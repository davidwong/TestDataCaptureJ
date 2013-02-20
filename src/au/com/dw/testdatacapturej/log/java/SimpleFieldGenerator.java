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
package au.com.dw.testdatacapturej.log.java;

import au.com.dw.testdatacapturej.log.FormatConstants;
import au.com.dw.testdatacapturej.meta.ObjectInfo;
import au.com.dw.testdatacapturej.meta.SetterGenerationType;

/**
 * Display for a simple field, i.e. not a array or collection but a primitive value or wrapper.
 * e.g. String, int, Integer, etc
 * 
 * @author David Wong
 *
 */
public class SimpleFieldGenerator extends BaseFieldGenerator {

	@Override
	public String log(ObjectInfo info)
	{
		StringBuilder builder = new StringBuilder();

		boolean literal = !info.isSimpleType();
		
		if (info.isInitalObject())
		{
			getLineBuilder().interpretValue(builder, info.getValue(), literal);
		}
		else
		{
			// check if configured to ignore a field for setter method generation
			if (info.getSetterAdderInfo().getSetterGenerationType() != SetterGenerationType.IGNORE)
			{
				builder.append(info.getContainingClassFieldName());
				builder.append(getLineBuilder().createSetterLine(info.getFieldName(), info.getValue(), literal));
				builder.append(FormatConstants.newLine);
			}
		}
		
		return builder.toString();
	}
}
