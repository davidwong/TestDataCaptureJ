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
package au.com.dw.testdatacapturej.mock.service;

import java.util.ArrayList;
import java.util.Collection;

import au.com.dw.testdatacapturej.mock.dataholder.SimpleDataHolder;


public class SimpleMockDataService {
	private Collection<SimpleDataHolder> dataCollection;
	
	public SimpleMockDataService()
	{
		SimpleDataHolder data1 = new SimpleDataHolder();
		data1.setText("first");
		data1.setNumber(1);
		data1.setCharacter('a');
		data1.setLongNumber(1000L);
		data1.setBool(true);
		data1.setPrimitiveFraction(1.1f);
		data1.setFraction(10.01d);
		
		SimpleDataHolder data2 = new SimpleDataHolder();
		data2.setText("second");
		data2.setNumber(2);
		data2.setCharacter('b');
		data2.setLongNumber(2000L);
		data2.setBool(false);
		data2.setPrimitiveFraction(2.2f);
		data2.setFraction(20.02d);
		
		Collection<SimpleDataHolder> dataCollection = new ArrayList<SimpleDataHolder>();
		dataCollection.add(data1);
		dataCollection.add(data2);
		
		setDataCollection(dataCollection);		
	}

	public Collection<SimpleDataHolder> getDataCollection() {
		return dataCollection;
	}

	public void setDataCollection(Collection<SimpleDataHolder> dataCollection) {
		this.dataCollection = dataCollection;
	}
}
