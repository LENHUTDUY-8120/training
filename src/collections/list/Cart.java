package collections.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Cart {

	public static int validateQuantityInput(Scanner sc) {
		String input = new String();
		while (true) {
			input = sc.nextLine();
			try {
				int num = Integer.parseInt(input);
				if (num > 0) {
					return num;
				} else {
					System.out.println("Please enter quantity > 0");
				}
			} catch (Exception e) {
				System.out.println("Please enter a number!!");
			}
		}

	}

	public static int validateMenuInput(Scanner sc) {
		String input = new String();
		while (true) {
			input = sc.nextLine();
			try {
				int num = Integer.parseInt(input);
				if (num > 0 && num < 5) {
					return num;
				} else {
					System.out.println("Please enter 1 || 2 || 3 || 4");
				}
			} catch (Exception e) {
				System.out.println("Please enter a number!!");
			}
		}
	}

	public static String checkContainProduct(List<Product> products, Scanner sc) {

		while (true) {
			String productid = sc.nextLine();
			for (Product product : products) {
				if (product.getId().equals(productid)) {
					return productid;
				}
			}
			System.out.println("ProductId invalid!!!");
		}

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		List<Product> listProduct = new ArrayList<Product>();
		listProduct.add(new Product("P1", "A"));
		listProduct.add(new Product("P2", "B"));
		listProduct.add(new Product("P3", "C"));
		listProduct.add(new Product("P4", "D"));
		listProduct.add(new Product("P5", "E"));
		listProduct.add(new Product("P6", "F"));
		listProduct.add(new Product("P7", "G"));
		listProduct.add(new Product("P8", "H"));

		Map<String, Integer> cart = new HashMap<String, Integer>();
		int key;
		while (true) {
			/*
			 * Show Products
			 */
			System.out.println("List Product:");
			for (Product product : listProduct) {
				System.out.println("ID: " + product.getId() + " || Name: " + product.getName());
			}

			/*
			 * Show Cart
			 */
			System.out.println("\nCart:");
			for (Map.Entry<String, Integer> entry : cart.entrySet()) {
				System.out.println("ID: " + entry.getKey() + " || " + entry.getValue());
			}

			/*
			 * Show Menu
			 */
			System.out
					.println("\n" + "Options:\n" + "1.Add to Cart || 2.Remove from Cart || 3. Set quantity || 4.Exit");

			key = validateMenuInput(sc);
			String productid;
			int quantity;

			switch (key) {

			// Add to Cart
			case 1:
				System.out.print("ID Product: ");
				productid = checkContainProduct(listProduct, sc);
				System.out.print("Quantity: ");
				quantity = validateQuantityInput(sc);

				if (cart.containsKey(productid)) {
					int newQuantity = cart.get(productid).intValue() + quantity;
					cart.replace(productid, newQuantity);
				} else {
					cart.put(productid, quantity);
				}
				break;

			// Remove from Cart
			case 2:
				System.out.print("ID Product: ");
				productid = checkContainProduct(listProduct, sc);
				if (cart.containsKey(productid)) {
					cart.remove(productid);
				} else {
					System.out.println("Productid invalid!");
				}
				break;

			// Set quantity
			case 3:
				System.out.print("ID Product: ");
				productid = checkContainProduct(listProduct, sc);
				if (cart.containsKey(productid)) {
					System.out.print("Quantity: ");
					quantity = validateQuantityInput(sc);
					cart.replace(productid, quantity);
				} else {
					System.out.println("Productid invalid!");
				}
				break;

			// Exit
			case 4:
				System.exit(0);
			default:
				break;
			}
		}

	}

}
