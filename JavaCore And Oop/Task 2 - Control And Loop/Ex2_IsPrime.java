import java.util.Scanner;

public class Ex2_IsPrime {
    public static boolean isPrime(long n) {
        // Kiểm tra nếu n <= 3 và n > 1 thì n là số nguyên tố và ngược lại
        if (n <= 3) return n > 1;
        // Nếu n là số chẵn hoặc số chia hết cho 3 thì không phải là số nguyên tố
        if (n % 2 == 0 || n % 3 == 0) return false;
        long temp = (long)Math.sqrt(n);
        // Số nguyên tố lớn hơn 3 có dạng 6k +- 1
        for (int i = 5; i <= temp; i += 6) {
            // Nếu n chia cho bất kỳ số nguyên tố nào nhỏ hơn thì không phải là số nguyên tố
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
        // Nếu n là số nguyên tố
        if (isPrime(n)) {
            System.out.println(n + " là một số nguyên tố!");
        } else { // Ngược lại
            System.out.println(n + " không phải là số nguyên tố!");
        }
    }
}