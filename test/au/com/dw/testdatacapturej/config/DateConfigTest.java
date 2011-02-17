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
package au.com.dw.testdatacapturej.config;

import static au.com.dw.testing.AssertUtil.assertEqualsWithoutFormatting;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import au.com.dw.testdatacapturej.log.ObjectLogger;
import au.com.dw.testdatacapturej.reflection.MetadataGenerationHandler;
import au.com.dw.testdatacapturej.reflection.ReflectionHandler;

/**
 * Test for configuration of java.util.Date.
 * 
 * This class was chosen because it is still commonly used and contains internal fields that have no setter
 * methods or which are transient and should be ignored.
 * 
 * Ensure that java.util.Date has been correctly configured for constructors and setters before running this
 * test.
 * i.e.
 * 
 * In the constructor config file:
 * 
 * 	<constructor class="java.util.Date">
 * 		<argument>
 * 			<field-name>fastTime</field-name>
 * 		</argument>
 * 	</constructor>
 * 
 * In the setter config file:
 * 
 * 	<setter class="java.util.Date">
 * 		<argument>
 * 			<field-name>cDate</field-name>
 * 		</argument>
 * 		<alternative>ignore</alternative>
 * 	</setter>
 * 
 * @author David Wong
 *
 */
public class DateConfigTest {

	private ReflectionHandler handler;
	private ObjectLogger logger;
	private StringBuilder builder;
	
	private long dateTime = 1234567890L;
	
	@Before
	public void setUp() throws Exception {
		handler = new MetadataGenerationHandler();
		logger = new ObjectLogger();
		builder = new StringBuilder();
	}

	/**
	 * Test that java.util.Date does not generate setter methods for it's internal fields.
	 */
	@Test
	public void testDate()
	{
		
		try {
			logger.logObject(builder, handler.handle(new Date(dateTime)));
			String result = builder.toString();
		
			String expected = "java.util.Date date0 = new java.util.Date(" + dateTime + "L);";
			
			System.out.println(result);
			assertEqualsWithoutFormatting(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("testPrimitiveHolder");
		}
	}
	
}
