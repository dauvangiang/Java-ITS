import java.text.DecimalFormat;
import java.util.ArrayList;

public class Ex1_EmployeeManagement {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new FullTimeEmployee("Nguyen Van A", "0123456789", "Sale", 2000000, 12000000));
        employees.add(new PartTimeEmployee("Nguyen Thuy D", "0652341789", "Sale", 1200000, 26000, 115));

        for (Employee employee : employees) {
            employee.show();
            System.out.println();
        }
    }
}

// Lớp trừu tượng (abstract) Employee: Không thể tạo đối tượng trực tiếp từ nó, dùng làm lớp cha cho lớp khác kế thừa
abstract class Employee {
    private static int n = 1;
    private final String code;
    private String name;
    private String phone;
    private String department;
    private double benefit;

    protected Employee(String name, String phone, String department, double benefit) {
        this.code = "NV" + n++;
        this.name = name;
        this.phone = phone;
        this.department = department;
        this.benefit = benefit;
    }

    public double getBenefit() { return benefit; }

    //Phương thức trừu tượng: Các lớp con phải triển khai nó nếu không được khai báo với abstract
    public abstract double salaryReceived();

    public void show() {
        DecimalFormat df = new DecimalFormat("#,###.##");
        System.out.println("Mã nhân viên: " + this.code);
        System.out.println("Họ và tên: " + this.name);
        System.out.println("Số điện thoại: " + this.phone);
        System.out.println("Bộ phận: " + this.department);
        System.out.println("Phụ cấp: " + df.format(this.benefit));
    }
}

class FullTimeEmployee extends Employee {
    private double salary;

    public FullTimeEmployee(String name, String phone, String department, double benefit, double salary) {
        super(name, phone, department, benefit); //Gọi đến hàm tạo của lớp cha
        this.salary = salary;
    }

    public double salaryReceived() {
        return getBenefit() + salary;
    }

    public void show() {
        DecimalFormat df = new DecimalFormat("#,###.##");
        super.show(); //Gọi đến hàm show() của lớp cha
        System.out.println("Lương cứng: " + df.format(salary));
        System.out.println("Tiền lương thực nhận: " + df.format(salaryReceived()));
    }
}

class PartTimeEmployee extends Employee {
    private double salaryPerHour;
    private float workingHoursPerMonth;

    public PartTimeEmployee(String name, String phone, String department, double benefit, double salaryPerHour, float workingHoursPerMonth) {
        super(name, phone, department, benefit); //Gọi hàm tạo của lớp cha
        this.salaryPerHour = salaryPerHour;
        this.workingHoursPerMonth = workingHoursPerMonth;
    }

    public double salaryReceived() {
        return getBenefit() + salaryPerHour * workingHoursPerMonth;
    }

    public void show() {
        DecimalFormat df = new DecimalFormat("#,###.##");
        super.show(); //Gọi hàm show() của lớp cha
        System.out.println("Tiền lương mỗi giờ: " + df.format(salaryPerHour));
        System.out.println("Số giờ làm việc mỗi tháng: " + workingHoursPerMonth);
        System.out.println("Tiền lương thực nhận: " + df.format(salaryReceived()));
    }
}