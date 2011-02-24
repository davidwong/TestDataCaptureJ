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
 * Display for a map entry.
 * e.g. field.put(key, value);
 * 
 * @author David Wong
 *
 */
public class MapEntryDisplay extends BaseFieldDisplay {

	@Override
	public String log(ObjectInfo info) 
	{
		StringBuilder builder = new StringBuilder();

		boolean literal = !info.isSimpleType();
		boolean keyLiteral = !info.getKeyInfo().isSimpleType();
		
		builder.append(getLineBuilder().createMapPutLine(info.getContainingClassFieldName(), info.getKeyInfo().getFullFieldName(), info.getFullFieldName(), info.getKeyInfo().getValue(), info.getValue(), keyLiteral, literal));
		builder.append(FormatConstants.newLine);
       	
		return builder.toString();
	}
}
