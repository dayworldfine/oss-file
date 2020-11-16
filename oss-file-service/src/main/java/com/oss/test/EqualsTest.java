package com.oss.test;

public class EqualsTest {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
    }

    public static void test1(){
        Boolean bool =false;
            int a = 11;
            Integer aa = 11;
        System.out.println("int equals Integer:" +aa.equals(a));
    }

    public static void test2(){
        Boolean bool =false;
        int a = 11;
        Integer aa = 11;
        if (a==aa){
            bool = true;
        }else {
            bool=false;
        }
        System.out.println("int == Integer:"+ bool);
    }

    public static void test3(){
        Boolean bool =false;
        int a = 11;
        Long aa = 11l;
        System.out.println("int equals smallLong:"+ aa.equals(a));
    }

    public static void test4(){
        Boolean bool =false;
        int a = 11;
        Long aa = 11l;
        if (a==aa){
            bool = true;
        }else {
            bool=false;
        }
        System.out.println("int == smallLong:"+bool);
    }

    public static void test5(){
        Boolean bool =false;
        Long a = 11111111111111l;
        Long aa = 11111111111111l;
        System.out.println("Long equals Long:"+ aa.equals(a));
    }

    public static void test6(){
        Boolean bool =false;
        Long a = 11111111111111l;
        Long aa = 11111111111111l;
        if (a==aa){
            bool = true;
        }else {
            bool=false;
        }
        System.out.println("Long == Long:"+bool);
    }
}
