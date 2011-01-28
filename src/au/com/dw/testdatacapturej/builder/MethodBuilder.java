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

import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.WordUtils;

/**
 * Generate text that can be used as code for starting and finishing a method. This is used to
 * wrap the code generation test data for each parameter or return value in a method.
 * e.g.
 * when the code is generated:
 * 
 *   com.test.TestClass testClass0 = new com.test.TestClass();
 *   testClass0.setField(..);
 *   ... etc
 *   
 * then by using the operations in this class, it should wrap the code thus:
 * 
 *   public com.test.TestClass create..() {
 *   .
 *   .
 *   .
 *   return testClass0;
 *   }
 * 
 * @author David Wong
 *
 */
public class MethodBuilder {
	
	/**
	 * Create the start of the method wrapping code.
	 * 
	 * The format for the method name will be:
	 * create[Param | Return][parameter index if is param][class name of object][fully qualified enclosing method name for the object using underscores instead of period]
	 * 
	 * If the object is of class com.test.TestClass and is the second parameter, and the method from which it is the parameter is org.test.DummyClass.dummyMethod -
	 * then the method name would be:
	 * 
	 *   createParam2TestClass_org_test_DummyClass_dummyMethod
	 * 
	 * If the object were the return value then:
	 * 
	 *   createReturnTestClass_org_test_DummyClass_dummyMethod
	 * 
	 * e.g. would generate something like:
	 * 
	 *   public  createParam1TestClass_org_test_DummyClass_dummyMethod()
	 *   {
	 *   
	 * @param enclosingMethod The fully qualified name of the method for which the object is the parameter or return value
	 * @param object Object to be logged for code generation
	 * @param paramIndex The index number of the parameter starting at 1 for the first parameter, else 0 if the object is a return value
	 * @return
	 */
	public String createMethodLine(String enclosingMethod, Object object, int paramIndex)
	{
		StringBuilder builder = new StringBuilder();
		
		String className = object.getClass().getSimpleName();
		String enclosingMethodName = enclosingMethod.replace('.', '_');
		
		builder.append("public ");
		builder.append(object.getClass().getName());
		builder.append(" create");
		
		// check if is a parameter or return value
		if (paramIndex != 0)
		{
			builder.append("Param" + paramIndex);
		}
		else
		{
			builder.append("Return");
		}
		builder.append(className);
		builder.append("_");
		builder.append(enclosingMethodName);
		builder.append("() {");
		builder.append(SystemUtils.LINE_SEPARATOR);
		
		return builder.toString();
	}
	
	/**
	 * Create the end of the method wrapping code.
	 * 
	 * e.g. would generate something like:
	 * 
	 *   return objectClassName;
	 *   }
	 *   
	 * @param object Object to be logged for code generation
	 * @return
	 */
	public String createMethodCompletion(Object object)
	{
		StringBuilder builder = new StringBuilder();
		
		// this is a kludge, should really get the field name from the recursive logging if possible
		// but here we are assuming that the field name will be the class name suffixed by '0'
		builder.append("\nreturn ");
		builder.append(WordUtils.uncapitalize(object.getClass().getSimpleName()) + "0;");
		builder.append(SystemUtils.LINE_SEPARATOR);
		
		// end of method
		builder.append("}");
		builder.append(SystemUtils.LINE_SEPARATOR);
		builder.append(SystemUtils.LINE_SEPARATOR);
		
		return builder.toString();
	}
}
