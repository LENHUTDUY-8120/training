package train;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class JavaChannel {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		RandomAccessFile file = new RandomAccessFile("\\Users\\duy.le.nhut\\Desktop\\test.txt", "r");
		FileChannel fileChannel = file.getChannel();
		ByteBuffer byteBuffer = ByteBuffer.allocate(512);
		while(fileChannel.read(byteBuffer) > 0) {
			byteBuffer.flip();
			while (byteBuffer.hasRemaining()) {
	            System.out.print((char) byteBuffer.get());
	         }
		}
		file.close();
	}
}
