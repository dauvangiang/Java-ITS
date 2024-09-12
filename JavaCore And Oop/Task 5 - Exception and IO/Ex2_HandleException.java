import java.util.Scanner;
//Import lớp InputMismatchException để bắt ngoại lệ khi kiểu dữ liệu đầu vào không như mong muốn
import java.util.InputMismatchException;

public class Ex2_HandleException {
    public static void main(String[] args) {
        // Khối try-catch để kiểm soát ngoại lệ
        // Khối try: khối mã có thể gây ra ngoại lệ
        // Khối catch: Khối xử lý khi ngoại lệ xảy ra
        try (Scanner inp = new Scanner(System.in)){
            System.out.print("Hãy nhập vào một số nguyên bất kỳ: ");
            int number = inp.nextInt();
            System.out.println("Bạn đã nhập số nguyên: " + number);
        } catch (InputMismatchException e) {
            System.out.println("Dữ liệu bạn đã nhập không phải là số nguyên!");
        }
    }
}
