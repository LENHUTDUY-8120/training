package deviceManager.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import deviceManager.dao.CategoryDao;
import deviceManager.dao.DeviceDao;
import deviceManager.model.Category;
import deviceManager.model.DeviceAdd;
import deviceManager.model.DeviceGet;
import deviceManager.util.DateUtil;

public class application {

	public static int validateIsNumberInput(String input) {
		while (true) {
			try {
				int num = Integer.parseInt(input);
				return num;
			} catch (Exception e) {
				System.out.println("Please enter a number!!");
			}
		}
	}

	public static int validateIsNumberInputUpdate(String input) {
		if ("x".equals(input)) {
			return 0;
		}
		while (true) {
			try {
				int num = Integer.parseInt(input);
				return num;
			} catch (Exception e) {
				System.out.println("Please enter a number!!");
			}
		}
	}

	public static int validateIsNumberInput(String input, int from, int to) {
		while (true) {
			try {
				int num = Integer.parseInt(input);
				if (num >= from && num <= to) {
					return num;
				} else {
					System.out.println("Please enter" + from + " => " + to);
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
			int num = validateIsNumberInput(input, 1, 8);
			return num;
		}
	}

	public static int validateSubMenuInput(Scanner sc) {
		String input = new String();
		while (true) {
			input = sc.nextLine();
			int num = validateIsNumberInput(input, 1, 4);
			return num;
		}
	}

	public static int checkHaveCategory(List<Category> categories, Scanner sc) {

		while (true) {
			String input = sc.nextLine();
			int categoryId = validateIsNumberInput(input);
			for (Category category : categories) {
				if (category.getId() == categoryId) {
					return categoryId;
				}
			}
			System.out.println("Category invalid!!!");
		}
	}

	public static int checkHaveCategoryUpdate(List<Category> categories, Scanner sc) {

		while (true) {
			String input = sc.nextLine();
			if ("x".equals(input)) {
				return 0;
			}
			int categoryId = validateIsNumberInput(input);
			for (Category category : categories) {
				if (category.getId() == categoryId) {
					return categoryId;
				}
			}
			System.out.println("Category invalid!!!");
		}
	}

	public static String validateDateInput(Scanner sc) {
		String input;
		while (true) {
			input = sc.nextLine();
			if (DateUtil.validateDateFormatYYYYMMDD(input)) {
				return input;
			} else {
				System.out.println("Date input invalid. Please enter again!");
			}
		}
	}

	public static String validateDateInputUpdate(Scanner sc) {
		String input;
		while (true) {
			input = sc.nextLine();
			if ("x".equals(input)) {
				return input;
			}
			if (DateUtil.validateDateFormatYYYYMMDD(input)) {
				return input;
			} else {
				System.out.println("Date input invalid. Please enter again!");
			}
		}
	}

	public static String validateNullInput(Scanner sc) {
		String input;
		while (true) {
			input = sc.nextLine();
			if (StringUtils.isNotBlank(input)) {
				return input;
			} else {
				System.out.println("Date input invalid. Please enter again!");
			}
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		DeviceDao deviceDao = new DeviceDao();
		CategoryDao categoryDao = new CategoryDao();

		List<DeviceGet> devices = new ArrayList<DeviceGet>();

		boolean isHomeMenu = true;
		boolean showDevice = false;
		while (true) {

			if (showDevice) {
				if (devices.size() == 0) {
					System.out.println("Don't have any Device!!");
				} else {
					devices.forEach(device -> System.out.println(device.toString()));
				}
			}

			/*
			 * Show Menu
			 */
			if (isHomeMenu) {
				System.out.println("\nList Device:\n" + "1.All || 2.By Category  || 3.By Warranty || 4.Price>100k"
						+ " || 5.Broken || 6.Expired || 7. Unexpired || 8.Search || 9.Exit");
				int key = validateMenuInput(sc);
				switch (key) {
				case 1:
					devices = deviceDao.getAll();
					showDevice = true;
					isHomeMenu = false;
					break;
				case 2:
					List<Category> categories = categoryDao.getAll();
					categories.forEach(category -> System.out.println(category.toString()));
					System.out.println("Choose category");
					int categoryId = checkHaveCategory(categories, sc);
					devices = deviceDao.findByCategory(categoryId);
					showDevice = true;
					isHomeMenu = false;
					break;
				case 3:
					System.out.println("\n1.Still || 2.Out of");
					String input = sc.nextLine();
					int option = validateIsNumberInput(input, 1, 2);
					if (option == 1) {
						devices = deviceDao.listByWarranty(true);
					} else {
						devices = deviceDao.listByWarranty(false);
					}
					showDevice = true;
					isHomeMenu = false;
					break;
				case 4:
					devices = deviceDao.getByPriceMoreThan100k();
					showDevice = true;
					isHomeMenu = false;
					break;
				case 5:
					devices = deviceDao.getBrokenDevice();
					showDevice = true;
					isHomeMenu = false;
					break;
				case 6:
					devices = deviceDao.listByExpiryDate(false);
					showDevice = true;
					isHomeMenu = false;
					break;
				case 7:
					devices = deviceDao.listByExpiryDate(true);
					showDevice = true;
					isHomeMenu = false;
					break;
				case 8:
					System.out.println("Enter device name: ");
					String name = sc.nextLine();
					devices = deviceDao.searchByName(name);
					showDevice = true;
					isHomeMenu = false;
					break;
				case 9:
					System.exit(0);
					break;
				}
			} else {
				System.out.println("\n1.Add || 2.Delete || 3. update || 4.List Device");
				int key = validateSubMenuInput(sc);
				switch (key) {
				case 1:
					DeviceAdd deviceAdd = new DeviceAdd();
					System.out.println("Device info:");
					System.out.println("Name: ");
					deviceAdd.setName(validateNullInput(sc));
					System.out.println("expiryDate(yyyy-mm-dd): ");
					deviceAdd.setExpiryDate(validateDateInput(sc));
					System.out.println("manufacturingDate(yyyy-mm-dd): ");
					deviceAdd.setManufacturingDate(validateDateInput(sc));
					System.out.println("Price: ");
					deviceAdd.setPrice(validateIsNumberInput(sc.nextLine()));
					System.out.println("Status: 1.OK  ||  2.Broken");
					String keyStatus = sc.nextLine();
					int status = validateIsNumberInput(keyStatus, 1, 2);
					if (status == 1) {
						deviceAdd.setStatus("OK");
					} else {
						deviceAdd.setStatus("BROKEN");
					}
					System.out.println("Buy Date(yyyy-mm-dd): ");
					deviceAdd.setBuyDate(validateDateInput(sc));
					System.out.println("Warranty Month: ");
					deviceAdd.setWarrantyMonth(validateIsNumberInput(sc.nextLine()));
					List<Category> categories = categoryDao.getAll();
					categories.forEach(category -> System.out.println(category.toString()));
					System.out.println("Enter categoryId: ");
					deviceAdd.setCategoryId(checkHaveCategory(categories, sc));

					deviceDao.save(deviceAdd);
					isHomeMenu = true;
					showDevice = false;
					break;
				case 2:
					System.out.println("Enter deviceId to delete: ");
					String deviceIdDel = validateNullInput(sc);
					if (deviceDao.getOneById(deviceIdDel) != null) {
						deviceDao.delete(deviceIdDel);
					} else {
						System.out.println("DeviceId invalid!!!");
					}
					isHomeMenu = true;
					showDevice = false;
					break;
				case 3:
					System.out.println("Enter deviceId to update: ");
					String deviceIdUpdate = validateNullInput(sc);
					DeviceGet deviceOld = deviceDao.getOneById(deviceIdUpdate);
					if (deviceOld != null) {
						System.out.println(deviceOld.toString());

						DeviceAdd deviceAddUpdate = new DeviceAdd();
						deviceAddUpdate.setId(deviceOld.getId());
						System.out.println("Device info: ");

						System.out.println("Name(enter x to skip): ");
						String name = validateNullInput(sc);
						if ("x".equals(name)) {
							deviceAddUpdate.setName(deviceOld.getName());
						} else {
							deviceAddUpdate.setName(name);
						}

						System.out.println("expiryDate(yyyy-mm-dd, enter x to skip): ");
						String expiryDate = validateDateInputUpdate(sc);
						if ("x".equals(expiryDate)) {
							deviceAddUpdate.setExpiryDate(deviceOld.getExpiryDate().toString());
						} else {
							deviceAddUpdate.setExpiryDate(expiryDate);
						}

						System.out.println("manufacturingDate(yyyy-mm-dd, enter x to skip): ");
						String manufacturingDate = validateDateInputUpdate(sc);
						if ("x".equals(manufacturingDate)) {
							deviceAddUpdate.setManufacturingDate(deviceOld.getManufacturingDate().toString());
						} else {
							deviceAddUpdate.setManufacturingDate(manufacturingDate);
						}

						System.out.println("Price(enter x to skip): ");
						int price = validateIsNumberInputUpdate(sc.nextLine());
						if (price == 0) {
							deviceAddUpdate.setPrice(deviceOld.getPrice());
						} else {
							deviceAddUpdate.setPrice(price);
						}

						System.out.println("Status(enter x to skip): 1.OK  ||  2.Broken");
						String keyStatusUpdate = sc.nextLine();
						if ("x".equals(keyStatusUpdate)) {
							deviceAddUpdate.setStatus(deviceOld.getStatus());
						} else {
							int statusUpdate = validateIsNumberInput(keyStatusUpdate, 1, 2);
							if (statusUpdate == 1) {
								deviceAddUpdate.setStatus("OK");
							} else {
								deviceAddUpdate.setStatus("BROKEN");
							}
						}

						System.out.println("Buy Date(yyyy-mm-dd, enter x to skip): ");
						String buyDate = validateDateInputUpdate(sc);
						if ("x".equals(buyDate)) {
							deviceAddUpdate.setBuyDate(deviceOld.getBuyDate().toString());
						} else {
							deviceAddUpdate.setBuyDate(buyDate);
						}

						System.out.println("Warranty Month(enter x to skip): ");
						int warrantyMonth = validateIsNumberInputUpdate(sc.nextLine());
						if (warrantyMonth == 0) {
							deviceAddUpdate.setWarrantyMonth(deviceOld.getWarrantyMonth());
						} else {
							deviceAddUpdate.setWarrantyMonth(warrantyMonth);
						}

						List<Category> categoriesUpdate = categoryDao.getAll();
						categoriesUpdate.forEach(category -> System.out.println(category.toString()));
						System.out.println("Enter categoryId(enter x to skip): ");
						int categoryId = checkHaveCategoryUpdate(categoriesUpdate, sc);
						if (categoryId == 0) {
							deviceAddUpdate.setCategoryId(deviceOld.getCategoryId());
						} else {
							deviceAddUpdate.setCategoryId(categoryId);
						}
						deviceDao.save(deviceAddUpdate);
						isHomeMenu = false;
						showDevice = true;
					} else {
						System.out.println("DeviceId invalid!!!");
						isHomeMenu = false;
						showDevice = true;
					}
					break;
				case 4:
					isHomeMenu = true;
					showDevice = false;
					break;
				}
			}

		}

	}

}
