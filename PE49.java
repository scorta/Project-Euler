import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Scorta on 26/04/2017.
 */
public class PE49 {
    private static byte[] baseArr;
    private static final byte sequenceLength = 3;
    private static final int maxNumber = 10000;

    public static void main(String[] args) {
        ArrayList<Long> selectedPrimeList = new ArrayList<>();
        int[] result = new int[sequenceLength - 1];

        Helpers.primeList.add(2L);

        for (long number = 3; number < maxNumber; number += 2) {
            if (Helpers.isPrime(number) && number > 1000) {
                selectedPrimeList.add(number);
            }
        }

        for(int outIndex = 0; outIndex < selectedPrimeList.size(); outIndex++){
            baseArr = numberToArray(selectedPrimeList.get(outIndex));
            byte count = 0;
            for(int innerIndex = outIndex + 1; innerIndex < selectedPrimeList.size(); innerIndex++){
                if(isPermutable(numberToArray(selectedPrimeList.get(innerIndex)))){

                    result[count] = (int)(long)(selectedPrimeList.get(innerIndex));
                    count++;

                    if(count == sequenceLength - 1){
                        if(result[1] - result[0] == result[0] - selectedPrimeList.get(outIndex)){
                            System.out.println(selectedPrimeList.get(outIndex) + " " + result[0]+ " " + result[1]+ " ");
                        }
                        break;
                    }
                }
            }

        }

    }

    private static boolean isPermutable(byte[] arr2){
        if(arr2.length != baseArr.length){
            return false;
        }

        boolean[] count = new boolean[baseArr.length];
        for(byte digit: arr2){
            for(int index = 0; index < baseArr.length;index++){
                if(digit == baseArr[index] && !count[index]){
                    count[index] = true;
                    break;
                }
            }
        }

        for(boolean element : count){
            if(!element){
                return false;
            }
        }

        return true;
    }

    private static byte[] numberToArray(long number) {
        byte length = (byte) (Math.log10(number) + 1);
        byte[] arr = new byte[length];
        arr[0] = (byte) (number / Math.pow(10, length - 1));

        for (int index = 1; index < length; index++) {
            number = number - (long) (arr[index - 1] * Math.pow(10, length - index));
            arr[index] = (byte)(number / Math.pow(10, length - index - 1));
        }
        return arr;
    }
}

class Helpers {
    public static ArrayList<Long> primeList = new ArrayList<>();

    public static boolean isPrime(Long isPrimeNumber) {
        long upperLimit = (long) Math.sqrt(isPrimeNumber) + 1;
        for (long prime : primeList) {
            if (isPrimeNumber % prime == 0) {
                return false;
            }
            if (prime > upperLimit) {
                break;
            }
        }
        primeList.add(isPrimeNumber);
        return true;
    }
}
