package com.geekbrains;

import org.junit.jupiter.api.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        start(ClassTest.class);
    }

    private static void start(Class testClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor constructor = testClass.getConstructor();
        Object instance = constructor.newInstance();

        Method[] methods = testClass.getDeclaredMethods();

        int beforeSuiteCount = 0;
        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                method.invoke(instance);
                beforeSuiteCount++;
            }
        }
        if (beforeSuiteCount > 1) {
            throw new RuntimeException("много BeforeSuite");
        }

        List<Method> testMethods = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
            }
        }

        testMethods.sort(Comparator.comparingInt(o -> o.getAnnotation(MyAnnotation.class).priority()));
        for (Method testMethod : testMethods) {
            testMethod.invoke(instance);
        }

        int afterSuiteCount = 0;
        for (Method method : methods) {
            if (method.isAnnotationPresent(AfterSuite.class)) {
                method.invoke(instance);
                afterSuiteCount++;
            }
        }
        if (afterSuiteCount > 1) {
            throw new RuntimeException("Много AfterSuite");
        }
    }
}
