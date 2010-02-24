package com.mysema.query.domain;

import java.util.Date;

import org.junit.Test;

import com.mysema.query.annotations.QueryEntity;
import com.mysema.query.annotations.QueryTransient;
import com.mysema.query.domain.rel.SimpleType;
import com.mysema.query.domain.rel.SimpleType2;

public class GenericTest {
    
    @QueryEntity
    public class GenericType<T extends ItemType> {
        T itemType;
    }
    
    @QueryEntity
    public class GenericType2<T extends ItemType> {
        T itemType;
        
        // simple
        @SuppressWarnings("unchecked")
        GenericSimpleType prop1;
        GenericSimpleType<?> prop2;
        GenericSimpleType<? extends GenericSimpleType<?>> prop3;
        
        // comparable
        GenericComparableType comp1;
        GenericComparableType<Number> comp2;
        GenericComparableType<Date> comp3;
        
        // number
        
        @QueryTransient
        GenericNumberType num1; // NOTE : doesn't work!
        
        GenericNumberType<Number> num2;
        GenericNumberType<Date> num3;
    }
    
    public class GenericSimpleType<T extends GenericSimpleType<T>>{
        
    }
    
    public class GenericComparableType<T> implements Comparable<GenericComparableType<T>>{
        @Override
        public int compareTo(GenericComparableType<T> o) {
            return 0;
        }       
        
        @Override
        public boolean equals(Object o){
            return o instanceof GenericComparableType;
        }
    }
    
    public class GenericNumberType<T> extends Number implements Comparable<GenericNumberType<T>>{
        @Override
        public double doubleValue() {
            return 0;
        }
        @Override
        public float floatValue() {
            return 0;
        }
        @Override
        public int intValue() {
            return 0;
        }
        @Override
        public long longValue() {
            return 0;
        }
        @Override
        public int compareTo(GenericNumberType<T> o) {
            return 0;
        }        
        
        @Override
        public int hashCode(){
            return super.hashCode();
        }
        
        @Override
        public boolean equals(Object o){
            return o instanceof GenericNumberType;
        }
    }
    
    
    @QueryEntity
    public class ItemType {    
        Amount<SimpleType> prop;    
        SimpleType2<Amount<SimpleType>> prop2;
        @SuppressWarnings("unchecked")
        SimpleType2<Amount> prop3;
        SimpleType2<?> prop4;
    }
    
    public class Amount<T>{
        
    }

    @Test
    public void test(){
        // TODO
    }
}
