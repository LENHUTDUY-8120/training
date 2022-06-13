package train;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public class BufferedOutputStreamExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileOutputStream fout = null;
		BufferedOutputStream bout = null;
		
		try {
			fout = new FileOutputStream("\\Users\\duy.le.nhut\\Desktop\\test.txt",true);
			bout = new BufferedOutputStream(fout);
			String string = "Welcome to java";
			byte[] b = string.getBytes();
			bout.write(b);
			bout.flush();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
