package train;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectOutputStreamExample {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ObjectOutputStream oss = null;
		try {
			oss = new ObjectOutputStream(new FileOutputStream("\\Users\\duy.le.nhut\\Desktop\\test.txt"));
			SV sv = new SV(1, "Le Nhut Duy", "BT", 8);
			oss.writeObject(sv);
			oss.flush();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			oss.close();
		}
	}

}
