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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test mock object with an attempt to have a field of every type that needs to be tested.
 * 
 * @author t-davidw
 *
 */
public class AllDataHolder {

	// simple fields
	private String text;
	private int primitiveNumber;
	private Integer number;
	private char character;
	private boolean bool;
	private Long longNumber;
	private float primitiveFraction;
	private Double fraction;

	// nested
	private AllDataHolder inner;
	private Object nullObject;

	// static
	private static String staticText;
	private static Object staticObject;

	// complex fields
	private Collection collection;
	private List list;
	private ArrayList arrayList;
	private String[] array;
	private int[] primitiveArray;
	private Object[] objectArray;
	private Map map;
	private Map objectMap;
	private HashMap hashMap;

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public int getPrimitiveNumber() {
		return primitiveNumber;
	}
	public void setPrimitiveNumber(int primitiveNumber) {
		this.primitiveNumber = primitiveNumber;
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
	public AllDataHolder getInner() {
		return inner;
	}
	public void setInner(AllDataHolder inner) {
		this.inner = inner;
	}
	public Object getNullObject() {
		return nullObject;
	}
	public void setNullObject(Object nullObject) {
		this.nullObject = nullObject;
	}
	public static String getStaticText() {
		return staticText;
	}
	public static void setStaticText(String staticText) {
		AllDataHolder.staticText = staticText;
	}
	public static Object getStaticObject() {
		return staticObject;
	}
	public static void setStaticObject(Object staticObject) {
		AllDataHolder.staticObject = staticObject;
	}
	public Collection getCollection() {
		return collection;
	}
	public void setCollection(Collection collection) {
		this.collection = collection;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public ArrayList getArrayList() {
		return arrayList;
	}
	public void setArrayList(ArrayList arrayList) {
		this.arrayList = arrayList;
	}
	public String[] getArray() {
		return array;
	}
	public void setArray(String[] array) {
		this.array = array;
	}
	public int[] getPrimitiveArray() {
		return primitiveArray;
	}
	public void setPrimitiveArray(int[] primitiveArray) {
		this.primitiveArray = primitiveArray;
	}
	public Object[] getObjectArray() {
		return objectArray;
	}

	public void setObjectArray(Object[] objectArray) {
		this.objectArray = objectArray;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public Map getObjectMap() {
		return objectMap;
	}
	public void setObjectMap(Map objectMap) {
		this.objectMap = objectMap;
	}
	public HashMap getHashMap() {
		return hashMap;
	}
	public void setHashMap(HashMap hashMap) {
		this.hashMap = hashMap;
	}
	public AllDataHolder() {
	}
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(array);

		Collection arrayListAsCollection = arrayList;
		//result = prime * result + ((arrayList == null) ? 0 : arrayList.hashCode());
		result = prime * result + ((arrayListAsCollection == null) ? 0 : arrayListAsCollection.hashCode());
		
		result = prime * result + (bool ? 1231 : 1237);
		result = prime * result + character;
		result = prime * result
				+ ((collection == null) ? 0 : collection.hashCode());
		result = prime * result
				+ ((fraction == null) ? 0 : fraction.hashCode());
		Map hashMapAsMap = hashMap;
		//result = prime * result + ((hashMap == null) ? 0 : hashMap.hashCode());
		result = prime * result + ((hashMapAsMap == null) ? 0 : hashMapAsMap.hashCode());
		
		result = prime * result + ((inner == null) ? 0 : inner.hashCode());
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		result = prime * result
				+ ((longNumber == null) ? 0 : longNumber.hashCode());
		result = prime * result + ((map == null) ? 0 : map.hashCode());
		result = prime * result
				+ ((nullObject == null) ? 0 : nullObject.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + Arrays.hashCode(objectArray);
		result = prime * result
				+ ((objectMap == null) ? 0 : objectMap.hashCode());
		result = prime * result + Arrays.hashCode(primitiveArray);
		result = prime * result + Float.floatToIntBits(primitiveFraction);
		result = prime * result + primitiveNumber;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AllDataHolder other = (AllDataHolder) obj;
		if (!Arrays.equals(array, other.array))
			return false;
		if (arrayList == null) {
			if (other.arrayList != null)
				return false;
		} else {
			Collection arrayListAsCollection = arrayList;
			Collection otherArrayListAsCollection = other.arrayList;
			if (!arrayListAsCollection.equals(otherArrayListAsCollection))	
			return false;
		}
		if (bool != other.bool)
			return false;
		if (character != other.character)
			return false;
		if (collection == null) {
			if (other.collection != null)
				return false;
		} else if (!collection.equals(other.collection))
			return false;
		if (fraction == null) {
			if (other.fraction != null)
				return false;
		} else if (!fraction.equals(other.fraction))
			return false;
		if (hashMap == null) {
			if (other.hashMap != null)
				return false;
		} else { 
			Map hashMapAsMap = hashMap;
			Map otherHashMapAsMap = other.hashMap;
			if (!hashMapAsMap.equals(otherHashMapAsMap))
			return false;
		}
		if (inner == null) {
			if (other.inner != null)
				return false;
		} else if (!inner.equals(other.inner))
			return false;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		if (longNumber == null) {
			if (other.longNumber != null)
				return false;
		} else if (!longNumber.equals(other.longNumber))
			return false;
		if (map == null) {
			if (other.map != null)
				return false;
		} else if (!map.equals(other.map))
			return false;
		if (nullObject == null) {
			if (other.nullObject != null)
				return false;
		} else if (!nullObject.equals(other.nullObject))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (!Arrays.equals(objectArray, other.objectArray))
			return false;
		if (objectMap == null) {
			if (other.objectMap != null)
				return false;
		} else if (!objectMap.equals(other.objectMap))
			return false;
		if (!Arrays.equals(primitiveArray, other.primitiveArray))
			return false;
		if (Float.floatToIntBits(primitiveFraction) != Float
				.floatToIntBits(other.primitiveFraction))
			return false;
		if (primitiveNumber != other.primitiveNumber)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	/*
	 public boolean equals(Object obj) {
		   return EqualsBuilder.reflectionEquals(this, obj);
		 }
	 public int hashCode() {
		   return HashCodeBuilder.reflectionHashCode(this);
		 }
		 */
}
