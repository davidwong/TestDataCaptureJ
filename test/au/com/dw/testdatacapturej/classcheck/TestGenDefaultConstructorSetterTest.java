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
package au.com.dw.testdatacapturej.classcheck;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;


import org.junit.Before;
import org.junit.Test;

import au.com.dw.testdatacapturej.meta.ObjectInfo;
import au.com.dw.testdatacapturej.mock.classcheck.DefaultConstructorNoSetter;
import au.com.dw.testdatacapturej.mock.classcheck.DefaultConstructorSetter;
import au.com.dw.testdatacapturej.mock.classcheck.Holder;
import au.com.dw.testdatacapturej.mock.classcheck.NoDefaultConstructorMultipleNoSetter;
import au.com.dw.testdatacapturej.mock.classcheck.NoDefaultConstructorNoSetter;
import au.com.dw.testdatacapturej.mock.classcheck.NoDefaultConstructorSetter;
import au.com.dw.testdatacapturej.reflection.MetadataGenerationHandler;
import au.com.dw.testdatacapturej.reflection.ReflectionHandler;

/**
 * Test for the checking of object for the presence of default no-argument constructors and of setter
 * methods for fields.
 * 
 * @author David Wong
 *
 */
public class TestGenDefaultConstructorSetterTest {

	private ReflectionHandler handler;
	//private ObjectLogger logger;
	private StringBuilder builder;
	private Holder holder;
	
	@Before
	public void setUp() throws Exception {
		handler = new MetadataGenerationHandler();
		//logger = new ObjectLogger();
		builder = new StringBuilder();
		holder = new Holder();
	}
 
    /**
     * Control test with both default constructor and setter
     */
    @Test
    public void DefaultConstructorSetterTest()
    {
		try {
			holder.setTestField(new DefaultConstructorSetter());
			ObjectInfo info = handler.handle(holder);
			List<ObjectInfo> fieldList = info.getFieldList();
			assertEquals(1, fieldList.size());
			
			// check if the test class has default constructor
			ObjectInfo fieldInfo = fieldList.get(0);		
			assertTrue(fieldInfo.getConstructorInfo().hasDefaultConstructor());
			
			List<ObjectInfo> innerFieldList = fieldInfo.getFieldList();
			assertEquals(1, innerFieldList.size());
			
			// check if the test class field has setter
			ObjectInfo innerFieldInfo = innerFieldList.get(0);
			assertTrue(innerFieldInfo.getSetterAdderInfo().hasSetter());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Default constructor and no setter
     */
    @Test
    public void DefaultConstructorNoSetterTest()
    {
		try {
			holder.setTestField(new DefaultConstructorNoSetter());
			ObjectInfo info = handler.handle(holder);
			List<ObjectInfo> fieldList = info.getFieldList();
			assertEquals(1, fieldList.size());
			
			// check if the test class has default constructor
			ObjectInfo fieldInfo = fieldList.get(0);		
			assertTrue(fieldInfo.getConstructorInfo().hasDefaultConstructor());
			
			List<ObjectInfo> innerFieldList = fieldInfo.getFieldList();
			assertEquals(1, innerFieldList.size());
			
			// check if the test class field has setter
			ObjectInfo innerFieldInfo = innerFieldList.get(0);
			assertFalse(innerFieldInfo.getSetterAdderInfo().hasSetter());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * No default constructor and setter
     */
    @Test
    public void NoDefaultConstructorSetterTest()
    {
		try {
			holder.setTestField(new NoDefaultConstructorSetter("test"));
			ObjectInfo info = handler.handle(holder);
			List<ObjectInfo> fieldList = info.getFieldList();
			assertEquals(1, fieldList.size());
			
			// check if the test class has default constructor
			ObjectInfo fieldInfo = fieldList.get(0);		
			assertFalse(fieldInfo.getConstructorInfo().hasDefaultConstructor());
			
			List<ObjectInfo> innerFieldList = fieldInfo.getFieldList();
			assertEquals(1, innerFieldList.size());
			
			// check if the test class field has setter
			ObjectInfo innerFieldInfo = innerFieldList.get(0);
			assertTrue(innerFieldInfo.getSetterAdderInfo().hasSetter());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * No default constructor and no setter
     */
    @Test
    public void NoDefaultConstructorNoSetterTest()
    {
		try {
			holder.setTestField(new NoDefaultConstructorNoSetter("test"));
			ObjectInfo info = handler.handle(holder);
			List<ObjectInfo> fieldList = info.getFieldList();
			assertEquals(1, fieldList.size());
			
			// check if the test class has default constructor
			ObjectInfo fieldInfo = fieldList.get(0);		
			assertFalse(fieldInfo.getConstructorInfo().hasDefaultConstructor());
			
			List<ObjectInfo> innerFieldList = fieldInfo.getFieldList();
			assertEquals(1, innerFieldList.size());
			
			// check if the test class field has setter
			ObjectInfo innerFieldInfo = innerFieldList.get(0);
			assertFalse(innerFieldInfo.getSetterAdderInfo().hasSetter());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * No default constructor and no setter for multiple fields
     */
    @Test
    public void NoDefaultConstructorMultipleNoSetterTest()
    {
		try {
			holder.setTestField(new NoDefaultConstructorMultipleNoSetter("test", "test2"));
			ObjectInfo info = handler.handle(holder);
			List<ObjectInfo> fieldList = info.getFieldList();
			assertEquals(1, fieldList.size());
			
			// check if the test class has default constructor
			ObjectInfo fieldInfo = fieldList.get(0);		
			assertFalse(fieldInfo.getConstructorInfo().hasDefaultConstructor());
			
			List<ObjectInfo> innerFieldList = fieldInfo.getFieldList();
			assertEquals(2, innerFieldList.size());
			
			// check if the test class fields have setter
			for (ObjectInfo innerFieldInfo : innerFieldList)
			{
				assertFalse(innerFieldInfo.getSetterAdderInfo().hasSetter());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
}
