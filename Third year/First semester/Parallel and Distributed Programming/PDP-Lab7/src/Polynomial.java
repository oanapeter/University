import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Polynomial implements Serializable {
    private List<Integer> coefficients;

    public Polynomial(List<Integer> coefficients) {
        this.coefficients = coefficients;
    }

    public Polynomial(int size) {
        this.coefficients = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            this.coefficients.add(0);
        }
    }

    public void generateRandomCoefficients() {
        Random random = new Random();
        for (int i = 0; i < this.coefficients.size(); i++) {
            int randomCoefficient = random.nextInt(10) * (random.nextBoolean() ? 1 : -1);
            while (randomCoefficient == 0 && i == this.coefficients.size() - 1) {
                randomCoefficient = random.nextInt(10) * (random.nextBoolean() ? 1 : -1);
            }
            this.coefficients.set(i, randomCoefficient);
        }
    }

    public List<Integer> getCoefficients() {
        return this.coefficients;
    }

    public int getDegree() {
        return this.coefficients.size() - 1;
    }

    public int getSize() {
        return this.coefficients.size();
    }

    public static Polynomial add(Polynomial firstPolynomial, Polynomial secondPolynomial) {
        int maxDegree = Math.max(firstPolynomial.getDegree(), secondPolynomial.getDegree());
        Polynomial resultPolynomial = new Polynomial(maxDegree + 1);

        for (int i = 0; i <= maxDegree; i++) {
            int firstCoefficient = i <= firstPolynomial.getDegree() ? firstPolynomial.getCoefficients().get(i) : 0;
            int secondCoefficient = i <= secondPolynomial.getDegree() ? secondPolynomial.getCoefficients().get(i) : 0;
            resultPolynomial.getCoefficients().set(i, firstCoefficient + secondCoefficient);
        }

        return resultPolynomial;
    }

    public static Polynomial subtract(Polynomial firstPolynomial, Polynomial secondPolynomial) {
        int maxDegree = Math.max(firstPolynomial.getDegree(), secondPolynomial.getDegree());
        Polynomial resultPolynomial = new Polynomial(maxDegree + 1);

        for (int i = 0; i <= maxDegree; i++) {
            int firstCoefficient = i <= firstPolynomial.getDegree() ? firstPolynomial.getCoefficients().get(i) : 0;
            int secondCoefficient = i <= secondPolynomial.getDegree() ? secondPolynomial.getCoefficients().get(i) : 0;
            resultPolynomial.getCoefficients().set(i, firstCoefficient - secondCoefficient);
        }

        int index = resultPolynomial.getCoefficients().size() - 1;
        while (index > 0 && resultPolynomial.getCoefficients().get(index) == 0) {
            resultPolynomial.getCoefficients().remove(index);
            index--;
        }
        return resultPolynomial;
    }

    public static Polynomial addZeros(Polynomial polynomial, int zeroCount) {
        List<Integer> extendedCoefficients = new ArrayList<>(zeroCount);
        for (int i = 0; i < zeroCount; i++) {
            extendedCoefficients.add(0);
        }
        extendedCoefficients.addAll(polynomial.getCoefficients());
        return new Polynomial(extendedCoefficients);
    }

    @Override
    public String toString() {
        StringBuilder polynomialString = new StringBuilder();
        int highestDegree = coefficients.size() - 1;

        if (coefficients.get(highestDegree) != 0) {
            polynomialString.append(coefficients.get(highestDegree)).append("x^").append(highestDegree);
        }

        for (int i = highestDegree - 1; i >= 0; i--) {
            if (coefficients.get(i) != 0) {
                polynomialString.append(coefficients.get(i) > 0 ? "+" : "")
                        .append(coefficients.get(i)).append("x^").append(i);
            }
        }

        if (coefficients.get(0) != 0 || polynomialString.length() == 0) {
            polynomialString.append(coefficients.get(0) > 0 ? "+" : "").append(coefficients.get(0));
        }

        return polynomialString.toString();
    }
}
