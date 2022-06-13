package javaConcurency;

import java.util.Random;

public class Ping extends Thread{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0; i < 3; i++) {
			try {
				Thread.sleep(new Random().nextInt(4000));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			System.out.println("ping");
		}
	}
}
