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


/**
 * Generates import statements in the required language syntax.
 * 
 * @author David Wong
 *
 */
public abstract class ImportStatementGenerator {

	/** Hold the class names internally in a set to de-duplicate */
	private ClassNameHolder classNameHolder;
	
	public ImportStatementGenerator(ClassNameHolder classNameHolder) {
		this.classNameHolder = classNameHolder;
	}
	
	/**
	 * Generate the import statements.
	 * 
	 * @return The import statements in the required syntax
	 */
	public abstract String generateImports();
}
