package train;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ObjectInputStreamExample {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		ObjectInputStream ois = null;
		
		try {
			ois = new ObjectInputStream(new FileInputStream("\\Users\\duy.le.nhut\\Desktop\\test.txt"));
			SV sv = (SV) ois.readObject();
			System.out.println(sv.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ois.close();
		}
	}
}
