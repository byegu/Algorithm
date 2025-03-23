import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < T; i++) {
            String line = sc.nextLine();
            String[] parts = line.split(" ", 2);

            int type = Integer.parseInt(parts[0]);

            if (type == 1) {
                String[] ip = parts[1].split("\\.");
                ipv8toUnsigned(ip);
            } else if (type == 2) {
                BigInteger usint = new BigInteger(parts[1]);
                unsignedtoIpv8(usint);
            }
        }

        sc.close();
    }

    static void ipv8toUnsigned(String[] ip) {
        BigInteger unsignedValue = BigInteger.ZERO;

        for (String s : ip) {
            BigInteger part = new BigInteger(s);

            unsignedValue = unsignedValue.shiftLeft(8).or(part);
        }

        System.out.println(unsignedValue);
    }

    static void unsignedtoIpv8(BigInteger usint) {
        StringBuilder sb = new StringBuilder();

        for (int i = 7; i >= 0; i--) {
            BigInteger part = usint.shiftRight(i * 8).and(BigInteger.valueOf(0xFF));
            sb.append(part);

            if (i > 0) sb.append(".");
        }

        System.out.println(sb);
    }
}
