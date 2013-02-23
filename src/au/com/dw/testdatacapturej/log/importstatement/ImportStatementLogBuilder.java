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

import au.com.dw.testdatacapturej.log.RawLogBuilder;
import au.com.dw.testdatacapturej.reflection.util.ReflectionUtil;

public class ImportStatementLogBuilder extends RawLogBuilder {

	private final ClassNameHolder classNameHolder;
	
	public ImportStatementLogBuilder() {
		super();
		
		classNameHolder = new ClassNameHolder();
	}

	public void process(String str) {
		// save the fully qualified class name and just 
		getClassNameHolder().addClassName(str);
		append(ReflectionUtil.getShortClassName(str));
	}
	
	public String getPreLog() {
		// TODO Auto-generated method stub
		return null;
	}

	public ClassNameHolder getClassNameHolder() {
		return classNameHolder;
	}

	
}
