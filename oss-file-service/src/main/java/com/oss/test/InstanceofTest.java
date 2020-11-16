package com.oss.test;

public class InstanceofTest {
    public static void main(String[] args) {
        testObject();
//        testStringComputer();
    }

    private static void testObject() {
        Object object = 12;

        System.out.println(object instanceof Integer);
        System.out.println(object instanceof String);
        System.out.println(object instanceof Double);
    }

    private static void testStringComputer() {
        String string1 = "1'36\"";
        String string2 = "4'22\"";
        System.out.println(string1.compareTo(string2));
    }
}
