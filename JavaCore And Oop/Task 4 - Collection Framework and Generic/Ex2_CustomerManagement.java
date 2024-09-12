import java.util.HashMap;
import java.util.Scanner;

public class Ex2_CustomerManagement {
    // Khai báo đối tượng customers thuộc lớp HashMap, key và value đều có kiểu String
    public static HashMap<String, String> customers = new HashMap<>();

    public static void main(String[] args) {
        InitCustomers();
        Scanner inp = new Scanner(System.in);
        int opt;
        do {
            System.out.println("\nMenu quản lý khách hàng:");
            System.out.println("\t1. Hiển thị danh sách khách hàng (1)");
            System.out.println("\t2. Tìm kiếm khách hàng theo mã   (2)");
            System.out.print("=> Nhập tùy chọn: ");
            opt = inp.nextInt(); inp.nextLine();
            switch (opt) {
                case 1: showCustomers(); break;
                case 2:
                    System.out.print("Nhập mã khách hàng cần tìm: ");
                    String id = inp.nextLine();
                    searchCustomerById(id);
                    break;
                default:
                    System.out.println("Kết thúc!"); return;
            }
        } while (true);
    }

    // Tìm kiếm khách hàng theo mã
    public static void searchCustomerById(String id) {
        // Trả về thông tin KH nếu mã tồn tại, ngược lại trả về null
        String result = customers.get(id);
        // Nếu có thông tin khách hàng
        if (result != null) {
            System.out.println("Khách hàng: " + result);
        } else { // Ngược lại
            System.out.println("Không có khách hàng có mã " + id);
        }
    }

    public static void InitCustomers() {
        // put(key, value): Thêm một cặp key-value vào HashMap
        customers.put("C001", "Nguyễn Văn A");
        customers.put("C002", "Trần Văn B");
        customers.put("C003", "Hồ Thị C");
        customers.put("C004", "Nguyễn Ngọc D");
        customers.put("C005", "Trần Văn E");
    }

    // Hiển thị danh sách khách hàng
    public static void showCustomers() {
        // Kiểm tra nếu map rỗng, in thông báo và kết thúc
        if (customers.isEmpty()) {
            System.out.println("Danh sách trống!");
            return;
        }
        System.out.println("Danh sách khách hàng:");
        int stt = 1;
        // Duyệt HashMap thông qua for-each và key
        // keySet(): trả về 1 set các key trong HashMap
        for (String key : customers.keySet()) {
            System.out.print("\t" + stt++ + ":");
            System.out.println("\tMã khách hàng: " + key);
            System.out.println("\t\tHọ tên khách hàng: " + customers.get(key));
        }
    }
}
