package train;

import java.io.FileInputStream;
import java.io.IOException;

public class JavaFileInputStream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String absoluteFilePath = "\\Users\\duy.le.nhut\\Desktop\\test.txt";
		 try {
	            String content = JavaFileInputStream.getContentFile(absoluteFilePath);
	            System.out.println("-------------------------------------------------");
	            System.out.println(content);
	            System.out.println("-------------------------------------------------");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
	}
	
	public static String getContentFile(String absoluteFilePath) throws IOException {
		StringBuilder resultBuilder = new StringBuilder();
		FileInputStream fileInputStream = new FileInputStream(absoluteFilePath);
		int b;
		byte[] bytes = new byte[1024];
		int size;
		while((b = fileInputStream.read(bytes,4,bytes.length)) != -1) {
			resultBuilder.append((char) b);
		}
		fileInputStream.close();
		return resultBuilder.toString();
	}

}
