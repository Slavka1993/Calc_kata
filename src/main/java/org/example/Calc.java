package org.example;
import java.util.Scanner;

public class Calc {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два римских или арабских числа");
        String expression = scanner.nextLine();
        System.out.println(parse(expression));
    }
    public static String parse(String expression) throws Exception {
        int num1;
        int num2;
        String oper;
        String result;
        boolean isRoman;
        String[] operands = expression.split("[+\\-*/]");
        if (operands.length !=2) throw new Exception("формат не удовлетворяет заданию. Должно быть два операнда и один оператор");
        oper = detecTOperation(expression);
        if (oper == null) throw new Exception("Неподдерживаемая мат. операция");
        // если оба числа римские
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {


            //конвертируем оба числа в арабские
            num1 = Roman.convertToArabian(operands[0]);
            num2 = Roman.convertToArabian(operands[1]);
            isRoman = true;
        }


        // если оба числа арабские
        else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[1]);
            isRoman = false;
        }


        // если одно число римское, а другое арабское
        else {
            throw new Exception("используются разные системы счислений");
        }
        if (num1 > 10 || num2 > 10) {
            throw new Exception("Числа должны быть не больше 10");
        }
        int arabian = calc(num1, num2, oper);
        if (isRoman) {


            //если риское число меньше или равно нулю, то выдаем ошибку
            if (arabian <= 0) {
                throw new Exception(" в римской системе нет отрицательных чисел");
            }
            //конвертируем из арабского в римское
            result = Roman.convertToRoman(arabian);
        } else {


            //Конвертируем арабское число в тип String
            result = String.valueOf(arabian);
        }
        // возвращаем результат
        return result;

    }

    static String detecTOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
        }

        static  int calc(int a, int b, String oper) {

        if (oper.equals("+")) return a + b;
            else if (oper.equals("-")) return a - b;
            else if (oper.equals("*")) return a * b;
            else return  a / b;
    }
}

class Roman {
    static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX",
            "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV"};

    public static boolean isRoman(String val) {
        for (int i = 0; i < romanArray.length; i++) {
            if (val.equals(romanArray[i])) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabian(String roman) {
        for (int i = 0; i < romanArray.length; i++) {
            if (roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRoman(int arabian) { return romanArray[arabian];}
}
