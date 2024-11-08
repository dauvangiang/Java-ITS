import java.util.Scanner;

public class Ex2_TempConversion {
    public static final float FACTOR = 9/5f;
    public static final float OFFSET = 32f;

    //Chuyển nhiệt độ từ Celsius sang Fahrenheit
    public static float cToF(float value) {
        return value * FACTOR + OFFSET;
    }

    //Chuyển nhiệt độ từ Fahrenheit sang Celsius
    public static float fToC(float value) {
        return (value - OFFSET) / FACTOR;
    }

    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);

        System.out.println("Đơn vị chuyển đổi:");
        System.out.println("\tCelsius -> Fahrenheit (1)");
        System.out.println("\tFahrenheit -> Celsius (2)");
        System.out.print("=> Chọn cách chuyển đổi nhiệt độ: ");

        int type = inp.nextInt();

        if (type != 1 && type != 2) {
            System.out.println("Không có kiểu chuyển đổi phù hợp!");
            return;
        }

        System.out.print("Nhập giá trị nhiêt độ cần đổi: "); float val = inp.nextFloat();

        float newVal = 0;

        // Kiểm tra đầu vào của người dùng
        // Nếu là 1, thực hiện đổi từ C sang F
        if (type == 1) newVal = cToF(val);
            // Nếu là 2, đổi từ F sang C
        else newVal = fToC(val);

        System.out.println("Nhiệt độ sau khi chuyển đổi là: " + newVal);
    }
}
