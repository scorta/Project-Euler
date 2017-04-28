import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Scorta on 26/04/2017.
 */
public class PE50 {
    private static final int BOUNDARY = 1000000;
    private static long[] sumOfPrimeArr;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        int maxConsequenceLength = 21, specialPrime = 0; //It is given in the problem's description that, the maxConsequenceLength = 21 with BOUNDARY < 1000. We can use that to save time.
        Helpers.primeList.add(2L);
        for (int number = 3; number < BOUNDARY; number += 2) {
            Helpers.isPrime(number);
        }

        sumOfPrimeArr = new long[Helpers.primeList.size()];
        sumOfPrimeArr[0] = Helpers.primeList.get(0);

        for (int index = 1; index < sumOfPrimeArr.length; index++) {
            sumOfPrimeArr[index] = Helpers.primeList.get(index) + sumOfPrimeArr[index - 1];
        }

        for (int outerIndex = sumOfPrimeArr.length - 1; outerIndex > maxConsequenceLength; outerIndex--) {
            //Save time by not calculating the even case: if both sumOfPrimeArr[outerIndex] and sumOfPrimeArr[innerIndex] are even or odd, sum will be an even number, which can't be a prime
            for (int innerIndex = outerIndex - maxConsequenceLength; innerIndex > -1; innerIndex -= 2) {
                long sum = sumOfPrimeArr[outerIndex] - sumOfPrimeArr[innerIndex];
                if (sum > BOUNDARY) {
                    break;
                }
                if (Helpers.primeList.contains(sum)) {
                    maxConsequenceLength = outerIndex - innerIndex;
                    specialPrime = (int) sum;
                }
            }
        }

        String output = String.format("The longest sum of consecutive primes below %,d that also is a prime: %,d; contains %d terms", BOUNDARY, specialPrime, maxConsequenceLength);
        System.out.println(output);

        System.out.println("Finish after (in milisecond): " + (System.currentTimeMillis() - startTime));
    }
}
