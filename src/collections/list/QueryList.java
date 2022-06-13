package collections.list;

import java.util.ArrayList;
import java.util.Scanner;

public class QueryList {

	public static void main(String[] args) {
			
		Scanner scanner = new Scanner(System.in);
        int numberRowList = scanner.nextInt();
        
        ArrayList[] set = new ArrayList[numberRowList];
        for(int i=0;i<numberRowList;i++) {
            set[i] = new ArrayList<>();
            for (int j=0;j<numberRowList;j++) {
                set[i].add(scanner.nextInt());
            }
        }
        int numerQueries = scanner.nextInt();
        for(int k=0; k<=numerQueries;k++) {
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            System.out.println(set[row-1].get(col-1));
        }
	}

}
