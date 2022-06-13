package train;

import java.util.Scanner;

public class SubArray {

	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int a[] = new int[n];
		for(int i=0;i<n;i++) {
			a[i] = scanner.nextInt();
		}
		
		int count = 0;
		for(int k=0;k<n;k++) {
			int sum=0;
			for(int j=k;j<n;j++) {
				sum += a[j];
				if(sum < 0) {
					count++;
				}
			}
		}
		System.out.println(count);
	}
}
