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
package au.com.dw.testdatacapturej.log.java;

import au.com.dw.testdatacapturej.log.FieldGenerator;
import au.com.dw.testdatacapturej.log.OutputGeneratorFactory;

/**
 * Factory to create output generators that generate text in Java syntax.
 * 
 * @author David Wong
 *
 */
public class JavaOutputGeneratorFactory implements OutputGeneratorFactory {

	public FieldGenerator getArrayFieldGenerator() {
		return new ArrayFieldGenerator();
	}

	public FieldGenerator getCollectionFieldGenerator() {
		return new CollectionFieldGenerator();
	}

	public FieldGenerator getMapFieldGenerator() {
		return new MapFieldGenerator();
	}

	public FieldGenerator getSimpleFieldGenerator() {
		return new SimpleFieldGenerator();
	}

	public FieldGenerator getClassFieldGenerator() {
		return  new ClassFieldGenerator();
	}

	public FieldGenerator getCollectionElementGenerator() {
		return new CollectionElementGenerator();
	}

	public FieldGenerator getArrayElementGenerator() {
		return new ArrayElementGenerator();
	}

	public FieldGenerator getMapEntryGenerator() {
		return new MapEntryGenerator();
	}

	public FieldGenerator getAddedElementGenerator() {
		return new AddedElementGenerator();
	}

}
