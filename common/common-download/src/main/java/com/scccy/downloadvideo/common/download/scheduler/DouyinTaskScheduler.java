//package com.scccy.downloadvideo.common.download.scheduler;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//
//@Slf4j
//@Component
//public class DouyinTaskScheduler {
//
//    private final ExecutorService executorService;
//    private final ConcurrentHashMap<String, Future<?>> taskMap;
//
//    public DouyinTaskScheduler() {
//        this.executorService = Executors.newFixedThreadPool(10);
//        this.taskMap = new ConcurrentHashMap<>();
//    }
//
//    public void submitTask(String taskId, Runnable task) {
//        Future<?> future = executorService.submit(task);
//        taskMap.put(taskId, future);
//    }
//
//    public void cancelTask(String taskId) {
//        Future<?> future = taskMap.remove(taskId);
//        if (future != null) {
//            future.cancel(true);
//        }
//    }
//
//    @Scheduled(fixedRate = 60000)
//    public void cleanupTasks() {
//        taskMap.entrySet().removeIf(entry -> entry.getValue().isDone());
//    }
//}