package ru.appline.logic;

import java.util.HashMap;
import java.util.Map;

public class Calculator {
    Map<String, Double> m = new HashMap<>();

    public Calculator(double a, double b, String math) {
        double result = 0;

        switch(math) {
            case ("+"):
                result = a + b;
                break;
            case ("-"):
                result = a - b;
                break;
            case ("*"):
                result = a * b;
                break;
            case ("/"):
                if (b == 0) break;
                result = a / b;
                break;
        }
        m.put("result", result);
    }

    public Map<String, Double> getM() {
        return m;
    }
}
