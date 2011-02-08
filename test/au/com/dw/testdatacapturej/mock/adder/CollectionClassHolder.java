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
package au.com.dw.testdatacapturej.mock.adder;

import java.util.ArrayList;

/**
 * Test mock object with collection field that does not use setter method to add to
 * it's contents. Instead uses an adder method so the the collection field is not
 * directly manipulated.
 * 
 * This version has an implementation class for the collection field instead of just
 * the Collection interface and has 2 collection fields
 * 
 * @author David Wong
 *
 */
public class CollectionClassHolder {
	private ArrayList<Object> collection = new ArrayList<Object>();
	private ArrayList<Object> collection2 = new ArrayList<Object>();

	public CollectionClassHolder() {
	}
	
	public void addCollectionElement(Object element) {
		this.collection.add(element);
	}

	public void addCollectionElement2(Object element) {
		this.collection2.add(element);
	}
}