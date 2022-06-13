package train;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JavaList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<n;i++) {
			list.add(scanner.nextInt());
		}
		int nquery = scanner.nextInt();
		for(int k=0;k<nquery;k++) {
			scanner.nextLine();
			String category = new String();
			category = scanner.nextLine();
			if (category.equalsIgnoreCase("Insert")) {
				int index = scanner.nextInt();
				int value = scanner.nextInt();
				list.add(index, value);
			} else {
				int index = scanner.nextInt();
				list.remove(index);
			}
		}
		list.forEach( x -> System.out.print(x + " "));
	}

}
