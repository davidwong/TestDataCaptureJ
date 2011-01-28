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
package au.com.dw.testdatacapturej.mock.dataholder;

import java.util.HashMap;
import java.util.Map;

/**
 * Holder class for a Map field containing nulls in the entries: for the key, the value or both.
 * Use both an interface field and an implementation class field that permit null elements.
 * 
 * @author David Wong
 *
 */
public class NullMapEntryHolder {

	// Map not parameterized to handle null key and value
	private Map nullField;
	private HashMap<?,?> implNullField;
	
	public NullMapEntryHolder()
	{
		nullField = new HashMap();
		nullField.put(null,"value");
		nullField.put("key",null);
		
		implNullField = new HashMap<Object,Object>();
		implNullField.put(null, null);
	}

	public Map getNullField() {
		return nullField;
	}

	public void setNullField(Map nullField) {
		this.nullField = nullField;
	}

	public HashMap<?, ?> getImplNullField() {
		return implNullField;
	}

	public void setImplNullField(HashMap<?, ?> implNullField) {
		this.implNullField = implNullField;
	}

}
