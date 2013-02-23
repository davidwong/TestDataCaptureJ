/*******************************************************************************
 * Copyright () 2013 David Wong
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
package au.com.dw.testdatacapturej.log.importstatement;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Holds class names to use to generate import statements.
 *  
 * @author David Wong
 *
 */
public class ClassNameHolder {

	/** Holds the class names internally in a set to de-duplicate */
	private final Set<String> classNames;
	
	public ClassNameHolder() {
		classNames = new HashSet<String>();
	}

	/**
	 * Add a class name for an object.
	 * 
	 * @param className
	 */
	public void addClassName(String className)
	{
		classNames.add(className);
	}

	public Set<String> getClassNames() {
		return Collections.unmodifiableSet(classNames);
	}

}
