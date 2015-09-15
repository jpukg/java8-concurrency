package org.lr.parallel;

import org.lr.data.Dog;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

public class ParallelStreams {

    public static void main(String[] args) {
        Random prng = new Random();
        int ceiling = 1000000;
        List<Dog> dogs = IntStream.rangeClosed(1, ceiling).mapToObj(i -> new Dog(aDogName(prng), prng.nextInt(21))).collect(toList());

        long startTime = System.currentTimeMillis();
        dogs.stream().sorted((d1, d2) -> d1.name().compareTo(d2.name())).collect(toList());
        long endTime = System.currentTimeMillis();
        System.out.println(format("Sorted %d dogs by name sequentially in [%d]ms", ceiling, (endTime - startTime)) + "\n");

        startTime = System.currentTimeMillis();
        dogs.stream().parallel().sorted((d1, d2) -> d1.name().compareTo(d2.name())).collect(toList());
        endTime = System.currentTimeMillis();
        System.out.println(format("Sorted %d dogs by name parallelly in [%d]ms", ceiling, (endTime - startTime)));
        //Parallel version is as twice as faster than sequential counterpart for lists with 10^6 elements.
    }

    private static String aDogName(final Random prng) {
        Optional<String> chars = Stream.generate(() -> prng.nextInt(27)).limit(5).map(i -> String.valueOf((char) (i + 65))).reduce((s1, s2) -> s1 + s2);
        return chars.get().toLowerCase();
    }
}
