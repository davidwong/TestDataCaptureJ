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
 * Test mock object with fields that are arrays of every primitive type.
 * 
 * @author t-davidw
 *
 */
public class FieldPrimitiveArrayHolder {
	private byte[] byteArray;
	private int[] intArray;
	private long[] longArray;
	private float[] floatArray;
	private double[] doubleArray;
	private char[] charArray;
	private boolean[] booleanArray;

	
	public byte[] getByteArray() {
		return byteArray;
	}


	public void setByteArray(byte[] byteArray) {
		this.byteArray = byteArray;
	}


	public int[] getIntArray() {
		return intArray;
	}


	public void setIntArray(int[] intArray) {
		this.intArray = intArray;
	}


	public long[] getLongArray() {
		return longArray;
	}


	public void setLongArray(long[] longArray) {
		this.longArray = longArray;
	}


	public float[] getFloatArray() {
		return floatArray;
	}


	public void setFloatArray(float[] floatArray) {
		this.floatArray = floatArray;
	}


	public double[] getDoubleArray() {
		return doubleArray;
	}


	public void setDoubleArray(double[] doubleArray) {
		this.doubleArray = doubleArray;
	}


	public char[] getCharArray() {
		return charArray;
	}


	public void setCharArray(char[] charArray) {
		this.charArray = charArray;
	}


	public boolean[] getBooleanArray() {
		return booleanArray;
	}


	public void setBooleanArray(boolean[] booleanArray) {
		this.booleanArray = booleanArray;
	}


	public FieldPrimitiveArrayHolder() {
		super();
	}
	
}