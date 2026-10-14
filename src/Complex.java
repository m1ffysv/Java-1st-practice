public class Complex {
    private double real;
    private double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex add(Complex other) {
        return new Complex(
                real + other.real,
                imaginary + other.imaginary
        );
    }

    public Complex subtract(Complex other) {
        return new Complex(
                real - other.real,
                imaginary - other.imaginary
        );
    }

    public Complex multiply(Complex other) {
        return new Complex(
                real * other.real - imaginary * other.imaginary,
                real * other.imaginary + imaginary * other.real
        );
    }

    public Complex divide(Complex other) {
        double denominator = other.real * other.real
                + other.imaginary * other.imaginary;

        if (denominator < 1e-20) {
            throw new ArithmeticException("Деление на ноль");
        }

        return new Complex(
                (real * other.real + imaginary * other.imaginary) / denominator,
                (imaginary * other.real - real * other.imaginary) / denominator
        );
    }

    public double abs() {
        return Math.sqrt(real * real + imaginary * imaginary);
    }

    public boolean isZero() {
        return abs() < 1e-10;
    }

    @Override
    public String toString() {
        if (Math.abs(imaginary) < 1e-10) {
            return String.format("%.2f", real);
        }

        if (Math.abs(real) < 1e-10) {
            return String.format("%.2fi", imaginary);
        }

        if (imaginary < 0) {
            return String.format("%.2f - %.2fi", real, -imaginary);
        }

        return String.format("%.2f + %.2fi", real, imaginary);
    }
}