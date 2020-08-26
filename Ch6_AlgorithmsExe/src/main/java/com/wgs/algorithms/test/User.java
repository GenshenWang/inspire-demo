package com.wgs.algorithms.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class User {

    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

        /*private static Map<Character, Character> pairs = new HashMap<>();

    static {
        pairs.put('}', '{');
        pairs.put(')', '(');
        pairs.put(']', '[');
    }*/

    private static Map<Character, Character> pairs = new HashMap<>();
    static {
        pairs.put('{', '}');
        pairs.put('(', ')');
        pairs.put('[', ']');
    }

    private static char[] left = {'{', '(', '['};
    private static char[] right = {'}', ')', ']'};


    private boolean isLeft(char c) {
        for (int i = 0; i < left.length; i++) {
            if (left[i] == c) {
                return true;
            }
        }
        return false;
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if (isLeft(temp)) {
                stack.push(temp);
            } else {
                char peek = stack.peek();
                if (!pairs.containsKey(peek) || pairs.get(peek) != temp) {
                    return false;
                } else {
                    stack.pop();
                }
            }

        }

        return stack.isEmpty();

       /* if (s.length() % 2 != 0) {
            return false;
        }

        Stack<Character> stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if (!pairs.containsKey(temp)) {
                stack.push(temp);
            } else {
                if (stack.isEmpty() || pairs.get(temp) != stack.peek()) {
                    return false;
                }
                stack.pop();
            }
        }

        return stack.isEmpty();*/

    }

    public User() {
    }

    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("value1");

        System.out.println(threadLocal.get());

        Runnable runnable = ()-> {
            System.out.println("线程2" + threadLocal.get());
        };

        new Thread(runnable).start();

    }
}
