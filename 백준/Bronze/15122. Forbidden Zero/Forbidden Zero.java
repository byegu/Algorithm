import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer n = sc.nextInt() + 1;

        n = Integer.parseInt(n.toString().replaceAll("0", "1"));

        System.out.print(n);
    }
}