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

import static au.com.dw.testing.AssertUtil.assertEqualsAnyWithoutFormatting;
import static au.com.dw.testing.AssertUtil.assertEqualsWithoutFormatting;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import au.com.dw.testdatacapturej.mock.classcheck.Holder;
import au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Array;
import au.com.dw.testdatacapturej.mock.classcheck.NDCNS_BooleanFloat;
import au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Collection;
import au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Double;
import au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Int;
import au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Integer;
import au.com.dw.testdatacapturej.mock.classcheck.NDCNS_LongChar;
import au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Map;
import au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Object;
import au.com.dw.testdatacapturej.mock.classcheck.NDCNS_String;
import au.com.dw.testdatacapturej.mock.dataholder.TestData;
import au.com.dw.testdatacapturej.reflection.BaseReflectionTest;
import au.com.dw.testdatacapturej.reflection.MetadataGenerationHandler;
import au.com.dw.testdatacapturej.reflection.ReflectionHandler;
import au.com.dw.testdatacapturej.util.Messages;


/**
 * Tests for logging of classes with constructor parameter(s).
 * 
 * The test classes must be configured in the test constructor configuration XML file.
 * 
 * @author David Wong
 *
 */
public class ConstructorParamTest extends BaseReflectionTest {

	private ReflectionHandler handler;
	private ObjectLogger logger;
	private StringBuilder builder;
	
	private Holder holder;
	
	@Before
	public void setUp() throws Exception {
		handler = new MetadataGenerationHandler();
		logger = new ObjectLogger();
		builder = new StringBuilder();
		holder = new Holder();
	}
 
    /**
     * Test for constructor with String parameter configured in XML config file.
     */
    @Test
    public void paramStringTest()
    {
		holder.setTestField(new NDCNS_String(createString()));

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.Holder holder0 = new au.com.dw.testdatacapturej.mock.classcheck.Holder();" +
			"au.com.dw.testdatacapturej.mock.classcheck.NDCNS_String nDCNS_String0 = new au.com.dw.testdatacapturej.mock.classcheck.NDCNS_String(\"test\");" +
			"holder0.setTestField(nDCNS_String0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

    /**
     * Test for constructor with Boolean and float parameters configured in XML config file.
     */
    @Test
    public void paramBooleanFloatTest()
    {
		holder.setTestField(new NDCNS_BooleanFloat(createBoolean(), createFloat()));

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.Holder holder0 = new au.com.dw.testdatacapturej.mock.classcheck.Holder();" +
			"au.com.dw.testdatacapturej.mock.classcheck.NDCNS_BooleanFloat nDCNS_BooleanFloat0 = new au.com.dw.testdatacapturej.mock.classcheck.NDCNS_BooleanFloat(true, 10.1f);" +
			"holder0.setTestField(nDCNS_BooleanFloat0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for constructor with int parameter, this is not configured in XML config file so should
     * continue using the default constructor instead.
     */
    @Test
    public void paramIntNotConfiguredTest()
    {
		holder.setTestField(new NDCNS_Int(createInt()));

    	String constructorComment = FormatConstants.commentLinePrefix + Messages.getMessage(Messages.DEFAULT_CONSTRUCTOR_NOT_EXIST, NDCNS_Int.class.getName());

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.Holder holder0 = new au.com.dw.testdatacapturej.mock.classcheck.Holder();" +
			"au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Int nDCNS_Int0 = new au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Int();" +
			constructorComment +
			"nDCNS_Int0.setNoSetterField(2);" +
			"holder0.setTestField(nDCNS_Int0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
 
    /**
     * Test for constructor with Long and char parameters, this mis-configured in XML config file so should
     * generate constructor with only one of the fields instead of both. The setter for the field not configured
     * should also be generated since it should not be used for the parameter logging.
     */
    @Test
    public void paramLongCharMisConfiguredMissingFieldTest()
    {
		holder.setTestField(new NDCNS_LongChar(createLongObject(), createChar()));

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.Holder holder0 = new au.com.dw.testdatacapturej.mock.classcheck.Holder();" +
			"au.com.dw.testdatacapturej.mock.classcheck.NDCNS_LongChar nDCNS_LongChar0 = new au.com.dw.testdatacapturej.mock.classcheck.NDCNS_LongChar(7L);" +
			"nDCNS_LongChar0.setNoSetterField2('a');" +
			"holder0.setTestField(nDCNS_LongChar0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for constructor with double parameter, this mis-configured in XML config file with an extra
     * field that does not exist in the class so should continue using the default constructor instead.
     */
    @Test
    public void paramDoubleMisConfiguredExtraFieldTest()
    {
		holder.setTestField(new NDCNS_Double(createDoubleObject()));

	   	String constructorComment = FormatConstants.commentLinePrefix + Messages.getMessage(Messages.DEFAULT_CONSTRUCTOR_NOT_EXIST, NDCNS_Double.class.getName());

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.Holder holder0 = new au.com.dw.testdatacapturej.mock.classcheck.Holder();" +
			"au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Double nDCNS_Double0 = new au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Double();" +
			constructorComment +
			"nDCNS_Double0.setNoSetterField(60.1d);" +
			"holder0.setTestField(nDCNS_Double0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

    /**
     * Test for constructor with Integer parameter, this is mis-configured in XML config file so that is
     * has the wrong field name and should continue using the default constructor instead.
     */
    @Test
    public void paramIntegerMisConfiguredWrongFieldTest()
    {
		holder.setTestField(new NDCNS_Integer(createInteger()));

	   	String constructorComment = FormatConstants.commentLinePrefix + Messages.getMessage(Messages.DEFAULT_CONSTRUCTOR_NOT_EXIST, NDCNS_Integer.class.getName());

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.Holder holder0 = new au.com.dw.testdatacapturej.mock.classcheck.Holder();" +
			"au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Integer nDCNS_Integer0 = new au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Integer();" +
			constructorComment +
			"nDCNS_Integer0.setNoSetterField(6);" +
			"holder0.setTestField(nDCNS_Integer0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for constructor with Object parameter configured in XML config file.
     */
    @Test
    public void paramObjectTest()
    {
		TestData testData = new TestData();
    	holder.setTestField(new NDCNS_Object(testData.createSimpleDataHolder()));

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.Holder holder0 = new au.com.dw.testdatacapturej.mock.classcheck.Holder();" +
			"au.com.dw.testdatacapturej.mock.dataholder.SimpleDataHolder simpleDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.SimpleDataHolder();" +
			"simpleDataHolder0.setText(\"aaa\");" +
			"simpleDataHolder0.setNumber(1);" +
			"simpleDataHolder0.setCharacter('a');" +
			"simpleDataHolder0.setBool(true);" +
			"simpleDataHolder0.setLongNumber(100L);" +
			"simpleDataHolder0.setPrimitiveFraction(0.1f);" +
			"simpleDataHolder0.setFraction(100.1d);" +
			"au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Object nDCNS_Object0 = new au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Object(simpleDataHolder0);" +
			"holder0.setTestField(nDCNS_Object0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for constructor with Collection parameters configured in XML config file.
     * The parameters include the Collection interface and an implementation class.
     */
    @Test
    public void paramCollectionTest()
    {
		TestData testData = new TestData();
		
		Collection<?> collection = createLongCollection();
			
		ArrayList<Object> arrayList = new ArrayList<Object>();
		arrayList.add(testData.createSimpleDataHolder());
		
    	holder.setTestField(new NDCNS_Collection(collection, arrayList));

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.Holder holder0 = new au.com.dw.testdatacapturej.mock.classcheck.Holder();" +
			"java.util.HashSet hashSet0 = new java.util.HashSet();" +
			"hashSet0.add(100L);" +
			"hashSet0.add(200L);" +
			"java.util.ArrayList arrayList0 = new java.util.ArrayList();" +
			"au.com.dw.testdatacapturej.mock.dataholder.SimpleDataHolder simpleDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.SimpleDataHolder();" +
			"simpleDataHolder0.setText(\"aaa\");" +
			"simpleDataHolder0.setNumber(1);" +
			"simpleDataHolder0.setCharacter('a');" +
			"simpleDataHolder0.setBool(true);" +
			"simpleDataHolder0.setLongNumber(100L);" +
			"simpleDataHolder0.setPrimitiveFraction(0.1f);" +
			"simpleDataHolder0.setFraction(100.1d);" +
			"arrayList0.add(simpleDataHolder0);" +
			"au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Collection nDCNS_Collection0 = new au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Collection(hashSet0, arrayList0);" +
			"holder0.setTestField(nDCNS_Collection0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }
    
    /**
     * Test for constructor with Array parameters configured in XML config file.
     * The parameters include Object and primitive arrays.
     */
    @Test
    public void paramArrayTest()
    {
		TestData testData = new TestData();
		
		Object[] objectArray = new Object[1];
		objectArray[0] = testData.createSimpleDataHolder();
		
		double[] doubleArray = createDoubleArray();
		
    	holder.setTestField(new NDCNS_Array(objectArray, doubleArray));

		try {
			logger.logObject(builder, handler.handle(holder));
			String result = builder.toString();
			
			String expected = "au.com.dw.testdatacapturej.mock.classcheck.Holder holder0 = new au.com.dw.testdatacapturej.mock.classcheck.Holder();" +
			"java.lang.Object[] objectArray0 = new java.lang.Object[1];" +
			"au.com.dw.testdatacapturej.mock.dataholder.SimpleDataHolder simpleDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.SimpleDataHolder();" +
			"simpleDataHolder0.setText(\"aaa\");" +
			"simpleDataHolder0.setNumber(1);" +
			"simpleDataHolder0.setCharacter('a');" +
			"simpleDataHolder0.setBool(true);" +
			"simpleDataHolder0.setLongNumber(100L);" +
			"simpleDataHolder0.setPrimitiveFraction(0.1f);" +
			"simpleDataHolder0.setFraction(100.1d);" +
			"objectArray0[0] = simpleDataHolder0;" +
			"double[] doubleArray0 = new double[2];" +
			"doubleArray0[0] = 30.1d;" +
			"doubleArray0[1] = 40.1d;" +
			"au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Array nDCNS_Array0 = new au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Array(objectArray0, doubleArray0);" +
			"holder0.setTestField(nDCNS_Array0);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
    }

    /**
     * Test for constructor with Map parameters configured in XML config file.
     * The parameters include the Map interface and an implementation class.
     */
    @Test
    public void paramMapTest()
    {
    	TestData testData = new TestData();
    	
    	Map<String, Object> map = new TreeMap<String, Object>();
    	map.put(BaseReflectionTest.KEY_1, testData.createSimpleDataHolder());
    	
    	HashMap<?, ?> hashMap = createCharMap();
    	
    	holder.setTestField(new NDCNS_Map(map, hashMap));
    	
    	try {
    		logger.logObject(builder, handler.handle(holder));
    		String result = builder.toString();
    		
    		Collection<String> expectedValues = new ArrayList<String>();
    		
    		String expected1 = "au.com.dw.testdatacapturej.mock.classcheck.Holder holder0 = new au.com.dw.testdatacapturej.mock.classcheck.Holder();" +
    		"java.util.TreeMap treeMap0 = new java.util.TreeMap();" +
    		"au.com.dw.testdatacapturej.mock.dataholder.SimpleDataHolder simpleDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.SimpleDataHolder();" +
    		"simpleDataHolder0.setText(\"aaa\");" +
    		"simpleDataHolder0.setNumber(1);" +
    		"simpleDataHolder0.setCharacter('a');" +
    		"simpleDataHolder0.setBool(true);" +
    		"simpleDataHolder0.setLongNumber(100L);" +
    		"simpleDataHolder0.setPrimitiveFraction(0.1f);" +
    		"simpleDataHolder0.setFraction(100.1d);" +
    		"treeMap0.put(\"key1\", simpleDataHolder0);" +
    		"java.util.HashMap hashMap0 = new java.util.HashMap();" +
    		"hashMap0.put(\"key1\", 'a');" +
    		"hashMap0.put(\"key2\", 'b');" +
    		"au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Map nDCNS_Map0 = new au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Map(treeMap0, hashMap0);" +
    		"holder0.setTestField(nDCNS_Map0);";
    		expectedValues.add(expected1);

    		String expected2 = "au.com.dw.testdatacapturej.mock.classcheck.Holder holder0 = new au.com.dw.testdatacapturej.mock.classcheck.Holder();" +
    		"java.util.TreeMap treeMap0 = new java.util.TreeMap();" +
    		"au.com.dw.testdatacapturej.mock.dataholder.SimpleDataHolder simpleDataHolder0 = new au.com.dw.testdatacapturej.mock.dataholder.SimpleDataHolder();" +
    		"simpleDataHolder0.setText(\"aaa\");" +
    		"simpleDataHolder0.setNumber(1);" +
    		"simpleDataHolder0.setCharacter('a');" +
    		"simpleDataHolder0.setBool(true);" +
    		"simpleDataHolder0.setLongNumber(100L);" +
    		"simpleDataHolder0.setPrimitiveFraction(0.1f);" +
    		"simpleDataHolder0.setFraction(100.1d);" +
    		"treeMap0.put(\"key1\", simpleDataHolder0);" +
    		"java.util.HashMap hashMap0 = new java.util.HashMap();" +
    		"hashMap0.put(\"key2\", 'b');" +
    		"hashMap0.put(\"key1\", 'a');" +
    		"au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Map nDCNS_Map0 = new au.com.dw.testdatacapturej.mock.classcheck.NDCNS_Map(treeMap0, hashMap0);" +
    		"holder0.setTestField(nDCNS_Map0);";
    		expectedValues.add(expected2);
    		
    		System.out.println(result);
    		assertEqualsAnyWithoutFormatting(expectedValues, result);
    	} catch (Exception e) {
    		e.printStackTrace();
    		fail();
    	}
    }
 }

