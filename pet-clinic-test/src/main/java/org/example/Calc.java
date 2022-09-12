package org.example;

public class Calc {
    public String calculate(String args[]) {
        if (args.length != 3) {
            return "usage: arg1 + arg2";
        }
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[2]);
        if (args[1].equals("+")) {
            return String.format("%d", a + b);
        } else {
            return "usage: arg1 + arg2";
        }

    }
}
