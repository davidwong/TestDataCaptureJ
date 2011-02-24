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
package au.com.dw.testdatacapturej.builder;

import java.lang.reflect.Array;
import java.util.List;

import org.apache.commons.lang.WordUtils;

import au.com.dw.testdatacapturej.meta.ObjectInfo;
import au.com.dw.testdatacapturej.util.StringEscapeUtil;


/**
 * Rendering methods for generation of code generation lines such as constructors, setters, etc.
 *
 * @author David Wong
 *
 */
public class LineBuilder {

	/**
	 * Generate a constructor line for the test data class.
	 * If the class is:
	 *   com.test.TestClass
	 * Then should generate:
	 *   com.test.TestClass testClass = new com.test.TestClass();
	 * 
	 * This should be used for classes that have a no-argument constructor.
	 * 
	 * @param info The ObjectInfo for the object that requires the constructor line to be generated
	 * @return The generated constructor line
	 */
	public String createConstructorLine(ObjectInfo info) {
		StringBuilder constructorLineBuilder = new StringBuilder();
		
		constructorLineBuilder.append(info.getClassName());
		constructorLineBuilder.append(" ");

		constructorLineBuilder.append(info.getClassFieldName());
		constructorLineBuilder.append(info.getClassFieldNameIndex());
		
		constructorLineBuilder.append(" = new ");
		constructorLineBuilder.append(info.getClassName());
		constructorLineBuilder.append("();");
		
		return constructorLineBuilder.toString();
	}

	/**
	 * Generate a parameterized constructor line for the test data class.
	 * If the class is:
	 *   com.test.TestClass
	 * And the parameters are:
	 *   "test"
	 *   fieldObject1
	 *   1000L
	 * Then should generate:
	 *   com.test.TestClass testClass = new com.test.TestClass("test", fieldObject1, 1000L);
	 * 
	 * @param info The ObjectInfo for the object that requires the constructor line to be generated
	 * @return The generated constructor line
	 */
	public String createParameterizedConstructorLine(ObjectInfo info) {
		
		List<String> params = info.getConstructorInfo().getConstructorParameters();

		StringBuilder constructorLineBuilder = new StringBuilder();

		constructorLineBuilder.append(info.getClassName());
		constructorLineBuilder.append(" ");

		constructorLineBuilder.append(info.getClassFieldName());
		constructorLineBuilder.append(info.getClassFieldNameIndex());
		
		constructorLineBuilder.append(" = new ");
		constructorLineBuilder.append(info.getClassName());
		constructorLineBuilder.append("(");
		
		// log the parameters
		int size = params.size();
		for (int i = 0; i < size; i++)
		{
			constructorLineBuilder.append(params.get(i));
			if (i < size-1)
			{
				constructorLineBuilder.append(", ");
			}
		}
		constructorLineBuilder.append(");");
		
		return constructorLineBuilder.toString();
	}
	
	/**
	 * Generate a constructor line for the test data class which is an array.
	 * 
	 * If the array is:
	 *   Object[]
	 * Then should generate:
	 *   Object[] objectArray = new Object[10];
	 *   
	 * @param info The ObjectInfo for the object that requires the constructor line to be generated
	 * @return The generated constructor line
	 */
	public String createArrayConstructorLine(ObjectInfo info) {
		// extra info required for an array constructor as opposed to an object constructor
		Object array = info.getValue();
		String arrayType = array.getClass().getComponentType().getName();
		int initialSize = Array.getLength(array);
		
		StringBuilder constructorLineBuilder = new StringBuilder();
		
		constructorLineBuilder.append(arrayType);
		constructorLineBuilder.append("[] ");

		constructorLineBuilder.append(info.getClassFieldName());
		constructorLineBuilder.append(info.getClassFieldNameIndex());
		
		constructorLineBuilder.append(" = new ");
		constructorLineBuilder.append(arrayType);
		constructorLineBuilder.append("[");
		if (initialSize >= 0)
		{
			constructorLineBuilder.append(initialSize);
		}
		constructorLineBuilder.append("];");
		
		return constructorLineBuilder.toString();
	}
	
    /**
     * Generate a line for the default setter method name for a field.
     * e.g. if the field name is 'someField', will generate 'setSomeField();'.
     * 
     * @param fieldName
     * @param fieldValue
     * @param literal Flag whether to use the fieldValue as is, or need to format it first
     * @return
     */
    public String createSetterLine(String fieldName, Object fieldValue, boolean literal) {
    	StringBuilder builder = new StringBuilder();
    	
    	builder.append(".");
    	builder.append(getSetterMethodName(fieldName));
    	builder.append("(");
    	if (literal)
    	{
    		// just use the literal value of the object, should be a string representing the class field name
    		builder.append(fieldValue); 
    	}
    	else
    	{
    		appendClassDetail(builder, fieldValue);
    	}
        builder.append(");");
        
        return builder.toString();
    }

    /**
     * Get the default setter method name for a field.
     * 
     * @param fieldName
     * @return
     */
    public String getSetterMethodName(String fieldName)
    {
    	return "set" + WordUtils.capitalize(fieldName);
    }
    
    /**
	 * Generate a line for adding an element to a collection.
	 * 
	 * If the collection field name is :
	 *   arrayList0
	 * and the value to be added is :
	 *   "test"
	 * Then should generate:
	 *   arrayList0.add("test");
     * 
     * @param classFieldName
     * @param fieldValue
     * @param literal
     * @return
     */
    public String createCollectionAddLine(String classFieldName, Object fieldValue, boolean literal)
    {
       	StringBuilder builder = new StringBuilder();
       	
		builder.append(classFieldName);
    	builder.append(".");
       	builder.append("add");
       	builder.append("(");
       	interpretValue(builder, fieldValue, literal);       
       	builder.append(");");
       	
       	return builder.toString();
    }

    /**
	 * Generate a line for adding an element to a collection by invoking an adder method
	 * on it's enclosing class
	 * 
	 * If the collection field name is :
	 *   arrayList0
	 * and the enclosing class name is :
	 *   test0
	 * and the value to be added is :
	 *   "test"
	 * and the adder method name is :
	 *   addItem
	 *   
	 * Then should generate:
	 *   test0.addItem("test");
     * 
     * @param enclosingClassName Class name for the class that contains the collection field
     * @param adderMethodName Name for the adder method, needed since there is no standard convention unlike for getters and setters
     * @param fieldValue
     * @param literal
     * @return
     */
    public String createCollectionEnclosingAdderLine(String enclosingClassName, String adderMethodName, Object fieldValue, boolean literal)
    {
       	StringBuilder builder = new StringBuilder();
       	
		builder.append(enclosingClassName);
    	builder.append(".");
       	builder.append(adderMethodName);
       	builder.append("(");
       	interpretValue(builder, fieldValue, literal);       
       	builder.append(");");
       	
       	return builder.toString();
    }
    
    /**
	 * Generate a line for assigning an element to an array.
	 * 
	 * If the array field name is :
	 *   stringArray0
	 * and the value to be assigned is :
	 *   "test"
	 * Then should generate:
	 *   stringArray0[0] = "test";
     * 
     * @param classFieldName
     * @param index
     * @param fieldValue
     * @param literal
     * @return
     */
    public String createArrayAssignLine(String classFieldName, int index, Object fieldValue, boolean literal)
    {
       	StringBuilder builder = new StringBuilder();

		builder.append(classFieldName);
    	builder.append("[");
       	builder.append(index);
       	builder.append("]");
       	builder.append(" = ");
       	interpretValue(builder, fieldValue, literal);       
       	builder.append(";");

       	return builder.toString();
    }

    /**
	 * Generate a line for adding an entry to a map.
	 * 
	 * If the map field name is :
	 *   hashMap0
	 * and the value to be added is :
	 *   "test"
	 * and the key to be used is :
	 *   "key"
	 * Then should generate:
	 *   hashMap0.put("key", "test");
     * 
     * @param classFieldName
     * @param keyName
     * @param fieldName
     * @param keyValue
     * @param fieldValue
     * @param keyLiteral
     * @param literal
     * @return
     */
    public String createMapPutLine(String classFieldName, String keyName, String fieldName, Object keyValue, Object fieldValue,
			boolean keyLiteral, boolean literal)
    {
       	StringBuilder builder = new StringBuilder();
       	
		builder.append(classFieldName);
    	builder.append(".");
       	builder.append("put");
       	builder.append("(");
       	if (keyLiteral)
       	{
       		builder.append(keyName);
       	}
       	else
       	{
       		interpretValue(builder, keyValue, keyLiteral);
       	}
       	builder.append(", ");
       	if (literal)
       	{
       		builder.append(fieldName);
       	}
       	else
       	{
       		interpretValue(builder, fieldValue, literal);       
       	}      	
       	builder.append(");");
       	
       	return builder.toString();
    }

    /**
     * Format a simple value based on it's type, so that it can be used in test code generation.
     * e.g. 
     * Add double quotes if the object is a String or single quotes for a char.
     * 
   	 * String would look like: "value"
   	 * char would look like: 'v'
   	 * Long would look like: 0L
   	 * 
   	 * Values that don't require formatting (e.g. int) should display the value unchanged.
   	 * 
     * @param builder
     * @param value The value to be formatted
     */
    public void appendClassDetail(StringBuilder builder, Object value) {
    	
    	// add prefixes, if required
    	if (value instanceof String)
    	{
    		builder.append("\"");
    	}
    	else if (value instanceof Character)
    	{
    		builder.append("'");
    	}
    		
    	// the actual value
    	if (value instanceof String)
    	{
    		builder.append(escapeString((String)value));
    	}
    	else
    	{
    		builder.append(value);
    	}
    	
    	// add suffixes, if required
    	if (value instanceof String)
    	{
    		builder.append("\"");
    	}        
    	else if (value instanceof Character)
    	{
    		builder.append("'");
    	}    
    	else if (value instanceof Long)
    	{
    		builder.append("L");
    	}    
    	else if (value instanceof Float)
    	{
    		builder.append("f");
    	}    
    	else if (value instanceof Double)
    	{
    		builder.append("d");
    	}    
    }

    /**
     * Format a string by escaping some chars so that the result can be used as code.
     * - turn '\' into '\\'
     * - turn '"' into '\"'
     * e.g.
     * hello becomes "hello"
     * \d becomes \\d
     * 
     * @return
     */
    public String escapeString(String stringValue)
    {
    	String escapedBackSlash = StringEscapeUtil.escapeBackSlash(stringValue);
	    String escapedQuotes= StringEscapeUtil.escapeDoubleQuotes(escapedBackSlash);
	    return escapedQuotes;
    }

	/**
	 * Adds any additional text formatting to the field value so that it can be interpreted as java
	 * code for the particular field type.
	 * e.g.
	 * String's need to be surrounded by double quotes ("").
	 * Float's need to have 'f' appended.
	 * etc ...
	 * 
	 * @param builder
	 * @param fieldValue
	 * @param literal Flag whether to do any additional formatting or just use the field value as is.
	 */
	public void interpretValue(StringBuilder builder, Object fieldValue, boolean literal)
	{
    	if (literal)
    	{
    		// just use the literal value of the object, should be a string representing the class field name
    		builder.append(fieldValue); 
    	}
    	else
    	{
    		appendClassDetail(builder, fieldValue);
    	}
	}

}
