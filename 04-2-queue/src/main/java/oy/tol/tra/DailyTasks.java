package oy.tol.tra;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A class showing your daily schedule using a timer.
 */
public class DailyTasks {

   private QueueInterface<String> dailyTaskQueue = null;
   private Timer timer = null;
   private static final int TASK_DELAY_IN_SECONDS = 1 * 1000;
   private static int counter = 0;

   private DailyTasks() {
   }

   /** 
    * Execute from the command line:  <code>java -jar target/04-queue-1.0-SNAPSHOT-jar-with-dependencies.jar</code>
    * @param args Not used.
    */
   public static void main(String[] args) {
      DailyTasks tasks = new DailyTasks();
      tasks.run();
   }


   private void run() {
      try {
         dailyTaskQueue = new QueueImplementation<>(10);
         readTasks();
         timer = new Timer();

         TimerTask task = new TimerTask() {
            @Override
            public void run() {
               if(dailyTaskQueue.isEmpty()){
                  timer.cancel();
               }
               else{
                  System.out.println(dailyTaskQueue.dequeue());
               }
            }
         };

         timer.scheduleAtFixedRate(task, 0 , TASK_DELAY_IN_SECONDS );
         
       } catch (IOException e) {
          System.out.println("Something went wrong :( " + e.getLocalizedMessage());
       }
   }

   private void readTasks() throws IOException {
      String tasks;
      int taskCounter = 0;
      tasks = new String(getClass().getClassLoader().getResourceAsStream("DailyTasks.txt").readAllBytes());
      String[] allTasks = tasks.split("\\r?\\n");
      for (String task : allTasks) {
         dailyTaskQueue.enqueue(task);
         taskCounter++;
      }
      System.out.println("The number of tasks: " + taskCounter);

   }
}
