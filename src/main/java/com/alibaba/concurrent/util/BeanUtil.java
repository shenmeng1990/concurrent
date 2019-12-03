/*
package com.alibaba.concurrent.util;

import java.beans.BeanInfo;
import java.beans.FeatureDescriptor;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.TypeVariable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


*/
/**
 * @Author shenmeng
 * @Date 2019/12/3
 **//*


public class BeanUtil {
    private static final Map<Class<?>,Class<?>> primitiveTypeToWrapperMap = new IdentityHashMap<>(8);
    private static final Map<Class<?>,Class<?>> primitiveWrapperTypeMap = new IdentityHashMap<>(8);


    public BeanUtil() {
    }

    private static void copyProperties(Object source,Object target,Map<String,Class> classMap,Class<?> editable,String...ignoreProperties) throws Exception{
        Class<?> actualEditable = target.getClass();
        if(editable!=null){
            if(!editable.isInstance(target)){
                throw new  RuntimeException("无法拷贝类"+editable.getName()+":到对象"+target.getClass().getName());
            }
            actualEditable=editable;
        }
        BeanInfo targetBI = Introspector.getBeanInfo(actualEditable);
        PropertyDescriptor[] targetPds=targetBI.getPropertyDescriptors();
        BeanInfo sourceBI=Introspector.getBeanInfo(source.getClass());
        Map<String,PropertyDescriptor> sourceMap=(Map) Arrays.asList(sourceBI.getPropertyDescriptors()).stream().collect(Collectors.toMap(FeatureDescriptor::getName,(p)->{
            return p;
        },(k1,k2)->{
            return k1;
        }));
        List<String> ignoreList=ignoreProperties!=null?Arrays.asList(ignoreProperties):null;
        PropertyDescriptor [] var11=targetPds;
        int var12=targetPds.length;
        for(int var13=0;var13<var12;++var13){
            PropertyDescriptor targetPd=var11[var13];
            Method writeMethod = targetPd.getWriteMethod();
            if(writeMethod!=null &&(ignoreList==null || ignoreList.contains(targetPd.getName()))){
                PropertyDescriptor sourcePd=(PropertyDescriptor)sourceMap.get(targetPd.getDisplayName());
                if(sourcePd!=null){
                    Method sourceRM = sourcePd.getReadMethod();
                    if(sourceRM!=null && isAssignable(writeMethod.getParameterTypes()[0],sourceRM.getReturnType())){
                        Object value = copyUtil(source,sourcePd,writeMethod,sourceRM,classMap);
                        writeMethod.invoke(target,value);
                    }
                }
            }
        }
    }

    private static Object copyUtil(Object source,PropertyDescriptor sourcePd,Method targetWM,Method sourceRM,Map<String,Class> classMap) throws Exception{
        if(!Modifier.isPublic(sourceRM.getDeclaringClass().getModifiers())){
            sourceRM.setAccessible(true);
        }
        Object value = sourceRM.invoke(source);
        if(!Modifier.isPublic(targetWM.getDeclaringClass().getModifiers())){
            targetWM.setAccessible(true);
        }
        if(value!=null && value instanceof Collection && classMap!=null && classMap.get(sourcePd.getDisplayName())!=null)){
            Class targetClass = (Class)classMap.get(sourcePd.getName());
            value=copyList((List)value,targetClass,classMap);
        }
        return value;
    }

    private static Object copyList(List lhsList, Class rhsListObject, Map<String, Class> classMap) throws Exception{
        List<Object> rhsList = new ArrayList<>();
        Constructor[] constructors = rhsListObject.getConstructors();
        Iterator iter = lhsList.iterator();
        while(iter.hasNext()){
            Object source = iter.next();
            if(constructors.length!=0){
                TypeVariable[] typeVariables = constructors[0].getTypeParameters();
                if(typeVariables.length==0){
                    Object targetObj = constructors[0].newInstance();
                    copyProperties(source,targetObj,classMap,rhsListObject);
                }
            }else{
                rhsList.add(dateLocalConvert(source,rhsListObject));
            }
        }
        return rhsList;
    }

    private static Object dateLocalConvert(Object value, Class tClass) {
        if(value==null){
            return value;
        } else if(value.getClass()==tClass){
            return value;
        } else if(value.getClass() == Date.class && tClass == LocalDate.class){
            return DateTimeUtil.date2LocalDate((Date)value);
        } else if(value.getClass() == Date.class && tClass == LocalDateTime.class){
            return DateTimeUtil.date2LocalDateTime((Date)value);
        } else if(value.getClass()==LocalDateTime.class && tClass==Date.class){
            return DateTimeUtil.localDateTime2Date((LocalDateTime)value);
        }else{
            return value.getClass()==LocalDate.class && tClass==Date.class?DateTimeUtil.localDate2Date((LocalDate)value):value;
        }
    }


}
*/
