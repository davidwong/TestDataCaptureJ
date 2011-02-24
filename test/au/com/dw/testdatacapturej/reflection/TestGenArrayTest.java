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

import static au.com.dw.testing.AssertUtil.assertEqualsWithoutFormatting;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import au.com.dw.testdatacapturej.log.ObjectLogger;
import au.com.dw.testdatacapturej.mock.dataholder.FieldObjectArrayHolder;
import au.com.dw.testdatacapturej.mock.dataholder.FieldPrimitiveArrayHolder;



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
		
			String expected = "int[] intArray0 = new int[0];";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
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
		
			String expected = "byte[] byteArray0 = new byte[2];" +
			"byteArray0[0] = 1;" +
			"byteArray0[1] = 2;";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
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
		
			String expected = "int[] intArray0 = new int[2];" +
			"intArray0[0] = 3;" +
			"intArray0[1] = 4;";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
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
		
			String expected = "long[] longArray0 = new long[2];" +
			"longArray0[0] = 5L;" +
			"longArray0[1] = 6L;";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
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
		
			String expected = "float[] floatArray0 = new float[2];" +
			"floatArray0[0] = 10.1f;" +
			"floatArray0[1] = 20.1f;";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
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
		
			String expected = "double[] doubleArray0 = new double[2];" +
			"doubleArray0[0] = 30.1d;" +
			"doubleArray0[1] = 40.1d;";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
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
		
			String expected = "char[] charArray0 = new char[2];" +
			"charArray0[0] = 'a';" +
			"charArray0[1] = 'b';";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
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
		
			String expected = "boolean[] booleanArray0 = new boolean[2];" +
			"booleanArray0[0] = true;" +
			"booleanArray0[1] = false;";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
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
		
			String expected = "au.com.dw.testdatacapturej.mock.dataholder.FieldPrimitiveArrayHolder fieldPrimitiveArrayHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.FieldPrimitiveArrayHolder();" +
			"byte[] byteArray0 = new byte[2];" +
			"byteArray0[0] = 1;" +
			"byteArray0[1] = 2;" +
			"fieldPrimitiveArrayHolder0.setByteArray(byteArray0);" +
			"int[] intArray0 = new int[2];" +
			"intArray0[0] = 3;" +
			"intArray0[1] = 4;" +
			"fieldPrimitiveArrayHolder0.setIntArray(intArray0);" +
			"long[] longArray0 = new long[2];" +
			"longArray0[0] = 5L;" +
			"longArray0[1] = 6L;" +
			"fieldPrimitiveArrayHolder0.setLongArray(longArray0);" +
			"float[] floatArray0 = new float[2];" +
			"floatArray0[0] = 10.1f;" +
			"floatArray0[1] = 20.1f;" +
			"fieldPrimitiveArrayHolder0.setFloatArray(floatArray0);" +
			"double[] doubleArray0 = new double[2];" +
			"doubleArray0[0] = 30.1d;" +
			"doubleArray0[1] = 40.1d;" +
			"fieldPrimitiveArrayHolder0.setDoubleArray(doubleArray0);" +
			"char[] charArray0 = new char[2];" +
			"charArray0[0] = 'a';" +
			"charArray0[1] = 'b';" +
			"fieldPrimitiveArrayHolder0.setCharArray(charArray0);" +
			"boolean[] booleanArray0 = new boolean[2];" +
			"booleanArray0[0] = true;" +
			"booleanArray0[1] = false;" +
			"fieldPrimitiveArrayHolder0.setBooleanArray(booleanArray0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
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
		
			String expected = "java.lang.Object[] objectArray0 = new java.lang.Object[0];";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
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
		
			String expected = "java.lang.Byte[] byteArray0 = new java.lang.Byte[2];" +
			"byteArray0[0] = 10;" +
			"byteArray0[1] = 11;";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
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
		
			String expected = "java.lang.Integer[] integerArray0 = new java.lang.Integer[2];" +
			"integerArray0[0] = 12;" +
			"integerArray0[1] = 13;";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
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
		
			String expected = "java.lang.Long[] longArray0 = new java.lang.Long[2];" +
			"longArray0[0] = 14L;" +
			"longArray0[1] = 15L;";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
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
		
			String expected = "java.lang.Float[] floatArray0 = new java.lang.Float[2];" +
			"floatArray0[0] = 50.1f;" +
			"floatArray0[1] = 60.1f;";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
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
		
			String expected = "java.lang.Double[] doubleArray0 = new java.lang.Double[2];" +
			"doubleArray0[0] = 70.1d;" +
			"doubleArray0[1] = 80.1d;";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
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
		
			String expected = "java.lang.Character[] characterArray0 = new java.lang.Character[2];" +
			"characterArray0[0] = 'c';" +
			"characterArray0[1] = 'd';";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
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
		
			String expected = "java.lang.Boolean[] booleanArray0 = new java.lang.Boolean[2];" +
			"booleanArray0[0] = false;" +
			"booleanArray0[1] = true;";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
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
		
			String expected = "java.lang.String[] stringArray0 = new java.lang.String[2];" +
			"stringArray0[0] = \"test1\";" +
			"stringArray0[1] = \"test2\";";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
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
		
			String expected = "au.com.dw.testdatacapturej.mock.dataholder.FieldObjectArrayHolder fieldObjectArrayHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.FieldObjectArrayHolder();" +
			"java.lang.Byte[] byteArray0 = new java.lang.Byte[2];" +
			"byteArray0[0] = 10;" +
			"byteArray0[1] = 11;" +
			"fieldObjectArrayHolder0.setByteArray(byteArray0);" +
			"java.lang.Integer[] integerArray0 = new java.lang.Integer[2];" +
			"integerArray0[0] = 12;" +
			"integerArray0[1] = 13;" +
			"fieldObjectArrayHolder0.setIntArray(integerArray0);" +
			"java.lang.Long[] longArray0 = new java.lang.Long[2];" +
			"longArray0[0] = 14L;" +
			"longArray0[1] = 15L;" +
			"fieldObjectArrayHolder0.setLongArray(longArray0);" +
			"java.lang.Float[] floatArray0 = new java.lang.Float[2];" +
			"floatArray0[0] = 50.1f;" +
			"floatArray0[1] = 60.1f;" +
			"fieldObjectArrayHolder0.setFloatArray(floatArray0);" +
			"java.lang.Double[] doubleArray0 = new java.lang.Double[2];" +
			"doubleArray0[0] = 70.1d;" +
			"doubleArray0[1] = 80.1d;" +
			"fieldObjectArrayHolder0.setDoubleArray(doubleArray0);" +
			"java.lang.Character[] characterArray0 = new java.lang.Character[2];" +
			"characterArray0[0] = 'c';" +
			"characterArray0[1] = 'd';" +
			"fieldObjectArrayHolder0.setCharArray(characterArray0);" +
			"java.lang.Boolean[] booleanArray0 = new java.lang.Boolean[2];" +
			"booleanArray0[0] = false;" +
			"booleanArray0[1] = true;" +
			"fieldObjectArrayHolder0.setBooleanArray(booleanArray0);" +
			"java.lang.String[] stringArray0 = new java.lang.String[2];" +
			"stringArray0[0] = \"test1\";" +
			"stringArray0[1] = \"test2\";" +
			"fieldObjectArrayHolder0.setStringArray(stringArray0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
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
		
			String expected = "java.lang.Object[] objectArray0 = new java.lang.Object[2];" +
			"au.com.dw.testdatacapturej.mock.dataholder.SimpleDataHolder simpleDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.SimpleDataHolder();" +
			"simpleDataHolder0.setText(\"aaa\");" +
			"simpleDataHolder0.setNumber(1);" +
			"simpleDataHolder0.setCharacter('a');" +
			"simpleDataHolder0.setBool(true);" +
			"simpleDataHolder0.setLongNumber(100L);" +
			"simpleDataHolder0.setPrimitiveFraction(0.1f);" +
			"simpleDataHolder0.setFraction(100.1d);" +
			"objectArray0[0] = simpleDataHolder0;" +
			"au.com.dw.testdatacapturej.mock.dataholder.SimpleDataHolder simpleDataHolder1 = new au.com.dw.testdatacapturej.mock.dataholder.SimpleDataHolder();" +
			"simpleDataHolder1.setText(\"bbb\");" +
			"simpleDataHolder1.setNumber(2);" +
			"simpleDataHolder1.setCharacter('b');" +
			"simpleDataHolder1.setBool(false);" +
			"simpleDataHolder1.setLongNumber(200L);" +
			"simpleDataHolder1.setPrimitiveFraction(0.2f);" +
			"simpleDataHolder1.setFraction(200.2d);" +
			"objectArray0[1] = simpleDataHolder1;";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
