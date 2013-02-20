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
package au.com.dw.testdatacapturej.log;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import au.com.dw.testdatacapturej.log.FormatConstants;
import au.com.dw.testdatacapturej.log.java.ArrayElementGenerator;
import au.com.dw.testdatacapturej.log.java.CollectionElementGenerator;
import au.com.dw.testdatacapturej.log.java.MapEntryGenerator;
import au.com.dw.testdatacapturej.log.java.SimpleFieldGenerator;
import au.com.dw.testdatacapturej.meta.ContainmentType;
import au.com.dw.testdatacapturej.meta.ObjectInfo;
import au.com.dw.testdatacapturej.meta.ObjectType;


/**
 * Tests for the log() implementations of FieldDisplay.
 * @see au.com.dw.testdatacapturej.log.FieldGenerator
 * 
 * @author David Wong
 *
 */
public class FieldDisplayTest {

	
	/** Dummy class name */
	private String classFieldName = "dummy.classField";
	
	/** index for arrays */
	private int index = 0;
	
	/** Dummy field name */
	private String fieldName = "fieldName";
	
	// test data
	
	private String stringValue = "test";
	private int intValue = 1;
	private Integer integerValue = new Integer(2);
	private long longValue = 3;
	private Long longObjectValue = new Long(4);
	private char charValue = 'a';
	private Character characterValue = new Character('b');
	private float floatValue = 10.1f;
	private Float floatObjectValue = new Float(20.1f);
	private double doubleValue = 30.1d;
	private Double doubleObjectValue = new Double(40.1d);
	
	private ObjectInfo info;
	
	@Before
	public void setUp() throws Exception {
		index = 0;
	}

	private ObjectInfo createObjectInfo(ObjectType type, ContainmentType containmentType) {
		info = new ObjectInfo();
		info.setContainingClassFieldName(classFieldName);
		info.setIndex(index);
		info.setFieldName(fieldName);
		info.setType(type);
		info.setContainmentType(containmentType);
		
		return info;
	}

	private ObjectInfo createKeyObjectInfo(ObjectType type, ContainmentType containmentType) {
		info = new ObjectInfo();
		info.setType(type);
		info.setContainmentType(containmentType);
		
		return info;
	}

	private String generateLog(FieldGenerator display, ObjectInfo info, Object value, ObjectInfo keyInfo)
	{
		info.setValue(value);
		info.setKeyInfo(keyInfo);
		String result = display.log(info);
		result = result.replaceAll(FormatConstants.newLine, "");
		
		return result;
	}

	private String generateLog(FieldGenerator display, ObjectInfo info, Object value)
	{
		return generateLog(display, info, value, null);
	}

	/**
	 * Test the output for various simple data types for fields for SimpleFieldDisplay.
	 * Output should be in the format:
	 * classFieldName.setFieldName(..);
	 * 
	 * For ease of comparison, newLine's are removed from the result string since we're not
	 * interested in formatting at this low level au.com.dw.testing.
	 * 
	 * @see au.com.dw.testdatacapturej.log.java.SimpleFieldGenerator
	 */
	@Test
	public void testSimpleField()
	{
		FieldGenerator display = new SimpleFieldGenerator();	
		ObjectInfo info = createObjectInfo(ObjectType.SIMPLE, ContainmentType.FIELD);
		
		String result = generateLog(display, info, stringValue);
		System.out.println(result);
		String expected = "dummy.classField.setFieldName(\"" + stringValue + "\");";
		assertEquals(expected, result);
		
		result = generateLog(display, info, intValue);
		System.out.println(result);
		expected = "dummy.classField.setFieldName(" + intValue + ");";
		assertEquals(expected, result);
		
		result = generateLog(display, info, integerValue);
		System.out.println(result);
		expected = "dummy.classField.setFieldName(" + integerValue + ");";
		assertEquals(expected, result);
		
		result = generateLog(display, info, longValue);
		System.out.println(result);
		expected = "dummy.classField.setFieldName(" + longValue + "L);";
		assertEquals(expected, result);
				
		result = generateLog(display, info, longObjectValue);
		System.out.println(result);
		expected = "dummy.classField.setFieldName(" + longObjectValue + "L);";
		assertEquals(expected, result);
				
		result = generateLog(display, info, charValue);
		System.out.println(result);
		expected = "dummy.classField.setFieldName('" + charValue + "');";
		assertEquals(expected, result);
		
		result = generateLog(display, info, characterValue);
		System.out.println(result);
		expected = "dummy.classField.setFieldName('" + characterValue + "');";
		assertEquals(expected, result);
		
		result = generateLog(display, info, floatValue);
		System.out.println(result);
		expected = "dummy.classField.setFieldName(" + floatValue + "f);";
		assertEquals(expected, result);
		
		result = generateLog(display, info, floatObjectValue);
		System.out.println(result);
		expected = "dummy.classField.setFieldName(" + floatObjectValue + "f);";
		assertEquals(expected, result);		
		
		result = generateLog(display, info, doubleValue);
		System.out.println(result);
		expected = "dummy.classField.setFieldName(" + doubleValue + "d);";
		assertEquals(expected, result);
		
		result = generateLog(display, info, doubleObjectValue);
		System.out.println(result);
		expected = "dummy.classField.setFieldName(" + doubleObjectValue + "d);";
		assertEquals(expected, result);
	}
	
	/**
	 * Test the output for various simple data types for elements in an array for ArrayElementDisplay.
	 * Output should be in the format:
	 * classFieldName[index] = ..;
	 * 
	 * @see au.com.dw.testdatacapturej.log.java.ArrayElementGenerator
	 */
	@Test
	public void testArrayElementField()
	{
		FieldGenerator display = new ArrayElementGenerator();
		ObjectInfo info = createObjectInfo(ObjectType.SIMPLE, ContainmentType.ARRAY_ELEMENT);
		
		String result = generateLog(display, info, stringValue);
		System.out.println(result);
		String expected = "dummy.classField[" + (index) + "] = \"" + stringValue + "\";";
		assertEquals(expected, result);
		
		result = generateLog(display, info, intValue);
		System.out.println(result);
		expected = "dummy.classField[" + (index) + "] = " + intValue + ";";
		assertEquals(expected, result);
		
		result = generateLog(display, info, integerValue);
		System.out.println(result);
		expected = "dummy.classField[" + (index) + "] = " + integerValue + ";";
		assertEquals(expected, result);
		
		result = generateLog(display, info, longValue);
		System.out.println(result);
		expected = "dummy.classField[" + (index) + "] = " + longValue + "L;";
		assertEquals(expected, result);
		
		result = generateLog(display, info, longObjectValue);
		System.out.println(result);
		expected = "dummy.classField[" + (index) + "] = " + longObjectValue + "L;";
		assertEquals(expected, result);
		
		result = generateLog(display, info, charValue);
		System.out.println(result);
		expected = "dummy.classField[" + (index) + "] = '" + charValue + "';";
		assertEquals(expected, result);
		
		result = generateLog(display, info, characterValue);
		System.out.println(result);
		expected = "dummy.classField[" + (index) + "] = '" + characterValue + "';";
		assertEquals(expected, result);
		
		result = generateLog(display, info, floatValue);
		System.out.println(result);
		expected = "dummy.classField[" + (index) + "] = " + floatValue + "f;";
		assertEquals(expected, result);
		
		result = generateLog(display, info, floatObjectValue);
		System.out.println(result);
		expected = "dummy.classField[" + (index) + "] = " + floatObjectValue + "f;";
		assertEquals(expected, result);
		
		result = generateLog(display, info, doubleValue);
		System.out.println(result);
		expected = "dummy.classField[" + (index) + "] = " + doubleValue + "d;";
		assertEquals(expected, result);
		
		result = generateLog(display, info, doubleObjectValue);
		System.out.println(result);
		expected = "dummy.classField[" + (index) + "] = " + doubleObjectValue + "d;";
		assertEquals(expected, result);

	}
	
	/**
	 * Test the output for various simple data types for elements in a collection for ElementDisplay.
	 * Output should be in the format:
	 * fieldName.add(..);
	 * 
	 * @see au.com.dw.testdatacapturej.log.java.CollectionElementGenerator
	 */
	@Test
	public void testCollectionElementField()
	{
		FieldGenerator display = new CollectionElementGenerator();
		ObjectInfo info = createObjectInfo(ObjectType.SIMPLE, ContainmentType.COLLECTION_ELEMENT);
		
		String result = generateLog(display, info, stringValue);
		System.out.println(result);
		String expected = "dummy.classField.add(\"" + stringValue + "\");";
		assertEquals(expected, result);
		
		result = generateLog(display, info, intValue);
		System.out.println(result);
		expected = "dummy.classField.add(" + intValue + ");";
		assertEquals(expected, result);
		
		result = generateLog(display, info, integerValue);
		System.out.println(result);
		expected = "dummy.classField.add(" + integerValue + ");";
		assertEquals(expected, result);
		
		result = generateLog(display, info, longValue);
		System.out.println(result);
		expected = "dummy.classField.add(" + longValue + "L);";
		assertEquals(expected, result);
		
		result = generateLog(display, info, longObjectValue);
		System.out.println(result);
		expected = "dummy.classField.add(" + longObjectValue + "L);";
		assertEquals(expected, result);
		
		result = generateLog(display, info, charValue);
		System.out.println(result);
		expected = "dummy.classField.add('" + charValue + "');";
		assertEquals(expected, result);
		
		result = generateLog(display, info, characterValue);
		System.out.println(result);
		expected = "dummy.classField.add('" + characterValue + "');";
		assertEquals(expected, result);
		
		result = generateLog(display, info, floatValue);
		System.out.println(result);
		expected = "dummy.classField.add(" + floatValue + "f);";
		assertEquals(expected, result);
		
		result = generateLog(display, info, floatObjectValue);
		System.out.println(result);
		expected = "dummy.classField.add(" + floatObjectValue + "f);";
		assertEquals(expected, result);
		
		result = generateLog(display, info, doubleValue);
		System.out.println(result);
		expected = "dummy.classField.add(" + doubleValue + "d);";
		assertEquals(expected, result);
		
		result = generateLog(display, info, doubleObjectValue);
		System.out.println(result);	
		expected = "dummy.classField.add(" + doubleObjectValue + "d);";
		assertEquals(expected, result);		
	}

	/**
	 * Test the output for various simple data types for entry elements in a map for MapEntryDisplay.
	 * Output should be in the format:
	 * dummy.classField.put(.., ..);
	 * 
	 * @see au.com.dw.testdatacapturej.log.java.MapEntryGenerator
	 */
	@Test
	public void testMapEntryField()
	{
		FieldGenerator display = new MapEntryGenerator();
		ObjectInfo info = createObjectInfo(ObjectType.SIMPLE, ContainmentType.MAP_ENTRY);
		
		ObjectInfo keyInfo = createKeyObjectInfo(ObjectType.SIMPLE, ContainmentType.FIELD);
		

		keyInfo.setValue(stringValue);
		String result = generateLog(display, info, stringValue, keyInfo);
		System.out.println(result);
		String expected = "dummy.classField.put(\"" + stringValue + "\", \"" + stringValue + "\");";
		assertEquals(expected, result);

		keyInfo.setValue(intValue);
		result = generateLog(display, info, intValue, keyInfo);
		System.out.println(result);
		expected = "dummy.classField.put(" + intValue + ", " + intValue + ");";
		assertEquals(expected, result);
		
		keyInfo.setValue(integerValue);
		result = generateLog(display, info, integerValue, keyInfo);
		System.out.println(result);
		expected = "dummy.classField.put(" + integerValue + ", " + integerValue + ");";
		assertEquals(expected, result);
		
		keyInfo.setValue(longValue);
		result = generateLog(display, info, longValue, keyInfo);
		System.out.println(result);
		expected = "dummy.classField.put(" + longValue + "L, " + longValue + "L);";
		assertEquals(expected, result);
		
		keyInfo.setValue(longObjectValue);
		result = generateLog(display, info, longObjectValue, keyInfo);
		System.out.println(result);
		expected = "dummy.classField.put(" + longObjectValue + "L, " + longObjectValue + "L);";
		assertEquals(expected, result);	
		
		keyInfo.setValue(charValue);
		result = generateLog(display, info, charValue, keyInfo);
		System.out.println(result);
		expected = "dummy.classField.put('" + charValue + "', '" + charValue + "');";
		assertEquals(expected, result);	
		
		keyInfo.setValue(characterValue);
		result = generateLog(display, info, characterValue, keyInfo);
		System.out.println(result);
		expected = "dummy.classField.put('" + characterValue + "', '" + characterValue + "');";
		assertEquals(expected, result);
		
		keyInfo.setValue(floatValue);
		result = generateLog(display, info, floatValue, keyInfo);
		System.out.println(result);
		expected = "dummy.classField.put(" + floatValue + "f, " + floatValue + "f);";
		assertEquals(expected, result);	

		keyInfo.setValue(floatObjectValue);
		result = generateLog(display, info, floatObjectValue, keyInfo);
		System.out.println(result);
		expected = "dummy.classField.put(" + floatObjectValue + "f, " + floatObjectValue + "f);";
		assertEquals(expected, result);	
		
		keyInfo.setValue(doubleValue);
		result = generateLog(display, info, doubleValue, keyInfo);
		System.out.println(result);
		expected = "dummy.classField.put(" + doubleValue + "d, " + doubleValue + "d);";
		assertEquals(expected, result);	
		
		keyInfo.setValue(doubleObjectValue);
		result = generateLog(display, info, doubleObjectValue, keyInfo);
		System.out.println(result);
		expected = "dummy.classField.put(" + doubleObjectValue + "d, " + doubleObjectValue + "d);";
		assertEquals(expected, result);	
	}

}
