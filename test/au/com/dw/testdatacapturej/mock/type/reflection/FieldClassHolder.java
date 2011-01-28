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
 * Test mock class for fields with and without setters.
 * i.e. 2 identical field types, but only 1 has a setter method.
 * 
 * @author t-davidw
 *
 */
public class FieldClassHolder {
	private FieldClass fieldWithSetter;
	private FieldClass fieldNoSetter;
	
	public FieldClassHolder(FieldClass fieldWithSetter, FieldClass fieldNoSetter) {
		super();
		this.fieldWithSetter = fieldWithSetter;
		this.fieldNoSetter = fieldNoSetter;
	}

	public FieldClass getFieldWithSetter() {
		return fieldWithSetter;
	}

	public void setFieldWithSetter(FieldClass fieldWithSetter) {
		this.fieldWithSetter = fieldWithSetter;
	}

	public FieldClass getFieldNoSetter() {
		return fieldNoSetter;
	}
	
}
