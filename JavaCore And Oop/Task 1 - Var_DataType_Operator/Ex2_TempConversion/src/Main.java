// Import lớp Scanner từ gói java.util để đọc dữ liệu đầu vào từ người dùng
import java.util.Scanner;

public class Main {
    // Hàm chuyển đổi nhiệt độ từ Celsius sang Fahrenheit
    public static float cToF(float value) {
        return value * 9/5 + 32; //Công thức chuyển đổi C -> F
    }

    // Hàm chuyển đổi nhiệt độ từ Fahrenheit sang Celsius
    public static float fToC(float value) {
        return (value - 32) * 5/9; //Công thức chuyển đổi F -> C
    }

    public static void main(String[] args) {
        //Tạo đối tượng từ lớp Scanner
        Scanner inp = new Scanner(System.in);

        // Hiển thị ra màn hình
        System.out.println("Đơn vị chuyển đổi:");
        System.out.println("\tCelsius -> Fahrenheit (1)");
        System.out.println("\tFahrenheit -> Celsius (2)");
        System.out.print("=> Vui lòng chọn cách chuyển đổi nhiệt độ: ");

        // Đọc giá trị kiểu số nguyên từ người dùng gán cho biến type (kiểu số nguyên)
        int type = inp.nextInt();

        // Kiểm tra lựa chọn của ngườ dùng có khớp với các giá trị đã quy ước đc phép chọn không
        // Nếu không khớp thì thực hiện khối lệnh if
        if (type != 1 && type != 2) {
            System.out.println("Không có kiểu chuyển đổi phù hợp!");
            return; // Dừng chương trình khi thực hiện xong khối lệnh
        }

        System.out.print("Nhập giá trị nhiêt độ cần đổi: "); float val = inp.nextFloat(); // Đọc giá trị kiểu số float

        float newVal = 0; //Khai báo biến newVal (kiểu số thực) và gán giá trị ban đầu bằng 0

        // Kiểm tra đầu vào của người dùng
        // Nếu là 1, thực hiện đổi từ C sang F
        if (type == 1) newVal = cToF(val);
            // Nếu là 2, đổi từ F sang C
        else newVal = fToC(val);

        // Hiển thi nhiệt độ sau khi chuyển lên màn hình
        System.out.println("Nhiệt độ sau khi chuyển đổi là: " + newVal);
    }
}