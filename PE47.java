import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Scorta on 25/04/2017.
 */

public class PE47 {
    private static final int SERI = 4;
    private static ArrayList<Long> primeList = new ArrayList<>();
    private static long newValue;

    public static void main(String[] args) {
        long numberToFind = 3L;
        primeList.add(2L);
        Date now = new Date();
        do{
            if(isSerie(numberToFind)){
                System.out.println(numberToFind);
                Date later = new Date();
                System.out.println((later.getTime() - now.getTime())/1000);
                break;
            }
            numberToFind = newValue;
        }
        while(true);
    }

    private static boolean isSerie(long isSerieNumber){
        for(int index = 0; index < SERI; index++){
            if(isMadeFromNprime(isSerieNumber)){
                isSerieNumber++;
            }
            else{
                newValue = isSerieNumber + 1;
                return false;
            }
        }

        newValue = isSerieNumber + 1;
        return true;
    }

    private static boolean isMadeFromNprime(long analystNumber) {
        int count = 0;
        if (isPrime(analystNumber)) {
            return false;
        }
        for (long prime : primeList) {
            if (analystNumber % prime == 0) {
                if(count++ > SERI){
                    return false;
                }

                while (analystNumber % prime == 0) {
                    analystNumber /= prime;
                }
            }
        }

        if(count == SERI){
            return true;
        }
        else{
            return false;
        }
    }

    private static boolean isPrime(Long isPrimeNumber) {
        long upperLimit = (long) Math.sqrt(isPrimeNumber) + 1;
        for (long prime : primeList) {
            if (isPrimeNumber % prime == 0) {
                return false;
            }
            if(prime > upperLimit){
                primeList.add(isPrimeNumber);
                return true;
            }
        }
        primeList.add(isPrimeNumber);
        return true;
    }
}
