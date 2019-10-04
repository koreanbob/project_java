import java.util.Scanner;

public class Exercise_7{
   
   public static void main(String a[])
   {
   Scanner sc = new Scanner(System.in);
  
      int num, product;
      System.out.print("Input Number: ");
      num = sc.nextInt();
      
      for(int i = 1; i < 10;i++)
      {
         product = num * i;
         System.out.print("\n"+num+" x "+i+" = "+product);
      }
   }
}