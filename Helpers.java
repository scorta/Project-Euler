import java.util.ArrayList;

/**
 * Created by Scorta on 26/04/2017.
 */
public class Helpers {
    public static ArrayList<Long> primeList = new ArrayList<>();

    public static boolean isPrime(long isPrimeNumber) {
        if(isPrimeNumber < 2){
            return false;
        }
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

    public static boolean isPrimePureMethod(long isPrimeNumber){
        if(isPrimeNumber < 2L || (isPrimeNumber & 1) == 0 ){
            return false;
        }
        long upperLimit = (long) Math.sqrt(isPrimeNumber) + 1;
        for(long number = 3L; number < upperLimit; number += 2){
            if(isPrimeNumber % number == 0){
                return false;
            }
        }

        return true;
    }
}
