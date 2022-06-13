package train;

import java.io.FileOutputStream;
import java.io.IOException;

public class JavaFileOutputStream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String absoluteFilePath = "\\Users\\duy.le.nhut\\Desktop\\test.txt";
        String content = "duy ne";
        boolean append = true;
        
        try {
            System.out.println("----------------Writing...---------------");
            JavaFileOutputStream.writeContent(absoluteFilePath, content, append);
            System.out.println("-------------------DONE!-------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public static void writeContent(String absoluteFilePath, String content, boolean append) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(absoluteFilePath, append);
        fileOutputStream.write(content.getBytes());
        fileOutputStream.close();
    }
}
