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
package au.com.dw.testdatacapturej.log.element;

import au.com.dw.testdatacapturej.log.FormatConstants;
import au.com.dw.testdatacapturej.log.LogBuilder;
import au.com.dw.testdatacapturej.log.field.BaseFieldGenerator;
import au.com.dw.testdatacapturej.meta.ObjectInfo;

/**
 * Generator for array elements.
 * e.g. field[0] = value
 * 
 * @author David Wong
 *
 */
public class ArrayElementGenerator extends BaseFieldGenerator {

	@Override
	public void log(LogBuilder builder, ObjectInfo info) {
		boolean literal = true;
		
		if (info.isSimpleType())
		{
			literal = false;
			getLineBuilder().createArrayAssignLine(builder, info.getContainingClassFieldName(), info.getIndex(), info.getValue(), literal);
		}
		else
		{
			getLineBuilder().createArrayAssignLine(builder, info.getContainingClassFieldName(), info.getIndex(), info.getFullFieldName(), literal);
		}
		builder.append(FormatConstants.newLine);
	}
}
