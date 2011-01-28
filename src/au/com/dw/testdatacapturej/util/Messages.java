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

import java.util.HashMap;
import java.util.Map;

/**
 * TODO get these from resource bundle.
 * 
 * @author David Wong
 *
 */
public class Messages {
	
	private static final Map<String, String> messages;
	
	static {
		messages = new HashMap<String, String>();
		
		messages.put(Messages.CONSTRUCTOR_PARAM_FIELD_NAME_NOT_FOUND, "Constructor parameter field names not found: ");
		messages.put(Messages.DEFAULT_CONSTRUCTOR_NOT_EXIST, "Default constructor for %s does not exist.");
	}
	
	public static final String CONSTRUCTOR_PARAM_FIELD_NAME_NOT_FOUND = "CONSTRUCTOR_PARAM_FIELD_NAME_NOT_FOUND";

	public static final String DEFAULT_CONSTRUCTOR_NOT_EXIST = "DEFAULT_CONSTRUCTOR_NOT_EXIST";

	public static String getMessage(String key, Object... args)
	{
		String message = null;
		
		String rawMessage = messages.get(key);
		if (rawMessage != null)
		{
			message = String.format(rawMessage, args);
		}

		return message;
	}
}
