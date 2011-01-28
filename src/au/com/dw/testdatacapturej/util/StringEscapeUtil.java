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
package au.com.dw.testdatacapturej.util;

/**
 * Utility class for escaping strings.
 * 
 * @author David Wong
 *
 */
public class StringEscapeUtil {
	
	/**
	 *  Escape any single quotes within a string (usefull for strings
	 *  that will be placed inside HTML inputs or code)
	 *  e.g. ['] becomes [\']
	 *
	 *@param  str
	 */
	public static String escapeSingleQuotes( String str ) {
		if ( str.indexOf( "'" ) == -1 ) {
			return str;
		}
		String ret = replace( str, "'", "\\'", false );
		return ret;
	}
	
	/**
	 *  Escape any double quotes within a string (usefull for strings
	 *  that will be placed inside HTML inputs or code)
	 *  e.g. ["] becomes [\"]
	 *
	 *@param  str
	 */
	public static String escapeDoubleQuotes( String str ) {
		if ( str.indexOf( '"' ) == -1 ) {
			return str;
		}
		String ret = replace( str, "\"", "\\\"", false );
		return ret;
	}
	

	/**
	 *  Escape any back slash within a string (usefull for strings
	 *  that will be placed inside HTML inputs or code)
	 *  e.g. [\] becomes [\\]
	 *
	 *@param  str
	 */
	public static String escapeBackSlash( String str ) {
		if ( str.indexOf( "\\" ) == -1  ) {
			return str;
		}
		String ret = replace( str, "\\", "\\\\", false );

		return ret;
	}

	/**
	 *  Escape any single or double quotes within a string (usefull for strings
	 *  that will be placed inside HTML inputs or code)
	 *
	 *	The original version that does all quotes and back slash.
	 *
	 *@param  str
	 */
	public static String escapeQuotes( String str ) {
		if ( ( str.indexOf( "'" ) == -1 ) && ( str.indexOf( '"' ) == -1 ) ) {
			return str;
		}
		String ret = replace( str, "\\", "\\\\", false );
		ret = replace( str, "'", "\\'", false );
		ret = replace( ret, "\"", "\\\"", false );
		return ret;
	}
	
	/**
	 *  SQL strings use ' as delimiter, so inner quotes must be converted to ''
	 *
	 *@param  str
	 */
	public static String escapeQuotesSQL( String str ) {
		if ( str.indexOf( "'" ) == -1 ) {
			return str;
		}
		String ret = replace( str, "'", "''", false );
		return ret;
	}
	
	/**
	 *  Given an input string, replace all occurences of a string sequence with a
	 *  given replacement string.
	 *
	 *@param  in               input string
	 *@param  find             string sequence match against
	 *@param  replace          string to replace matches with
	 *@param  caseInsensitive  should matching be case insensitive?
	 *@return                  the newly altered string
	 */
	public static String replace( String in,
	                              String find,
	                              String replace,
	                              boolean caseInsensitive ) {
		String match;
		if ( caseInsensitive ) {
			match = in.toLowerCase();
			find = find.toLowerCase();
		}
		else {
			match = in;
		}
		StringBuffer ret = new StringBuffer();
		int ind1 = 0;
		int ind2 = match.indexOf( find );
		int findLength = find.length();
		while ( ind2 > -1 ) {
			ret.append( in.substring( ind1, ind2 ) )
				.append( replace );
			ind1 = ind2 + findLength;
			ind2 = match.indexOf( find, ind1 );
		}
		ret.append( in.substring( ind1 ) );
		return ret.toString();
	}
	
}
