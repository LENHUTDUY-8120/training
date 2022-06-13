package train;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataOutputStreamExample {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		DataOutputStream dos = null;
		
		try {
			dos = new DataOutputStream(new FileOutputStream("\\Users\\duy.le.nhut\\Desktop\\test.txt"));
			dos.writeBytes("hello world!!!");
			dos.flush();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			dos.close();
		}
	}

}
