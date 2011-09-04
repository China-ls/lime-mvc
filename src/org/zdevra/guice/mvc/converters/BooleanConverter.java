/*****************************************************************************
 * Copyright 2011 Zdenko Vrabel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 *****************************************************************************/
package org.zdevra.guice.mvc.converters;

import java.lang.annotation.Annotation;

import org.zdevra.guice.mvc.ConversionService.Converter;
import org.zdevra.guice.mvc.ConversionService.ConverterFactory;
import org.zdevra.guice.mvc.Utils;

/**
 * This convertor is responsible for converting from strings
 * to boolean value. 
 * 
 * The parameter may be annotated by BooleanParam annotation which
 * carying metadata information about string form for true and false.
 * 
 * @see BooleanConv 
 *
 */
public final class BooleanConverter extends TypeConverter<Boolean> {
	
/*---------------------------- m. variables ----------------------------*/
	
	private final BooleanConv boolAnnotation;
	
/*---------------------------- constructors ----------------------------*/
	
	/**
	 * Factory for {@link BooleanConverter}
	 */
	public static class Factory implements ConverterFactory {
		
		@Override
		public Converter<?> createConvertor(Class<?> type, Annotation[] annotations) {
			if ((type != boolean.class) && (type != Boolean.class)) {
				return null;
			}
			
			BooleanConv ba = Utils.getAnnotation(BooleanConv.class, annotations); 
			return new BooleanConverter(ba);
		}		
	}

// ------------------------------------------------------------------------
	
	/**
	 * Constructor
	 */
	private BooleanConverter(BooleanConv boolAnnotation) {
		this.boolAnnotation = boolAnnotation;
	}
	
// ------------------------------------------------------------------------
	
	@Override
	protected Boolean convertType(String stringValue) {
		if (boolAnnotation != null) {
			if ( boolAnnotation.trueVal().equalsIgnoreCase(stringValue) ) {
				return true;
			} else if ( boolAnnotation.falseVal().equalsIgnoreCase(stringValue) ) {
				return false;
			} else {
				return false;
			}
		}
		return Boolean.parseBoolean(stringValue);
	}
		
// ------------------------------------------------------------------------
		
}
