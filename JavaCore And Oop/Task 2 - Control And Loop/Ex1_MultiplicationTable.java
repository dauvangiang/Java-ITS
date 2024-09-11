public class Ex1_MultiplicationTable {
    public static void main(String[] args) {
        // Lặp i từ 1 đến 10
        for (int i = 1; i <= 10; i++) {
            System.out.println("Bảng nhân " + i + ":");
            // Lặp j từ 1 đến 10
            for (int j = 1; j <= 10; j++) {
                System.out.printf("%d x %d = %d%n", i, j, i*j);
            }
            System.out.println();
        }
    }
}