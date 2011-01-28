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
package au.com.dw.testdatacapturej.test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import au.com.dw.testdatacapturej.util.TypeUtil;


/**
 * Tests for utility to determine the class type of fields.
 * 
 * @author David Wong
 *
 */
public class TypeTest {

	@Before
	public void setUp() throws Exception {
	}

	// internal test methods
	// *********************
	private boolean classIsArray(Object object)
	{
		return TypeUtil.isArray(object);
	}

	private boolean classIsCollection(Object object)
	{
		return TypeUtil.isCollection(object);
	}
	
	private boolean classIsMap(Object object)
	{
		return TypeUtil.isMap(object);
	}

	private boolean isJavaClass(Object object)
	{
		return TypeUtil.isJavaClass(object);
	}
	
	// test data creators
	// ******************
	private String[] createStringArray() {
		String[] stringArray = new String[]{"test1", "test2"};
		return stringArray;
	}
	
	private Collection createCollection() {
		Collection col = new ArrayList();
		col.add("test1");
		col.add("test2");
		return col;
	}

	private List createList() {
		List list = new ArrayList();
		list.add("test1");
		list.add("test2");
		return list;
	}

	private Map createMap() {
		Map map = new HashMap();
		map.put("key1", "value1");
		map.put("key1", "value1");
		return map;
	}

	private HashMap createHashMap() {
		HashMap hashMap = new HashMap();
		hashMap.put("key1", "value1");
		hashMap.put("key1", "value1");
		return hashMap;
	}
	
	// test methods
	// ************
	
	@Test
	public void isArray()
	{
		String[] stringArray = createStringArray();
		assertTrue(classIsArray(stringArray));
		
		String string = "fail";
		assertFalse(classIsArray(string));
	}


	@Test
	public void isCollection()
	{
		Collection col = createCollection();
		assertTrue(classIsCollection(col));

		List list = createList();
		assertTrue(classIsCollection(list));
		
		String[] stringArray = createStringArray();
		assertFalse(classIsCollection(stringArray));
		
		Map map = createMap();
		assertFalse(classIsCollection(map));
		
		HashMap hashMap = createHashMap();
		assertFalse(classIsCollection(hashMap));
	}

	@Test
	public void isMap()
	{
		Collection col = createCollection();
		assertFalse(classIsMap(col));

		List list = createList();
		assertFalse(classIsMap(list));
		
		String[] stringArray = createStringArray();
		assertFalse(classIsMap(stringArray));
		
		Map map = createMap();
		assertTrue(classIsMap(map));
		
		HashMap hashMap = createHashMap();
		assertTrue(classIsMap(hashMap));
	}
	
	@Test
	public void isJavaClass()
	{
		Collection col = createCollection();
		assertFalse(isJavaClass(col));

		List list = createList();
		assertFalse(isJavaClass(list));
		
		String[] stringArray = createStringArray();
		assertFalse(isJavaClass(stringArray));
		
		Map map = createMap();
		assertFalse(isJavaClass(map));
		
		HashMap hashMap = createHashMap();
		assertFalse(isJavaClass(hashMap));
		
		assertTrue(isJavaClass("test"));
		assertTrue(isJavaClass(new String("test")));
		assertTrue(isJavaClass(new Character('a')));
		assertTrue(isJavaClass(new Integer(0)));
		assertTrue(isJavaClass(new Long(0)));
		assertTrue(isJavaClass(new Boolean(true)));
		assertTrue(isJavaClass(new Double(0)));
		assertTrue(isJavaClass(new Float(0)));
		
	}
}
