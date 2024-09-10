import java.util.Scanner;

public class Main {
    public static boolean isPrime(long n) {
        if (n <= 3) return n > 1;
        if (n % 2 == 0 || n % 3 == 0) return false;
        long temp = (long)Math.sqrt(n);
        // Số nguyên tố lớn hơn 3 có dạng 6k +- 1
        for (int i = 5; i <= temp; i += 6) {
            if (n % i == 0 || n % (i+2) == 0) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        System.out.print("Nhập một số nguyên dương bất kỳ: ");
        long n = inp.nextLong();
        if (isPrime(n)) {
            System.out.println(n + " là 1 số nguyên tố!");
        } else {
            System.out.println(n + " không phải là số nguyên tố!");
        }
    }
}