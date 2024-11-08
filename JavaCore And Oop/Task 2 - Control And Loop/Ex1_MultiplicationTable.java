public class Ex1_MultiplicationTable {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Bảng nhân " + i + ":\t\tBảng chia " + i + ":");
            for (int j = 1; j <= 10; j++) {
                System.out.printf("%d x %d = %d\t\t\t%d : %d = %d\n", i, j, i*j, i*j, i, j);
            }
            System.out.println();
        }
    }
}