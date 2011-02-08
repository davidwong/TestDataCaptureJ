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
package au.com.dw.testdatacapturej.meta;

/**
 * Metadata types for objects that are contained in another object.
 * 
 * NONE:
 * Object is not contained, should only be for the initial object to be logger.
 * 
 * FIELD:
 * Object is a field of a parent object.
 * 
 * COLLECTION_ELEMENT:
 * Object is an element of a collection.
 * 
 * ARRAY_ELEMENT:
 * Object is an element of an array.
 * 
 * MAP_ENTRY:
 * Object is an entry of a map.
 * 
 * ADDED_COLLECTION_ELEMENT:
 * Object is an element of a collection that is only added to the collection by a
 * adder method in the enclosing class that contains the collection
 * 
 * @author David Wong
 *
 */
public enum ContainmentType {
	NONE, FIELD, COLLECTION_ELEMENT, ARRAY_ELEMENT, MAP_ENTRY, ADDED_COLLECTION_ELEMENT
}
