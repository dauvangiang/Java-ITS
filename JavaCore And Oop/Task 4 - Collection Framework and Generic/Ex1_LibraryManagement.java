import java.util.ArrayList;
import java.util.Scanner;

public class Ex1_LibraryManagement {
    public static Scanner inp = new Scanner(System.in);
    public static ArrayList<String> books = new ArrayList<>();

    // Thêm sách
    public static void addBook() {
        System.out.print("Nhập sách mới: ");
        String newBook = inp.nextLine().toUpperCase();
        books.add(newBook); // Thêm vào cuối danh sách
        System.out.println("Đã thêm sách mới vào danh sách!");
    }

    // Tìm kiếm sách
    public static void searchBook() {
        System.out.print("Nhập sách cần tìm: ");
        String book = inp.nextLine().toUpperCase();
        int idx = books.indexOf(book); // Lấy index của sách cần tìm, nếu không có => -1
        if (idx == -1) {
            System.out.println("Không tìm thấy thông tin sách!");
        } else {
            System.out.println("Tìm thấy thông tin sách tại vị trí " + idx);
        }
    }

    // Sửa sách
    public static void updateBook() {
        System.out.print("Nhập sách cần sửa: ");
        String oldBook = inp.nextLine().toUpperCase();
        System.out.print("Nhập sách thay thế: ");
        String newBook = inp.nextLine().toUpperCase();
        int idxOfOldBook = books.indexOf(oldBook);
        if (idxOfOldBook == -1) {
            books.add(newBook);
            System.out.println("Sách cần sửa không tồn tại, đã thêm sách thay thế vào danh sách!");
        } else {
            books.set(idxOfOldBook, newBook); // Cập nhật phần tử có chỉ mục idxOfOldBook bằng newBook
            System.out.println("Đã cập nhật sách thành công!");
        }
    }

    // Xóa sách
    public static void deleteBook() {
        System.out.print("Nhập sách cần xóa: ");
        String book = inp.nextLine().toUpperCase();
        int idxOfBook = books.indexOf(book);
        if (idxOfBook == -1) {
            System.out.println("Lỗi. Sách cần xóa không tồn tại!");
        } else {
            books.remove(idxOfBook); //Xóa phần tử có chỉ mục là idxOfBook
            System.out.println("Đã xóa sách thành công!");
        }
    }

    public static void showBooks() {
        if (books.isEmpty()) {
            System.out.println("Danh sách trống!");
            return;
        }
        System.out.println("Danh sách sách:");
        int stt = 1;
        // Duyệt danh sách bằng for-each
        for (String book : books) {
            System.out.println("\t" + stt + ". " + book);
            stt++;
        }
    }

    public static void showMenu() {
        System.out.println("\nMenu quản lý thư viện:");
        System.out.println("\t1. Hiển thị danh sách (1)");
        System.out.println("\t2. Tìm kiếm sách      (2)");
        System.out.println("\t3. Thêm sách mới      (3)");
        System.out.println("\t4. Cập nhật sách      (4)");
        System.out.println("\t5. Xóa sách           (5)");
        System.out.println("\t6. Kết thúc           (0)");
    }

    public static void handleOpt(int opt) {
        switch (opt) {
            case 1: showBooks(); break;
            case 2: searchBook(); break;
            case 3: addBook(); break;
            case 4: updateBook(); break;
            case 5: deleteBook(); break;
            case 0: System.out.println("Kết thúc!"); break;
            default:
                System.out.println("Không có tùy chọn phù hợp. Vui lòng chọn lại!");
                break;
        }
    }

    public static void main(String[] args) {
        int opt;
        do {
            showMenu();
            System.out.print("=> Nhập tùy chọn: "); opt = inp.nextInt();
            inp.nextLine(); //đọc tiếp ký tự \n
            handleOpt(opt);
        } while (opt != 0);
    }
}
