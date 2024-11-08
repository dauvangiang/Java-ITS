public class Ex1_SortingArray {
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void bubbleSort(int[] arr) {
        int size = arr.length; // Lấy kích thước mảng
        for (int i = 0; i < size - 1; i++) {
            boolean isSwapped = false;
            for (int j = 0; j < size - 1 - i; j++) {
                if (arr[j] > arr[j+1]) {
                    swap(arr, j,j+1);
                    isSwapped = true;
                }
            }
            if (!isSwapped) return;
        }
    }
    public static void main(String[] args) {
        int[] arr = {7, 12, 89, 2, 0, 45, 13, 22, 68, 4, 9, 21};
        bubbleSort(arr);
        System.out.print("Mảng sau khi sắp xếp tăng dần:");
        for (int val : arr) {
            System.out.print(" " + val + ",");
        }
    }
}
