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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.apache.commons.lang.SystemUtils;
import org.junit.Before;
import org.junit.Test;

import au.com.dw.testdatacapturej.log.ObjectLogger;
import au.com.dw.testdatacapturej.mock.dataholder.FieldObjectArrayHolder;
import au.com.dw.testdatacapturej.mock.dataholder.FieldPrimitiveArrayHolder;
import au.com.dw.testdatacapturej.reflection.MetadataGenerationHandler;
import au.com.dw.testdatacapturej.reflection.ReflectionHandler;



/**
 * Test for primitive and wrapper object arrays, and holder classes that just contain primitive and wrapper
 * object arrays.
 * 
 * @author David Wong
 *
 */
public class TestGenArrayTest extends BaseReflectionTest {

	private ReflectionHandler handler;
	private ObjectLogger logger;
	private StringBuilder builder;
	
	@Before
	public void setUp() throws Exception {
		handler = new MetadataGenerationHandler();
		logger = new ObjectLogger();
		builder = new StringBuilder();
	}

	// Testing for primitive arrays
	// ****************************

	@Test
	public void testEmptyArray()
	{
		try {
			logger.logObject(builder, handler.handle(createEmptyArray()));
			String result = builder.toString();
		
			String expected = SystemUtils.LINE_SEPARATOR +
			"int[] intArray0 = new int[0];" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testByteArray()
	{
		try {
			logger.logObject(builder, handler.handle(createByteArray()));
			String result = builder.toString();
		
			String expected = SystemUtils.LINE_SEPARATOR +
			"byte[] byteArray0 = new byte[2];" +
			SystemUtils.LINE_SEPARATOR +
			"byteArray0[0] = 1;" +
			SystemUtils.LINE_SEPARATOR +
			"byteArray0[1] = 2;" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testIntArray()
	{
		try {
			logger.logObject(builder, handler.handle(createIntArray()));
			String result = builder.toString();
		
			String expected = SystemUtils.LINE_SEPARATOR +
			"int[] intArray0 = new int[2];" +
			SystemUtils.LINE_SEPARATOR +
			"intArray0[0] = 3;" +
			SystemUtils.LINE_SEPARATOR +
			"intArray0[1] = 4;" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testLongArray()
	{
		try {
			logger.logObject(builder, handler.handle(createLongArray()));
			String result = builder.toString();
		
			String expected = SystemUtils.LINE_SEPARATOR +
			"long[] longArray0 = new long[2];" +
			SystemUtils.LINE_SEPARATOR +
			"longArray0[0] = 5L;" +
			SystemUtils.LINE_SEPARATOR +
			"longArray0[1] = 6L;" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testFloatArray()
	{
		try {
			logger.logObject(builder, handler.handle(createFloatArray()));
			String result = builder.toString();
		
			String expected = SystemUtils.LINE_SEPARATOR +
			"float[] floatArray0 = new float[2];" +
			SystemUtils.LINE_SEPARATOR +
			"floatArray0[0] = 10.1f;" +
			SystemUtils.LINE_SEPARATOR +
			"floatArray0[1] = 20.1f;" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testDoubleArray()
	{
		try {
			logger.logObject(builder, handler.handle(createDoubleArray()));
			String result = builder.toString();
		
			String expected = SystemUtils.LINE_SEPARATOR +
			"double[] doubleArray0 = new double[2];" +
			SystemUtils.LINE_SEPARATOR +
			"doubleArray0[0] = 30.1d;" +
			SystemUtils.LINE_SEPARATOR +
			"doubleArray0[1] = 40.1d;" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testCharArray()
	{
		try {
			logger.logObject(builder, handler.handle(createCharArray()));
			String result = builder.toString();
		
			String expected = SystemUtils.LINE_SEPARATOR +
			"char[] charArray0 = new char[2];" +
			SystemUtils.LINE_SEPARATOR +
			"charArray0[0] = 'a';" +
			SystemUtils.LINE_SEPARATOR +
			"charArray0[1] = 'b';" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testBooleanArray()
	{
		try {
			logger.logObject(builder, handler.handle(createBooleanArray()));
			String result = builder.toString();
		
			String expected = SystemUtils.LINE_SEPARATOR +
			"boolean[] booleanArray0 = new boolean[2];" +
			SystemUtils.LINE_SEPARATOR +
			"booleanArray0[0] = true;" +
			SystemUtils.LINE_SEPARATOR +
			"booleanArray0[1] = false;" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testPrimitiveArrayHolder()
	{
		FieldPrimitiveArrayHolder holder = new FieldPrimitiveArrayHolder();
		holder.setByteArray(createByteArray());
		holder.setIntArray(createIntArray());
		holder.setLongArray(createLongArray());
		holder.setFloatArray(createFloatArray());
		holder.setDoubleArray(createDoubleArray());
		holder.setCharArray(createCharArray());
		holder.setBooleanArray(createBooleanArray());
		
		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
		
			String expected = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.FieldPrimitiveArrayHolder fieldPrimitiveArrayHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.FieldPrimitiveArrayHolder();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"byte[] byteArray0 = new byte[2];" +
			SystemUtils.LINE_SEPARATOR +
			"byteArray0[0] = 1;" +
			SystemUtils.LINE_SEPARATOR +
			"byteArray0[1] = 2;" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"fieldPrimitiveArrayHolder0.setByteArray(byteArray0);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"int[] intArray1 = new int[2];" +
			SystemUtils.LINE_SEPARATOR +
			"intArray1[0] = 3;" +
			SystemUtils.LINE_SEPARATOR +
			"intArray1[1] = 4;" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"fieldPrimitiveArrayHolder0.setIntArray(intArray1);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"long[] longArray2 = new long[2];" +
			SystemUtils.LINE_SEPARATOR +
			"longArray2[0] = 5L;" +
			SystemUtils.LINE_SEPARATOR +
			"longArray2[1] = 6L;" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"fieldPrimitiveArrayHolder0.setLongArray(longArray2);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"float[] floatArray3 = new float[2];" +
			SystemUtils.LINE_SEPARATOR +
			"floatArray3[0] = 10.1f;" +
			SystemUtils.LINE_SEPARATOR +
			"floatArray3[1] = 20.1f;" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"fieldPrimitiveArrayHolder0.setFloatArray(floatArray3);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"double[] doubleArray4 = new double[2];" +
			SystemUtils.LINE_SEPARATOR +
			"doubleArray4[0] = 30.1d;" +
			SystemUtils.LINE_SEPARATOR +
			"doubleArray4[1] = 40.1d;" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"fieldPrimitiveArrayHolder0.setDoubleArray(doubleArray4);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"char[] charArray5 = new char[2];" +
			SystemUtils.LINE_SEPARATOR +
			"charArray5[0] = 'a';" +
			SystemUtils.LINE_SEPARATOR +
			"charArray5[1] = 'b';" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"fieldPrimitiveArrayHolder0.setCharArray(charArray5);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"boolean[] booleanArray6 = new boolean[2];" +
			SystemUtils.LINE_SEPARATOR +
			"booleanArray6[0] = true;" +
			SystemUtils.LINE_SEPARATOR +
			"booleanArray6[1] = false;" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"fieldPrimitiveArrayHolder0.setBooleanArray(booleanArray6);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	// Testing for wrapper fields
	// **************************

	@Test
	public void testEmptyObjectArray()
	{
		try {
			logger.logObject(builder, handler.handle(createEmptyObjectArray()));
			String result = builder.toString();
		
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.lang.Object[] objectArray0 = new java.lang.Object[0];" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testByteObjectArray()
	{
		try {
			logger.logObject(builder, handler.handle(createByteObjectArray()));
			String result = builder.toString();
		
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.lang.Byte[] byteArray0 = new java.lang.Byte[2];" +
			SystemUtils.LINE_SEPARATOR +
			"byteArray0[0] = 10;" +
			SystemUtils.LINE_SEPARATOR +
			"byteArray0[1] = 11;" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testIntegerArray()
	{
		try {
			logger.logObject(builder, handler.handle(createIntegerArray()));
			String result = builder.toString();
		
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.lang.Integer[] integerArray0 = new java.lang.Integer[2];" +
			SystemUtils.LINE_SEPARATOR +
			"integerArray0[0] = 12;" +
			SystemUtils.LINE_SEPARATOR +
			"integerArray0[1] = 13;" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testLongObjectArray()
	{
		try {
			logger.logObject(builder, handler.handle(createLongObjectArray()));
			String result = builder.toString();
		
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.lang.Long[] longArray0 = new java.lang.Long[2];" +
			SystemUtils.LINE_SEPARATOR +
			"longArray0[0] = 14L;" +
			SystemUtils.LINE_SEPARATOR +
			"longArray0[1] = 15L;" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testFloatObjectArray()
	{
		try {
			logger.logObject(builder, handler.handle(createFloatObjectArray()));
			String result = builder.toString();
		
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.lang.Float[] floatArray0 = new java.lang.Float[2];" +
			SystemUtils.LINE_SEPARATOR +
			"floatArray0[0] = 50.1f;" +
			SystemUtils.LINE_SEPARATOR +
			"floatArray0[1] = 60.1f;" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testDoubleObjectArray()
	{
		try {
			logger.logObject(builder, handler.handle(createDoubleObjectArray()));
			String result = builder.toString();
		
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.lang.Double[] doubleArray0 = new java.lang.Double[2];" +
			SystemUtils.LINE_SEPARATOR +
			"doubleArray0[0] = 70.1d;" +
			SystemUtils.LINE_SEPARATOR +
			"doubleArray0[1] = 80.1d;" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testCharacterArray()
	{
		try {
			logger.logObject(builder, handler.handle(createCharacterArray()));
			String result = builder.toString();
		
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.lang.Character[] characterArray0 = new java.lang.Character[2];" +
			SystemUtils.LINE_SEPARATOR +
			"characterArray0[0] = 'c';" +
			SystemUtils.LINE_SEPARATOR +
			"characterArray0[1] = 'd';" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testBooleanObjectArray()
	{
		try {
			logger.logObject(builder, handler.handle(createBooleanObjectArray()));
			String result = builder.toString();
		
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.lang.Boolean[] booleanArray0 = new java.lang.Boolean[2];" +
			SystemUtils.LINE_SEPARATOR +
			"booleanArray0[0] = false;" +
			SystemUtils.LINE_SEPARATOR +
			"booleanArray0[1] = true;" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testStringArray()
	{
		try {
			logger.logObject(builder, handler.handle(createStringArray()));
			String result = builder.toString();
		
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.lang.String[] stringArray0 = new java.lang.String[2];" +
			SystemUtils.LINE_SEPARATOR +
			"stringArray0[0] = \"test1\";" +
			SystemUtils.LINE_SEPARATOR +
			"stringArray0[1] = \"test2\";" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testObjectHolder()
	{
		FieldObjectArrayHolder holder = new FieldObjectArrayHolder();
		holder.setByteArray(createByteObjectArray());
		holder.setIntArray(createIntegerArray());
		holder.setLongArray(createLongObjectArray());
		holder.setFloatArray(createFloatObjectArray());
		holder.setDoubleArray(createDoubleObjectArray());
		holder.setCharArray(createCharacterArray());
		holder.setBooleanArray(createBooleanObjectArray());
		holder.setStringArray(createStringArray());
		
		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
		
			String expected = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.FieldObjectArrayHolder fieldObjectArrayHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.FieldObjectArrayHolder();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.lang.Byte[] byteArray0 = new java.lang.Byte[2];" +
			SystemUtils.LINE_SEPARATOR +
			"byteArray0[0] = 10;" +
			SystemUtils.LINE_SEPARATOR +
			"byteArray0[1] = 11;" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"fieldObjectArrayHolder0.setByteArray(byteArray0);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.lang.Integer[] integerArray1 = new java.lang.Integer[2];" +
			SystemUtils.LINE_SEPARATOR +
			"integerArray1[0] = 12;" +
			SystemUtils.LINE_SEPARATOR +
			"integerArray1[1] = 13;" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"fieldObjectArrayHolder0.setIntArray(integerArray1);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.lang.Long[] longArray2 = new java.lang.Long[2];" +
			SystemUtils.LINE_SEPARATOR +
			"longArray2[0] = 14L;" +
			SystemUtils.LINE_SEPARATOR +
			"longArray2[1] = 15L;" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"fieldObjectArrayHolder0.setLongArray(longArray2);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.lang.Float[] floatArray3 = new java.lang.Float[2];" +
			SystemUtils.LINE_SEPARATOR +
			"floatArray3[0] = 50.1f;" +
			SystemUtils.LINE_SEPARATOR +
			"floatArray3[1] = 60.1f;" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"fieldObjectArrayHolder0.setFloatArray(floatArray3);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.lang.Double[] doubleArray4 = new java.lang.Double[2];" +
			SystemUtils.LINE_SEPARATOR +
			"doubleArray4[0] = 70.1d;" +
			SystemUtils.LINE_SEPARATOR +
			"doubleArray4[1] = 80.1d;" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"fieldObjectArrayHolder0.setDoubleArray(doubleArray4);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.lang.Character[] characterArray5 = new java.lang.Character[2];" +
			SystemUtils.LINE_SEPARATOR +
			"characterArray5[0] = 'c';" +
			SystemUtils.LINE_SEPARATOR +
			"characterArray5[1] = 'd';" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"fieldObjectArrayHolder0.setCharArray(characterArray5);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.lang.Boolean[] booleanArray6 = new java.lang.Boolean[2];" +
			SystemUtils.LINE_SEPARATOR +
			"booleanArray6[0] = false;" +
			SystemUtils.LINE_SEPARATOR +
			"booleanArray6[1] = true;" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"fieldObjectArrayHolder0.setBooleanArray(booleanArray6);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.lang.String[] stringArray7 = new java.lang.String[2];" +
			SystemUtils.LINE_SEPARATOR +
			"stringArray7[0] = \"test1\";" +
			SystemUtils.LINE_SEPARATOR +
			"stringArray7[1] = \"test2\";" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"fieldObjectArrayHolder0.setStringArray(stringArray7);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testObjectArray()
	{
		try {
			logger.logObject(builder, handler.handle(createObjectArray()));
			String result = builder.toString();
		
			String expected = SystemUtils.LINE_SEPARATOR +
			"java.lang.Object[] objectArray0 = new java.lang.Object[2];" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.SimpleDataHolder simpleDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.SimpleDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"simpleDataHolder0.setText(\"aaa\");" +
			SystemUtils.LINE_SEPARATOR +
			"simpleDataHolder0.setNumber(1);" +
			SystemUtils.LINE_SEPARATOR +
			"simpleDataHolder0.setCharacter('a');" +
			SystemUtils.LINE_SEPARATOR +
			"simpleDataHolder0.setBool(true);" +
			SystemUtils.LINE_SEPARATOR +
			"simpleDataHolder0.setLongNumber(100L);" +
			SystemUtils.LINE_SEPARATOR +
			"simpleDataHolder0.setPrimitiveFraction(0.1f);" +
			SystemUtils.LINE_SEPARATOR +
			"simpleDataHolder0.setFraction(100.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"objectArray0[0] = simpleDataHolder0;" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.SimpleDataHolder simpleDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.SimpleDataHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"simpleDataHolder1.setText(\"bbb\");" +
			SystemUtils.LINE_SEPARATOR +
			"simpleDataHolder1.setNumber(2);" +
			SystemUtils.LINE_SEPARATOR +
			"simpleDataHolder1.setCharacter('b');" +
			SystemUtils.LINE_SEPARATOR +
			"simpleDataHolder1.setBool(false);" +
			SystemUtils.LINE_SEPARATOR +
			"simpleDataHolder1.setLongNumber(200L);" +
			SystemUtils.LINE_SEPARATOR +
			"simpleDataHolder1.setPrimitiveFraction(0.2f);" +
			SystemUtils.LINE_SEPARATOR +
			"simpleDataHolder1.setFraction(200.2d);" +
			SystemUtils.LINE_SEPARATOR +
			"objectArray0[1] = simpleDataHolder1;" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
