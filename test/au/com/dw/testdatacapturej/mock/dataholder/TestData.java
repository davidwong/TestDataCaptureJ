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
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import au.com.dw.testdatacapturej.mock.type.reflection.NoMethodDataHolder;


/**
 * Test mock generic object creator for test objects that are just collections of fields of various
 * types.
 * 
 * @author David Wong
 *
 */
public class TestData {
	
	/**
	 * Create a test object that holds all(?) types, including another of the same object as one of
	 * the fields.
	 * 
	 * @return
	 */
	public AllDataHolder createTestDataHolder()
	{
        AllDataHolder data = new AllDataHolder();
        
        data.setText("aaa");
        data.setNumber(new Integer(1));
        data.setPrimitiveNumber(2);
        data.setCharacter('a');
        data.setBool(true);
        data.setLongNumber(new Long(3));
        data.setPrimitiveFraction(0.1f);
        data.setFraction(new Double(0.2));
        
        AllDataHolder inner = createInnerDataHolder();
        data.setInner(inner);
        
        data.setStaticText("static-aaa");
        data.setStaticObject(new InnerDataHolder("immutable-1", 1.0));

        Collection collection = new HashSet();
        collection.add("collection-1");
        collection.add("collection-2");  
        data.setCollection(collection);
        
        List list = new LinkedList();
        list.add(new InnerDataHolder("immutable-2", 2.0));
        list.add(new InnerDataHolder("immutable-3", 3.0));
        data.setList(list);

        ArrayList arrayList = new ArrayList();
        arrayList.add(new Integer(4));
        arrayList.add(new InnerDataHolder("immutable-4", 4.1));
        data.setArrayList(arrayList);
        
        String[] array = new String[] {"array-1", "array-2"};
        data.setArray(array);
        
        int[] primitiveArray = new int[] {9,8};
        data.setPrimitiveArray(primitiveArray);
        
        Object[] objectArray = new Object[] {new InnerDataHolder("immutable-5", 5.0), inner};
        data.setObjectArray(objectArray);
        
		Map map = new TreeMap();
		map.put("key-1", "value-1");
		map.put("key-2", "value-2");
        data.setMap(map);
        
        Map objectMap = new HashMap();
        objectMap.put("object-key-1", new InnerDataHolder("immutable-6", 6.0));
        objectMap.put("object-key-2", new InnerDataHolder("immutable-7", 7.0));
        data.setObjectMap(objectMap);

        HashMap hashMap = new HashMap();
        hashMap.put("hash-key-1", new Boolean(false));
        hashMap.put("hash-key-2", new InnerDataHolder("immutable-8", 8.0));
        data.setHashMap(hashMap);
        
        return data;
	}

	/**
	 * Create another AllDataHolder for testing multiple parameters. This version does not have an inner class.
	 * 
	 * @return
	 */
	public AllDataHolder createTestDataHolder2()
	{
        AllDataHolder data = new AllDataHolder();
        
        data.setText("bbb");
        data.setNumber(new Integer(100));
        data.setPrimitiveNumber(200);
        data.setCharacter('b');
        data.setBool(false);
        data.setLongNumber(new Long(300));
        data.setPrimitiveFraction(0.001f);
        data.setFraction(new Double(0.002));
        
        data.setStaticText("static-bbb");
        data.setStaticObject(new InnerDataHolder("immutable-100", 100.0));

        Collection collection = new HashSet();
        collection.add("collection-100");
        collection.add("collection-200");  
        data.setCollection(collection);
        
        List list = new LinkedList();
        list.add(new InnerDataHolder("immutable-200", 200.0));
        list.add(new InnerDataHolder("immutable-300", 300.0));
        data.setList(list);

        ArrayList arrayList = new ArrayList();
        arrayList.add(new Integer(400));
        arrayList.add(new InnerDataHolder("immutable-400", 400.1));
        data.setArrayList(arrayList);
        
        String[] array = new String[] {"array-100", "array-200"};
        data.setArray(array);
        
        int[] primitiveArray = new int[] {900,800};
        data.setPrimitiveArray(primitiveArray);
        
        Object[] objectArray = new Object[] {new InnerDataHolder("immutable-500", 500.0)};
        data.setObjectArray(objectArray);
        
		Map map = new TreeMap();
		map.put("key-100", "value-100");
		map.put("key-200", "value-200");
        data.setMap(map);
        
        Map objectMap = new HashMap();
        objectMap.put("object-key-100", new InnerDataHolder("immutable-600", 600.0));
        objectMap.put("object-key-200", new InnerDataHolder("immutable-700", 700.0));
        data.setObjectMap(objectMap);

        HashMap hashMap = new HashMap();
        hashMap.put("hash-key-100", new Boolean(false));
        hashMap.put("hash-key-200", new InnerDataHolder("immutable-800", 800.0));
        data.setHashMap(hashMap);
        
        return data;
	}

	/**
	 * Create a test object that is meant to be used as a field inside another test object.
	 * 
	 * @return
	 */
	private AllDataHolder createInnerDataHolder()
	{
        AllDataHolder data = new AllDataHolder();
        
        data.setText("zzz");
        data.setNumber(new Integer(100));
        data.setPrimitiveNumber(200);
        data.setCharacter('z');
        data.setBool(false);
        data.setLongNumber(new Long(300));
        data.setPrimitiveFraction(100.1f);
        data.setFraction(new Double(100.2));
        
        data.setStaticText("static-zzz");
        data.setStaticObject(new InnerDataHolder("immutable-inner-1", 100.0));

        Collection collection = new HashSet();
        collection.add("collection-inner-1");
        collection.add("collection-inner-2");  
        data.setCollection(collection);
        
        List list = new LinkedList();
        list.add(new InnerDataHolder("immutable-inner-2", 200.0));
        list.add(new InnerDataHolder("immutable-inner-3", 300.0));
        data.setList(list);

        ArrayList arrayList = new ArrayList();
        arrayList.add(new Integer(400));
        arrayList.add(new InnerDataHolder("immutable-inner-4", 400.1));
        data.setArrayList(arrayList);
        
        String[] array = new String[] {"array-inner-1", "array-inner-2"};
        data.setArray(array);

        int[] primitiveArray = new int[] {7,6};
        data.setPrimitiveArray(primitiveArray);
        
        Object[] objectArray = new Object[] {new InnerDataHolder("immutable-inner-5", 500.0), new Long(501)};
        data.setObjectArray(objectArray);
        
		Map map = new TreeMap();
		map.put("key-inner-1", "value-inner-1");
		map.put("key-inner-2", "value-inner-2");
        data.setMap(map);
        
        Map objectMap = new HashMap();
        objectMap.put("object-key-inner-1", new InnerDataHolder("immutable-inner-6", 600.0));
        objectMap.put("object-key-inner-2", new InnerDataHolder("immutable-inner-7", 700.0));
        data.setObjectMap(objectMap);

        HashMap hashMap = new HashMap();
        hashMap.put("hash-key-inner-1", new Boolean(true));
        hashMap.put("hash-key-inner-2", new InnerDataHolder("immutable-inner-8", 800.0));
        data.setHashMap(hashMap);
        
        return data;
	}

	// Test data for negative tests
	// ----------------------------
	
	public NoMethodDataHolder createNoMethodDataHolder()
	{
		return new NoMethodDataHolder("no setter");
	}
	
	/**
	 * Create nested collection/array/map test object.
	 * 
	 * @return
	 */
	public Collection createNestedDataHolderLevel5()
	{

		HashMap hashMap = new HashMap();
		hashMap.put("inner-map-key", "inner-map-value");

		
		HashMap[] innerArray = new HashMap[1];
		innerArray[0] = hashMap;
		
		ArrayList arrayList = new ArrayList();
		arrayList.add(innerArray);
		
		Map map = new TreeMap();
		map.put("out-map-key", arrayList);
		
		Object[] outerArray = new Object[1];
		outerArray[0] = map;
		
		Collection collection = new HashSet();
		collection.add(outerArray);
		
		return collection;
	}

	public Collection createNestedDataHolderLevel4()
	{		
		HashMap[] innerArray = new HashMap[1];
		
		ArrayList arrayList = new ArrayList();
		arrayList.add(innerArray);
		
		Map map = new TreeMap();
		map.put("out-map-key", arrayList);
		
		Object[] outerArray = new Object[1];
		outerArray[0] = map;
		
		Collection collection = new HashSet();
		collection.add(outerArray);
		
		return collection;
	}
	
	public Collection createNestedDataHolderLevel3()
	{		
		ArrayList arrayList = new ArrayList();
		
		Map map = new TreeMap();
		map.put("out-map-key", arrayList);
		
		Object[] outerArray = new Object[1];
		outerArray[0] = map;
		
		Collection collection = new HashSet();
		collection.add(outerArray);
		
		return collection;
	}
	
	public Collection createNestedDataHolderLevel2()
	{	
		Map map = new TreeMap();
		
		Object[] outerArray = new Object[1];
		outerArray[0] = map;
		
		Collection collection = new HashSet();
		collection.add(outerArray);
		
		return collection;
	}
	
	public Collection createNestedDataHolderLevel1()
	{
		Object[] outerArray = new Object[1];
		
		Collection collection = new HashSet();
		collection.add(outerArray);
		
		return collection;
	}
	
	// Test data for nested tests
	// --------------------------
	// (Collection contains ...)
	
	public Collection createNestedCollectionOfArrayNull()
	{
		Object[] innerArray = new Object[1];
		
		Collection collection = new TreeSet();
		collection.add(innerArray);
		
		return collection;
	}
	
	public Collection createNestedCollectionOfArrayEmpty()
	{
		Object[] innerArray = new Object[0];
		
		Collection collection = new HashSet();
		collection.add(innerArray);
		
		return collection;
	}
	
	public Collection createNestedCollectionOfArrayPrimitive()
	{
		int[] innerArray = new int[]{1};
		
		Collection collection = new LinkedHashSet();
		collection.add(innerArray);
		
		return collection;
	}
	
	public Collection createNestedCollectionOfArrayObject()
	{
		
		InnerDataHolder[] innerArray = new InnerDataHolder[1];
		innerArray[0] = new InnerDataHolder("one", 1);
		
		Collection collection = new ArrayList();
		collection.add(innerArray);
		
		return collection;
	}

	public Collection createNestedCollectionOfArrayObjects()
	{
		
		InnerDataHolder[] innerArray = new InnerDataHolder[] {new InnerDataHolder("one", 1), new InnerDataHolder("two", 2)};
		
		Collection collection = new ArrayList();
		collection.add(innerArray);
		
		return collection;
	}
	
	public Collection createNestedCollectionOfCollectionEmpty()
	{
		
		ArrayList innerCollection = new ArrayList();

		Collection collection = new LinkedList();
		collection.add(innerCollection);
		
		return collection;
	}
	
	public Collection createNestedCollectionOfCollectionNull()
	{
		
		TreeSet innerCollection = new TreeSet();
		innerCollection.add(null);
		
		Collection collection = new HashSet();
		collection.add(innerCollection);
		
		return collection;
	}
	
	public Collection createNestedCollectionOfCollectionObject()
	{
		
		ArrayList innerCollection = new ArrayList();
		innerCollection.add(new InnerDataHolder("one", 1));
		
		Collection collection = new LinkedHashSet();
		collection.add(innerCollection);
		
		return collection;
	}

	public Collection createNestedCollectionOfCollectionObjects()
	{
		
		ArrayList innerCollection = new ArrayList();
		innerCollection.add(new InnerDataHolder("one", 1));
		innerCollection.add(new InnerDataHolder("two", 2));
		
		Collection collection = new LinkedHashSet();
		collection.add(innerCollection);
		
		return collection;
	}
	
	public Collection createNestedCollectionOfMapEmpty()
	{
		
		HashMap innerMap = new HashMap();

		Collection collection = new LinkedList();
		collection.add(innerMap);
		
		return collection;
	}
	
	public Collection createNestedCollectionOfMapNull()
	{
		
		TreeMap innerMap = new TreeMap();
		innerMap.put("key1", null);
		
		Collection collection = new HashSet();
		collection.add(innerMap);
		
		return collection;
	}
	
	public Collection createNestedCollectionOfMapObject()
	{
		
		LinkedHashMap innerMap = new LinkedHashMap();
		innerMap.put("key1", new InnerDataHolder("one", 1));
		
		Collection collection = new LinkedHashSet();
		collection.add(innerMap);
		
		return collection;
	}

	public Collection createNestedCollectionOfMapObjects()
	{
		
		LinkedHashMap innerMap = new LinkedHashMap();
		innerMap.put("key1", new InnerDataHolder("one", 1));
		innerMap.put("key2", new InnerDataHolder("two", 2));
		
		Collection collection = new LinkedHashSet();
		collection.add(innerMap);
		
		return collection;
	}
	
	// Test data for nested tests
	// --------------------------
	// (Array contains ...)
	
	public Object[] createNestedArrayOfArrayNull()
	{
		Object[] innerArray = new Object[1];
		
		Object[] outerArray = new Object[1];
		outerArray[0] = innerArray;
		
		return outerArray;
	}
	
	public Object[] createNestedArrayOfArrayEmpty()
	{
		Object[] innerArray = new Object[0];
		
		Object[] outerArray = new Object[1];
		outerArray[0] = innerArray;
		
		return outerArray;
	}
	
	public Object[] createNestedArrayOfArrayObject()
	{
		Object[] innerArray = new Object[] {new InnerDataHolder("one", 1)};
		
		Object[] outerArray = new Object[1];
		outerArray[0] = innerArray;
		
		return outerArray;
	}

	public Object[] createNestedArrayOfArrayObjects()
	{
		Object[] innerArray = new Object[] {new InnerDataHolder("one", 1), new InnerDataHolder("two", 2)};
		
		Object[] outerArray = new Object[1];
		outerArray[0] = innerArray;
		
		return outerArray;
	}
	
	public Object[] createNestedArrayOfCollectionEmpty()
	{
		
		ArrayList innerCollection = new ArrayList();

		Object[] outerArray = new Object[1];
		outerArray[0] = innerCollection;
		
		return outerArray;
	}
	
	public Object[] createNestedArrayOfCollectionNull()
	{
		
		TreeSet innerCollection = new TreeSet();
		innerCollection.add(null);
		
		Object[] outerArray = new Object[1];
		outerArray[0] = innerCollection;
		
		return outerArray;
	}
	
	public Object[] createNestedArrayOfCollectionObject()
	{
		
		ArrayList innerCollection = new ArrayList();
		innerCollection.add(new InnerDataHolder("one", 1));
		
		Object[] outerArray = new Object[1];
		outerArray[0] = innerCollection;
		
		return outerArray;
	}

	public Object[] createNestedArrayOfCollectionObjects()
	{
		
		ArrayList innerCollection = new ArrayList();
		innerCollection.add(new InnerDataHolder("one", 1));
		innerCollection.add(new InnerDataHolder("two", 2));
		
		Object[] outerArray = new Object[1];
		outerArray[0] = innerCollection;
		
		return outerArray;
	}
	
	public Object[] createNestedArrayOfMapEmpty()
	{
		
		HashMap innerMap = new HashMap();

		Object[] outerArray = new Object[1];
		outerArray[0] = innerMap;
		
		return outerArray;
	}
	
	public Object[] createNestedArrayOfMapNull()
	{
		
		TreeMap innerMap = new TreeMap();
		innerMap.put("key1", null);
		
		Object[] outerArray = new Object[1];
		outerArray[0] = innerMap;
		
		return outerArray;
	}
	
	public Object[] createNestedArrayOfMapObject()
	{
		
		LinkedHashMap innerMap = new LinkedHashMap();
		innerMap.put("key1", new InnerDataHolder("one", 1));
		
		Object[] outerArray = new Object[1];
		outerArray[0] = innerMap;
		
		return outerArray;
	}

	public Object[] createNestedArrayOfMapObjects()
	{
		
		LinkedHashMap innerMap = new LinkedHashMap();
		innerMap.put("key1", new InnerDataHolder("one", 1));
		innerMap.put("key2", new InnerDataHolder("two", 2));
		
		Object[] outerArray = new Object[1];
		outerArray[0] = innerMap;
		
		return outerArray;
	}
	
	// Test data for nested tests
	// --------------------------
	// (Map contains ...)
	
	public Map createNestedMapOfArrayNull()
	{
		Object[] innerArray = new Object[1];
		
		HashMap map = new HashMap();
		map.put("key1", innerArray);
		
		return map;
	}
	
	public Map createNestedMapOfArrayEmpty()
	{
		Object[] innerArray = new Object[0];
		
		TreeMap map = new TreeMap();
		map.put("key1", innerArray);
		
		return map;
	}

	public Map createNestedMapOfArrayPrimitive()
	{
		int[] innerArray = new int[] {1};
		
		LinkedHashMap map = new LinkedHashMap();
		map.put("key1", innerArray);
		
		return map;
	}
	
	public Map createNestedMapOfArrayObject()
	{
		Object[] innerArray = new Object[] {new InnerDataHolder("one", 1)};
		
		LinkedHashMap map = new LinkedHashMap();
		map.put("key1", innerArray);
		
		return map;
	}

	public Map createNestedMapOfArrayObjects()
	{
		Object[] innerArray = new Object[] {new InnerDataHolder("one", 1), new InnerDataHolder("two", 2)};
		
		LinkedHashMap map = new LinkedHashMap();
		map.put("key1", innerArray);
		
		return map;
	}
	
	public Map createNestedMapOfCollectionEmpty()
	{
		
		ArrayList innerCollection = new ArrayList();

		HashMap map = new HashMap();
		map.put("key1", innerCollection);
		
		return map;
	}
	
	public Map createNestedMapOfCollectionNull()
	{
		
		TreeSet innerCollection = new TreeSet();
		innerCollection.add(null);
		
		TreeMap map = new TreeMap();
		map.put("key1", innerCollection);
		
		return map;
	}
	
	public Map createNestedMapOfCollectionObject()
	{
		
		LinkedList innerCollection = new LinkedList();
		innerCollection.add(new InnerDataHolder("one", 1));
		
		LinkedHashMap map = new LinkedHashMap();
		map.put("key1", innerCollection);
		
		return map;
	}

	public Map createNestedMapOfCollectionObjects()
	{
		
		LinkedList innerCollection = new LinkedList();
		innerCollection.add(new InnerDataHolder("one", 1));
		innerCollection.add(new InnerDataHolder("two", 2));
		
		LinkedHashMap map = new LinkedHashMap();
		map.put("key1", innerCollection);
		
		return map;
	}
	
	public Map createNestedMapOfMapEmpty()
	{
		
		HashMap innerMap = new HashMap();

		HashMap map = new HashMap();
		map.put("key1", innerMap);
		
		return map;
	}
	
	public Map createNestedMapOfMapNull()
	{
		
		TreeMap innerMap = new TreeMap();
		innerMap.put("innerkey", null);
		
		TreeMap map = new TreeMap();
		map.put("key1", innerMap);
		
		return map;
	}
	
	public Map createNestedMapOfMapObject()
	{
		
		LinkedHashMap innerMap = new LinkedHashMap();
		innerMap.put("innerkey", new InnerDataHolder("one", 1));
		
		LinkedHashMap map = new LinkedHashMap();
		map.put("key1", innerMap);
		
		return map;
	}

	public Map createNestedMapOfMapObjects()
	{
		
		LinkedHashMap innerMap = new LinkedHashMap();
		innerMap.put("innerkey", new InnerDataHolder("one", 1));
		innerMap.put("innerkey2", new InnerDataHolder("two", 2));
		
		LinkedHashMap map = new LinkedHashMap();
		map.put("key1", innerMap);
		
		return map;
	}
	
	// simpler versions of AllDataHolder with just simple fields
	// *********************************************************
	
	public SimpleDataHolder createSimpleDataHolder()
	{
		SimpleDataHolder holder = new SimpleDataHolder();
		
		holder.setText("aaa");
		holder.setCharacter('a');
		holder.setBool(true);
		holder.setNumber(1);
		holder.setLongNumber(100L);
		holder.setPrimitiveFraction(0.1f);
		holder.setFraction(100.1d);
		
		return holder;
	}

	public SimpleDataHolder createSimpleDataHolder2()
	{
		SimpleDataHolder holder = new SimpleDataHolder();
		
		holder.setText("bbb");
		holder.setCharacter('b');
		holder.setBool(false);
		holder.setNumber(2);
		holder.setLongNumber(200L);
		holder.setPrimitiveFraction(0.2f);
		holder.setFraction(200.2d);
		
		return holder;
	}
}
