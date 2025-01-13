package Algorithms;

import Model.Polynomial;

import java.util.ArrayList;
import java.util.List;

public class SequentialClassic {

    /**
     * Multiplies two polynomials using the classic (brute force) approach.
     *
     * @param poly1 the first polynomial
     * @param poly2 the second polynomial
     * @return the product of poly1 and poly2 as a Polynomial object
     */
    public static Polynomial multiply(Polynomial poly1, Polynomial poly2) {
        // Retrieve the coefficients and degrees of the input polynomials
        List<Integer> coefficients1 = poly1.getCoefficients();
        List<Integer> coefficients2 = poly2.getCoefficients();
        int degree1 = poly1.getDegree();
        int degree2 = poly2.getDegree();

        // Initialize the result coefficients list with zeros
        // The degree of the resulting polynomial will be degree1 + degree2
        List<Integer> resultCoefficients = new ArrayList<>();
        for (int i = 0; i <= degree1 + degree2; i++) {
            resultCoefficients.add(0); // Set initial value of each coefficient to 0
        }

        // Perform classic polynomial multiplication
        // Multiply each coefficient from poly1 with each coefficient from poly2
        for (int i = 0; i <= degree1; i++) {
            for (int j = 0; j <= degree2; j++) {
                // Compute the product of coefficients
                int coeffProduct = coefficients1.get(i) * coefficients2.get(j);

                // Add the product to the correct position in the result coefficients
                // The position corresponds to the sum of the current degrees (i + j)
                resultCoefficients.set(i + j, resultCoefficients.get(i + j) + coeffProduct);
            }
        }

        // Return the resulting polynomial
        return new Polynomial(resultCoefficients);
    }
}
