import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class ArrayProgram{
   private static int inputValue = 0;
   private static int arr_size = 0;
   private static int[] myArray;
   private static Scanner input = new Scanner(System.in);
   private static boolean empty;
   private static int i = 0;
   private static int index_free = 0;
   private static   List<String> myList = new ArrayList<String>();
   private static int in_size = 0;
   private static boolean IsCorrect;
   private static String rev;
   
   public static void main(String []a){
      AskSize();
   }
   public static void AskSize(){
      input = new Scanner(System.in);
      System.out.print("Input Array Size: ");
      try{
      in_size = input.nextInt();
      myArray = new int[in_size];
      Size();
      }catch(Exception x){
         System.out.println("Invalid Array Size! Please try again!");
         AskSize();
      }
   }
   
   public static void OnStart(){
    Size();
   }
   
   public static void Size(){
     try{
     arr_size = myArray.length; 
     i = availableSlots(i);
    // System.out.println("Array total Size: "+arr_size);
    // System.out.println("Array free index: "+i);
     UserSelection();
     }catch(Exception e){
       arr_size = 0; 
     i = 0;
     System.out.println("Array total Size: "+arr_size);
     System.out.println("Array free index: "+i);
     UserSelection();
     }
   }   
   
   public static int availableSlots(int v){
     v = 0;
      for(int i = 0 ; i < myArray.length;i++){
         if(myArray[i]==0){
            ++v;
         }
      }
      return v;
   } 
   public static void UserSelection(){
    System.out.println("\n=====ARRAY OPTIONS=====");
      System.out.println("\n1) Add\n2) Delete\n3) Find\n4) Insert\n5) Sort\n6) View\n7) Exit\n");
       Validate();
   }
   
   public static void Validate(){
   // Resets Value to Scanner
    input = new Scanner(System.in);
    
       System.out.print("Option: ");
      if (input.hasNextInt()){
         inputValue = input.nextInt();
         if (inputValue< 1 || inputValue > 7){
             System.out.println("Invalid code option! Please try again.");
             Validate();
         }
         else {
            CreateInstance();
         }
       }else{
         System.out.println("Invalid code option! Please try again.");
         Validate();
        }
        
      }
   
   public static void CreateInstance(){
      if (inputValue == 1)
      {
         System.out.println("\n=====ADD VALUE TO ARRAY=====\n");
        boolean full = false;
        full = isFull(full);
        //Check if Array is Full!
        if(full == true){
            System.out.println("Array is currently Full!\ntry deleting or replacing array values...\n");
            UserSelection();
        }
         System.out.print("Add Value: ");
         while(IsCorrect != true){
          try{
           int in_value = input.nextInt();
           int index_free = last_slot(i);
           myArray[index_free] = in_value;
           System.out.println("value has been successfully added to index "+index_free+"!");
           IsCorrect = false;
           UserSelection();
          }catch (Exception e){
            System.out.println("Invalid value! Please Try again!");
             IsCorrect = false;
             System.out.print("Add Value: ");
             input = new Scanner(System.in); 
            }
         }//End of While Loop
       
      }
      
      else if(inputValue == 2){
        System.out.print("Delete Index: ");
        try{
        int delete_idx = input.nextInt();
        myArray[delete(delete_idx)] = 0;
      }catch(Exception e){
        System.out.println("\nArray Index is invalid!\nTry Adding an Array length.\n");
        OnStart();
       }
      }
      
      else if(inputValue == 3){
      System.out.print("\nFind: ");
      String s = input.nextLine();
      String value = find(s);
      System.out.println("Findings: "+value);
      
      }
      
      else if(inputValue == 4){
     insert();
      } 
            
      else if(inputValue == 5){
        System.out.println("\n=====SORT ARRAY VALUES=====\n");
        System.out.println("1) Incrementing Order");
        System.out.println("2) Decrementing Order\n");
         // input = new Scanner(System.in);
      for(int i = 0;;i++){
         try{
          System.out.print("Sort Option: ");
          int option_sort = input.nextInt();
          if(option_sort == 1){
               sort(1);
               break;
          }else if(option_sort == 2){
               sort(2);
                break;
          }
          else{
                System.out.print("Invalid Sort Option! Please try again!" );
          }
         }catch(Exception e){
            System.out.print("Invalid Sort Option! Please try again!" );
          //  IsCorrect = false;
         }
        } //End of Loop
        //  sort();
      }//End of Option
      
      else if(inputValue == 6)
     view();
      
      else if(inputValue == 7)
         Exit(); 
              
   } 
   
   public static int add(int value){
    return value;
   }
   
    public static int delete(int index){
    return index;
   
   }
    public static String find(String f){ 
    int v = Integer.parseInt(f);
    for(int i = 0; i < myArray.length;i++){
      if(myArray[i] == v){
        if (i == 1){
         f = ""+i;
        }
        else{
         f = f + myArray[i];
        }
      }
      else{
         f = "Nothing...";
      }
    }
    return f;
   }
   
    public static void insert(){
    System.out.println("insert Method");

   }
   
    public static void sort(int x){
      Arrays.sort(myArray);
    System.out.println("Sorting Method");
    if(x == 1){
    System.out.println("Incrementing Order: ");
     for(int i = 0;i < myArray.length;i++){
       System.out.println(myArray[i]);
     }// end of Loop
    }else{
      System.out.println("Decrementing Order: ");
     Arrays.sort(myArray);
     // Collections.reverse(Arrays.asList(myArray));      ;
 
      for(int i = 0; i < myArray.length;i++){
      if(rev == null){
         rev = myArray[i]+"\n";
      }else if(i != 0){
         rev = rev + myArray[i]+"\n";
      }
         System.out.println(""+rev);
      }// end of loop
    }UserSelection();
       
   }
   
    public static void view(){
    System.out.println("view Method");

   }
   
    public static void Exit(){
     System.out.println("\nProgram Terminated...");
     System.exit(0);

   }
   
   public static boolean isFull(boolean full){
      int ctr = 0;
      for(int i = 0; i < myArray.length;i++){
         if(myArray[i] != 0){
            ctr++;
          } 
         } // End of Loop
          if (ctr == myArray.length){
            full = true;
         }else if(ctr != myArray.length){
            full = false;
         }
       return full;
   }
   
   public static int last_slot(int free_slot){
      
      for(int i = 0; i <= myArray.length;i++){
         if(myArray[i]==0){
            free_slot = i;
            break;
         }
      }
      return free_slot;
   }
   
   
   
}