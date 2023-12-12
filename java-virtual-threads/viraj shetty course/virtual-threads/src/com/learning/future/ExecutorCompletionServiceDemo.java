package com.learning.future;

import com.learning.utils.TaskUtil;
import com.learning.utils.ThreadUtils;

import java.util.concurrent.*;

public class ExecutorCompletionServiceDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        try(ExecutorService executorService = Executors.newFixedThreadPool(2)){
            ExecutorCompletionService executorCompletionService = new ExecutorCompletionService(executorService);

            Future<String> task1Future = executorCompletionService.submit(TaskUtil::task1);

            Future<String> task2Future = executorCompletionService.submit(() -> TaskUtil.task2("jai uma mahesh"));

            for(int i = 0 ; i < 2 ;i++){
                Future<String> take = executorCompletionService.take();
                String response = "nothing";
                if(take.equals(task1Future)){
                    response = task1Future.get();
                } else if (take.equals(task2Future)) {
                    response = task2Future.get();
                }

                ThreadUtils.print("response received: "+response);

            }


        }
    }
}
