import java.util.Scanner;

public class Main {
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
            case '+':
                res = a + b;
                break;
            case '-':
                res = a - b;
                break;
            case 'x':
                res = a * b;
                break;
            case ':':
                res = a / b;
                break;
            default:
                System.out.println("Không có phép toán tương ứng!");
                return;
        }

        System.out.printf("Kết quả: %f %c %f = %f", a, opt, b, res);
    }
}