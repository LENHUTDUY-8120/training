package train;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Scanner;

public class IsPrime {

	public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        BigInteger n = scanner.nextBigInteger();
        System.out.print(n.isProbablePrime(1)? "prime" : "not prime");
    }

}
