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
package au.com.dw.testdatacapturej.mock.classcheck;

/**
 * NDCNS - No Default Constructor No Setter
 * 
 * Deliberately mis-configured in XML file with only one of the fields so that it can be used for negative testing.
 * 
 * @author David Wong
 *
 */
public class NDCNS_LongChar {
	
	private Long noSetterField;
	private char noSetterField2;
	
	public NDCNS_LongChar(Long noSetterField, char noSetterField2) {
		super();
		this.noSetterField = noSetterField;
		this.noSetterField2 = noSetterField2;
	}
	public Long getNoSetterField() {
		return noSetterField;
	}
	public char getNoSetterField2() {
		return noSetterField2;
	}
	
}
