/*
 * Copyright (c) 2009 Mysema Ltd.
 * All rights reserved.
 * 
 */
package com.mysema.query.types.custom;

import java.util.Arrays;
import java.util.List;

import com.mysema.query.types.Template;
import com.mysema.query.types.TemplateFactory;
import com.mysema.query.types.Visitor;
import com.mysema.query.types.expr.Expr;

/**
 * CSimple defines custom simple expressions
 * 
 * @author tiwe
 *
 * @param <T>
 */
@SuppressWarnings("serial")
public class CSimple<T> extends Expr<T> implements Custom<T> {
    
    public static <T> Expr<T> create(Class<? extends T> type, String template, Expr<?>... args){
        return new CSimple<T>(type, TemplateFactory.DEFAULT.create(template), Arrays.<Expr<?>>asList(args));
    }
    
    public static <T> Expr<T> create(Class<? extends T> type, Template template, Expr<?>... args){
        return new CSimple<T>(type, template, Arrays.<Expr<?>>asList(args));
    }
    
    private final Custom<T> customMixin;
    
    public CSimple(Class<? extends T> type, Template template, List<Expr<?>> args) {
        super(type);
        customMixin = new CustomMixin<T>(this, args, template);
    }

    @Override
    public void accept(Visitor v){
        v.visit(this);
    }
    
    @Override
    public Expr<?> getArg(int index) {
        return customMixin.getArg(index);
    }

    @Override
    public List<Expr<?>> getArgs() {
        return customMixin.getArgs();
    }

    @Override
    public Template getTemplate() {
        return customMixin.getTemplate();
    }
    
    @Override
    public boolean equals(Object o){
        return customMixin.equals(o);
    }
    
    @Override
    public int hashCode(){
        return getType().hashCode();
    }
}