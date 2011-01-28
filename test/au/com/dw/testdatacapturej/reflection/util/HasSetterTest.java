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


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import au.com.dw.testdatacapturej.mock.type.reflection.FieldClass;
import au.com.dw.testdatacapturej.mock.type.reflection.FieldClassBaseClass;
import au.com.dw.testdatacapturej.mock.type.reflection.FieldClassBaseClassBaseInterface;
import au.com.dw.testdatacapturej.mock.type.reflection.FieldClassBaseClassBaseInterfaceHolder;
import au.com.dw.testdatacapturej.mock.type.reflection.FieldClassBaseClassHolder;
import au.com.dw.testdatacapturej.mock.type.reflection.FieldClassBaseInterface;
import au.com.dw.testdatacapturej.mock.type.reflection.FieldClassBaseInterfaceHolder;
import au.com.dw.testdatacapturej.mock.type.reflection.FieldClassHolder;
import au.com.dw.testdatacapturej.mock.type.reflection.FieldClassSubInterface;
import au.com.dw.testdatacapturej.mock.type.reflection.FieldClassSubInterfaceHolder;
import au.com.dw.testdatacapturej.reflection.util.ReflectionUtil;

/**
 * Test the various combinations of inheritance and interface implementation for checking whether a 
 * class has a particular setter method.
 * 
 * @author David Wong
 *
 */
public class HasSetterTest {

	private String fieldNameWithSetter = "fieldWithSetter";
	private String fieldNameNoSetter = "fieldNoSetter";
	
	@Before
	public void setUp() throws Exception {
	}
	
	/**
	 * No superclasses or interfaces.
	 */
	@Test
	public void simpleClass()
	{
		FieldClassHolder holder = new FieldClassHolder(new FieldClass(), new FieldClass());
		assertTrue("FieldClassHolder setter", ReflectionUtil.hasSetterMethod(holder, fieldNameWithSetter, new FieldClass()));
		assertFalse("FieldClassHolder no setter", ReflectionUtil.hasSetterMethod(holder, fieldNameNoSetter, new FieldClass()));
	}
	
	/**
	 * Base interfaces.
	 */
	@Test
	public void ClassBaseInterface()
	{
		FieldClassBaseInterfaceHolder holder = new FieldClassBaseInterfaceHolder(new FieldClassBaseInterface(), new FieldClassBaseInterface());
		assertTrue("FieldClassBaseInterfaceHolder setter", ReflectionUtil.hasSetterMethod(holder, fieldNameWithSetter, new FieldClassBaseInterface()));
		assertFalse("FieldClassBaseInterfaceHolder no setter", ReflectionUtil.hasSetterMethod(holder, fieldNameNoSetter, new FieldClassBaseInterface()));
	}

	/**
	 * Intermediate sub-interfaces.
	 */
	
	@Test
	public void ClassSubInterface()
	{
		FieldClassSubInterfaceHolder holder = new FieldClassSubInterfaceHolder(new FieldClassSubInterface(), new FieldClassSubInterface());
		assertTrue("FieldClassSubInterfaceHolder setter", ReflectionUtil.hasSetterMethod(holder, fieldNameWithSetter, new FieldClassSubInterface()));
		assertFalse("FieldClassSubInterfaceHolder no setter", ReflectionUtil.hasSetterMethod(holder, fieldNameNoSetter, new FieldClassSubInterface()));
	}
	
	/**
	 * Sub-classes base class.
	 */
	@Test
	public void ClassSubclass()
	{		
		FieldClassBaseClassHolder holder = new FieldClassBaseClassHolder(new FieldClassBaseClass(), new FieldClassBaseClass());
		assertTrue("FieldClassBaseClassHolder setter", ReflectionUtil.hasSetterMethod(holder, fieldNameWithSetter, new FieldClassBaseClass()));
		assertFalse("FieldClassBaseClassHolder no setter", ReflectionUtil.hasSetterMethod(holder, fieldNameNoSetter, new FieldClassBaseClass()));
	}
	
	/**
	 * Sub-classes base class which implements base interface.
	 */
	@Test
	public void ClassSubclassBaseInterface()
	{		
		FieldClassBaseClassBaseInterfaceHolder holder = new FieldClassBaseClassBaseInterfaceHolder(new FieldClassBaseClassBaseInterface(), new FieldClassBaseClassBaseInterface());
		assertTrue("FieldClassBaseClassBaseInterfaceHolder setter", ReflectionUtil.hasSetterMethod(holder, fieldNameWithSetter, new FieldClassBaseClassBaseInterface()));
		assertFalse("FieldClassBaseClassBaseInterfaceHolder no setter", ReflectionUtil.hasSetterMethod(holder, fieldNameNoSetter, new FieldClassBaseClassBaseInterface()));
	}
}
