import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Ex1_BooksManagement {
    private static final Scanner inp = new Scanner(System.in);
    private static final String FILE_PATH = "listBook.txt";

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Thêm sách mới");
            System.out.println("2. Hiển thị tất cả sách");
            System.out.println("0. Kết thúc");
            System.out.print("=> Nhập tùy chọn: ");
            int opt = inp.nextInt(); inp.nextLine();
            switch (opt) {
                case 1: addBook(); break;
                case 2: showBooks(); break;
                case 0: System.out.println("Kết thúc!"); return;
                default: System.out.println("Tùy chọn không đúng"); break;
            }
        }
    }

    private static void addBook () {
        System.out.print("Tên sách: ");
        String name = inp.nextLine().trim();
        System.out.print("Tác giả: ");
        String author = inp.nextLine().trim();
        System.out.print("Năm xuất bản: ");
        String year = inp.nextLine().trim();

        String book = String.format("%s; %s; %s\n", name, author, year);

        // Sử dụng try-with-resources: tự động đóng FileWriter
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(book); //Ghi nội dung vào file
            System.out.println("Thêm sách vào file thành công!");
        } catch (IOException e) { //Bắt ngoại lệ để xử lý
            System.out.println("Đã có lỗi xảy ra khi ghi file!");
        }
    }

    private static void showBooks() {
        ArrayList<String[]> listBook = new ArrayList<>();
        // Sử dụng try-with-resources: tự động đóng BufferedReader
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))){
            String line;
            //Đọc từng dòng trong file cho đến khi đọc hết
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    listBook.add(line.split("; "));
                }
            }
            if (listBook.isEmpty()) {
                System.out.println("Danh sách trống!");
            } else {
                for (int i = 0; i < listBook.size(); i++) {
                    String[] book = listBook.get(i);
                    System.out.printf("%d. %s\n\tTác giả: %s\n\tNăm xuất bản: %s\n", i+1, book[0], book[1], book[2]);
                }
            }
        } catch (IOException e) { // Bắt ngoại lệ
            System.out.println("Đã có lỗi xảy ra khi đọc file!");
        }
    }
}
