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
package au.com.dw.testdatacapturej.mock.dataholder;

/**
 * Test methods with different signatures:
 * - different access specifiers
 * - some throw exceptions
 * - some with annotations
 * 
 * There are versions that should be invoked and some that shouldn't for each type of test method.
 * 
 * @author t-davidw
 *
 */
public class MockTarget {

	// ****** Methods to be invoked ******
	
	public String publicTargetMethod()
	{
		return "publicTargetMethod";
	}

	protected String protectedTargetMethod()
	{
		return "protectedTargetMethod";
	}
	
	private String privateTargetMethod()
	{
		return "privateTargetMethod";
	}

	public String publicTargetExceptionMethod() throws TargetException
	{
		throw new TargetException("publicTargetExceptionMethod");
	}

	protected String protectedTargetExceptionMethod() throws TargetException
	{
		throw new TargetException("protectedTargetExceptionMethod");
	}
	
	private String privateTargetExceptionMethod() throws TargetException
	{
		throw new TargetException("privateTargetExceptionMethod");
	}
	
	// ****** Methods not to be invoked ******

	public String publicNonTargetMethod()
	{
		return "publicNonTargetMethod";
	}

	protected String protectedNonTargetMethod()
	{
		return "protectedNonTargetMethod";
	}
	
	private String privateNonTargetMethod()
	{
		return "privateNonTargetMethod";
	}

	// ****** Annotated method to be invoked ******

	@Target
	public String annotationTargetMethod()
	{
		return "annotationTargetMethod";
	}

	@Target
	public String annotationTargetExceptionMethod() throws TargetException
	{
		throw new TargetException("annotationTargetExceptionMethod");
	}
	
	// ****** Annotated method not to be invoked ******

	@NonTarget
	public String annotationNonTargetMethod()
	{
		return "annotationNonTargetMethod";
	}
	
	@NonTarget
	public String annotationNonTargetExceptionMethod() throws TargetException
	{
		throw new TargetException("annotationNonTargetExceptionMethod");
	}
}
