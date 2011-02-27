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
package au.com.dw.testdatacapturej.mock.type.reflection;

/**
 * Test data with parameterized constructor only and no default constructor.
 * 
 * @author David Wong
 *
 */
public class ParameterizedConstructorOnlyDataHolder {
	private String field1;
	private Object field2;
	
	public ParameterizedConstructorOnlyDataHolder(String field1, Object field2) {
		super();
		this.field1 = field1;
		this.field2 = field2;
	}
	
}
