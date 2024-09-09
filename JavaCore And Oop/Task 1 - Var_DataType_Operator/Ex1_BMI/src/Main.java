// Import lớp Scanner từ java.util để đọc đầu vào từ người dùng
import java.util.Scanner;

public class Main {

    // Hàm tính chỉ số BMI từ chiều cao và cân nặng
    public static float BMI(float height, float weight) {
        final float bmi = weight / (height * height); //Tính chỉ số BMI và gán cho biến bmi (Kiểu số thực)
        return bmi; // Trả về kết quả
    }

    // Hàm main
    public static void main(String[] args) {
        // Tạo đối tượng của lớp Scanner để sử dụng các phương thức đọc đầu vào
        Scanner inp = new Scanner(System.in);

        System.out.print("Nhập chiều cao (m): ");
        float height = inp.nextFloat(); //Đọc giá trị đầu vào kiểu số thực gán cho biến height
        System.out.print("Nhập cân nặng (kg): ");
        float weight = inp.nextFloat(); //Đọc giá trị đầu vào kiểu số thực gán cho biến weight

        final float bmi = BMI(height, weight); //Gọi hàm BMI tính chỉ só BMI và gán cho biến bmi (kiểu số thực)

        // Hiển thị chỉ số BMI ra màn hình
        System.out.println("Chỉ số BMI là: " + BMI(height, weight));

        // Khai báo biến res kiểu String và gàn giá trị ban đầu là "bình thường"
        String res = "bình thường";

        // Kiểm tra chỉ số BMI để tìm ra kết quả đánh giá tương ứng
        // Nếu dưới 18.5 là "gầy"
        if (bmi < 18.5) {
            res = "gầy";
        } else if (25 <= bmi && bmi < 30) { //Từ 25 đến 30 là "thừa cân"
            res = "thừa cân";
        } else if (bmi >= 30) { //Từ 30 trở lên là "béo phì"
            res = "béo phì";
        }

        // Hiển thị đánh giá ra màn hình
        System.out.println("Bạn là người: " + res);
    }
}