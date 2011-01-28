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
package au.com.dw.testdatacapturej.reflection.util;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Map;

/**
 * Tests for logging generics.
 * 
 * This class declaration defines a type variable T.
 * 
 */
public class GenericsTest<T extends Thread> {

   // This method is used in the reflection example below
   public void aMethod(Class<? extends T> clazz1, 
                       Class<T> clazz2, T[] ts) {
   }

   // Prints information about a type variable

   private static void print(TypeVariable v) {
      System.out.println("Type variable");
      System.out.println("Name: " + v.getName());
      System.out.println("Declaration: " + 
                         v.getGenericDeclaration());
      System.out.println("Bounds:");
      for (Type t : v.getBounds()) {
         print(t);
      }
   }
   // Prints information about a wildcard type
   private static void print(WildcardType wt) {
      System.out.println("Wildcard type");
      System.out.println("Lower bounds:");
      for (Type b : wt.getLowerBounds()) {
         print(b);
      }

      System.out.println("Upper bounds:");
      for (Type b : wt.getUpperBounds()) {
         print(b);
      }
   }

   // Prints information about a parameterized type
   private static void print(ParameterizedType pt) {
      System.out.println("Parameterized type");
      System.out.println("Owner: " + pt.getOwnerType());
      System.out.println("Raw type: " + pt.getRawType());
      
      for (Type actualType : pt.getActualTypeArguments()) {
         print(actualType);
      }
   }

   // Prints information about a generic array type
   private static void print(GenericArrayType gat) {
      System.out.println("Generic array type");
      System.out.println("Type of array: ");
      print(gat.getGenericComponentType());
   }

   /**
    * Prints information about a type. The nested 
    * if/else-if chain calls the
    * appropriate overloaded print method for the 
    * type. If t is just a Class,
    * we print it directly.
    */

   private static void print(Type t) {
      if (t instanceof TypeVariable) {
         print((TypeVariable)t);
      } else if (t instanceof WildcardType) {
         print((WildcardType)t);
      } else if (t instanceof ParameterizedType) {
         print((ParameterizedType)t);
      } else if (t instanceof GenericArrayType) {
         print((GenericArrayType)t);
      } else {
         System.out.println(t);
      }
   }

   public static void main(String[] args) throws Exception {
      // Some classes we are going to play with
      Class[] classes = new Class[] {Class.class, Map.class,
                                     GenericsTest.class};

      // Iterate the array for each class instance...
      for (Class clazz : classes) {
         // Prints its name and ...
         System.out.println("Class: " + clazz);

         // Iterate for each type variable defined by this class
         for (TypeVariable v : clazz.getTypeParameters()) {
            print(v);
         }

         System.out.println();
      }
      System.out.println("Reflective information " + 
                         "about the parameters of aMethod");
      // Iterate for each method...
      for (Method method : GenericsTest.class.getDeclaredMethods()) {
         // Until we find aMethod
         if (method.getName().equals("aMethod")) {
            // Then, go over all parameters ...
            for (Type t : method.getGenericParameterTypes()) {
               System.out.print("Parameter: ");
               // And print reflexive information about them
               print(t);
               System.out.println();
            }
            break;
         }
      }
   }
}