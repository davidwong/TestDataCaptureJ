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

import au.com.dw.testdatacapturej.log.FormatConstants;
import au.com.dw.testdatacapturej.log.LogBuilder;
import au.com.dw.testdatacapturej.meta.ObjectInfo;
import au.com.dw.testdatacapturej.util.Messages;

/**
 * Generate a default no-argument constructor line for a class that is not an array.
 * 
 * @author David Wong
 *
 */
public class DefaultConstructorGenerator extends BaseConstructorGenerator {

	@Override
	public void generateConstructor(LogBuilder builder, ObjectInfo info) {
		// no need to construct a collection is it is only accessed through adder method
		if (!info.getSetterAdderInfo().isUsesAdder())
		{
			builder.append(FormatConstants.newLine);
	
			// get the constructor line
			getLineBuilder().createConstructorLine(builder, info);
			
			// pass the newly created class field name to child objects
			for (ObjectInfo fieldInfo : info.getFieldList())
			{
				fieldInfo.setContainingClassFieldName(info.getFullFieldName());
			}
			
			builder.append(FormatConstants.newLine);
		}
	}

	@Override
	public void addConstructorComment(LogBuilder builder, ObjectInfo info) {
		// should be output when a default constructor line is generated for a class that does not have
		// a no-argument default constructor
		builder.append(FormatConstants.commentLinePrefix);
		builder.append(Messages.getMessage(Messages.DEFAULT_CONSTRUCTOR_NOT_EXIST, info.getClassName()));
		builder.append(FormatConstants.newLine);
	}
}
