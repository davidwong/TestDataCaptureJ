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
import au.com.dw.testdatacapturej.log.field.BaseFieldGenerator;
import au.com.dw.testdatacapturej.meta.ObjectInfo;

/**
 * Generate a collection element for a collection that is accessed through adder methods in the class
 * containing the collection
 * e.g. parentField.addElement(value);
 *
 * @author David Wong
 *
 */
public class AddedElementGenerator extends BaseFieldGenerator {

	@Override
	public String log(ObjectInfo info)
	{
		StringBuilder builder = new StringBuilder();
		ObjectInfo parentInfo = info.getParentInfo();

		if (parentInfo != null)
		{
			if (parentInfo.getSetterAdderInfo().isUsesAdder())
			{
				boolean literal = true;
				String classFieldName = parentInfo.getContainingClassFieldName();
				String adderMethodName = parentInfo.getSetterAdderInfo().getAdderMethodName();

				if (info.isSimpleType())
				{
					literal = false;
					builder.append(getLineBuilder().createCollectionEnclosingAdderLine(classFieldName, adderMethodName, info.getValue(), literal));
				}
				else
				{
					builder.append(getLineBuilder().createCollectionEnclosingAdderLine(classFieldName, adderMethodName, info.getFullFieldName(), literal));
				}
				builder.append(FormatConstants.newLine);
			}
			else
			{
				// log error
			}
		}
		else
		{
			// log error
		}

		return builder.toString();
	}
}
