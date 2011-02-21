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
 * setterAdderInfo.hasSetter = false, since is not a field
 * constructorInfo.hasDefaultConstructor = true, since does have InitialObject() constructor
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
 * setterAdderInfo.hasSetter = true, since InitialObject has setFieldName()
 * constructorInfo.hasDefaultConstructor = false, since does have FieldObject() constructor - constructor has arguments
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
 * setterAdderInfo.hasSetter = false, since InitialObject doesn't have a setPrimitiveName() method
 * constructorInfo.hasDefaultConstructor = false, since constructor no required for primitive values
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
 * 
 * The info for handling of generating constructor lines is in a ConstructorInfo class.
 * The alreadyLogged flag is also used when dealing with constructors.
 * 
 * @see au.com.dw.testdatacapturej.meta.ConstructorInfo
 * 
 * ***************************************************
 * Adder method fields
 * ***************************************************
 * The info for handling of generating adder method lines is in a SetterAdderInfo class.
 * 
 * @see au.com.dw.testdatacapturej.meta.SetterAdderInfo
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
	
	/** Info for handling generation of constructor line, should never be null */
	private ConstructorInfo constructorInfo = new ConstructorInfo(true,
			new ArrayList<String>(), new ArrayList<String>());

	/** Nested ObjectInfo for the key, if object is a map entry */
	private ObjectInfo keyInfo;
	
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
	
	/** Info for handling setter and adder methods, should never be null */
	private SetterAdderInfo setterAdderInfo = new SetterAdderInfo(true,
			SetterGenerationType.DEFAULT, false);

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
		return (getSetterAdderInfo().getSetterGenerationType() == SetterGenerationType.IGNORE);
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
		return (constructorInfo.getConstructorParamFieldNames() != null);
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
	
	public ConstructorInfo getConstructorInfo() {
		return constructorInfo;
	}

	public ObjectInfo getKeyInfo() {
		return keyInfo;
	}
	
	public void setKeyInfo(ObjectInfo keyInfo) {
		this.keyInfo = keyInfo;
	}
	
	public SetterAdderInfo getSetterAdderInfo() {
		return setterAdderInfo;
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
	
	public List<String> getErrors() {
		return errors;
	}

	public void addError(String message) {
		errors.add(message);
	}
	
}
