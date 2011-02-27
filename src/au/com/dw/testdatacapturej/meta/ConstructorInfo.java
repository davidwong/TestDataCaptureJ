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

import java.util.Collections;
import java.util.List;

/**
 * Holder for metadata related to generating constructor methods. This is meant to be used as part
 * of the metadata contained in ObjectInfo, but holding the data only related to generating
 * constructor lines.
 * 
 * Where a parameterized constructor has been configured for this object, the following fields are used.
 * 1. constructorParamFieldNames
 * This is the list of field names for the parameters, assuming that the constructor is setting fields rather
 * than setting fields latter with setters.
 * 2. constructorParameters
 * When the field object (not this object) is generated as a value or class field name in the parameter logging, it is added to
 * this list in the parent ObjectInfo (parentInfo)
 * 3. alreadyLogged (field in ObjectInfo, not in ConstructorInfo)
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
 * 
 * @see au.com.dw.testdatacapturej.meta.ObjectInfo
 * 
 * @author David Wong
 *
 */
public class ConstructorInfo {
	
	/** Does the object have a default no argument constructor */
	private boolean hasDefaultConstructor;
	
	/** List of field names to use as constructor parameters when generating a constructor line */
	private List<String> constructorParamFieldNames;
	
	/** 
	 * List of constructor parameter values, these can be field names or formatted simple values. These
	 * are to be used to generate a constructor line with parameters.
	 */
	private List<String> constructorParameters;
	
	/**
	 * This is a placeholder for the types of the parameters that would be passed to a parameterized
	 * constructor line. The use for this would be to check that the parameterized constructor for
	 * the constructor line actually exists in the class, and would be passed to
	 * ReflectionUtil.hasParameterizedConstructor().
	 * 
	 * This check is currently not implemented since there may be many permutations to check since for
	 * each parameter, not only would the class type of the parameter have to be checked, but also
	 * every superclass of the parameter and every interface that the parameter implements. The specific
	 * type for the parameter could be specified in the XML configuration, but since the configuration
	 * is manually done by the user anyway, might as well leave it to the user to check that the
	 * constructor to be configured does exist.
	 * 
	 * @see au.com.dw.testdatacapturej.reflection.util.ReflectionUtil
	 */
	private List<Class<?>> constructorParameterTypes;
	
	/** If the object is a field, PLACEHOLDER for alternative name for the constructor - not implemented yet */
	private String alternateConstructorName;

	public ConstructorInfo(boolean hasDefaultConstructor,
			List<String> constructorParamFieldNames,
			List<String> constructorParameters,
			List<Class<?>> constructorParameterTypes) {
		this.hasDefaultConstructor = hasDefaultConstructor;
		this.constructorParamFieldNames = constructorParamFieldNames;
		this.constructorParameters = constructorParameters;
		this.constructorParameterTypes = constructorParameterTypes;
	}

	public boolean hasDefaultConstructor() {
		return hasDefaultConstructor;
	}

	public void setHasDefaultConstructor(boolean hasDefaultConstructor) {
		this.hasDefaultConstructor = hasDefaultConstructor;
	}

	public List<String> getConstructorParamFieldNames() {
		return Collections.unmodifiableList(constructorParamFieldNames);
	}

	public void addConstructorParamFieldName(String constructorParamFieldName) {
		this.constructorParamFieldNames.add(constructorParamFieldName);
	}

	public void addConstructorParamFieldNames(List<String> constructorParamFieldNames) {
		this.constructorParamFieldNames.addAll(constructorParamFieldNames);
	}

	public List<String> getConstructorParameters() {
		return Collections.unmodifiableList(constructorParameters);
	}

	public void addConstructorParameter(String constructorParameter) {
		this.constructorParameters.add(constructorParameter);
	}

	public List<Class<?>> getConstructorParameterTypes() {
		return constructorParameterTypes;
	}

	public void addConstructorParameterType(Class<?> constructorParameterType) {
		constructorParameterTypes.add(constructorParameterType);
	}

	public String getAlternateConstructorName() {
		return alternateConstructorName;
	}

	public void setAlternateConstructorName(String alternateConstructorName) {
		this.alternateConstructorName = alternateConstructorName;
	}
}