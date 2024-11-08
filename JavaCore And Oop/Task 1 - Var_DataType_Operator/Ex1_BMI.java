import java.util.Scanner;

public class Ex1_BMI {
    public static final float UNDERWEIGHT_THRESHOLD = 18.5f;
    public static final float NORMAL_WEIGHT_THRESHOLD = 24.99f;
    public static final float OVERWEIGHT_THRESHOLD = 30.0f;

    //Tính chỉ số BMI từ chiều cao, cân nặng
    public static float BMI(float height, float weight) {
        return weight / (height * height);
    }

    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);

        System.out.print("Nhập chiều cao (m): ");
        float height = inp.nextFloat();
        System.out.print("Nhập cân nặng (kg): ");
        float weight = inp.nextFloat();

        final float bmi = BMI(height, weight);
        System.out.println("Chỉ số BMI là: " + BMI(height, weight));

        String res;

        if (bmi < UNDERWEIGHT_THRESHOLD) {
            res = "gầy";
        } else if (bmi <= NORMAL_WEIGHT_THRESHOLD) {
            res = "bình thường";
        } else if (bmi < OVERWEIGHT_THRESHOLD) {
            res = "thừa cân";
        } else {
            res = "béo phì";
        }

        System.out.println("Bạn là người: " + res);
    }
}
