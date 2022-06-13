package javaConcurency;

public class TestJavaRunable{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Start of main thread");
	      Thread ping = new Ping();
	      Thread pong = new Pong();
	      ping.start();   // async
	      pong.start();   // async
	      System.out.println("End of main thread");
	}

}
