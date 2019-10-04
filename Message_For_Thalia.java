import java.util.Scanner;
public class Message_For_Thalia{
   
   public static int i = 0;
   public static String[] message;
   public static String disp = " ";
   public static String S = " ";
   public static String[] alpha = {"A","B","C","D","E","F","G","H",
                                   "I","J","K","L","M","N","O","P",
                                   "Q","R","S","T","U","V","W","X",
                                   "Y","Z","0","!","2","3","4","5"};
   
   public static void main(String a[]){
      Scanner sc = new Scanner(System.in);
      System.out.println("Crack The Message!");
      System.out.print("Password: ");
      String value = sc.nextLine();
      i++;
      String hint = alpha[(20-i)]+alpha[(8-i)]+alpha[(1-i)]+alpha[(12-i)]
         +alpha[(9-i)]+alpha[(1-i)];
         
      if(value.equals(hint)){
         String[] message = {alpha[(9-i)],alpha[(27-i)],alpha[(12-i)],alpha[(15-i)],alpha[(21-i)],alpha[(5-i)],alpha[(27-i)],
                    alpha[(25-i)],alpha[(15-i)],alpha[(21-i)],alpha[(27-i)],alpha[(2-i)],alpha[(1-i)],alpha[(2-i)],
                    alpha[(5-i)],alpha[(28-i)]};
                    DISPLAY_MESSAGE(message);
      }else if(value.contains(alpha[(20-i)])||!value.contains(alpha[(8-i)])||!value.contains(alpha[(1-i)])||!value.contains(alpha[(12-i)])
         ||value.contains(alpha[(9-i)])||!value.contains(alpha[(1-i)])){
         String[] message = {alpha[(14-i)],alpha[(9-i)],alpha[(3-i)],alpha[(5-i)],alpha[(27-i)],alpha[(20-i)],
                    alpha[(18-i)],alpha[(25-i)],alpha[(28-i)]};
                    DISPLAY_MESSAGE(message);
      }
      
   }
   
   private static void DISPLAY_MESSAGE(String[] m){
      
      for(int i = 0; i<m.length;i++){
         if(m[i] == alpha[(27-1)]){
            disp = disp + S;
         }else{
            disp = disp + m[i];
         }
      }
      System.out.println("\n\n"+disp);
   }
}