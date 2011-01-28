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


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import au.com.dw.testdatacapturej.builder.LineBuilder;

/**
 * Tests for string escaping.
 * 
 * @author t-davidw
 *
 */
public class ToStringStyleUtilTest {

	private LineBuilder stringUtil;
	
	@Before
	public void setUp() throws Exception {
		stringUtil = new LineBuilder();
	}

	@Test
	public void testEscapeDoubleQuotes()
	{
		String doubleQuote = "\"";
		String result = stringUtil.escapeString(doubleQuote);
		
		System.out.println(result);
		assertEquals("\\\"", result);		
		
		String testDoubleQuote = "\"aaa\"\"bbb\"";
		result = stringUtil.escapeString(testDoubleQuote);
		
		System.out.println(result);
		assertEquals("\\\"aaa\\\"\\\"bbb\\\"", result);		
	}

	@Test
	public void testNotEscapeSingleQuotes()
	{
		String singleQuote = "'";
		String result = stringUtil.escapeString(singleQuote);
		
		System.out.println(result);
		assertEquals("'", result);		
		
		String testSingleQuote = "'aaa''bbb'";
		result = stringUtil.escapeString(testSingleQuote);
		
		System.out.println(result);
		assertEquals("'aaa''bbb'", result);		
	}
	
	@Test
	public void testEscapeBackSlash()
	{
		String backSlash = "\\";
		String result = stringUtil.escapeString(backSlash);
		
		System.out.println(result);
		assertEquals("\\\\", result);
		
		
		String testBackSlash = "\\aaa\\\\bbb\\";
		result = stringUtil.escapeString(testBackSlash);
		
		System.out.println(result);
		assertEquals("\\\\aaa\\\\\\\\bbb\\\\", result);			
	}
	
	@Test
	public void testEscapeMixed()
	{
		String mixed = "\\'\"";
		String result = stringUtil.escapeString(mixed);
		
		System.out.println(result);
		assertEquals("\\\\'\\\"", result);
		
		
		String testMixed = "\\'\"aaa\\'\"\\'\"bbb\\'\"";
		result = stringUtil.escapeString(testMixed);
		
		System.out.println(result);
		assertEquals("\\\\'\\\"aaa\\\\'\\\"\\\\'\\\"bbb\\\\'\\\"", result);			
	}
}
