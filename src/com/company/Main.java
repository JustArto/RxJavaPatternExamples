package com.company;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Object o1 = String.valueOf(1);
        Object o2 = 123 + "T";
        System.out.println(o1.getClass().equals(o2.getClass()));
        //o1.getClass() == o2.getClass();

        Class s = int.class;
        String name = s.getName();
        System.out.println(name);

        Class clazz = "123".getClass();
        System.out.println(clazz);

        Class[] interfaces = List.class.getInterfaces();
        System.out.println(interfaces.getClass());

        Method[] methods = List.class.getMethods();
        System.out.println(Arrays.stream(methods).toList());
    }
}
