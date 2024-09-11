import java.util.Scanner;

public class Ex3_Caculator {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);

        double a = 0, b = 0;
        System.out.print("Số thứ nhất: "); a = inp.nextDouble();
        System.out.print("Số thứ hai: "); b = inp.nextDouble();

        System.out.println("Các phép toán:");
        System.out.println("a + b = ? (+)");
        System.out.println("a - b = ? (-)");
        System.out.println("a x b = ? (x)");
        System.out.println("a : b = ? (:)");
        System.out.print("Nhập phép toán tương ứng: ");
        char opt = inp.next().charAt(0); //Lấy kí tự đầu tiên của chuỗi được nhập vào

        double res = 0;
        switch (opt) {
            case '+': // Nếu là phép cộng (+)
                res = a + b;
                break;
            case '-': // Nếu là phép trừ (-)
                res = a - b;
                break;
            case 'x': // Nếu là phép nhân (x)
                res = a * b;
                break;
            case ':': // Nếu là phép chia (:)
                res = a / b;
                break;
            default: // Nếu người dùng chọn bất kỳ ký tự nào không hợp lệ
                System.out.println("Không có phép toán tương ứng!");
                return;
        }

        System.out.println("Kết quả: " + a + " " + opt + " " + b + " = " + res);
    }
}