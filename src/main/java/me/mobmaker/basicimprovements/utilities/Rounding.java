package me.mobmaker.basicimprovements.utilities;

public class Rounding {

    public static Pair<Double, String> timeRounding(long value) {
        return timeRounding((double) value);
    }

    public static Pair<Double, String> timeRounding(double value) {
        double valueSec;
        double valueMin;
        double valueHr;
        String unit = "seconds";
        double valueReturn = value;
        valueSec = round(value / 20.0, 0);
        if (valueSec >= 60) {
            valueMin = round(valueSec / 60, 1);
            System.out.println(valueMin);
            unit = "minutes";
            if (valueMin >= 120) {
                valueHr = round(valueMin / 60, 1);
                System.out.println(valueHr);
                unit = "hours";
                if (valueHr >= 120) {
                    valueReturn = round(valueHr / 24, 2);
                    System.out.println(valueReturn);
                    unit = "days";
                } else {
                    valueReturn = valueHr;
                }
            } else {
                valueReturn = valueMin;
            }
        }
        return new Pair<>(valueReturn, unit);
    }

    public static double round (double value, double precision) {
        double scale = Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

}
