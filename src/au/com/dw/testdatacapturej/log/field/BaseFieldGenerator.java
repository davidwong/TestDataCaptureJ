/*******************************************************************************
 * Copyright () 2009, 2011, 2013 David Wong
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
package au.com.dw.testdatacapturej.log.field;

import java.util.List;

import au.com.dw.testdatacapturej.builder.LineBuilder;
import au.com.dw.testdatacapturej.log.FieldGenerator;
import au.com.dw.testdatacapturej.log.FormatConstants;
import au.com.dw.testdatacapturej.log.LogBuilder;
import au.com.dw.testdatacapturej.log.constructor.ArrayConstructorGenerator;
import au.com.dw.testdatacapturej.log.constructor.ConstructorGenerator;
import au.com.dw.testdatacapturej.log.constructor.DefaultConstructorGenerator;
import au.com.dw.testdatacapturej.log.constructor.ParameterizedConstructorGenerator;
import au.com.dw.testdatacapturej.meta.ObjectInfo;
import au.com.dw.testdatacapturej.meta.SetterGenerationType;
import au.com.dw.testdatacapturej.util.Messages;


/**
 * Base class for FieldDisplay implementations, needs to be subclassed for the various field types.
 * Just need to override the abstract preLog or log() methods.
 * 
 * Also acts as an adaptor to provide default or do-nothing implementions of the logging methods.
 * 
 * @author David Wong
 *
 */
public class BaseFieldGenerator implements FieldGenerator {

	private LineBuilder lineBuilder = new LineBuilder();

	public void preLog(LogBuilder builder, ObjectInfo info) {
		if (!info.isSimpleType())
		{
			// check if configured to ignore a field for setter method generation
			if (info.getSetterAdderInfo().getSetterGenerationType() != SetterGenerationType.IGNORE)
			{
				generateConstructor(builder, info);
			}
		}
	}

	public void log(LogBuilder builder, ObjectInfo info)
	{
		// default do-nothing log
	}

	/**
	 * Generate a constructor line, delegates the actual generation to an implemention of ConstructorGenerator.
	 * 
	 * @param builder
	 * @param info
	 * 
	 * @see au.com.dw.testdatacapturej.log.constructor.ConstructorGenerator
	 */
	protected void generateConstructor(LogBuilder builder, ObjectInfo info) {
		ConstructorGenerator gen = null;
		boolean doNoDefaultConstructorComment = false;
		
		// find the appropriate ConstructorGenerator implementation
		if (info.isArrayType())
		{
			gen = new ArrayConstructorGenerator();
		}
		else
		{
			List<String> constructorParams = info.getConstructorInfo().getConstructorParameters();
			
			if (!constructorParams.isEmpty())
			{
				// generate constructor with parameters
				gen = new ParameterizedConstructorGenerator();
				
				// There is currently no class checking that the parameterized constructor for the
				// constructor line actually exists.
			}
			else
			{
				// generate default constructor
				gen = new DefaultConstructorGenerator();
				
				// if the class doesn't have a default constructor, add comment
				if (!info.getConstructorInfo().hasDefaultConstructor())
				{
					doNoDefaultConstructorComment = true;
					
					info.addError(Messages.getMessage(Messages.DEFAULT_CONSTRUCTOR_NOT_EXIST, info.getClassName()));
				}
			}
		}
		
		// generate the appropriate constructor line
		gen.generateConstructor(builder, info);
		
		// log a comment if a default constructor was generated, but the class does not have a default constructor
		if (doNoDefaultConstructorComment)
		{
			gen.addConstructorComment(builder, info);
		}
	}

	/**
	 * Generate a setter line.
	 * 
	 * @param builder
	 * @param info
	 */
	protected void generateSetter(LogBuilder builder, ObjectInfo info) {
		if (!info.isInitalObject())
		{
			boolean literal = !info.isSimpleType();
			
			builder.append(FormatConstants.newLine);
			builder.append(info.getContainingClassFieldName());
			getLineBuilder().createSetterLine(builder, info.getFieldName(), info.getFullFieldName(), literal);
			builder.append(FormatConstants.newLine);
		}
	}
	
	// Accessors
	// *********
	
	public LineBuilder getLineBuilder() {
		return lineBuilder;
	}
}

