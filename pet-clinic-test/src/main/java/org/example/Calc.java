package org.example;

public class Calc {
    public String calculate(String[] args) throws NumberFormatException {
        String format = "usage: arg1 + arg2";
        if (args.length != 3) {
            return format;
        }
        int a;
        try {
            a = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            return format;
        }
        int b;
        try {
            b = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            return format;
        }
        if (args[1].equals("+")) {
            return String.format("%d", a + b);
        } else {
            return format;
        }

    }
}
