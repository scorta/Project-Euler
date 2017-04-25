import java.util.ArrayList;

/**
 * Created by Scorta on 24/04/2017.
 */
public class PE46 {
    private static ArrayList<Long> primeList = new ArrayList<>();

    public static void main(String[] args) {
        Goldbach();
    }

    private static void Goldbach(){
        long numberToCheck = 3L;
        primeList.add(2L);

        do {
            if (!isPrime(numberToCheck)) {
                if (!isGoldbach(numberToCheck)) {
                    System.out.println(numberToCheck);
                    break;
                }
            }
            numberToCheck += 2;
        }
        while (true);
    }

    private static boolean isGoldbach(long number) {
        for (long prime : primeList) {
            for (long twiceSquare = 1; twiceSquare < Math.sqrt((number - prime) / 2) + 1; twiceSquare++) {
                if (number == prime + 2 * twiceSquare * twiceSquare) return true;
            }
        }

        return false;
    }

    private static boolean isPrime(long number) {
        for (Long prime : primeList) {
            if (number % prime == 0L) return false;
        }
        primeList.add(number);
        return true;
    }
}
