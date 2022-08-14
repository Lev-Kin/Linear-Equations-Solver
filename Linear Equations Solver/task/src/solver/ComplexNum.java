package solver;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

class ComplexNum {
    private static final double EPSILON = 0.001;
    private final double real;
    private final double imagine;

    ComplexNum(double real, double imagine) {
        this.real = real;
        this.imagine = imagine;
    }

    ComplexNum(String num) {
        String[] toParse = getTwoParts(num);

        if ("+".equals(toParse[1])) {
            toParse[1] = "1";
        }

        if ("-".equals(toParse[1])) {
            toParse[1] = "-1";
        }

        real = Double.parseDouble(toParse[0]);
        imagine = Double.parseDouble(toParse[1]);
    }

    private String[] getTwoParts(String num) {
        StringBuilder re = new StringBuilder();
        String im = "0";
        int length = num.length();

        if (length == 1) {
            if ("i".equals(num)) {
                return new String[]{"0", "1"};
            }

            return new String[]{num, "0"};
        }

        for (int i = 1; i < length; i++) {
            if (num.charAt(i) == '+' || num.charAt(i) == '-') {
                re.append(num, 0, i);
                im = num.substring(i, length - 1).replace("i", "");
                break;

            } else if (i == length - 1) {
                if (num.contains("i")) {
                    re.append("0");
                    im = num.substring(0, length - 1).replace("i", "");
                } else {
                    re.append(num);
                    im = "0";
                }
            }
        }

        return new String[]{re.toString(), im};
    }

    static ComplexNum sum(ComplexNum a, ComplexNum bi) {
        return new ComplexNum(a.real + bi.real, a.imagine + bi.imagine);
    }

    static ComplexNum multiply(ComplexNum a, ComplexNum bi) {
        return new ComplexNum(a.real * bi.real - a.imagine * bi.imagine,
                a.real * bi.imagine + a.imagine * bi.real);
    }

    static ComplexNum subtract(ComplexNum first, ComplexNum second) {
        return new ComplexNum(first.real - second.real, first.imagine - second.imagine);
    }

    static ComplexNum divide(ComplexNum top, ComplexNum bot) {
        ComplexNum temp = multiply(top, conjugate(bot));
        double divider = bot.real * bot.real + bot.imagine * bot.imagine;

        return new ComplexNum(temp.real / divider, temp.imagine / divider);
    }

    private static ComplexNum conjugate(ComplexNum a) {
        return new ComplexNum(a.real, -a.imagine);
    }

    boolean isZero() {
        return real == 0 && imagine == 0;
    }

    @Override
    public String toString() {
        final DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        final DecimalFormat realFormat = new DecimalFormat("0.####", symbols);
        final DecimalFormat imagFormat = new DecimalFormat("0.####i", symbols);

        if (Math.abs(imagine) < EPSILON) {
            return realFormat.format(real);
        }

        if (Math.abs(real) < EPSILON) {
            return imagFormat.format(imagine);
        }

        imagFormat.setPositivePrefix("+");

        return String.format("%s%s", realFormat.format(real), imagFormat.format(imagine));
    }
}


