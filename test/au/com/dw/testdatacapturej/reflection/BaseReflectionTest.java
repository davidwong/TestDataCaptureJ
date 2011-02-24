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
package au.com.dw.testdatacapturej.reflection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

import au.com.dw.testdatacapturej.mock.dataholder.InnerDataHolder;
import au.com.dw.testdatacapturej.mock.dataholder.SimpleDataHolder;
import au.com.dw.testdatacapturej.mock.dataholder.TestData;

/**
 * Test data generator for the various types of fields to be tested. To be used as a base class for the
 * proper test classes so that they can use the same test data.
 * 
 * Note: test code generation for map entries may be in reverse order
 * e.g.
 * The code:
 *   map.put(key1, a);
 *   map.put(key2, b);
 * may generate this:
 *   map.put(key2, b);
 *   map.put(key1, a);
 * So for au.com.dw.testing may need both alternatives.
 *   
 * @author David Wong
 *
 */
public class BaseReflectionTest {

	public static final String KEY_1 = "key1";
	public static final String KEY_2 = "key2";
	
	public BaseReflectionTest() {
		super();
	}

	// test data for primitive fields
	
	protected byte createByte()
	{
		return 1;
	}
	
	protected int createInt()
	{
		return 2;
	}
	
	protected long createLong()
	{
		return 3L;
	}
	
	protected float createFloat()
	{
		return 10.1f;
	}
	
	protected double createDouble()
	{
		return 20.1d;
	}
	
	protected char createChar()
	{
		return 'a';
	}
	
	protected boolean createBoolean()
	{
		return true;
	}

	// test data for wrapper fields
	
	protected Byte createByteObject()
	{
		return new Byte((byte)5);
	}
	
	protected Integer createInteger()
	{
		return new Integer(6);
	}
	
	protected Long createLongObject()
	{
		return new Long(7L);
	}
	
	protected Float createFloatObject()
	{
		return new Float(50.1f);
	}
	
	protected Double createDoubleObject()
	{
		return new Double(60.1d);
	}
	
	protected Character createCharacter()
	{
		return new Character('b');
	}
	
	protected Boolean createBooleanObject()
	{
		return new Boolean(false);
	}

	protected String createString()
	{
		return "test";
	}
	
	// test data for Collection interface

	protected Collection<?> createEmptyCollection()
	{
		Collection<?> collection = new HashSet<Object>();

		return collection;
	}
	
	protected Collection createBuiltinCollection()
	{
		Collection collection = new HashSet();
		collection.add("value-1");
		collection.add("value-2");

		return collection;
	}

	protected Collection createLongCollection()
	{
		Collection collection = new HashSet();
		collection.add(100L);
		collection.add(new Long(200L));

		return collection;
	}
	
	protected Collection createObjectCollection()
	{
		Collection collection = new HashSet();
		collection.add(new InnerDataHolder("immutable-1", 1.1));
		collection.add(new InnerDataHolder("immutable-2", 2.1));

		return collection;
	}
	
	protected Collection createMixedCollection()
	{
		Collection collection = new HashSet();
		collection.add(new Integer(1));
		collection.add(new InnerDataHolder("immutable-2", 2.1));

		return collection;
	}
	
	// test data for specific Collection class

	protected ArrayList createEmptyCollectionClass()
	{
		ArrayList arrayList = new ArrayList();

		return arrayList;
	}
	
	protected ArrayList createBuiltinCollectionClass()
	{
		ArrayList arrayList = new ArrayList();
		arrayList.add("value-1");
		arrayList.add("value-2");

		return arrayList;
	}

	protected ArrayList createObjectCollectionClass()
	{
		ArrayList arrayList = new ArrayList();
		arrayList.add(new InnerDataHolder("immutable-1", 1.1));
		arrayList.add(new InnerDataHolder("immutable-2", 2.1));

		return arrayList;
	}
	
	protected ArrayList createMixedCollectionClass()
	{
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Integer(1));
        arrayList.add(new InnerDataHolder("immutable-2", 2.1));

		return arrayList;
	}
	
	// test data for primitive array
	
	protected int[] createEmptyArray()
	{
		return new int[0];
	}
	
	protected byte[] createByteArray()
	{
		return new byte[]{1,2};
	}
	
	protected int[] createIntArray()
	{
		return new int[]{3,4};
	}
	
	protected long[] createLongArray()
	{
		return new long[]{5L,6L};
	}
	
	protected float[] createFloatArray()
	{
		return new float[]{10.1f,20.1f};
	}
	
	protected double[] createDoubleArray()
	{
		return new double[]{30.1d,40.1d};
	}
	
	protected char[] createCharArray()
	{
		return new char[]{'a','b'};
	}
	
	protected boolean[] createBooleanArray()
	{
		return new boolean[]{true,false};
	}

	// test data for wrapper array
	
	protected Object[] createEmptyObjectArray()
	{
		return new Object[0];
	}
	
	protected Byte[] createByteObjectArray()
	{
		return new Byte[]{new Byte((byte)10),new Byte((byte)11)};
	}
	
	protected Integer[] createIntegerArray()
	{
		return new Integer[]{new Integer(12),new Integer(13)};
	}
	
	protected Long[] createLongObjectArray()
	{
		return new Long[]{new Long(14L),new Long(15L)};
	}
	
	protected Float[] createFloatObjectArray()
	{
		return new Float[]{new Float(50.1f),new Float(60.1f)};
	}
	
	protected Double[] createDoubleObjectArray()
	{
		return new Double[]{new Double(70.1d),new Double(80.1d)};
	}
	
	protected Character[] createCharacterArray()
	{
		return new Character[]{new Character('c'),new Character('d')};
	}
	
	protected Boolean[] createBooleanObjectArray()
	{
		return new Boolean[]{new Boolean(false),new Boolean(true)};
	}

	protected String[] createStringArray()
	{
		return new String[]{"test1","test2"};
	}

	protected Object[] createObjectArray()
	{
		TestData testData = new TestData();
		SimpleDataHolder object1 = testData.createSimpleDataHolder();
		SimpleDataHolder object2 = testData.createSimpleDataHolder2();
		
		return new Object[] {object1, object2};
	}
	
	// test data for Map interface

	protected Map createEmptyMap()
	{
		Map map = new TreeMap();

		return map;
	}
	
	protected Map createBuiltinMap()
	{
		Map map = new TreeMap();
		map.put(KEY_1, "value1");
		map.put(KEY_2, "value2");

		return map;
	}

	protected Map createObjectMap()
	{
		Map map = new TreeMap();
		map.put(new InnerDataHolder("key-1", 1.1), new InnerDataHolder("immutable-10", 10.1));
		map.put(new InnerDataHolder("key-2", 2.1), new InnerDataHolder("immutable-20", 20.1));

		return map;
	}
	
	protected Map createMixedMap()
	{
		Map map = new TreeMap();
		map.put(KEY_1, new InnerDataHolder("immutable-10", 10.1));
		map.put(KEY_2, new Integer(20));
		return map;
	}

	protected HashMap createEmptyMapClass()
	{
		HashMap hashMap = new HashMap();

		return hashMap;
	}
	
	protected HashMap createBuiltinMapClass()
	{
		HashMap hashMap = new HashMap();
		hashMap.put(KEY_1, "value1");
		hashMap.put(KEY_2, "value2");

		return hashMap;
	}

	protected HashMap createObjectMapClass()
	{
		HashMap hashMap = new HashMap();
		hashMap.put(new InnerDataHolder("key-1", 1.1), new InnerDataHolder("immutable-10", 10.1));
		hashMap.put(new InnerDataHolder("key-2", 2.1), new InnerDataHolder("immutable-20", 20.1));

		return hashMap;
	}
	
	protected HashMap createMixedMapClass()
	{
		HashMap hashMap = new HashMap();
		hashMap.put(KEY_1, new InnerDataHolder("immutable-10", 10.1));
		hashMap.put(KEY_2, new Integer(20));
		return hashMap;
	}

	protected HashMap createCharMap()
	{
		HashMap hashMap = new HashMap();
		hashMap.put(KEY_1, 'a');
		hashMap.put(KEY_2, 'b');
		return hashMap;
	}
}