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
package au.com.dw.testdatacapturej.reflection.util;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import au.com.dw.testdatacapturej.mock.dataholder.AllDataHolder;
import au.com.dw.testdatacapturej.mock.dataholder.TestData;
import au.com.dw.testdatacapturej.mock.type.reflection.DefaultAndParameterizedConstructorDataHolder;
import au.com.dw.testdatacapturej.mock.type.reflection.DefaultConstructorOnlyDataHolder;
import au.com.dw.testdatacapturej.mock.type.reflection.NoMethodDataHolder;
import au.com.dw.testdatacapturej.mock.type.reflection.ParameterizedConstructorOnlyDataHolder;
import au.com.dw.testdatacapturej.reflection.BaseReflectionTest;
import au.com.dw.testdatacapturej.reflection.util.ReflectionUtil;



/**
 * Test for reflection utilities that check if classes have constructors or setters.
 * 
 * @author David Wong
 *
 */
public class ReflectionUtilTest extends BaseReflectionTest {

	private TestData testData;
	
	@Before
	public void setUp() throws Exception {
		testData = new TestData();
	}

	@Test
	public void hasDefaultConstructor()
	{
		assertTrue("has default constructor", ReflectionUtil.hasDefaultConstructor(new DefaultConstructorOnlyDataHolder()));

		assertTrue("has default constructor, parameterized constructor not used", ReflectionUtil.hasDefaultConstructor(new DefaultAndParameterizedConstructorDataHolder()));

		// internally should throw NoSuchMethodException
		assertFalse("no default constructor", ReflectionUtil.hasDefaultConstructor(new ParameterizedConstructorOnlyDataHolder("test", testData.createTestDataHolder())));
	}

	@Test
	public void hasParameterizedConstructor()
	{
		// common fields in all test classes that would be used as constructor parameters, if a parameterized constructor
		// were to exist
		Class<?>[] parameterTypes = new Class<?>[2];
		parameterTypes[0] = String.class;
		parameterTypes[1] = Object.class;
		
		// internally should throw NoSuchMethodException
		assertFalse("no parameterized constructor", ReflectionUtil.hasParameterizedConstructor(new DefaultConstructorOnlyDataHolder(), parameterTypes));

		assertTrue("has parameterized constructor, although parameterized constructor not used", ReflectionUtil.hasParameterizedConstructor(new DefaultAndParameterizedConstructorDataHolder(), parameterTypes));

		assertTrue("has parameterized constructor", ReflectionUtil.hasParameterizedConstructor(new ParameterizedConstructorOnlyDataHolder("test", testData.createTestDataHolder()), parameterTypes));

	}

	@Test
	public void hasSetterMethod()
	{
		AllDataHolder data = testData.createTestDataHolder();
		assertTrue("has setter", ReflectionUtil.hasSetterMethod(data, "text", "test"));
		
		
		NoMethodDataHolder noData = testData.createNoMethodDataHolder();
		// internally should throw NoSuchMethodException
		assertFalse("no setter", ReflectionUtil.hasSetterMethod(noData, "noSetterField", "test"));
	}
	
	@Test
	public void arrayClassNameTest()
	{
		String result = ReflectionUtil.getArrayClassName(createStringArray());
		System.out.println(result);
		assertEquals("String", "java.lang.String[]", result);

		result = ReflectionUtil.getArrayClassName(createCharArray());
		System.out.println(result);
		assertEquals("char", "char[]", result);
		
		result = ReflectionUtil.getArrayClassName(createByteArray());
		System.out.println(result);
		assertEquals("byte", "byte[]", result);
		
		result = ReflectionUtil.getArrayClassName(createIntArray());
		System.out.println(result);
		assertEquals("int", "int[]", result);
		
		result = ReflectionUtil.getArrayClassName(createLongArray());
		System.out.println(result);
		assertEquals("long", "long[]", result);

		result = ReflectionUtil.getArrayClassName(createBooleanArray());
		System.out.println(result);
		assertEquals("boolean", "boolean[]", result);
		
		result = ReflectionUtil.getArrayClassName(createFloatArray());
		System.out.println(result);
		assertEquals("float", "float[]", result);
		
		result = ReflectionUtil.getArrayClassName(createDoubleArray());
		System.out.println(result);
		assertEquals("double", "double[]", result);
		
		result = ReflectionUtil.getArrayClassName(createObjectArray());
		System.out.println(result);
		assertEquals("Object", "java.lang.Object[]", result);
	}
}
