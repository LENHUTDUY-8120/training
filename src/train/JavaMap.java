package train;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JavaMap {
	public static void main(String[] args) {
		Map<String, String> phoneBook = new HashMap<String, String>();
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.nextLine();
		for(int i=0;i<n;i++) {
			String name = new String();
			name = scanner.nextLine();
			String phoneNumber = new String();
			phoneNumber = scanner.nextLine();
			phoneBook.put(name, phoneNumber);
		}

		do {
			String query = new String();
			query = scanner.nextLine();
			if(phoneBook.containsKey(query)) {
				System.out.println(query+"="+phoneBook.get(query));
			} else {
				System.out.println("Not found");
			}
		} while (scanner.hasNext());
		
	}
}
