import java.math.BigInteger;
import java.util.ArrayList;

/*****************************************************************
 This class converts a number between two bases ranging from 2-36

 @author Jake Geers
 @version 9/6/2016
 *****************************************************************/
public class NumberBase {

    /*****************************************************************
     Central method used to convert bases. Calls various other methods
     to help convert.
     @param input the string representation of number to convert
     @param base_in the base that input is in currently
     @param base_out the base which input will be converted into
     @throws IllegalArgumentException when params are improper
     @return String representation of converted input
     *****************************************************************/
    public static String convert (String input, int base_in, int base_out){
        //checks input
        if (isValidInput(input, base_in)==false){
            throw new IllegalArgumentException();
        }

        //creates array of bigInts via convertIntoArray method
        ArrayList<BigInteger> nums = convertIntoArray(input);

        //transforms nums array into a single bigInt
        BigInteger input_as_integer = toInteger(nums);

        //returned string
        String ans;

        //variable used to temporarily hold converted input
        BigInteger sum;


        if (base_out == 10){ //checking if base out is base10
            //sets sum to method return of toBaseTen
            sum = toBaseTen(nums,BigInteger.valueOf(base_in));
            ans = sum + ""; //quotes used for type conversion
            ans = ans.trim(); //eliminates whitespace created above

        }
        else if (base_in != 10){ //detects if base in is anything but 10
            //converts input to base ten
            BigInteger input_in_base_ten = toBaseTen(nums, BigInteger.valueOf(base_in));

            //necessary for recursion in toOtherBase method
            ArrayList<BigInteger> temp = new ArrayList<>();

            //now that input is in base ten, converts to desired base
            ans = toOtherBase(input_in_base_ten, BigInteger.valueOf(base_out), temp);

        } else { //catches case in-which baseIn is 10
            //necessary for recursion in toOtherBase method
            ArrayList<BigInteger> temp = new ArrayList<>();

            //since baseIn is 10 we skip converting toBaseTen
            ans = toOtherBase(input_as_integer, BigInteger.valueOf(base_out), temp);
        }

        if (ans.startsWith("0")){ //checking for leading zeros
            //regex that removes all leading zeros unless needed i.e ans = 0
            //regex taken from stack overflow thread
            ans = ans.replaceFirst("^0+(?!$)", "");
        }

        return ans;
    }

    /*****************************************************************
     Converts any input in any base into base 10
     @param nums arrayList which holds each digit of initial input
     @param base_in base which input was initially
     @return number converted to base 10
     *****************************************************************/
    public static BigInteger toBaseTen (ArrayList<BigInteger> nums, BigInteger base_in){
        BigInteger sum = BigInteger.valueOf(0);
        for (int i = 0; i < nums.size(); i++) { //cycles through array
            //multiplies nums value at i by the baseIn raised to the
            // i'th power and then adds to sum
            sum = sum.add(nums.get(i).multiply(base_in.pow(i)));
        }
        return sum;
    }

    /*****************************************************************
     Converts input into any base besides base 10
     @param input bigInt representation of original input string
     @param base_out base to which to convert
     @param remainders holds remainders for use in toLetters method
     @return converted base as a string
     *****************************************************************/
    public static String toOtherBase(BigInteger input, BigInteger base_out, ArrayList<BigInteger> remainders ){
        remainders.add(input.mod(base_out)); //adding remainder to array

        //exits recursion when modular division reaches zero
        if (input.compareTo(BigInteger.ZERO) == 0){
            //sends remainder array to be converted into a string
            return toLetters(remainders);
        } else {
            //recursive call sends input divided by baseOut and retains list of remainders
            return toOtherBase(input.divide(base_out), base_out, remainders);
        }
    }

    /*****************************************************************
     Converts an arrayList of bigInts into a single bigInt
     @param nums an arrayList that represents each digit of the
     original input
     @return the bigInt value of the original input
     *****************************************************************/
    public static BigInteger toInteger (ArrayList<BigInteger> nums){
        BigInteger sum = BigInteger.valueOf(0);

        //used to convert a value in the arry to the correct number place
        BigInteger num_place = BigInteger.valueOf(1);

         for (int i = 0; i < nums.size(); i++) { //cycles through array

            //if the value in the array at i is a zero, multiplying by
            //the num_place would result in zero. This statement prevents
            //this by ignoring the zero and continuing through loop
            if (nums.get(i).compareTo(BigInteger.ZERO) == 0){
                num_place = num_place.multiply(BigInteger.valueOf(10));
            } else {
                //multiplies value at i with its number place and adds it to sum
                sum = sum.add(nums.get(i).multiply(num_place));
                num_place = num_place.multiply(BigInteger.valueOf(10));
            }
        }
        return sum;
    }

    /*****************************************************************
     Converts an array of bigInts into a string that represents the
     array in the correct value based on base
     @param nums arrayList of bigInts holding the value to be transformed
     @return a string of letters and numbers representing converted base
     *****************************************************************/
    public static String toLetters (ArrayList<BigInteger> nums){
        String ans = "";
        String[] letters = { //array holding sequence of values used to go up to base 36
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
        };
        for (int i = 0; i < nums.size(); i++) { //cycles through array
            ans = letters[nums.get(i).intValue()] + ans; //adding values to string in reverse order
        }
        return ans;
    }

    /*****************************************************************
     Converts original input in the form of a string into an array with
     each element added in reverse order
     @param input string representation of original input in base baseIn
     @return an arrayList of bigInts whos elemnts hold a single digit
     of the original input in reverse order
     *****************************************************************/
    public static ArrayList<BigInteger> convertIntoArray (String input){
        ArrayList<BigInteger> nums = new ArrayList<>();
        char [] charDigits = input.toCharArray(); //converting to array
        for (int i = charDigits.length-1; i >= 0; i--) { //cycles through in reverse order
            //converts char to int to bigInt and adds to nums array
            nums.add(BigInteger.valueOf(Character.getNumericValue(charDigits[i])));
        }
        return nums;
    }

    /*****************************************************************
     Ensures that input is valid numerically as well as compared
     to its supposed base
     @param input to be tested
     @return true if input is valid, false if not
     *****************************************************************/
    public static boolean isValidInput (String input, int base_in){
        boolean valid = true;

        if (input == null){ //checks input isnt null, first to prevent null pointer ex
            return false;
        }

        ArrayList<BigInteger> nums = convertIntoArray(input);
        BigInteger baseIn = BigInteger.valueOf(base_in).subtract(BigInteger.ONE);

        if (!input.matches("^[a-z0-9]*$")){ //checks for non-alphanumeric chars
            valid = false;
        }
        if (input.matches(".*\\s+.*")){ //checks for white space
            valid = false;
        }
        if (input.length() == 0){ //checks input isnt empty
            valid = false;
        }

        for (BigInteger temp: nums) { //checks that input is in the correct base
            if (temp.compareTo(baseIn)== 1) {
                valid = false;
            }
        }
        return valid;
    }

    public static void main(String[] args) {
        System.out.println(convert("A", 36, 2));
    }

}
