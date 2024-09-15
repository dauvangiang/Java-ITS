public class Ex2_ShapeManagement {
    public static void main(String[] args) {
        Circle circle = new Circle(3);
        System.out.printf("Diện tích hình tròn bán kính %.3f la %.3f\n", circle.getR(), circle.calculateArea());

        Rectangle rec = new Rectangle(7, 4);
        System.out.printf("Diện tích hình chữ nhật (%.3f x %.3f) là: %.3f", rec.getLength(), rec.getWidth(), rec.calculateArea());
    }

}

//Shape: Giao dien de cac lop ke thua va trien khai
interface Shape {
    float calculateArea();
}

// Triển khai lớp Circle từ Shape interface
class Circle implements Shape {
    private static final float PI = 3.1415F;
    private final float r;

    public Circle(float r) {
        this.r = r;
    }

    public float getR () { return r; }

    //Triển khai phương thức trong Interface
    public float calculateArea() {
        return r * r * PI;
    }
}

// Triển khai lớp Rectangle từ Shape interface
class Rectangle implements Shape {
    private final float length;
    private final float width;

    public Rectangle(float length, float width) {
        this.length = length;
        this.width = width;
    }

    public float getLength() { return length; }
    public float getWidth() { return width; }

    //Triển khai phương thức calculateArea() trong Interface
    public float calculateArea() {
        return length * width;
    }
}