package model;

public class EquationModel {
    private String[] stack;
    private int initPosition;

    public EquationModel(String line) {
        this.stack = line.split(" ");
        this.initPosition = 0;
    }

    public double evaluateEquation() {
        double a = getPriority();
        while (initPosition < stack.length) {
            String operator = stack[initPosition];
            if (!operator.equals("+") && !operator.equals("-")) {
                break;
            } else {
                initPosition++;
            }
            double b = getPriority();
            if (operator.equals("+")) {
                a += b;
            } else {
                a -= b;
            }
        }
        return a;
    }

    private double getPriority() {
        double first = getOperator();
        while (initPosition < stack.length) {
            String operator = stack[initPosition];
            if (!operator.equals("*") && !operator.equals("/") && !operator.equals("//") && !operator.equals("^") && !operator.equals("**")) {
                break;
            } else {
                initPosition++;
            }
            double second = getOperator();
            if (operator.equals("*")) {
                first *= second;
            } else if (operator.equals("/")) {
                first /= second;
            } else if (operator.equals("//")) {
                first %= second;
            } else if (operator.equals("^") || operator.equals("**")) {
                first = Math.pow(first, second);
            }
        }
        return first;
    }
    private double getOperator() {
        String next = stack[initPosition];
        if (next.equals("(")) {
            initPosition++;
            double result = evaluateEquation();
            String closingBracket;
            if (initPosition < stack.length) {
                closingBracket = stack[initPosition];
            } else {
                throw new IllegalArgumentException("");
            }
            if (closingBracket.equals(")")) {
                initPosition++;
                return result;
            }
            throw new IllegalArgumentException("");
        } else if (next.equals("log")) {
            initPosition++;
            double argument = getOperator();
            return Math.log(argument)/Math.log(2);
        } else if (next.equals("exp")) {
            initPosition++;
            double argument = getOperator();
            return Math.exp(argument);
        } else if (next.equals("!")) {
            initPosition++;
            double argument = getOperator();
            return isFactorial((int) argument);
        } else {
            initPosition++;
            return Double.parseDouble(next);
        }
    }
    private double isFactorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("numb < 0");
        }
        double result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}