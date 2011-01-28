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
import au.com.dw.testdatacapturej.mock.dataholder.NullArrayElementHolder;
import au.com.dw.testdatacapturej.mock.dataholder.NullArrayHolder;
import au.com.dw.testdatacapturej.mock.dataholder.NullCollectionElementHolder;
import au.com.dw.testdatacapturej.mock.dataholder.NullCollectionHolder;
import au.com.dw.testdatacapturej.mock.dataholder.NullMapEntryHolder;
import au.com.dw.testdatacapturej.mock.dataholder.NullMapHolder;
import au.com.dw.testdatacapturej.mock.dataholder.NullObjectHolder;
import au.com.dw.testdatacapturej.mock.dataholder.NullStringHolder;
import au.com.dw.testdatacapturej.reflection.MetadataGenerationHandler;
import au.com.dw.testdatacapturej.reflection.ReflectionHandler;



/**
 * Test the test code generation for null values. Note that the initial object cannot be null, but may have
 * null value for fields somewhere in the recursive process. Also arrays, and some implementations of Collection
 * and Map can also have null elements.
 * 
 * @author David Wong
 *
 */
public class TestGenNullTest extends BaseReflectionTest {

	private ReflectionHandler handler;
	private ObjectLogger logger;
	private StringBuilder builder;
	
	@Before
	public void setUp() throws Exception {
		handler = new MetadataGenerationHandler();
		logger = new ObjectLogger();
		builder = new StringBuilder();
	}
 
    /**
     * Test for null Object field.
     */
    @Test
    public void nullObjectTest()
    {
		try {
			logger.logObject(builder, handler.handle(new NullObjectHolder()));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.NullObjectHolder nullObjectHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.NullObjectHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"nullObjectHolder0.setNullField(null);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

    /**
     * Test for null String field.
     */
    @Test
    public void nullStringTest()
    {
		try {
			logger.logObject(builder, handler.handle(new NullStringHolder()));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.NullStringHolder nullStringHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.NullStringHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"nullStringHolder0.setNullField(null);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for null Collection field.
     */
    @Test
    public void nullCollectionTest()
    {
		try {
			logger.logObject(builder, handler.handle(new NullCollectionHolder()));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.NullCollectionHolder nullCollectionHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.NullCollectionHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"nullCollectionHolder0.setNullField(null);" +
			SystemUtils.LINE_SEPARATOR +
			"nullCollectionHolder0.setImplNullField(null);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for null Array field.
     */
    @Test
    public void nullArrayTest()
    {
		try {
			logger.logObject(builder, handler.handle(new NullArrayHolder()));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.NullArrayHolder nullArrayHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.NullArrayHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"nullArrayHolder0.setNullField(null);" +
			SystemUtils.LINE_SEPARATOR +
			"nullArrayHolder0.setPrimitiveNullField(null);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for null Map field.
     */
    @Test
    public void nullMapTest()
    {
		try {
			logger.logObject(builder, handler.handle(new NullMapHolder()));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.NullMapHolder nullMapHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.NullMapHolder();" +
			SystemUtils.LINE_SEPARATOR +
			"nullMapHolder0.setNullField(null);" +
			SystemUtils.LINE_SEPARATOR +
			"nullMapHolder0.setImplNullField(null);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for Collection field with null elements.
     */
    @Test
    public void nullCollectionElementTest()
    {
		try {
			logger.logObject(builder, handler.handle(new NullCollectionElementHolder()));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.NullCollectionElementHolder nullCollectionElementHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.NullCollectionElementHolder();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			SystemUtils.LINE_SEPARATOR +
			"arrayList0.add(null);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"nullCollectionElementHolder0.setNullField(arrayList0);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.ArrayList arrayList1 = new java.util.ArrayList();" +
			SystemUtils.LINE_SEPARATOR +
			"arrayList1.add(null);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"nullCollectionElementHolder0.setImplNullField(arrayList1);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for Array field with null elements.
     */
    @Test
    public void nullArrayElementTest()
    {
		try {
			logger.logObject(builder, handler.handle(new NullArrayElementHolder()));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.NullArrayElementHolder nullArrayElementHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.NullArrayElementHolder();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.lang.Object[] objectArray0 = new java.lang.Object[2];" +
			SystemUtils.LINE_SEPARATOR +
			"objectArray0[0] = null;" +
			SystemUtils.LINE_SEPARATOR +
			"objectArray0[1] = null;" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"nullArrayElementHolder0.setNullField(objectArray0);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for Map field with null elements in the key and/or value.
     */
    @Test
    public void nullMapEntryTest()
    {
		try {
			logger.logObject(builder, handler.handle(new NullMapEntryHolder()));
			String result = builder.toString();
			
			String expected = SystemUtils.LINE_SEPARATOR +
			"au.com.dw.testdatacapturej.mock.dataholder.NullMapEntryHolder nullMapEntryHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.NullMapEntryHolder();" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.HashMap hashMap0 = new java.util.HashMap();" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap0.put(null, \"value\");" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap0.put(\"key\", null);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"nullMapEntryHolder0.setNullField(hashMap0);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"java.util.HashMap hashMap1 = new java.util.HashMap();" +
			SystemUtils.LINE_SEPARATOR +
			"hashMap1.put(null, null);" +
			SystemUtils.LINE_SEPARATOR +
			SystemUtils.LINE_SEPARATOR +
			"nullMapEntryHolder0.setImplNullField(hashMap1);" +
			SystemUtils.LINE_SEPARATOR;
			
			System.out.println(result);
			assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
}
