import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int A = scanner.nextInt();
        int B = scanner.nextInt();
        int V = scanner.nextInt();

        int count = (V - B) / (A - B);

        if ((V - B) % (A - B) != 0) {
            count++;
        }

        System.out.println(count);
    }
}
