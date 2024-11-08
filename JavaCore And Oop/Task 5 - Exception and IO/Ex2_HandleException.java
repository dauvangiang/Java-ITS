import java.util.Scanner;
//Import lớp InputMismatchException để bắt ngoại lệ khi kiểu dữ liệu đầu vào không như mong muốn
import java.util.InputMismatchException;

public class Ex2_HandleException {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Hãy nhập vào một số nguyên bất kỳ: ");
                int number = inp.nextInt();
                System.out.println("Bạn đã nhập số nguyên: " + number);
                return;
            } catch (InputMismatchException e) {
                System.out.println("Dữ liệu bạn đã nhập không phải là số nguyên!");
                inp.skip(".*");
            }
        }
    }
}
