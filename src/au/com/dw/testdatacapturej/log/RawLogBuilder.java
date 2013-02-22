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
package au.com.dw.testdatacapturej.log;

/**
 * The default implementation of LogBuilder which is a wrapper around the StringBuilder that holds the logging.
 * 
 * This does not do any processing of the log, but just passes the strings to the StringBuilder.
 * 
 * @author David Wong
 *
 */
public class RawLogBuilder implements LogBuilder {

	private final StringBuilder builder;

	public RawLogBuilder() {
		builder = new StringBuilder();
	}

	public void append(String str) {
		builder.append(str);
	}
	
	public void process(String str) {
		builder.append(str);
	}

	public String getPreLog() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLog() {
		return builder.toString();
	}

}
