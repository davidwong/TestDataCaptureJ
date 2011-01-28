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
package au.com.dw.testdatacapturej;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	au.com.dw.testdatacapturej.builder.AllTests.class,
	au.com.dw.testdatacapturej.classcheck.AllTests.class,
	au.com.dw.testdatacapturej.config.AllTests.class,
	au.com.dw.testdatacapturej.log.AllTests.class,
	au.com.dw.testdatacapturej.test.AllTests.class,
	au.com.dw.testdatacapturej.reflection.AllTests.class,
	au.com.dw.testdatacapturej.reflection.util.AllTests.class
})

public class AllTests {
		//just an empty class for make file compile with annotation
}
