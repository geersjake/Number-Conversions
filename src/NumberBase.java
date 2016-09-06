import java.math.BigInteger;
import java.util.ArrayList;

/**
 Created by Jake on 9/2/2016.
 */
public class NumberBase {



    public static String convert (String input, int base_in, int base_out){
        ArrayList<BigInteger> nums = convertIntoArray(input);
        BigInteger input_as_integer = toInteger(nums);
        String ans = "miss";
        BigInteger sum;
        if (base_out == 10){
            sum = toBaseTen(nums,BigInteger.valueOf(base_in));
            ans = sum + "";
            ans.trim();

        }
        else if (base_in != 10){
            BigInteger input_in_base_ten = toBaseTen(nums, BigInteger.valueOf(base_in));

            ArrayList<BigInteger> temp = new ArrayList<>();
            ans = toBaseTwo(input_in_base_ten, BigInteger.valueOf(base_out), temp);

        } else {
            ArrayList<BigInteger> temp = new ArrayList<>();
            ans = toBaseTwo(input_as_integer, BigInteger.valueOf(base_out), temp);
        }

        if (ans.startsWith("0")){
            ans = ans.replaceFirst("^0+(?!$)", "");
        }

        return ans;
    }

    public static String toBaseTwo (BigInteger input, BigInteger base_out,ArrayList<BigInteger> remainders ){
        remainders.add(input.mod(base_out));
        if (input.compareTo(BigInteger.ZERO) == 0){
            System.out.println(remainders);
            return toLetters(remainders);
        }
        else {
            return toBaseTwo(input.divide(base_out), base_out, remainders);
        }



    }

    public static BigInteger toInteger (ArrayList<BigInteger> nums){
        BigInteger sum = BigInteger.valueOf(0);
        BigInteger num_place = BigInteger.valueOf(1);

        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i).compareTo(BigInteger.ZERO) == 0){
                num_place = num_place.multiply(BigInteger.valueOf(10));

            }
            else {
                sum = sum.add(nums.get(i).multiply(num_place));
                num_place = num_place.multiply(BigInteger.valueOf(10));
            }

        }
        return sum;
    }

    public static String toLetters (ArrayList<BigInteger> nums){
        String ans = "";

        String[] letters = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
        };

        for (int i = 0; i < nums.size(); i++) {

            ans = letters[nums.get(i).intValue()] + ans;

        }
        return ans;
    }

    public static BigInteger toBaseTen (ArrayList<BigInteger> nums, BigInteger base_in){
        BigInteger sum = BigInteger.valueOf(0);

        for (int i = 0; i < nums.size(); i++) {
            sum = sum.add(nums.get(i).multiply(base_in.pow(i)));


        }

        return sum;
    }


    public static ArrayList<BigInteger> convertIntoArray (String input){
        ArrayList<BigInteger> nums = new ArrayList<>();
        char [] charDigits = input.toCharArray();
        for (int i = charDigits.length-1; i >= 0; i--) {
            nums.add(BigInteger.valueOf(Character.getNumericValue(charDigits[i])));

        }
        return nums;
    }



    public static void main(String[] args) {
        System.out.println(convert("123", 10, 36));
    }

}


 /*  int sum = 0;
        int tempPow = -1;
        ArrayList<Integer> powers = new ArrayList<Integer>();

        for (int i = 0; i < nums.size(); i++) {
            tempPow = (int)Math.pow(base_in, i);
            if (tempPow == 0){
                powers.add(0);
            }
            else {
                powers.add(tempPow);
            }
        }
        for (int i = 0; i < powers.size(); i++) {
            sum += powers.get(i);
        }

        return sum;*/
