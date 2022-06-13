package train;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class DataInputStreamExample {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DataInputStream dis = null;
		try {
			dis = new DataInputStream(new FileInputStream("\\Users\\duy.le.nhut\\Desktop\\test.txt"));
			int count  = dis.available();
			byte[] arr = new byte[count];
			dis.read(arr);
			for(byte bt : arr) {
				char k = (char) bt;
				System.out.print(k+"-");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			dis.close();
		}
	}

}
