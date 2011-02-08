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

import java.util.ArrayList;
import java.util.List;

/**
 * Holder for the metadata required to generate test data for an object, whether it is for the initial object
 * to be logged or a field somewhere in the recursive process.
 * 
 * ***************************************************
 * Overview of the most common fields
 * ***************************************************
 * e.g. The object to be logged as a parameter or return value is InitialObject in the example.
 * 
 * public class FieldObject {
 *    
 *    public FieldObject(..some param..) {}
 * }
 * 
 * public class InitialObject {
 *   
 *   private FieldObject fieldObjectName;
 *   private float primitiveName;
 *   
 *   public InitialObject()
 *   {
 *   }
 * 
 *   public setFieldName(FieldObject fieldObjectName)
 *   {
 *     this.fieldObjectName = fieldObjectName;
 *   }
 * }
 * 
 * The ObjectInfo for InitialObject would be:
 * 
 * isInitalObject = true
 * type = OBJECT
 * value = the InitialObject object
 * className = ...InitialObject
 * fieldName = null, since is not a field
 * classFieldName = initialObject
 * classFieldNameIndex = 0, this is set externally
 * containingClassFieldName = null, since doesn't have a containing class
 * containmentType = NONE, since is the top level object
 * index = 0, since is not an array element
 * hasSetter = false, since is not a field
 * hasDefaultConstructor = true, since does have InitialObject() constructor
 * fieldList = [2], contains the ObjectInfo for the fields fieldObject and primitive
 * 
 * The ObjectInfo for fieldObjectName would be:
 * 
 * isInitalObject = false, since is a field of InitialObject
 * type = OBJECT
 * value = the fieldObject object
 * className = ...FieldObject
 * fieldName = fieldObjectName
 * classFieldName = fieldObject
 * classFieldNameIndex = 0, this is set externally
 * containingClassFieldName = initialObject0, the class field name generated for InitialObject
 * containmentType = FIELD, since is a field of it's parent
 * index = 0, since is not an array element
 * hasSetter = true, since InitialObject has setFieldName()
 * hasDefaultConstructor = false, since does have FieldObject() constructor - constructor has arguments
 * fieldList = [0], does not contain any fields itself
 * 
 * The ObjectInfo for primitiveName would be:
 * 
 * isInitalObject = false, since is a field of InitialObject
 * type = SIMPLE
 * value = the value of the primitiveName field
 * className = java.lang.Float, the wrapper class name
 * fieldName = primitiveName
 * classFieldName = null, since not required to be constructed
 * classFieldNameIndex = 0, this is set externally
 * containingClassFieldName = initialObject0, the class field name generated for InitialObject
 * containmentType = FIELD, since is a field of it's parent
 * index = 0, since is not an array element
 * hasSetter = false, since InitialObject doesn't have a setPrimitiveName() method
 * hasDefaultConstructor = false, since constructor no required for primitive values
 * fieldList = [0], does not contain any fields itself
 * 
 * Therefore if Initial Object and FieldObject were to be constructed and generated:
 * 
 * ..InitialObject  initialObject0  = new ..InitialObject();
 *   [className]   [classFieldName]
 *              [+ classFieldNameIndex] 
 * 
 * ..FieldObject  fieldObject0  = new ..FieldObject(..);
 *  [className]  [classFieldName]
 *            [+ classFieldNameIndex] 
 *  
 *       initialObject0.   setFieldObjectName  (fieldObject0);
 * [containingClassFieldName] [ derived ]     [classFieldName]
 *                          [from fieldName]
 * 
 * ***************************************************
 * Array specific fields
 * ***************************************************
 * The index would only be used for array elements, so the first element would have index 0, the second element
 * would have index 1, etc.
 * 
 * ***************************************************
 * Map specific fields
 * ***************************************************
 * The keyInfo is only used for map entries. The ObjectInfo would store the metadata for the value while the
 * keyInfo would be created to store the metadata for the key.
 * 
 * ***************************************************
 * Constructor specific fields
 * ***************************************************
 * Where a parameterized constructor has been configured for this object, the following fields are used.
 * 1. constructorParamFieldNames
 * This is the list of field names for the parameters, assuming that the constructor is setting fields rather
 * than setting fields latter with setters.
 * 2. constructorParameters
 * When the field object (not this object) is generated as a value or class field name in the parameter logging, it is added to
 * this list in the parent ObjectInfo (parentInfo)
 * 3. alreadyLogged
 * If this object has been logged as a parameter, then this flag is set to indicate do not need to log again as a field.
 *
 * e.g.
 * This is the object to be logger:
 * 
 * public class ImmutableClass {
 *   private Double immutableField;
 *   
 *   public Immutable(Double immutableField)
 *   {
 *     this.immutableField = immutableField;
 *   }
 *   
 *   public Double getImmutableField()
 *   {
 *     return immutableField;
 *   }
 * }
 * 
 * The parameterized constructor is configured in the XML file:
 *
 *   <constructor class="dummy.ImmutableClass">
 *	   <argument>
 *		  <field-name>immutableField</field-name>
 *	   </argument>
 *   </constructor>
 *   
 * 1. constructorParamFieldNames would get the value [immutableField] from the configuration.
 * 2. after the field being logged as a parameter, constructorParameters would have the value added to
 *    it from the field ObjectInfo, e.g. 100L.
 * 3. alreadyLogged in the field ObjectInfo would be set to true.
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
public class ObjectInfo {
	
	/** Flag whether is the initial object to be logged, e.g. the top level object */
	private boolean isInitalObject;
	
	/** The type of the object using the ObjectType enum */
	private ObjectType type;
	
	/** The value or the object itself */
	private Object value;
	
	/** The fully qualified class name of the object */
	private String className;
	
	/** If the object is a field, the name of the field from it's containing object */
	private String fieldName;
	
	/** The created field name for the object, e.g. when constructed */
	public String classFieldName;
	
	/** Number suffix to use to create a unique classfieldName for this object */
	private int classFieldNameIndex;
	
	/** If the object is a field, the field name of the object that contains it */
	private String containingClassFieldName;
	
	/** How this object is contained in it's parent object, using the ContainmentType enum */
	private ContainmentType containmentType = ContainmentType.FIELD;
	
	/** Index if is an array element */
	private int index;
	
	/** Does the object have a default no argument constructor */
	private boolean hasDefaultConstructor = true;

	/** List of field names to use as constructor parameters when generating a constructor line */
	private List<String> constructorParamFieldNames = new ArrayList<String>();
	
	/** 
	 * List of constructor parameter values, these can be field names or formatted simple values. These
	 * are to be used to generate a constructor line with parameters.
	 */
	private List<String> constructorParameters = new ArrayList<String>();
	
	/** If the object is a field, PLACEHOLDER for alternative name for the constructor - not implemented yet */
	private String alternateConstructorName;

	/** Nested ObjectInfo for the key, if object is a map entry */
	private ObjectInfo keyInfo;
	
	/** If the object is a field, does the containing class has a setter for it with standard naming */
	private boolean hasSetter = true;
	
	/** If the object is a field, the type of setter method to generate */
	private SetterGenerationType setterGenerationType = SetterGenerationType.DEFAULT;
	
	/** If the object is a field, PLACEHOLDER for alternative name for the setter method - not implemented yet */
	private String alternateSetterMethodName;
	
	/** List of ObjectInfo's for fields of the object, including elements of a collection, array or map */
	private List<ObjectInfo> fieldList = new ArrayList<ObjectInfo>();
	
	/** Link back to parent object for a field, or the container for a contained element */
	private ObjectInfo parentInfo;
	
	/** 
	 * Flag to indicate this object has already been logged. This would happen if this object was a field
	 * that was also configured as a constructor parameter. Once logged as a constructor parameter, this flag
	 * should be set in case we don't want it logged again as a field.
	 */
	private boolean alreadyLogged;
	
	/** If the object is a collection, are elements added to it through an adder method in it's enclosing
	 *  class, instead being added directly to the collection.
	 */
	private boolean usesAdder = false;

	/** If the object is a collection and elements are only to be added to it through an add method in it's
	 *  enclosing class, then this is the name of that adder method.
	 */
	private String adderMethodName;
	
	/** Store any errors from the recursive reflection process that sets the meta-data */
	private List<String> errors = new ArrayList<String>();

	// Utility methods
	// ***************
	
	public boolean isSimpleType()
	{
		return (getType() == ObjectType.SIMPLE);
	}

	public boolean isArrayType()
	{
		return (getType() == ObjectType.ARRAY);
	}

	public boolean isSetterIgnoreType()
	{
		return (getSetterGenerationType() == SetterGenerationType.IGNORE);
	}
	
	/**
	 * Find an ObjectInfo for a field with a particular field name.
	 * 
	 * @param fieldName
	 * @return
	 */
	public ObjectInfo getField(String fieldName)
	{
		ObjectInfo foundFieldInfo = null;
		
		for (ObjectInfo fieldInfo : fieldList)
		{
			if (fieldName.equals(fieldInfo.getFieldName()))
			{
				foundFieldInfo = fieldInfo;
				break;
			}
		}
		
		return foundFieldInfo;
	}
	
	/**
	 * Check if object should use parameterized constructor instead of default constructor.
	 * 
	 * @return
	 */
	public boolean useParameterizedConstructor()
	{
		return (constructorParamFieldNames != null);
	}
	
	// Accessors
	// *********
	public boolean isInitalObject() {
		return isInitalObject;
	}
	
	public void setInitalObject(boolean isInitalObject) {
		this.isInitalObject = isInitalObject;
	}
	
	public ObjectType getType() {
		return type;
	}

	public void setType(ObjectType type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	public String getClassName() {
		return className;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getClassFieldName() {
		return classFieldName;
	}
	
	public void setClassFieldName(String classFieldName) {
		this.classFieldName = classFieldName;
	}
	
	public int getClassFieldNameIndex() {
		return classFieldNameIndex;
	}
	
	public void setClassFieldNameIndex(int classFieldNameIndex) {
		this.classFieldNameIndex = classFieldNameIndex;
	}
	
	public String getContainingClassFieldName() {
		return containingClassFieldName;
	}
	
	public void setContainingClassFieldName(String containingClassFieldName) {
		this.containingClassFieldName = containingClassFieldName;
	}
	
	public ContainmentType getContainmentType() {
		return containmentType;
	}
	
	public void setContainmentType(ContainmentType containmentType) {
		this.containmentType = containmentType;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}

	public List<String> getConstructorParamFieldNames() {
		return constructorParamFieldNames;
	}

	public void setConstructorParamFieldNames(
			List<String> constructorParamFieldNames) {
		this.constructorParamFieldNames = constructorParamFieldNames;
	}

	public void addParamFieldName(String fieldName)
	{
		constructorParamFieldNames.add(fieldName);
	}

	public List<String> getConstructorParameters() {
		return constructorParameters;
	}

	public void setConstructorParameters(List<String> constructorParameters) {
		this.constructorParameters = constructorParameters;
	}

	public void addConstructorParameter(String param)
	{
		constructorParameters.add(param);
	}
	
	public String getAlternateConstructorName() {
		return alternateConstructorName;
	}

	public void setAlternateConstructorName(String alternateConstructorName) {
		this.alternateConstructorName = alternateConstructorName;
	}

	public ObjectInfo getKeyInfo() {
		return keyInfo;
	}
	
	public void setKeyInfo(ObjectInfo keyInfo) {
		this.keyInfo = keyInfo;
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

	public boolean hasDefaultConstructor() {
		return hasDefaultConstructor;
	}

	public void setHasDefaultConstructor(boolean hasDefaultConstructor) {
		this.hasDefaultConstructor = hasDefaultConstructor;
	}

	public List<ObjectInfo> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<ObjectInfo> fieldList) {
		this.fieldList = fieldList;
	}

	public void addFieldToList(ObjectInfo info) {
		fieldList.add(info);
	}

	public ObjectInfo getParentInfo() {
		return parentInfo;
	}

	public void setParentInfo(ObjectInfo parentInfo) {
		this.parentInfo = parentInfo;
	}

	public boolean isAlreadyLogged() {
		return alreadyLogged;
	}

	public void setAlreadyLogged(boolean alreadyLogged) {
		this.alreadyLogged = alreadyLogged;
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

	public List<String> getErrors() {
		return errors;
	}

	public void addError(String message) {
		errors.add(message);
	}
	
}
