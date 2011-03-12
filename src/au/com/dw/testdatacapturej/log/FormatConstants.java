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

import org.apache.commons.lang.SystemUtils;

/**
 * Formatting constants.
 * 
 * @author David Wong
 *
 */
public class FormatConstants {
	
	/** New line */
	public static final String newLine = SystemUtils.LINE_SEPARATOR;
	
	/**
	 * Make a line a code comment.
	 */
	public static final String commentLinePrefix = "// ";
	
	/**
	 * Enclose in a code comment block.
	 */
	public static final String commentBlockPrefix = "/** ";
	public static final String commentBlockSuffix = " */";
	
	/**
	 * Enum to flag whether to turn generated test data lines into comments.
	 */
	public static enum Comment {NO_COMMENT, SINGLE_LINE_COMMENT, COMMENT_BLOCK};
	
	/**
	 * Suffix for arrays, e.g. add to java.lang.Object to create an array:
	 * java.lang.Object[]
	 */
	public static String arraySuffix = "[]";
	
	public final static String EMPTY_STRING = "";
	
	public final static String FILE_NAME_SEPARATOR = "-";
	
	public final static String PARAMETER = "Parameter";
	
	public final static String RETURN = "Return";
}
