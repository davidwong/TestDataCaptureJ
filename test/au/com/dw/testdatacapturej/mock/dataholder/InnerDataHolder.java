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
package au.com.dw.testdatacapturej.mock.dataholder;

/**
 * Simple test mock class, useful as objects to put into collection, arrays, etc, where DataHolder would
 * generate too much information.
 * 
 * @author t-davidw
 *
 */
public class InnerDataHolder implements Comparable {
	private String imText;
	private double imNumber;
	
	public InnerDataHolder() {
		super();
	}
	
	public InnerDataHolder(String imText, double imNumber) {
		super();
		this.imText = imText;
		this.imNumber = imNumber;
	}
	
	public String getImText() {
		return imText;
	}
	public double getImNumber() {
		return imNumber;
	}
	public void setImText(String imText) {
		this.imText = imText;
	}
	public void setImNumber(double imNumber) {
		this.imNumber = imNumber;
	}
	
	public int compareTo(Object o)
	{
		if (!(o instanceof InnerDataHolder))
		      throw new ClassCastException("A SimpleDataHolder object expected.");
		    Double compareNumber = ((InnerDataHolder) o).getImNumber();  
		    return new Double(imNumber).compareTo(compareNumber);  		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(imNumber);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((imText == null) ? 0 : imText.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InnerDataHolder other = (InnerDataHolder) obj;
		if (Double.doubleToLongBits(imNumber) != Double
				.doubleToLongBits(other.imNumber))
			return false;
		if (imText == null) {
			if (other.imText != null)
				return false;
		} else if (!imText.equals(other.imText))
			return false;
		return true;
	}
}

