import java.util.Scanner;

public class Exercise_6{
   
   public static void main(String a[])
   {
   Scanner sc = new Scanner(System.in);
   
   int Fnum, Snum;
   int Sum, difference, product, quotient, remainder;
   
   System.out.print("Input First Number: ");
   Fnum = sc.nextInt();
   System.out.print("Input Second Number: ");
   Snum = sc.nextInt();
   
   Sum = Fnum + Snum;
   difference = Fnum - Snum;
   product = Fnum * Snum;
   quotient = Fnum / Snum;
   remainder = Fnum % Snum;
   
   System.out.print("\n"+Fnum+" + "+Snum+" = "+Sum);
     System.out.print("\n"+Fnum+" - "+Snum+" = "+difference);
      System.out.print("\n"+Fnum+" * "+Snum+" = "+product);
        System.out.print("\n"+Fnum+" / "+Snum+" = "+quotient);
          System.out.print("\n"+Fnum+" % "+Snum+" = "+remainder);
    
      
   }
}