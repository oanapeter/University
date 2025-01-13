public class PolynomialUtils {

    public static Polynomial getResult(Object[] polynomials) {
        int degree = ((Polynomial) polynomials[0]).getDegree();
        Polynomial resultPolynomial = new Polynomial(degree + 1);
        for (Object polynomial : polynomials) {
            resultPolynomial = Polynomial.add(resultPolynomial, (Polynomial) polynomial);
        }
        return resultPolynomial;
    }

    public static Polynomial multiplySequence(Polynomial firstPolynomial, Polynomial secondPolynomial, int start, int end) {
        Polynomial resultPolynomial = new Polynomial(2 * firstPolynomial.getDegree() + 1);
        for (int i = start; i < end; i++) {
            for (int j = 0; j < secondPolynomial.getCoefficients().size(); j++) {
                resultPolynomial.getCoefficients().set(i + j, resultPolynomial.getCoefficients().get(i + j) +
                        firstPolynomial.getCoefficients().get(i) *
                                secondPolynomial.getCoefficients().get(j));
            }
        }
        return resultPolynomial;
    }

    public static Polynomial regularSequential(Polynomial firstPolynomial, Polynomial secondPolynomial) {
        Polynomial resultPolynomial = new Polynomial(firstPolynomial.getDegree() + secondPolynomial.getDegree() + 1);
        for (int i = 0; i < firstPolynomial.getCoefficients().size(); i++) {
            for (int j = 0; j < secondPolynomial.getCoefficients().size(); j++) {
                resultPolynomial.getCoefficients().set(i + j, resultPolynomial.getCoefficients().get(i + j) +
                        firstPolynomial.getCoefficients().get(i) *
                                secondPolynomial.getCoefficients().get(j));
            }
        }
        return resultPolynomial;
    }

    public static Polynomial KaratsubaSequential(Polynomial firstPolynomial, Polynomial secondPolynomial) {
        if (firstPolynomial.getDegree() < 2 || secondPolynomial.getDegree() < 2) {
            return regularSequential(firstPolynomial, secondPolynomial);
        }

        int halfDegree = Math.min(firstPolynomial.getDegree(), secondPolynomial.getDegree()) / 2;

        Polynomial low1 = new Polynomial(firstPolynomial.getCoefficients().subList(0, halfDegree));
        Polynomial high1 = new Polynomial(firstPolynomial.getCoefficients().subList(halfDegree, firstPolynomial.getSize()));
        Polynomial low2 = new Polynomial(secondPolynomial.getCoefficients().subList(0, halfDegree));
        Polynomial high2 = new Polynomial(secondPolynomial.getCoefficients().subList(halfDegree, secondPolynomial.getSize()));

        Polynomial z0 = KaratsubaSequential(low1, low2);
        Polynomial z1 = KaratsubaSequential(Polynomial.add(low1, high1), Polynomial.add(low2, high2));
        Polynomial z2 = KaratsubaSequential(high1, high2);

        Polynomial resultPart1 = Polynomial.addZeros(z2, 2 * halfDegree);
        Polynomial resultPart2 = Polynomial.addZeros(Polynomial.subtract(Polynomial.subtract(z1, z2), z0), halfDegree);

        return Polynomial.add(Polynomial.add(resultPart1, resultPart2), z0);
    }
}
