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


/**
 * Test mock object with primitive array field.
 * 
 * @author t-davidw
 *
 */
public class ArrayPrimitiveHolder {
	private int[] intArray;

	public ArrayPrimitiveHolder(int[] intArray) {
		super();
		this.intArray = intArray;
	}

	public int[] getIntArray() {
		return intArray;
	}

	public void setIntArray(int[] intArray) {
		this.intArray = intArray;
	}
}