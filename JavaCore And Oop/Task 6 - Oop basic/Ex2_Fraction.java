public class Ex2_Fraction {
    public static void main(String[] args) {
        try {
            Fraction f1 = new Fraction(1, -2);
            Fraction f2 = new Fraction(4, 6);
            System.out.println("Phân số f1: " + f1.show());
            System.out.println("Phân số f2: " + f2.show());

            Fraction sum = f1.add(f2);
            Fraction diff = f1.subtract(f2);
            Fraction prod = f1.multiply(f2);
            Fraction trade = f1.divide(f2);

            System.out.printf("%s + %s = %s\n", f1.show(), f2.show(), sum.show());
            System.out.printf("%s - %s = %s\n", f1.show(), f2.show(), diff.show());
            System.out.printf("%s x %s = %s\n", f1.show(), f2.show(), prod.show());
            System.out.printf("%s : %s = %s\n", f1.show(), f2.show(), trade.show());
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }
}

class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) throws ArithmeticException {
        if (denominator == 0) {
            throw new ArithmeticException("Khởi tạo phân số không thành công. Mẫu số không được âm!");
        } else {
            int[] arr = shorten(numerator, denominator);
            if (arr[1] < 0) {
                arr[0] *= -1; arr[1] *= -1;
            }
            this.numerator = arr[0];
            this.denominator = arr[1];
        }
    }

    public Fraction add(Fraction f) {
        int numerator = this.numerator*f.denominator + f.numerator*this.denominator;
        int denominator = this.denominator * f.denominator;
        return new Fraction(numerator, denominator);
    }

    public Fraction subtract(Fraction f) {
        int numerator = this.numerator * f.denominator - f.numerator * this.denominator;
        int denominator = this.denominator * f.denominator;
        return new Fraction(numerator, denominator);
    }

    public Fraction multiply(Fraction f) {
        int numerator = this.numerator * f.numerator;
        int denominator = this.denominator * f.denominator;
        return new Fraction(numerator, denominator);
    }

    public Fraction divide(Fraction f) {
        int numerator = this.numerator * f.denominator;
        int denominator = this.denominator * f.numerator;
        return new Fraction(numerator, denominator);
    }

    public String show() {
        if (this.numerator == 0) {
            return "0";
        }
        return String.format("%d/%d", this.numerator, this.denominator);
    }

    private static int[] shorten(int numerator, int denominator) {
        int a = numerator, b = denominator;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return new int[]{numerator/a, denominator/a};
    }
}