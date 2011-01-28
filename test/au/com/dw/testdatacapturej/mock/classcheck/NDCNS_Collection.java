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

import java.util.ArrayList;
import java.util.Collection;

/**
 * NDCNS - No Default Constructor No Setter
 * 
 * @author David Wong
 *
 */
public class NDCNS_Collection {
	
	private Collection<?> noSetterField;
	private ArrayList<?> noSetterField2;
	
	public NDCNS_Collection(Collection<?> noSetterField,
			ArrayList<?> noSetterField2) {
		super();
		this.noSetterField = noSetterField;
		this.noSetterField2 = noSetterField2;
	}

	public Collection<?> getNoSetterField() {
		return noSetterField;
	}

	public ArrayList<?> getNoSetterField2() {
		return noSetterField2;
	}
	
}
