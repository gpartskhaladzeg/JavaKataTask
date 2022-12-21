
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static boolean isPlus = false;
    static boolean isMinus = false;
    static boolean isMultiply = false;
    static boolean isDivide = false;

    static String[] nums;

    public static void main(String[] args) throws Exception {
        String str = input();
        getOperation(str);
        if (isNumeric(nums[0]) && isNumeric(nums[1])){
            System.out.println(result(nums));
        } else if (!isNumeric(nums[0]) && !isNumeric(nums[1])){
            String[] tmp = {romanToArabic(nums[0]), romanToArabic(nums[1])};
            if (Integer.parseInt(result(tmp)) <= 0) {
                throw new Exception();
            } else {
                System.out.println(arabicToRoman(Integer.parseInt(result(tmp))));
            }

        } else {
            throw new Exception();
        }
    }

    public static String input(){
        System.out.println("Введите пример");
        return scanner.nextLine().replaceAll("\\s+","");
    }

    public static void getOperation(String str){
        if (str.contains("+")){
            isPlus = true;
            nums = str.split("\\+");
        } else if (str.contains("-")) {
            isMinus = true;
            nums = str.split("-");
        } else if (str.contains("*")) {
            isMultiply = true;
            nums = str.split("\\*");
        } else if (str.contains("/")) {
            isDivide = true;
            nums = str.split("/");
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static String result(String[] nums) throws Exception {
        int num1 = Integer.parseInt(nums[0]);
        int num2 = Integer.parseInt(nums[1]);
        if (num1 > 0 && num1 <= 10 && num2 > 0 && num2 <= 10) {
            if (isPlus) {
                return String.valueOf(num1 + num2);
            } else if (isMinus) {
                return String.valueOf(num1 - num2);
            } else if (isMultiply) {
                return String.valueOf(num1 * num2);
            } else if (isDivide) {
                return String.valueOf(num1 / num2);
            } else {
                throw new Exception();
            }
        } else {
            throw new Exception();
        }
    }


    public static String romanToArabic(String input) throws Exception {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new Exception();
        }

        return String.valueOf(result);
    }

    public static String arabicToRoman(int number) throws Exception {
        if ((number <= 0) || (number > 4000)) {
            throw new Exception();
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }
}