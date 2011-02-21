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
package au.com.dw.testdatacapturej.meta;

/**
 * Holder for metadata relating to generating setter or adder methods.
 * 
 * ***************************************************
 * Setter method fields
 * ***************************************************
 * 
 * See ObjectInfo comments for explanation of some of the setter method generation fields.
 * 
 * @see au.com.dw.testdatacapturej.meta.ObjectInfo
 * 
 * ***************************************************
 * Adder method fields
 * ***************************************************
 * A common pattern for collection fields is not to allow direct access to the collection, but only
 * allow adding elements to it through a adder method in the class containing the collection. So there
 * is usually no setter or getter for the collection field itself.
 * e.g.
 * 
 * public class ClassWithAdder {
 *   private Collection<?> collectionField = new ArrayList<Object>();
 *   
 *   public ClassWithAdder()
 *   {
 *   }
 *   
 *   public void addElement(Object element)
 *   {
 *     collectionField.add(element);
 *   }
 * }
 * 
 * The ObjectInfo for the collection field will have to be marked as requiring generation for this
 * pattern instead of the default generation of:
 * 1. create the collection object
 * 2. add element to the collection
 * 3. set the collection to the field of the containing class
 * Instead we just want to invoke the adder method in the containing class.
 * 
 * usesAdder is the flag field, which is set in the collection object (not the ObjectInfo for the elements
 * or the containing class).
 * 
 * adderMethodName is the name of the adder method name in the containing class and must be specified since
 * there is no standard naming convention.
 * 
 * @author David Wong
 *
 */
public class SetterAdderInfo {
	
	/** If the object is a field, does the containing class has a setter for it with standard naming */
	public boolean hasSetter;
	
	/** If the object is a field, the type of setter method to generate */
	public SetterGenerationType setterGenerationType;
	
	/** If the object is a field, PLACEHOLDER for alternative name for the setter method - not implemented yet */
	public String alternateSetterMethodName;
	
	/** If the object is a collection, are elements added to it through an adder method in it's enclosing
	 *  class, instead being added directly to the collection.
	 */
	public boolean usesAdder;
	
	/** If the object is a collection and elements are only to be added to it through an add method in it's
	 *  enclosing class, then this is the name of that adder method.
	 */
	public String adderMethodName;

	public SetterAdderInfo(boolean hasSetter,
			SetterGenerationType setterGenerationType, boolean usesAdder) {
		this.hasSetter = hasSetter;
		this.setterGenerationType = setterGenerationType;
		this.usesAdder = usesAdder;
	}
	
	public boolean hasSetter() {
		return hasSetter;
	}
	
	public void setHasSetter(boolean hasSetter) {
		this.hasSetter = hasSetter;
	}
	
	public SetterGenerationType getSetterGenerationType() {
		return setterGenerationType;
	}
	
	public void setSetterGenerationType(SetterGenerationType setterGenerationType) {
		this.setterGenerationType = setterGenerationType;
	}
	
	public String getAlternateSetterMethodName() {
		return alternateSetterMethodName;
	}
	
	public void setAlternateSetterMethodName(String alternateSetterMethodName) {
		this.alternateSetterMethodName = alternateSetterMethodName;
	}
	
	public boolean isUsesAdder() {
		return usesAdder;
	}
	
	public void setUsesAdder(boolean usesAdder) {
		this.usesAdder = usesAdder;
	}
	
	public String getAdderMethodName() {
		return adderMethodName;
	}
	
	public void setAdderMethodName(String adderMethodName) {
		this.adderMethodName = adderMethodName;
	}
}
