/**
 * Created by Scorta on 15/05/2017.
 * Solution for Problem 58: https://projecteuler.net/index.php?section=problems&id=58
 */
public class PE58 {
    public static void main(String[] args) {
        long countPrime = 0, totalNumber = 1;
        long currentNumber = 1, step = 0;

        do {
            step += 2;
            for (int index = 0; index < 3; index++) {
                currentNumber += step;

                if (Helpers.isPrimePureMethod(currentNumber)) {
                    countPrime++;
                }
            }
            currentNumber += step;
            totalNumber += 4;
        }
        while ((double) countPrime / (totalNumber) > 0.1);

        System.out.println(step + 1);
    }
}
