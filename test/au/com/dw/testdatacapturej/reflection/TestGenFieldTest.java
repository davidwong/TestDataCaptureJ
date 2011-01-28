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
import au.com.dw.testdatacapturej.mock.dataholder.FieldObjectHolder;
import au.com.dw.testdatacapturej.mock.dataholder.FieldPrimitiveHolder;
import au.com.dw.testdatacapturej.reflection.MetadataGenerationHandler;
import au.com.dw.testdatacapturej.reflection.ReflectionHandler;



/**
 * Test for primitive and wrapper objects, and holder classes that just contain primitive and wrapper
 * objects.
 * 
 * @author David Wong
 *
 */
public class TestGenFieldTest extends BaseReflectionTest {

	private ReflectionHandler handler;
	private ObjectLogger logger;
	private StringBuilder builder;
	
	@Before
	public void setUp() throws Exception {
		handler = new MetadataGenerationHandler();
		logger = new ObjectLogger();
		builder = new StringBuilder();
	}

	// Testing for primitive fields
	// ****************************

	
	@Test
	public void testByte()
	{
		try {
			logger.logObject(builder, handler.handle(createByte()));
			String result = builder.toString();
		
			String expected = "1";
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("testByte");
		}
	}

	@Test
	public void testInt()
	{
		try {
			logger.logObject(builder, handler.handle(createInt()));
			String result = builder.toString();
		
			String expected = "2";
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("testInt");
		}
	}

	@Test
	public void testLong()
	{
		try {
			logger.logObject(builder, handler.handle(createLong()));
			String result = builder.toString();
		
			String expected = "3L";
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("testLong");
		}
	}

	@Test
	public void testFloat()
	{
		try {
			logger.logObject(builder, handler.handle(createFloat()));
			String result = builder.toString();
		
			String expected = "10.1f";
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("testFloat");
		}
	}
	
	@Test
	public void testDouble()
	{
		try {
			logger.logObject(builder, handler.handle(createDouble()));
			String result = builder.toString();
		
			String expected = "20.1d";
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("testDouble");
		}
	}
	
	@Test
	public void testChar()
	{
		try {
			logger.logObject(builder, handler.handle(createChar()));
			String result = builder.toString();
		
			String expected = "'a'";
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("testChar");
		}
	}
	
	@Test
	public void testBoolean()
	{
		try {
			logger.logObject(builder, handler.handle(createBoolean()));
			String result = builder.toString();
		
			String expected = "true";
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("testBoolean");
		}
	}
	
	@Test
	public void testPrimitiveHolder()
	{
		FieldPrimitiveHolder holder = new FieldPrimitiveHolder();
		holder.setByteField(createByte());
		holder.setIntField(createInt());
		holder.setLongField(createLong());
		holder.setFloatField(createFloat());
		holder.setDoubleField(createDouble());
		holder.setCharField(createChar());
		holder.setBooleanField(createBoolean());
		
		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
		
			String expected = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.FieldPrimitiveHolder fieldPrimitiveHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.FieldPrimitiveHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"fieldPrimitiveHolder0.setByteField(1);" +
			SystemUtils.LINE_SEPARATOR +
			"fieldPrimitiveHolder0.setIntField(2);" +
			SystemUtils.LINE_SEPARATOR +
			"fieldPrimitiveHolder0.setLongField(3L);" +
			SystemUtils.LINE_SEPARATOR +
			"fieldPrimitiveHolder0.setFloatField(10.1f);" +
			SystemUtils.LINE_SEPARATOR +
			"fieldPrimitiveHolder0.setDoubleField(20.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"fieldPrimitiveHolder0.setCharField('a');" +
			SystemUtils.LINE_SEPARATOR +
			"fieldPrimitiveHolder0.setBooleanField(true);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("testPrimitiveHolder");
		}
	}
	
	// Testing for wrapper fields
	// **************************

	@Test
	public void testByteObject()
	{
		try {
			logger.logObject(builder, handler.handle(createByteObject()));
			String result = builder.toString();
		
			String expected = "5";
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("testByteObject");
		}
	}

	@Test
	public void testInteger()
	{
		try {
			logger.logObject(builder, handler.handle(createInteger()));
			String result = builder.toString();
		
			String expected = "6";
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("testInteger");
		}
	}

	@Test
	public void testLongObject()
	{
		try {
			logger.logObject(builder, handler.handle(createLongObject()));
			String result = builder.toString();
		
			String expected = "7L";
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("testLongObject");
		}
	}

	@Test
	public void testFloatObject()
	{
		try {
			logger.logObject(builder, handler.handle(createFloatObject()));
			String result = builder.toString();
		
			String expected = "50.1f";
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("testFloatObject");
		}
	}
	
	@Test
	public void testDoubleObject()
	{
		try {
			logger.logObject(builder, handler.handle(createDoubleObject()));
			String result = builder.toString();
		
			String expected = "60.1d";
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("testDoubleObject");
		}
	}
	
	@Test
	public void testCharacter()
	{
		try {
			logger.logObject(builder, handler.handle(createCharacter()));
			String result = builder.toString();
		
			String expected = "'b'";
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("testCharacter");
		}
	}
	
	@Test
	public void testBooleanObject()
	{
		try {
			logger.logObject(builder, handler.handle(createBooleanObject()));
			String result = builder.toString();
		
			String expected = "false";
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("testBooleanObject");
		}
	}
	
	@Test
	public void testString()
	{
		try {
			logger.logObject(builder, handler.handle(createString()));
			String result = builder.toString();
		
			String expected = "\"test\"";
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("testString");
		}
	}
	
	@Test
	public void testObjectHolder()
	{
		FieldObjectHolder holder = new FieldObjectHolder();
		holder.setByteField(createByteObject());
		holder.setIntField(createInteger());
		holder.setLongField(createLongObject());
		holder.setFloatField(createFloatObject());
		holder.setDoubleField(createDoubleObject());
		holder.setCharField(createCharacter());
		holder.setBooleanField(createBooleanObject());
		holder.setStringField(createString());
		
		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
		
			String expected = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.FieldObjectHolder fieldObjectHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.FieldObjectHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"fieldObjectHolder0.setByteField(5);" +
			SystemUtils.LINE_SEPARATOR +
			"fieldObjectHolder0.setIntField(6);" +
			SystemUtils.LINE_SEPARATOR +
			"fieldObjectHolder0.setLongField(7L);" +
			SystemUtils.LINE_SEPARATOR +
			"fieldObjectHolder0.setFloatField(50.1f);" +
			SystemUtils.LINE_SEPARATOR +
			"fieldObjectHolder0.setDoubleField(60.1d);" +
			SystemUtils.LINE_SEPARATOR +
			"fieldObjectHolder0.setCharField('b');" +
			SystemUtils.LINE_SEPARATOR +
			"fieldObjectHolder0.setBooleanField(false);" +
			SystemUtils.LINE_SEPARATOR +
			"fieldObjectHolder0.setStringField(\"test\");" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("testObjectHolder");
		}
	}

}
