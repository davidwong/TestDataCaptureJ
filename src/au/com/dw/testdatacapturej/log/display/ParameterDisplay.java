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

import au.com.dw.testdatacapturej.log.FormatConstants;
import au.com.dw.testdatacapturej.meta.ObjectInfo;

/**
 * Display for a constructor parameter.
 * 
 * @author David Wong
 *
 */
public class ParameterDisplay extends BaseFieldDisplay {
	
	@Override
	public String log(ObjectInfo info)
	{
		StringBuilder builder = new StringBuilder();

		if (!info.isInitalObject())
		{
			if (info.isSimpleType())
			{
				getLineBuilder().interpretValue(builder, info.getValue(), false);
			}
			else
			{
				builder.append(info.getFullFieldName());
			}
			
			// just store the value in the parent class
			ObjectInfo parentInfo = info.getParentInfo();
			parentInfo.getConstructorInfo().addConstructorParameter(builder.toString());
		}
		
		// don't actually log anything
		return FormatConstants.EMPTY_STRING;
	}
}
