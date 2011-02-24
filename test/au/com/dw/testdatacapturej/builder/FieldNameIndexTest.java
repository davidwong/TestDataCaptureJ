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

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FieldNameIndexTest {
	
	private String field1 = "Test1";
	private String field2 = "Test2";

	/**
	 * Test for a single class to show that the index for the class is incrementing.
	 */
	@Test
	public void singleClassIndex() {
		int limit = 100;
		
		FieldNameIndex nameIndex = new FieldNameIndex();
		
		for (int i = 0; i < limit; i++)
		{
			int nextIndex = nameIndex.getAndIncrementIndexForField(field1);
			assertTrue(nextIndex == i);
		}
	}

	/**
	 * Test with 2 classes, should increment each class index independently when
	 * interleaving the calls to retrieve the index for each class.
	 */
	@Test
	public void multipleClassIndex() {
		FieldNameIndex nameIndex = new FieldNameIndex();
		
		int nextIndex = nameIndex.getAndIncrementIndexForField(field1);
		assertTrue(nextIndex == 0);
		
		nextIndex = nameIndex.getAndIncrementIndexForField(field1);
		assertTrue(nextIndex == 1);

		nextIndex = nameIndex.getAndIncrementIndexForField(field2);
		assertTrue(nextIndex == 0);

		nextIndex = nameIndex.getAndIncrementIndexForField(field1);
		assertTrue(nextIndex == 2);

		nextIndex = nameIndex.getAndIncrementIndexForField(field2);
		assertTrue(nextIndex == 1);

	}

	/**
	 * Test the index reset with 2 classes, doing some retrievals and then invoking
	 * the reset to check that the next retrievals after that will return the initial
	 * value.
	 */
	@Test
	public void resetClassIndices() {
		FieldNameIndex nameIndex = new FieldNameIndex();
		
		int nextIndex = nameIndex.getAndIncrementIndexForField(field1);
		assertTrue(nextIndex == 0);
		
		nextIndex = nameIndex.getAndIncrementIndexForField(field1);
		assertTrue(nextIndex == 1);

		nextIndex = nameIndex.getAndIncrementIndexForField(field2);
		assertTrue(nextIndex == 0);

		nextIndex = nameIndex.getAndIncrementIndexForField(field1);
		assertTrue(nextIndex == 2);

		nextIndex = nameIndex.getAndIncrementIndexForField(field2);
		assertTrue(nextIndex == 1);
		
		// do the reset
		nameIndex.resetIndices();
		
		nextIndex = nameIndex.getAndIncrementIndexForField(field1);
		assertTrue(nextIndex == 0);

		nextIndex = nameIndex.getAndIncrementIndexForField(field2);
		assertTrue(nextIndex == 0);
	}
}
