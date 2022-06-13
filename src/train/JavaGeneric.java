package train;

import java.lang.reflect.Method;

class Printer <C>
{
   //Write your code here
   public void printArray(C[] a){
       for(int i=0;i<a.length;i++) {
    	   System.out.println(a[i]);
       }
   }
 
}

public class JavaGeneric {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Printer myPrinter = new Printer();
        Integer[] intArray = { 1, 2, 3 };
        String[] stringArray = {"Hello", "World"};
        myPrinter.printArray(intArray);
        myPrinter.printArray(stringArray);
        int count = 0;

        for (Method method : Printer.class.getDeclaredMethods()) {
            String name = method.getName();

            if(name.equals("printArray"))
                count++;
        }

        if(count > 1)System.out.println("Method overloading is not allowed!");
	}

}
