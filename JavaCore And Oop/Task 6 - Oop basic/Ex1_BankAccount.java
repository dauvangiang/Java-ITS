import java.util.InputMismatchException;
import java.util.Scanner;

public class Ex1_BankAccount {
    public static Scanner inp = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Nhập sô tài khoản: ");
        String accNumber = inp.nextLine();
        // Tạo đối tượng lớp BankAccount với hàm tạo 1 tham số
        BankAccount acc = new BankAccount(accNumber);

        int opt = 0;
        do {
            try {
                showMenu();
                System.out.print("Nhập tùy chọn dịch vụ: ");
                opt = inp.nextInt(); inp.nextLine();
                switch (opt) {
                    case 1:
                        acc.checkBalance(); //Gọi phương thức checkBalance từ đối tượng acc
                        break;
                    case 2:
                        acc.deposit(inp); //Gọi phương thức deposit từ đối tượng acc
                        break;
                    case 3:
                        acc.withdraw(inp); //Gọi phương thức withdraw từ đối tượng acc
                        break;
                    case 4: System.out.println("Thoát!"); break;
                    default:
                        System.out.println("Tùy chọn không hợp lệ!");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Tùy chọn không hợp lệ!");
                inp.nextLine();
            }
        } while (opt != 4);
    }

    private static void showMenu() {
        System.out.println("\nMenu thực hiện giao dịch:");
        System.out.println("\t1. Kiểm tra số dư");
        System.out.println("\t2. Gửi tiền");
        System.out.println("\t3. Rút tiền");
        System.out.println("\t4. Thoát");
    }
}

class BankAccount {
    //Attributes
    private String accountNumber;
    private long balance;

    //Constructor
    // Hàm tạo mặc định
    public BankAccount() {
        this.accountNumber = "000000";
        this.balance = 0;
        System.out.print("Xin chào: 000000");
    }
    // Hàm tạo 1 tham số
    public BankAccount(String accNumber) {
        System.out.print("Xin chào: " + accNumber);
        this.accountNumber = accNumber;
        this.balance = 0;
    }
    // Hàm tạo 2 tham sô
    public BankAccount(String accNumber, long balance) {
        System.out.print("Xin chào: " + accNumber);
        this.accountNumber = accNumber;
        this.balance = balance;
    }

    //Methods
    public void deposit(Scanner inp) { //Gui tien
        System.out.print("Nhập số tiền gửi: ");
        try {
            long deposit = inp.nextLong();
            this.balance = this.balance + deposit;
            System.out.println("Bạn đã gửi thành công số tiền: " + deposit);
            this.checkBalance();
        } catch (InputMismatchException e) {
            System.out.println("Lỗi. Vui lòng nhập số tiền bằng số!");
            inp.nextLine();
        }
    }

    public void withdraw(Scanner inp) {
        try {
            System.out.print("Nhập số tiền cần rút: ");
            long withdraw = inp.nextLong();
            if (this.balance < withdraw) {
                System.out.println("Số dư của bạn không đủ!");
            } else {
                this.balance = this.balance - withdraw;
                System.out.println("Bạn đã rút thành công số tiền: " + withdraw);
                this.checkBalance();
            }
        } catch (InputMismatchException e) {
            System.out.println("Lỗi. Vui lòng nhập số tiền bằng số!");
            inp.nextLine();
        }
    }

    public void checkBalance() {
        System.out.println("Số dư của bạn là: " + this.balance);
    }
}