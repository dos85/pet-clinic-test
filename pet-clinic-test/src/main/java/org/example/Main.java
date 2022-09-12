package org.example;

public class Main {
    public static void main(String[] args) {
        Calc calc = new Calc();
        String result = calc.calculate(args);
        System.out.println(result);
    }
}