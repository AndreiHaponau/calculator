import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("введи выражение");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String par = calc(s);
        System.out.println(par);
    }

    static String[] parseS(String input) {
        String[] arr = input.split(" ");
        if (arr.length != 3) {
            throw new IllegalArgumentException("Введи число, знак, число через пробел");
        }
        if (!(arr[1].equals("+") || arr[1].equals("-") || arr[1].equals("*") || arr[1].equals("/"))) {
            throw new IllegalArgumentException("не верно указал оператор, выбери один из этих знаков: +,-,*,/");
        }
        return arr;

    }

    static void checkNumber(int num1, int num2) {
        if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
            throw new IllegalArgumentException("Число должно быть от 1 до 10");
        }
    }

    static boolean isRoman(String s) {
        return s.matches("I|II|III|IV|V|VI|VII|VIII|IX|X");
    }

    static int romanToArabic(String roman) {
        switch (roman) {
            case "I":
                return 1;
            case "II":
                return 2;
            case "III":
                return 3;
            case "IV":
                return 4;
            case "V":
                return 5;
            case "VI":
                return 6;
            case "VII":
                return 7;
            case "VIII":
                return 8;
            case "IX":
                return 9;
            case "X":
                return 10;
            default:
                throw new IllegalArgumentException("Invalid Roman numeral");
        }

    }

    static String arabicToRoman(int number) {
        if (number < 1 || number > 100) {
            throw new IllegalArgumentException("Римские числа должны быть от 1 до 100");
        }

        String[] romanNumerals = {
                "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
        };
        int[] arabicValues = {
                100, 90, 50, 40, 10, 9, 5, 4, 1
        };

        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < arabicValues.length; i++) {
            while (number >= arabicValues[i]) {
                number -= arabicValues[i];
                roman.append(romanNumerals[i]);
            }
        }
        return roman.toString();
    }


    static String checkSameNumberSystem(String[] s) {
        if (isRoman(s[0]) && isRoman(s[2])) {
            return "roman";
        } else if (!isRoman(s[0]) && !isRoman(s[2])) {
            return "arabic";
        } else {
            throw new IllegalArgumentException("нельзя смешивать арабские и римские числа");
        }
    }

    public static String calc(String input) {
        String[] parts1 = parseS(input);
        String parts2 = checkSameNumberSystem(parts1);
        int nom1, nom2;
        if (parts2.equals("roman")) {
            nom1 = romanToArabic(parts1[0]);
            nom2 = romanToArabic(parts1[2]);
        } else {
            nom1 = Integer.parseInt(parts1[0]);
            nom2 = Integer.parseInt(parts1[2]);
        }
        checkNumber(nom1, nom2);

        int result = 0;

        switch (parts1[1]) {
            case "+":
                result = nom1 + nom2;
                break;
            case "-":
                result = nom1 - nom2;
                break;
            case "*":
                result = nom1 * nom2;
                break;
            case "/":
                result = nom1 / nom2;
                break;
        }

        if (parts2.equals("roman")) {
            if (result < 1) {
                throw new IllegalArgumentException("Римские цифры не могут быть меньше 1");
            }
            return arabicToRoman(result);
        }

        return Integer.toString(result);
    }
}
