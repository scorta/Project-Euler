import java.math.BigInteger;

/**
 * Created by Scorta on 04/05/2017.
 */
public class PE57 {

    private static final int LIMIT = 1000;

    public static void main(String[] args) {
        System.out.println(Calculate(LIMIT));
    }

    private static int Calculate(long limit) {
        int count = 0;
        BigInteger numerator = new BigInteger("3");
        BigInteger denominator = new BigInteger("2");
        BigInteger two = new BigInteger("2");

        for(int iteration = 1; iteration < limit; iteration++){
            BigInteger tmpNumerator;
            tmpNumerator = numerator.add(denominator.multiply(two));
            BigInteger tmpDenominator;
            tmpDenominator = tmpNumerator.subtract(denominator);

            numerator = tmpNumerator;
            denominator = tmpDenominator;

            if(numerator.toString().length() > denominator.toString().length()){
                count++;
            }
        }

        return count;
    }
}
