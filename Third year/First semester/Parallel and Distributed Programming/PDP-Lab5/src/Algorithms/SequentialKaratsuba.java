package Algorithms;

import Model.Polynomial;
import Utils.PolynomialUtils;

public class SequentialKaratsuba {

    /**
     * Multiplies two polynomials using the Karatsuba algorithm.
     *
     * @param p1 the first polynomial
     * @param p2 the second polynomial
     * @return the product of p1 and p2
     */
    public static Polynomial multiply(Polynomial p1, Polynomial p2) {
        // Base case: if the degree of either polynomial is less than 2, use the classic multiplication
        if (p1.getDegree() < 2 || p2.getDegree() < 2) {
            return SequentialClassic.multiply(p1, p2);
        }

        // Find the midpoint of the longer polynomial to split the polynomials into two halves
        int len = Math.max(p1.getDegree(), p2.getDegree()) / 2;

        // Split p1 into lowP1 (lower half) and highP1 (upper half)
        Polynomial lowP1 = new Polynomial(p1.getCoefficients().subList(0, len));
        Polynomial highP1 = new Polynomial(p1.getCoefficients().subList(len, p1.getCoefficients().size()));

        // Split p2 into lowP2 (lower half) and highP2 (upper half)
        Polynomial lowP2 = new Polynomial(p2.getCoefficients().subList(0, len));
        Polynomial highP2 = new Polynomial(p2.getCoefficients().subList(len, p2.getCoefficients().size()));

        // Recursively compute the three subproducts
        // z1 = lowP1 * lowP2
        Polynomial z1 = multiply(lowP1, lowP2);

        // z2 = (lowP1 + highP1) * (lowP2 + highP2)
        Polynomial z2 = multiply(PolynomialUtils.add(lowP1, highP1), PolynomialUtils.add(lowP2, highP2));

        // z3 = highP1 * highP2
        Polynomial z3 = multiply(highP1, highP2);

        // Combine the results to compute the final polynomial product

        // r1 = z3 shifted by 2 * len (equivalent to multiplying z3 by x^(2 * len))
        Polynomial r1 = PolynomialUtils.addWithZeros(z3, 2 * len);

        // r2 = (z2 - z3 - z1) shifted by len (equivalent to multiplying by x^len)
        Polynomial r2 = PolynomialUtils.addWithZeros(PolynomialUtils.subtract(PolynomialUtils.subtract(z2, z3), z1), len);

        // Final result: r1 + r2 + z1
        return PolynomialUtils.add(PolynomialUtils.add(r1, r2), z1);
    }
}
