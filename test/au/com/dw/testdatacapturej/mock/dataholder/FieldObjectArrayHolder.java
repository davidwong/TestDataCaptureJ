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
 * Test mock object with fields that are arrays of every primitive wrapper.
 * 
 * @author t-davidw
 *
 */
public class FieldObjectArrayHolder {
	private Byte[] byteArray;
	private Integer[] intArray;
	private Long[] longArray;
	private Float[] floatArray;
	private Double[] doubleArray;
	private Character[] charArray;
	private Boolean[] booleanArray;
	private String[] stringArray;

	
	public Byte[] getByteArray() {
		return byteArray;
	}


	public void setByteArray(Byte[] byteArray) {
		this.byteArray = byteArray;
	}


	public Integer[] getIntArray() {
		return intArray;
	}


	public void setIntArray(Integer[] intArray) {
		this.intArray = intArray;
	}


	public Long[] getLongArray() {
		return longArray;
	}


	public void setLongArray(Long[] longArray) {
		this.longArray = longArray;
	}


	public Float[] getFloatArray() {
		return floatArray;
	}


	public void setFloatArray(Float[] floatArray) {
		this.floatArray = floatArray;
	}


	public Double[] getDoubleArray() {
		return doubleArray;
	}


	public void setDoubleArray(Double[] doubleArray) {
		this.doubleArray = doubleArray;
	}


	public Character[] getCharArray() {
		return charArray;
	}


	public void setCharArray(Character[] charArray) {
		this.charArray = charArray;
	}


	public Boolean[] getBooleanArray() {
		return booleanArray;
	}


	public void setBooleanArray(Boolean[] booleanArray) {
		this.booleanArray = booleanArray;
	}


	public String[] getStringArray() {
		return stringArray;
	}


	public void setStringArray(String[] stringArray) {
		this.stringArray = stringArray;
	}


	public FieldObjectArrayHolder() {
		super();
	}
	
}