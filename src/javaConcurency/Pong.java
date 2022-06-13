package javaConcurency;

import java.util.Random;

public class Pong extends Thread{

	 @Override
	   public void run() {
	      for (int i = 0; i < 3; i++) {
	         // simulates work that takes between from 0 to 4 sec
	         try { Thread.sleep(new Random().nextInt(4000)); } 
	         catch (InterruptedException e) { } 
	         System.out.println("pong");
	      }
	   }

}
