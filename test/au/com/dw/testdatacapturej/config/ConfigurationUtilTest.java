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
package au.com.dw.testdatacapturej.config;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import au.com.dw.testdatacapturej.config.ConfigUtil;
import au.com.dw.testdatacapturej.meta.ObjectInfo;
import au.com.dw.testdatacapturej.mock.adder.CollectionClassHolder;
import au.com.dw.testdatacapturej.mock.adder.CollectionHolder;
import au.com.dw.testdatacapturej.mock.adder.CollectionHolderWithSetter;
import au.com.dw.testdatacapturej.mock.classcheck.Holder;
import au.com.dw.testdatacapturej.mock.classcheck.NDCNS_BooleanFloat;
import au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Int;
import au.com.dw.testdatacapturej.mock.classcheck.NDCNS_String;
import au.com.dw.testdatacapturej.mock.classcheck.Setter_Simple;
import au.com.dw.testdatacapturej.mock.classcheck.Setter_UnconfiguredSimple;
import au.com.dw.testdatacapturej.reflection.MetadataGenerationHandler;
import au.com.dw.testdatacapturej.reflection.ReflectionHandler;


/**
 * Test for the checking of object for the presence of default no-argument constructors, of setter
 * methods for fields, and of adder methods for collections. These are objects that have had
 * custom configurations defined.
 * 
 * For these tests to run, the test constructor config files, test setter config files and test
 * collection config files must be setup for the objects to be tested, and loaded into Configuration.
 * 
 * @author David Wong
 *
 */
public class ConfigurationUtilTest {

	private ReflectionHandler handler;
	
	private Holder holder;
	private String holderFieldName = "testField";
	private String noSetterFieldName = "noSetterField";
	private String noSetterFieldName2 = "noSetterField2";
	private String setterFieldName = "setterField";
	private String collectionFieldName = "collection";
	private String collectionFieldName2 = "collection2";
	private String collectionAdderMethod = "addCollectionElement";
	private String collectionClassAdderMethod = "addCollectionElementToClass";
	private String collectionClassAdderMethod2 = "addCollectionElementToClass2";
	private String collectionSetterAdderMethod = "addCollectionElementInsteadOfSetter";
	
	private ConfigUtil configUtil;
	
	@Before
	public void setUp() throws Exception {
		handler = new MetadataGenerationHandler();
		holder = new Holder();
		configUtil = new ConfigUtil();
	}
 
    /**
     * Test for getting constructor parameters from the Configuration.
     * 
     */
    @Test
    public void constructorParamTest()
    {
		try {
			holder.setTestField(new NDCNS_String("test"));
			ObjectInfo info = handler.handle(holder);
			
			ObjectInfo fieldInfo = info.getField(holderFieldName);
			assertNotNull(fieldInfo);
			
			info.getConstructorInfo().addConstructorParamFieldNames(configUtil.getConstructionParameters(fieldInfo));
			List<String> paramNames = fieldInfo.getConstructorInfo().getConstructorParamFieldNames();
			assertNotNull(paramNames);
			assertEquals(1, paramNames.size());
			
			String param = paramNames.get(0);
			assertEquals(noSetterFieldName, param);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for getting multiple constructor parameters from the Configuration.
     * 
     */
    @Test
    public void constructorMultipleParamTest()
    {
		try {
			holder.setTestField(new NDCNS_BooleanFloat(true, 0.1f));
			ObjectInfo info = handler.handle(holder);
			
			ObjectInfo fieldInfo = info.getField(holderFieldName);
			assertNotNull(fieldInfo);
			
			info.getConstructorInfo().addConstructorParamFieldNames(configUtil.getConstructionParameters(fieldInfo));
			List<String> paramNames = fieldInfo.getConstructorInfo().getConstructorParamFieldNames();
			assertNotNull(paramNames);
			assertEquals(2, paramNames.size());
			
			String param = paramNames.get(0);
			assertEquals(noSetterFieldName, param);
			param = paramNames.get(1);
			assertEquals(noSetterFieldName2, param);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }   

    /**
     * Negative test for getting constructor parameters from the Configuration.
     * i.e. doesn't exist in the config
     * 
     */
    @Test
    public void constructorNegativeParamTest()
    {
		try {
			holder.setTestField(new NDCNS_Int(1));
			ObjectInfo info = handler.handle(holder);
			
			ObjectInfo fieldInfo = info.getField(holderFieldName);
			assertNotNull(fieldInfo);
			
			info.getConstructorInfo().addConstructorParamFieldNames(configUtil.getConstructionParameters(fieldInfo));
			List<String> paramNames = fieldInfo.getConstructorInfo().getConstructorParamFieldNames();
			assertNotNull(paramNames);
			assertTrue(paramNames.isEmpty());
		
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for getting setter parameters from the Configuration.
     * 
     */
    @Test
    public void setterTest()
    {
		Setter_Simple simple = new Setter_Simple();
		simple.setSetterField(1);
    	
    	try {
			holder.setTestField(simple);
			ObjectInfo info = handler.handle(holder);
			
			ObjectInfo fieldInfo = info.getField(holderFieldName);
			assertNotNull(fieldInfo);
			
			List<String> fieldNames = configUtil.getIgnoredSetters(fieldInfo);
			assertNotNull(fieldNames);
			assertEquals(1, fieldNames.size());
			
			String param = fieldNames.get(0);
			assertEquals(setterFieldName, param);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Negative test for getting setter parameters from the Configuration.
     * i.e. doesn't exist in the config
     * 
     */
    @Test
    public void setterNegativeTest()
    {
    	Setter_UnconfiguredSimple simple = new Setter_UnconfiguredSimple();
		simple.setSetterField(1);
    	
    	try {
			holder.setTestField(simple);
			ObjectInfo info = handler.handle(holder);
			
			ObjectInfo fieldInfo = info.getField(holderFieldName);
			assertNotNull(fieldInfo);
			
			List<String> fieldNames = configUtil.getIgnoredSetters(fieldInfo);
			assertNotNull(fieldNames);
			assertTrue(fieldNames.isEmpty());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for getting collection adders from the Configuration.
     * 
     */
    @Test
    public void collectionTest()
    {
		CollectionHolder holder = new CollectionHolder();
		holder.addCollectionElement("test1");
		
    	try {
			ObjectInfo info = handler.handle(holder);
			
			ObjectInfo fieldInfo = info.getField(collectionFieldName);
			assertNotNull(fieldInfo);
			assertTrue(fieldInfo.getSetterAdderInfo().isUsesAdder());
			assertEquals(collectionAdderMethod, fieldInfo.getSetterAdderInfo().getAdderMethodName());
			
			List<CollectionAdderConfig> collectionConfigs = configUtil.getAddedCollections(info);
			assertNotNull(collectionConfigs);
			assertEquals(1, collectionConfigs.size());
			
			assertNotNull(configUtil.getCollectionAdderConfig(collectionConfigs, collectionFieldName));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for getting collection adders from the Configuration.
     * 
     * This version has multiple collection fields with adders, both of which are configured.
     * 
     */
    @Test
    public void collectionMultipleTest()
    {
		CollectionClassHolder holder = new CollectionClassHolder();
		holder.addCollectionElement(new Integer(1));
		// deliberately leave one of the collections empty
		
    	try {
			ObjectInfo info = handler.handle(holder);
			
			ObjectInfo fieldInfo = info.getField(collectionFieldName);
			assertNotNull(fieldInfo);
			assertTrue(fieldInfo.getSetterAdderInfo().isUsesAdder());
			assertEquals(collectionClassAdderMethod, fieldInfo.getSetterAdderInfo().getAdderMethodName());

			fieldInfo = info.getField(collectionFieldName2);
			assertNotNull(fieldInfo);
			assertTrue(fieldInfo.getSetterAdderInfo().isUsesAdder());
			assertEquals(collectionClassAdderMethod2, fieldInfo.getSetterAdderInfo().getAdderMethodName());

			List<CollectionAdderConfig> collectionConfigs = configUtil.getAddedCollections(info);
			assertNotNull(collectionConfigs);
			assertEquals(2, collectionConfigs.size());
			
			assertNotNull(configUtil.getCollectionAdderConfig(collectionConfigs, collectionFieldName));
			assertNotNull(configUtil.getCollectionAdderConfig(collectionConfigs, collectionFieldName2));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for getting collection adders from the Configuration for a collection field that
     * has both adder and setter methods.
     * 
     */
    @Test
    public void collectionWithSetterTest()
    {
		CollectionHolderWithSetter holder = new CollectionHolderWithSetter();
		holder.addCollectionElement("test1");
		
    	try {
			ObjectInfo info = handler.handle(holder);
			
			ObjectInfo fieldInfo = info.getField(collectionFieldName);
			assertNotNull(fieldInfo);
			assertTrue(fieldInfo.getSetterAdderInfo().isUsesAdder());
			assertEquals(collectionSetterAdderMethod, fieldInfo.getSetterAdderInfo().getAdderMethodName());
			
			List<CollectionAdderConfig> collectionConfigs = configUtil.getAddedCollections(info);
			assertNotNull(collectionConfigs);
			assertEquals(1, collectionConfigs.size());
			
			assertNotNull(configUtil.getCollectionAdderConfig(collectionConfigs, collectionFieldName));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
}
