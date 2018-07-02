package com.code.analyze.demo;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Ethan Zhang
 * @email ethan.zj@antfin.com
 */
@Resource
public class TestCase {

    static {
        System.out.println("static construct");
    }

    {
        System.out.println("obj construct");
    }

    @Resource
    private Object obj;

    private Map<String, Object> hello(String name) {

        hello1();

        return null;
    }

    private void hello1() {hello2();hello3();}
    private void hello2() {hello4();}
    private void hello3() {hello5();}
    private void hello4() {hello6();}
    private void hello5() {hello7();}
    private void hello6() {
        System.out.println();
    }
    private void hello7() {hello5();}

    public static void main(String[] args) {
        new TestCase().hello("");
    }
}
