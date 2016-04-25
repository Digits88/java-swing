package chapter6;

import java.lang.reflect.*;
import java.util.*;

public class Variable {

    private Class<?> type;
    private String name;
    private Object value;
    private ArrayList<Field> fields;
    
    public Variable(Class<?> aType, String aName, Object aValue) {
        type = aType;
        name = aName;
        value = aValue;
        fields = new ArrayList<>();
        
        if (!type.isPrimitive() && !type.isArray() && !type.equals(String.class) && value != null) {
            for (Class<?> c = value.getClass(); c != null; c = c.getSuperclass()) {
                Field[] fs = c.getDeclaredFields();
                AccessibleObject.setAccessible(fs, true);
                for (Field f : fs) {
                    if ((f.getModifiers() & Modifier.STATIC) == 0) fields.add(f);
                }
            }
        }
    }
    
    public Object getValue() {
        return value;
    }
    
    public ArrayList<Field> getFields() {
        return fields;
    }
    
    public String toString() {
        String r = type + " " + name;
        if (type.isPrimitive()) r += "=" + value;
        else if (type.equals(String.class)) r += "=" + value;
        else if (value == null) r += "=null";
        return r;
    }
}