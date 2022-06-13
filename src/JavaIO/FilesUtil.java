package JavaIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FilesUtil {

	public static File createFile(String path, String name) {
		String filePath = path + "\\" + name;
		System.out.println(filePath);
		File file = new File(path + "\\" + name);
		try {
			if (file.createNewFile()) {
				System.out.println("File is created!");
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	public static void readFile(File file) {

		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);

			String line = bufferedReader.readLine();
			while (line != null) {
				System.out.println(line);
				line = bufferedReader.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void writeFile(File file, String content) {
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;

		try {
			fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void appendFile(File file, String content) {
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;

		try {
			fileWriter = new FileWriter(file, true);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void deleteFileByExtend(String folder, String ext) {
		ExtFilter filter = new ExtFilter(".txt");
		File dir = new File(folder);

		if (dir.isDirectory() == false) {
			System.out.println("Directory does not exists : " + folder);
		} else {
			// list out all the file name and filter by the extension
			String[] list = dir.list(filter);
			if (list.length == 0) {
				System.out.println("no files end with : " + ext);
			} else {
				for (String file : list) {
					String temp = new StringBuffer(folder).append(File.separator).append(file).toString();
					File fileDelete = new File(temp);
					fileDelete.delete();
				}
			}
		}
	}

	public static void listFileByExtend(String folder, String ext) {
		ExtFilter filter = new ExtFilter(".txt");
		File dir = new File(folder);

		if (dir.isDirectory() == false) {
			System.out.println("Directory does not exists : " + folder);
		} else {
			// list out all the file name and filter by the extension
			String[] list = dir.list(filter);
			if (list.length == 0) {
				System.out.println("no files end with : " + ext);
			} else {
				for (String file : list) {
					String temp = new StringBuffer(folder).append(File.separator).append(file).toString();
					System.out.println("file : " + temp);
				}
			}
		}
	}

	public static void renameFile(File file, String newName) {
		File newFile = new File(new StringBuffer(file.getParentFile().getAbsolutePath()).append(File.separator)
				.append(newName).toString());
		file.renameTo(newFile);
	}

	public static void copyFile(File file, String newPath) {
		InputStream inStream = null;
		OutputStream outStream = null;

		try {
			inStream = new FileInputStream(file);
			outStream = new FileOutputStream(new File(newPath));

			int length;
			byte[] buffer = new byte[1024];
			while ((length = inStream.read(buffer)) > 0) {
				outStream.write(buffer, 0, length);
			}
			System.out.println("File is copied successful!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inStream.close();
				outStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void moveFile(File file, String newPath) {
		File newFile = new File(newPath);
		try {
			if (newFile.createNewFile()) {
				copyFile(file, newPath);
				file.delete();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void listFileInFolder(File dir) {
		if (dir.isDirectory()) {
			System.out.println(dir.getAbsolutePath());
			File[] children = dir.listFiles();
			if (children == null) {
				return;
			}
			Arrays.sort(children, new Comparator<File>() {
				@Override
				public int compare(File o1, File o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});
			for (File file : children) {
				listFileInFolder(file);
			}
		} else {
			System.out.println(dir.getAbsolutePath());
		}
	}

	public static void deleteFileInFolder(File dir) {
		if (dir.isDirectory()) {
			File[] children = dir.listFiles();
			if (children == null) {
				return;
			}
			for (File file : children) {
				deleteFileInFolder(file);
			}
			if (dir.list().length == 0) {
				dir.delete();
				System.out.println("Folder bi da bi xoa " + dir.getAbsolutePath());
			}
		} else {
			dir.delete();
			System.out.println("File bi da bi xoa " + dir.getAbsolutePath());
			;
		}
	}

	public static void deleteFolder(File file) {
		if (file.isDirectory()) {
			// liet ke tat ca thu muc va file
			String[] files = file.list();
			for (String child : files) {
				File childDir = new File(file, child);
				if (childDir.isDirectory()) {
					// neu childDir la thu muc thi goi lai phuong thuc deleteDir()
					deleteFolder(childDir);
				} else {
					// neu childDir la file thi xoa
					childDir.delete();
					System.out.println("File bi da bi xoa " + childDir.getAbsolutePath());
				}
			}
			// Check lai va xoa thu muc cha
			if (file.list().length == 0) {
				file.delete();
				System.out.println("Folder bi da bi xoa " + file.getAbsolutePath());
			}

		} else {
			// neu file la file thi xoa
			file.delete();
			System.out.println("File bi da bi xoa " + file.getAbsolutePath());
		}
	}

	public static void zipFile(File file, String zipFilePath) {
		FileOutputStream fileOutputStream = null;
		FileInputStream fileInputStream = null;
		ZipOutputStream zipOutputStream = null;

		try {
			fileInputStream = new FileInputStream(file);
			fileOutputStream = new FileOutputStream(new File(zipFilePath));
			zipOutputStream = new ZipOutputStream(fileOutputStream);

			ZipEntry zipEntry = new ZipEntry(file.getName());
			zipOutputStream.putNextEntry(zipEntry);

			byte[] buf = new byte[1024];
			int length;

			while ((length = fileInputStream.read(buf)) > 0) {
				zipOutputStream.write(buf, 0, length);
			}
			System.out.println("File " + file + " da duoc zip thanh file " + zipFilePath);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileInputStream.close();
				zipOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void unzip(File file, String destPath) {
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		ZipInputStream zipInputStream = null;
		File destDir = new File(destPath);

		try {
			fileInputStream = new FileInputStream(file);
			zipInputStream = new ZipInputStream(fileInputStream);

			byte[] buf = new byte[1024];
			ZipEntry zipEntry = zipInputStream.getNextEntry();

			while (zipEntry != null) {
				File newFile = new File(destDir, zipEntry.getName());
				String destDirPath = destDir.getCanonicalPath();
				String desFilePath = newFile.getCanonicalPath();
				if (!desFilePath.startsWith(destDirPath + File.separator)) {
					throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
				}
				if (zipEntry.isDirectory()) {
					if (!newFile.isDirectory() && !newFile.mkdirs()) {
						throw new IOException("Failed to create directory " + newFile);
					}
				} else {
					File parent = newFile.getParentFile();
					if (!parent.isDirectory() && !parent.mkdirs()) {
						throw new IOException("Failed to create directory " + parent);
					}
					fileOutputStream = new FileOutputStream(newFile);
					int length;
					while ((length = zipInputStream.read(buf)) > 0) {
						fileOutputStream.write(buf, 0, length);
					}
					fileOutputStream.close();
				}
				zipEntry = zipInputStream.getNextEntry();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				zipInputStream.closeEntry();
				zipInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
//		String folder1 = "D:\\folder1";
//		String folder2 = "D:\\folder2";
//		File zipFile = new File("D:\\folder1\\8120.zip");
		/*
		 * createFile(folder1, "newfile.txt"); writeFile(file, "alo 1234");
		 * appendFile(file, "alo 3214"); readFile(file); listFileByExtend(folder1,
		 * "txt"); renameFile(file, "8120.txt"); createFile(folder2, "copy.txt");
		 * copyFile(file, folder2+"\\"+"copy.txt"); File dir1 = new File(folder1); File
		 * dir2 = new File(folder2); deleteFileInFolder(dir1);
		 * System.out.println("/////"); deleteFolder(dir2);
		 * unzip(zipFile,"D:\\folder3");
		 */
		//zipFile(file, "D:\\folder1\\8120.zip");
	}
	
}
