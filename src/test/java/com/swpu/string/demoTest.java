package com.swpu.string;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Stack;

public class demoTest {
    @Test
    public void test() {
        System.out.println(ReplaceTest.replaceSpace(new StringBuffer("asd afsd af af")));
    }

    @Test
    public void continueTest(){
        for (int i = 0; i < 10; i++) {
            if(i == 5) continue;
            System.out.println(i);
        }
    }
    @Test
    public void stackTest(){
        Stack stack = new Stack();
        LinkedList list = new LinkedList();
    }

    @Test
    public void randomTest(){
        int  num = (int) (Math.random() * 6 + 1);
        System.out.println(num);
    }
}
