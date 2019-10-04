import java.util.*;
import java.lang.*;
import java.text.*;

public class CPU_SCHED_v3_1_Alpha{
   
   private static int option = 0;
   private static Scanner sc;
   private static String s_task = "";
   private static int at = 0;
   private static int et = 0;
   private static int ctr = 0;
   private static int sub_ctr = 0;
   private static DecimalFormat df = new DecimalFormat(".##");
   
   private static ArrayList<Integer> at_bank = new ArrayList<Integer>();
   private static ArrayList<String> task_bank = new ArrayList<String>();
   private static ArrayList<Integer> et_bank = new ArrayList<Integer>();
   private static ArrayList<Integer> complete_bank = new ArrayList<Integer>();
   private static ArrayList<Integer> turn_bank = new ArrayList<Integer>();
   private static ArrayList<Integer> wait_bank = new ArrayList<Integer>();
   private static ArrayList<String> gant_bank_task = new ArrayList<String>();
   //unProcessed
   private static ArrayList<Integer> raw_at_bank = new ArrayList<Integer>();
   private static ArrayList<String> raw_task_bank = new ArrayList<String>();
   private static ArrayList<Integer> raw_et_bank = new ArrayList<Integer>();
   private static int g_value = 0;
   private static ArrayList<Integer> gant_ct_bank = new ArrayList<Integer>();
   private static ArrayList<Integer> gant_disp_bank = new ArrayList<Integer>();
   public static ArrayList<String> space_bank_left = new ArrayList<String>();
   public static ArrayList<String> space_bank_right = new ArrayList<String>();
   
   public static void main(String args[]){
      System.out.println("FCFS Scheduling Processor v3.1_Alpha (by: KoreanBob)");
      sc = new Scanner(System.in);
      do{
      System.out.println("\nSelect a process: \n1) Input a CPU Process\n2) Compute Process\n3) Display Process Bank\n4) Exit Program\n");
      System.out.print("process: ");
         try{
            sc = new Scanner(System.in);
            option = sc.nextInt();
            if(option == 1){
               sc = new Scanner(System.in);
               System.out.println("\nInput Process Function:\nInput a Task...");
               System.out.print("Task: ");
               s_task = sc.nextLine();
               
               do{
                  try{
                  sc = new Scanner(System.in);
                  System.out.println("\nInput an Arrival Time...");
                  System.out.print("Arrival Time: ");
                  at = sc.nextInt();
                  
                  if(at < 0){
                     throw new Exception();
                  }
                  sub_ctr = 0;
                  }catch(Exception e){
                     System.out.println("Invalid Arrival Time value! Please try again!");
                     sub_ctr++;
                  }
               }while(sub_ctr != 0);
               
               do{
                  try{
                  sc = new Scanner(System.in);
                  System.out.println("\nInput a Execution Time...");
                  System.out.print("Execution Time: ");
                  et = sc.nextInt();
                  
                  if(et < 0){
                     throw new Exception();
                  }
                  sub_ctr = 0;
                  }catch(Exception e){
                     System.out.println("Invalid Execution Time value! Please try again!");
                     sub_ctr++;
                  }
               }while(sub_ctr != 0);
               //for process
               at_bank.add(at);
               et_bank.add(et);
               task_bank.add(s_task);
               //For Display
               raw_at_bank.add(at);
               raw_et_bank.add(et);
               raw_task_bank.add(s_task);
               
               ctr++;  
            }else if(option == 2){
            if(task_bank.size() == 0){
               System.out.println("Please add Tasks First!");
            }else{
             Compute_Process();
             }
             ctr++;
            }
            else if(option == 3){
            if(raw_at_bank.size() == 0){
               System.out.println("Please Input a Task to Display!");
               ctr++;
            }else{
            String itm_que = "";
               sc = new Scanner(System.in);
               for(int i = 0;i<raw_at_bank.size();i++){
                  itm_que = itm_que +"Task = "+ raw_task_bank.get(i)+" | AT (Arrival Time) = "+raw_at_bank.get(i)+" | ET (Execution Time) = "+raw_et_bank.get(i)+"\n";
                 }
               
               System.out.println("\nProcess Bank View: (Unprocessed/in Queue)\n"+itm_que);
               ctr++;
             }  
            }else if(option == 4){
               System.out.println("\n~ ~ ~ GOOD BYE! ~ ~ ~");
               ctr = 0;
            }else{
               throw new Exception();
            }
         }catch(Exception e){
            System.out.println("Invalid Option! Please try again!\n"+e);
            ctr++;
         }
      }while(ctr != 0);   
   }//main
   
   public static void Compute_Process(){
      
      for(int i = 0;i<at_bank.size();i++){
         for(int j = i+1;j<at_bank.size();j++){
            int temp_at;
            String temp_task;
            int temp_et;
            if(at_bank.get(i)>at_bank.get(j)){
               // Sort AT
               temp_at = at_bank.get(i);
               at_bank.set(i, at_bank.get(j));
               at_bank.set(j,temp_at);
               //Sort ET
               temp_et = et_bank.get(i);
               et_bank.set(i, et_bank.get(j));
               et_bank.set(j,temp_et);
               //Sort Task
               temp_task = task_bank.get(i);
               task_bank.set(i, task_bank.get(j));
               task_bank.set(j, temp_task);
            }else if(at_bank.get(i) == at_bank.get(j)){
               if(et_bank.get(i)>et_bank.get(j)){
                  // Sort AT
                  temp_at = at_bank.get(i);
                  at_bank.set(i, at_bank.get(j));
                  at_bank.set(j,temp_at);
                  //Sort ET
                  temp_et = et_bank.get(i);
                  et_bank.set(i, et_bank.get(j));
                  et_bank.set(j,temp_et);
                  //Sort Task
                  temp_task = task_bank.get(i);
                  task_bank.set(i, task_bank.get(j));
                  task_bank.set(j, temp_task);
                  
               }
            }
         }// End Inner Loop
      }// End Outer Loop
      
      //----FCFS process----
      
      //Gantt Task Bank and Complete
      for(int gant = 0;gant<at_bank.size();gant++){
        //Init
        if(gant == 0){ 
               gant_bank_task.add(task_bank.get(gant)); 
               //Complete value
                g_value = at_bank.get(gant) + et_bank.get(gant);
                       
         }else{
            
            // if +1 value || if has the same value
            if(at_bank.get(gant) == (at_bank.get(gant-1)+1) || at_bank.get(gant-1) == at_bank.get(gant) || at_bank.get(gant) == gant_ct_bank.get(gant-1) || gant_ct_bank.get(gant-1) >= at_bank.get(gant)){
               gant_bank_task.add(task_bank.get(gant));
                g_value = gant_ct_bank.get(gant-1)+et_bank.get(gant);
            }
            // if !=+1 value
            else{
               gant_bank_task.add(" null ");
               gant_bank_task.add(task_bank.get(gant));
                 g_value = at_bank.get(gant)+et_bank.get(gant);
                  gant_disp_bank.add(at_bank.get(gant));
            }
         }
         gant_ct_bank.add(g_value);
         gant_disp_bank.add(gant_ct_bank.get(gant));
         
      } 
      
      //Get Completion_Time
      int ct = 0;
      for(int i = 0;i<at_bank.size();i++){
         if(i == 0){
            ct = at_bank.get(i)+et_bank.get(i);
            complete_bank.add(ct);
         }else{
            ct = ct+et_bank.get(i);
            complete_bank.add(ct);
         }
      }
      
      
      //Get Turn Around Time 
      int tat = 0;
      for(int x = 0;x<at_bank.size();x++){
         tat = gant_ct_bank.get(x) - at_bank.get(x);
         turn_bank.add(tat);
      }
      
      
      //Get Waiting Time
      int wt = 0;
      for(int y = 0;y<at_bank.size();y++){
         wt = turn_bank.get(y) - et_bank.get(y);
         wait_bank.add(wt);
      }
      
      //Get Total Execution Time
      int tet = 0;
      for(int t = 0;t<at_bank.size();t++){
         tet = tet+et_bank.get(t);
      }
      
      //Get Total Waiting Time
      int twt = 0;
      for(int l = 0;l<at_bank.size();l++){
         twt = twt+wait_bank.get(l);
      }
     
      String disp = "Processed Data: \nTask:     AT:     ET:     CT:     TAT:     WT:\n";
      String AT = "";                  
      String ET = "";
      String CT = "";
      String TAT = "";
      String WT = "";
      
      for(int g = 0;g<at_bank.size();g++){
         //Check if AT Double digits
         if(at_bank.get(g).toString().length() == 1){
            AT = " "+at_bank.get(g); 
         }else{
            AT = at_bank.get(g)+"";
         }
         
         //Check if ET Double digits
         if(et_bank.get(g).toString().length() == 1){
            ET = " "+et_bank.get(g); 
         }else{
            ET = et_bank.get(g)+"";
         }
         
         //Check if CT Double digits
         if(gant_ct_bank.get(g).toString().length() == 1){
            CT = " "+gant_ct_bank.get(g); 
         }else{
            CT = gant_ct_bank.get(g)+"";
         }
         
         //Check if TAT Double digits
         if(turn_bank.get(g).toString().length() == 1){
            TAT = " "+turn_bank.get(g); 
         }else{
            TAT = turn_bank.get(g)+"";
         }
         
         //Check if WAIT Double digits
         if(wait_bank.get(g).toString().length() == 1){
            WT = ""+wait_bank.get(g); 
         }else{
            WT = wait_bank.get(g)+"";
         }
         
         disp = disp+task_bank.get(g)+"    "+AT+"      "+ET+"      "+CT+"       "+TAT+"       "+WT+"      \n";
          
      }
      
      String blank_sp_left = "";
      String blank_sp_right = "";
      //Spaces maker
      for(int sp = 0;sp<gant_disp_bank.size();sp++){
         if(sp == 0){
            for(int space = 0;space<((gant_disp_bank.get(sp)-at_bank.get(sp))/2);space++){
               blank_sp_left = blank_sp_left+" ";
               blank_sp_right = blank_sp_right+" ";
            }
         }else{
            for(int blank_space = 0;blank_space<((gant_disp_bank.get(sp) - gant_disp_bank.get(sp-1))/2);blank_space++){
               blank_sp_left = blank_sp_left+" ";
               blank_sp_right = blank_sp_right+" ";
            }
         }
         blank_sp_left = blank_sp_left+"";
         blank_sp_right = blank_sp_right+"";
         
         space_bank_left.add(blank_sp_left);
         space_bank_right.add(blank_sp_right);
         
         blank_sp_left = "";
         blank_sp_right = "";
      }
      
     String bar = "|";
     String num = "";
     String graph = "";
     String digits = "";
     
     // sample: | Task 1 | Task 2 | Task 3 |
       //       0        12       15       20
     for(int q = 0;q<gant_bank_task.size();q++){
     //Last
     if(q == gant_bank_task.size()-1){
      graph = graph+bar+space_bank_left.get(q)+" "+gant_bank_task.get(q)+" "+space_bank_right.get(q)+bar;
     }
     //Start
     else{
      if(q == 0){
         if(at_bank.get(q) == 0){
            //graph = graph+bar+" "+gant_bank_task.get(q)+" ";
            
         }else{
            graph = graph+bar+" "+"null"+" ";   
         }
      } // Checks if there is 0 at First
       graph = graph+bar+space_bank_left.get(q)+" "+gant_bank_task.get(q)+" "+space_bank_right.get(q);
     }
       
       if(q == 0){
         if(at_bank.get(q) != 0){
            digits = digits+"0"+"      ";
               if(at_bank.get(q).toString().length() == 1){
                    digits = digits+at_bank.get(q)+"        "+space_bank_right.get(q)+space_bank_left.get(q);
                  if(gant_disp_bank.get(q).toString().length() == 1){
                    digits = digits+gant_disp_bank.get(q)+"        ";
                  }else{
                     digits = digits+gant_disp_bank.get(q)+"       ";
                  }
               }else{
                  digits = digits+at_bank.get(q)+"       "+space_bank_right.get(q)+space_bank_left.get(q);
                  if(gant_disp_bank.get(q).toString().length() == 1){
                     digits = digits+gant_disp_bank.get(q)+"        ";
                  }else{
                     digits = digits+gant_disp_bank.get(q)+"       ";
                  }
               }
         }else{
            digits = digits+at_bank.get(q)+"        "+space_bank_right.get(q)+space_bank_left.get(q);
            digits = digits+gant_disp_bank.get(q)+"        ";
         }
      } // initial
      else{
         if(gant_disp_bank.get(q).toString().length() == 1){
            digits = digits+space_bank_right.get(q)+space_bank_left.get(q)+gant_disp_bank.get(q)+"        ";
         }else{
            digits = digits+space_bank_right.get(q)+space_bank_left.get(q)+gant_disp_bank.get(q)+"       ";
        }
      }                                                
     }
     String graph_top = "";
     String graph_bot = "";
     
     for(int bor = 0;bor<graph.length();bor++){
      graph_top = graph_top+"_";
      graph_bot = graph_bot+"-";
     }
     
     System.out.println("\nGantt Graph: "); 
     System.out.println(graph_top); 
     System.out.println(graph);
     System.out.println(graph_bot); 
     System.out.println(digits); 
     System.out.println("\n"+disp);
     System.out.println("Total Execution Time: "+tet);
     System.out.println("Total Waiting Time: "+twt);
     double decimal_twt = (double)twt/(double)at_bank.size();
     System.out.println("Total Average Waiting Time: "+df.format(decimal_twt));
     System.out.println("Total Task Size: "+at_bank.size());
     
    
      gant_bank_task.clear();
      gant_ct_bank.clear();
      gant_disp_bank.clear();
      space_bank_left.clear();
      space_bank_right.clear();
     
     
     //DEBUG  
     //System.out.println("Gant Bank Task: "+gant_bank_task.toString());
     //System.out.println("Gant CT Bank: "+gant_ct_bank.toString());
     //System.out.println("Gant Graph Spaces: "+space_bank_left.toString());
     //System.out.println("Gant Graph Spaces: "+space_bank_right.toString());
     //System.out.println("Gant Disp Value: "+gant_disp_bank.toString());
     //System.out.println("\nSorted AT: "+at_bank.toString());  
     //System.out.println("Sorted ET: "+et_bank.toString());
     //System.out.println("Sorted TASK: "+task_bank.toString());
     //System.out.println("Sorted CT: "+complete_bank.toString());
     //System.out.println("Sorted TAT: "+turn_bank.toString());
     //System.out.println("Sorted WT: "+wait_bank.toString());
     
      
   }//End Compute_Process
   
 }