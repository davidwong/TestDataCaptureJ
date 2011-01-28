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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Test mock object with an attempt to have a field of simple type, without collections or nested.
 * 
 * Note this depends on EqualsBuilder and HashCodeBuilder.
 * 
 * @author t-davidw
 *
 */
public class SimpleDataHolder {

	// simple fields
	private String text;
	private int number;
	private char character;
	private boolean bool;
	private Long longNumber;
	private float primitiveFraction;
	private Double fraction;

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public char getCharacter() {
		return character;
	}
	public void setCharacter(char character) {
		this.character = character;
	}
	public boolean isBool() {
		return bool;
	}
	public void setBool(boolean bool) {
		this.bool = bool;
	}
	public Long getLongNumber() {
		return longNumber;
	}
	public void setLongNumber(Long longNumber) {
		this.longNumber = longNumber;
	}	
	public float getPrimitiveFraction() {
		return primitiveFraction;
	}
	public void setPrimitiveFraction(float primitiveFraction) {
		this.primitiveFraction = primitiveFraction;
	}
	public Double getFraction() {
		return fraction;
	}
	public void setFraction(Double fraction) {
		this.fraction = fraction;
	}
	
	 public boolean equals(Object obj) {
		   return EqualsBuilder.reflectionEquals(this, obj);
		 }
	 public int hashCode() {
		   return HashCodeBuilder.reflectionHashCode(this);
		 }
		 
}
