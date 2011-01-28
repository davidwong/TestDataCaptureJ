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
 * Test mock object with fields of every primitive wrapper type.
 * 
 * @author t-davidw
 *
 */
public class FieldObjectHolder {
	private Byte byteField;
	private Integer intField;
	private Long longField;
	private Float floatField;
	private Double doubleField;
	private Character charField;
	private Boolean booleanField;
	private String stringField;
	
	public Byte getByteField() {
		return byteField;
	}

	public void setByteField(Byte byteField) {
		this.byteField = byteField;
	}

	public Integer getIntField() {
		return intField;
	}

	public void setIntField(Integer intField) {
		this.intField = intField;
	}

	public Long getLongField() {
		return longField;
	}

	public void setLongField(Long longField) {
		this.longField = longField;
	}

	public Float getFloatField() {
		return floatField;
	}


	public void setFloatField(Float floatField) {
		this.floatField = floatField;
	}

	public Double getDoubleField() {
		return doubleField;
	}

	public void setDoubleField(Double doubleField) {
		this.doubleField = doubleField;
	}

	public Character getCharField() {
		return charField;
	}

	public void setCharField(Character charField) {
		this.charField = charField;
	}

	public Boolean getBooleanField() {
		return booleanField;
	}


	public void setBooleanField(Boolean booleanField) {
		this.booleanField = booleanField;
	}

	public String getStringField() {
		return stringField;
	}

	public void setStringField(String stringField) {
		this.stringField = stringField;
	}

	public FieldObjectHolder() {
		super();
	}
	
}