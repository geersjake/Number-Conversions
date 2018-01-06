import java.util.ArrayList;
import java.util.Scanner;

/*****************************************************************
 Basic program for converting the base of a number to another.
 NEEDS TO BE REWRITTEN

 @author Jake Geers
 @version 1/4/2018
 *****************************************************************/
public class NumberBase {

    public static String convert(String input, int base_in, int base_out) {
        ArrayList<Integer> nums = convertIntoArray(input);
        int input_as_integer = toInteger(nums);
        String ans = "";
        int sum = -1;
        if (base_out == 10) {
            sum = toBaseTen(nums, base_in);
            ans = String.valueOf(sum);
        } else {
            ArrayList<Integer> temp = new ArrayList<>();
            sum = toBaseTwo(input_as_integer, base_out, temp);
            ans = String.valueOf(sum);
        }


        return ans;
    }

    public static int toBaseTwo(int input, int base_out, ArrayList<Integer> remainders) {
        //ArrayList<Integer> remainders = new ArrayList<>();
        remainders.add(input % base_out);
        if (input <= 0) {
            System.out.println(remainders);
            return toInteger(remainders);
        } else {
            return toBaseTwo(input / base_out, base_out, remainders);
        }


    }

    public static int toInteger(ArrayList<Integer> nums) {
        int sum = 0;
        int num_place = 1;

        for (int i = 0; i < nums.size(); i++) {
            sum += nums.get(i) * num_place;
            num_place *= 10;
        }
        return sum;
    }

    public static int toBaseTen(ArrayList<Integer> nums, int base_in) {
        int sum = 0;

        for (int i = 0; i < nums.size(); i++) {
            sum += nums.get(i) * Math.pow(base_in, i);
        }

        return sum;
    }


    public static ArrayList<Integer> convertIntoArray(String input) {
        ArrayList<Integer> nums = new ArrayList<>();
        char[] charDigits = input.toCharArray();
        for (int i = charDigits.length - 1; i >= 0; i--) {
            nums.add(Character.getNumericValue(charDigits[i]));
        }
        return nums;
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input;
        int base_in, base_out;

        System.out.println("Base Converter:");
        System.out.println("Number to be converted: ");
        input = scan.next();
        System.out.println("Base of Number: ");
        base_in = scan.nextInt();
        System.out.println("Base to Convert to: ");
        base_out = scan.nextInt();
        System.out.println(convert(input, base_in, base_out));

    }

}
