package com.geekbrains;

import org.junit.jupiter.api.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class ClassTest {


    @MyAnnotation(priority = 10)
    @Test
    void test1() {
        System.out.println("test1");
    }

    @MyAnnotation(priority = 1)
    @Test
    void test2() {
        System.out.println("test2");
    }

    @AfterSuite
    void lastTest() {
        System.out.println("lastTest");
    }

    @BeforeSuite
    void test0() {
        System.out.println("first test");
    }


    @MyAnnotation(priority = 7)
    @Test
    void test3() {
        System.out.println("test3");
    }


}

