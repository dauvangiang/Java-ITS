import java.util.Scanner;

public class Ex2_CountChar {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        System.out.print("Nhập 1 chuỗi bất kỳ: ");
        String str = inp.nextLine();
        System.out.print("Nhập ký tự cần đếm: ");
        char c = inp.next().charAt(0);

        int cnt = 0;
        // Lặp qua từng ký tự trong chuỗi
        for (int i = 0; i < str.length(); i++) { //str.length(): độ dài chuỗi
            if (c == str.charAt(i)) { //String trong java không hỗ trợ var[index]
                cnt++;
            }
        }

        System.out.printf("Ký tự %c xuất hiện %d lần!", c, cnt);
    }
}
