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
import au.com.dw.testdatacapturej.log.display.AddedElementDisplay;
import au.com.dw.testdatacapturej.log.display.ArrayElementDisplay;
import au.com.dw.testdatacapturej.log.display.ElementDisplay;
import au.com.dw.testdatacapturej.log.display.FieldDisplay;
import au.com.dw.testdatacapturej.log.display.MapEntryDisplay;
import au.com.dw.testdatacapturej.log.display.SimpleFieldDisplay;
import au.com.dw.testdatacapturej.meta.ContainmentType;
import au.com.dw.testdatacapturej.meta.ObjectInfo;
import au.com.dw.testdatacapturej.meta.ObjectType;


/**
 * Tests for the log() implementations of FieldDisplay.
 * @see au.com.dw.testdatacapturej.log.display.FieldDisplay
 * 
 * @author David Wong
 *
 */
public class CollectionAdderTest {

	/** Parent field class name */
	private String parentClassFieldName = "parent.classField";
	
	/** Parent adder method name */
	private String adderMethodName = "addElement";
	
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
		
		ObjectInfo parentInfo = new ObjectInfo();
		parentInfo.setContainingClassFieldName(parentClassFieldName);
		parentInfo.setClassFieldName("collection");
		parentInfo.setUsesAdder(true);
		parentInfo.setAdderMethodName(adderMethodName);
			
		info.setParentInfo(parentInfo);
		parentInfo.addFieldToList(info);
		
		return info;
	}

	private ObjectInfo createKeyObjectInfo(ObjectType type, ContainmentType containmentType) {
		info = new ObjectInfo();
		info.setType(type);
		info.setContainmentType(containmentType);
		
		return info;
	}

	private String generateLog(FieldDisplay display, ObjectInfo info, Object value, ObjectInfo keyInfo)
	{
		info.setValue(value);
		info.setKeyInfo(keyInfo);
		String result = display.log(info);
		result = result.replaceAll(FormatConstants.newLine, "");
		
		return result;
	}

	private String generateLog(FieldDisplay display, ObjectInfo info, Object value)
	{
		return generateLog(display, info, value, null);
	}

	
	/**
	 * Test the output for various simple data types for elements in a collection for AddedElementDisplay.
	 * Output should be in the format:
	 * parentFieldName.addElement(..);
	 * 
	 * @see au.com.dw.testdatacapturej.log.display.ElementDisplay
	 */
	@Test
	public void testCollectionElementField()
	{
		FieldDisplay display = new AddedElementDisplay();
		ObjectInfo info = createObjectInfo(ObjectType.SIMPLE, ContainmentType.ADDED_COLLECTION_ELEMENT);
		
		String result = generateLog(display, info, stringValue);
		System.out.println(result);
		String expected = "parent.classField.addElement(\"" + stringValue + "\");";
		assertEquals(expected, result);
		
		result = generateLog(display, info, intValue);
		System.out.println(result);
		expected = "parent.classField.addElement(" + intValue + ");";
		assertEquals(expected, result);
		
		result = generateLog(display, info, integerValue);
		System.out.println(result);
		expected = "parent.classField.addElement(" + integerValue + ");";
		assertEquals(expected, result);
		
		result = generateLog(display, info, longValue);
		System.out.println(result);
		expected = "parent.classField.addElement(" + longValue + "L);";
		assertEquals(expected, result);
		
		result = generateLog(display, info, longObjectValue);
		System.out.println(result);
		expected = "parent.classField.addElement(" + longObjectValue + "L);";
		assertEquals(expected, result);
		
		result = generateLog(display, info, charValue);
		System.out.println(result);
		expected = "parent.classField.addElement('" + charValue + "');";
		assertEquals(expected, result);
		
		result = generateLog(display, info, characterValue);
		System.out.println(result);
		expected = "parent.classField.addElement('" + characterValue + "');";
		assertEquals(expected, result);
		
		result = generateLog(display, info, floatValue);
		System.out.println(result);
		expected = "parent.classField.addElement(" + floatValue + "f);";
		assertEquals(expected, result);
		
		result = generateLog(display, info, floatObjectValue);
		System.out.println(result);
		expected = "parent.classField.addElement(" + floatObjectValue + "f);";
		assertEquals(expected, result);
		
		result = generateLog(display, info, doubleValue);
		System.out.println(result);
		expected = "parent.classField.addElement(" + doubleValue + "d);";
		assertEquals(expected, result);
		
		result = generateLog(display, info, doubleObjectValue);
		System.out.println(result);	
		expected = "parent.classField.addElement(" + doubleObjectValue + "d);";
		assertEquals(expected, result);		
	}


}
