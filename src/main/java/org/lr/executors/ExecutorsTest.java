package org.lr.executors;

import org.lr.data.Dog;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExecutorsTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newWorkStealingPool(4);
        executeUsing(executorService);
    }

    private static void executeUsing(final ExecutorService executorService) {
        List<Callable<Dog>> tasks = IntStream.range(0, 50).mapToObj(i -> (Callable<Dog>) () -> {
            System.out.println("Thread is " + Thread.currentThread().getName());
            return new Dog("dog-" + i, i);
        }).collect(Collectors.toList());
        try {
            executorService.invokeAll(tasks).stream().map(future -> {
                try {
                    return future.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                return null;
            }).forEach(System.out::println);
            executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
