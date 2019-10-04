import java.util.Scanner;
import java.lang.Math;


public class Exercise_11{
   
   public static void main(String a[])
   {
   Scanner sc = new Scanner(System.in);
      double r, perimeter, area, Area2;
      int areaSQUARE;
      
      System.out.print("Input Radius: ");
      r = sc.nextDouble();
      
      perimeter = 2 * Math.PI * r;
      area = Math.PI * r;
      Area2 = Math.pow(area, 1);
     
      
       System.out.println("Perimeter: "+perimeter);
       System.out.println("Area: "+Area2);
   }
}